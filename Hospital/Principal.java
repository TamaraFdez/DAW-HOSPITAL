package Hospital;

import Hospital.model.Cita;
import Hospital.service.HospitalService;
import Hospital.util.MenuHelper;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        HospitalService service = new HospitalService();
        service.cargarDatosPrueba();
        Scanner sc = new Scanner(System.in);
        MenuHelper helper = new MenuHelper(sc);

        int opcion = -1;
        System.out.println("\n--- Bienvenido a DAW HOSPITAL ---");
        
        do {

            System.out.println("Selecione una operación de la siguientes:");
            System.out.println("1. Dar de alta un paciente");
            System.out.println("2. Mostrar todos los pacientes");
            System.out.println("3. Crear una cita");
            System.out.println("4. Mostrar citas de un paciente");
            System.out.println("5. Mostrar todas las citas");
            System.out.println("6. Editar una cita");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    String nombre = helper.pedirTexto("Nombre: ");
                    String apellidos = helper.pedirTexto("Apellidos: ");

                    String dniAlta;
                    do {
                        dniAlta = helper.pedirDNI();
                        if (service.existePaciente(dniAlta)) {
                            System.out.println("DNI ya registrado. Inténtalo de nuevo.");
                        }
                    } while (service.existePaciente(dniAlta));

                    int edad = helper.pedirEdad();

                    service.registrarPaciente(nombre, apellidos, dniAlta, edad);
                    System.out.println("Paciente registrado con éxito.");
                    break;

                case 2:
                    service.listarPacientes();
                    break;

                case 3:
                    String dniCita;
                    do {
                        dniCita = helper.pedirDNI();
                        if (!service.existePaciente(dniCita)) {
                            System.out.println("Paciente no encontrado. Inténtalo de nuevo.");
                        }
                    } while (!service.existePaciente(dniCita));

                    LocalDateTime fechaHora = helper.pedirFechaHora();
                    String especialidad = helper.pedirTexto("Especialidad: ");

                    service.añadirCita(dniCita, fechaHora, especialidad);
                    System.out.println("Cita registrada con éxito.");
                    break;

                case 4:
                    String dniListar;
                    do {
                        dniListar = helper.pedirDNI();
                        if (!service.existePaciente(dniListar)) {
                            System.out.println("Paciente no encontrado. Inténtalo de nuevo.");
                        }
                    } while (!service.existePaciente(dniListar));

                    service.listarCitasPorPaciente(dniListar);
                    break;

                case 5:
                    service.listarTodasLasCitas();
                    break;
                case 6:
                    int idCita = helper.pedirIdCita();

                    System.out.println("¿Qué deseas modificar?");
                    System.out.println("1. Fecha y hora");
                    System.out.println("2. Estado");
                    System.out.println("3. Ambos");

                    LocalDateTime nuevaFecha = null;
                    Cita.Estado nuevoEstado = null;

                    try {
                        switch (Integer.parseInt(sc.nextLine())) {
                            case 1:
                                nuevaFecha = helper.pedirFechaHora();
                                break;
                            case 2:
                                nuevoEstado = helper.pedirEstado();
                                break;
                            case 3:
                                nuevaFecha = helper.pedirFechaHora();
                                nuevoEstado = helper.pedirEstado();
                                break;
                            default:
                                System.out.println("Opción incorrecta.");
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Introduce un número válido.");
                    }

                    if (nuevaFecha != null || nuevoEstado != null) {
                        service.editarCita(idCita, nuevaFecha, nuevoEstado);
                        System.out.println("Cita actualizada con éxito.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del hospital... ¡Que tenga un buen día!");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);

        sc.close();
    }
}