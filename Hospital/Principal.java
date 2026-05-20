package Hospital;

import Hospital.model.Cita;
import Hospital.model.Gravedad;
import Hospital.model.Urgencia;
import Hospital.service.HospitalService;
import Hospital.service.UrgenciaService;
import Hospital.util.MenuHelper;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        HospitalService hospitalService = new HospitalService();
        UrgenciaService urgenciaService = new UrgenciaService();
        hospitalService.cargarDatosPrueba();

        // datos mockeados de urgencias
        urgenciaService.registrarLlegada("11223344C", Gravedad.CRITICO); // Pedro
        urgenciaService.registrarLlegada("87654321B", Gravedad.LEVE); // María
        urgenciaService.registrarLlegada("12345678A", Gravedad.GRAVE); // Juan

        Scanner sc = new Scanner(System.in);
        MenuHelper helper = new MenuHelper(sc);

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   Bienvenido a DAW HOSPITAL      ║");
        System.out.println("╚══════════════════════════════════╝");

        int opcion = -1;
        do {
            mostrarMenuPrincipal();
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> menuPacientes(sc, helper, hospitalService);
                case 2 -> menuCitas(sc, helper, hospitalService);
                case 3 -> menuUrgencias(sc, helper, hospitalService, urgenciaService);
                case 0 -> System.out.println("\nSaliendo del hospital... ¡Que tenga un buen día!");
                default -> System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n┌─────────────────────────────┐");
        System.out.println("│        MENÚ PRINCIPAL       │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│  1. Pacientes               │");
        System.out.println("│  2. Citas                   │");
        System.out.println("│  3. Urgencias               │");
        System.out.println("│  0. Salir                   │");
        System.out.println("└─────────────────────────────┘");
        System.out.print("Selecciona: ");
    }

    private static void menuPacientes(Scanner sc, MenuHelper helper, HospitalService service) {
        int op = -1;
        do {
            System.out.println("\n--- 👤 PACIENTES ---");
            System.out.println("1. Dar de alta un paciente");
            System.out.println("2. Mostrar todos los pacientes");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona: ");

            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                op = -1;
            }

            switch (op) {
                case 1 -> {
                    String nombre = helper.pedirTexto("Nombre: ");
                    String apellidos = helper.pedirTexto("Apellidos: ");

                    String dniAlta;
                    do {
                        dniAlta = helper.pedirDNI();
                        if (service.existePaciente(dniAlta))
                            System.out.println("DNI ya registrado. Inténtalo de nuevo.");
                    } while (service.existePaciente(dniAlta));

                    int edad = helper.pedirEdad();
                    service.registrarPaciente(nombre, apellidos, dniAlta, edad);
                    System.out.println("Paciente registrado con éxito.");
                }
                case 2 -> service.listarPacientes();
                case 0 -> {
                    /* volver */ }
                default -> System.out.println("Opción incorrecta.");
            }
        } while (op != 0);
    }

    private static void menuCitas(Scanner sc, MenuHelper helper, HospitalService service) {
        int op = -1;
        do {
            System.out.println("\n--- CITAS ---");
            System.out.println("1. Crear una cita");
            System.out.println("2. Mostrar citas de un paciente");
            System.out.println("3. Mostrar todas las citas");
            System.out.println("4. Editar una cita");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona: ");

            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                op = -1;
            }

            switch (op) {
                case 1 -> {
                    String dniCita;
                    do {
                        dniCita = helper.pedirDNI();
                        if (!service.existePaciente(dniCita))
                            System.out.println("Paciente no encontrado. Inténtalo de nuevo.");
                    } while (!service.existePaciente(dniCita));

                    LocalDateTime fechaHora = helper.pedirFechaHora();
                    String especialidad = helper.pedirTexto("Especialidad: ");
                    service.añadirCita(dniCita, fechaHora, especialidad);
                    System.out.println(" Cita registrada con éxito.");
                }
                case 2 -> {
                    String dniListar;
                    do {
                        dniListar = helper.pedirDNI();
                        if (!service.existePaciente(dniListar))
                            System.out.println("Paciente no encontrado. Inténtalo de nuevo.");
                    } while (!service.existePaciente(dniListar));
                    service.listarCitasPorPaciente(dniListar);
                }
                case 3 -> service.listarTodasLasCitas();
                case 4 -> {
                    int idCita = helper.pedirIdCita();

                    System.out.println("¿Qué deseas modificar?");
                    System.out.println("1. Fecha y hora");
                    System.out.println("2. Estado");
                    System.out.println("3. Ambos");
                    System.out.print("Selecciona: ");

                    LocalDateTime nuevaFecha = null;
                    Cita.Estado nuevoEstado = null;

                    try {
                        switch (Integer.parseInt(sc.nextLine().trim())) {
                            case 1 -> nuevaFecha = helper.pedirFechaHora();
                            case 2 -> nuevoEstado = helper.pedirEstado();
                            case 3 -> {
                                nuevaFecha = helper.pedirFechaHora();
                                nuevoEstado = helper.pedirEstado();
                            }
                            default -> System.out.println("Opción incorrecta.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Introduce un número válido.");
                    }

                    if (nuevaFecha != null || nuevoEstado != null) {
                        service.editarCita(idCita, nuevaFecha, nuevoEstado);
                        System.out.println("Cita actualizada con éxito.");
                    }
                }
                case 0 -> {
                    /* volver */ }
                default -> System.out.println("Opción incorrecta.");
            }
        } while (op != 0);
    }

    private static void menuUrgencias(Scanner sc, MenuHelper helper,
            HospitalService hospitalService,
            UrgenciaService urgenciaService) {
        int op = -1;
        do {
            System.out.println("\n--- URGENCIAS ---");
            System.out.println("  Pacientes en espera: " + urgenciaService.totalEnEspera());
            System.out.println("1. Registrar llegada de paciente");
            System.out.println("2. Ver lista de espera");
            System.out.println("3. Atender siguiente paciente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona: ");

            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                op = -1;
            }

            switch (op) {

                case 1 -> {
                    String dni = helper.pedirDNI();

                    while (!hospitalService.existePaciente(dni)) {
                        System.out.println("Paciente no encontrado.");
                        System.out.println("1. Introducir otro DNI");
                        System.out.println("2. Registrar paciente nuevo");
                        System.out.print("Selecciona: ");

                        try {
                            int subOp = Integer.parseInt(sc.nextLine().trim());
                            if (subOp == 1) {
                                dni = helper.pedirDNI();
                            } else if (subOp == 2) {
                                String nombre = helper.pedirTexto("Nombre: ");
                                String apellidos = helper.pedirTexto("Apellidos: ");
                                // el DNI ya lo tenemos, pero puede que quieran cambiarlo
                                // así que usamos el que ya introdujeron
                                int edad = helper.pedirEdad();
                                hospitalService.registrarPaciente(nombre, apellidos, dni, edad);
                                System.out.println("Paciente registrado.");
                                break; 
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Introduce un número válido.");
                        }
                    }

                    if (urgenciaService.estaEnUrgencias(dni)) {
                        System.out.println("Este paciente ya está en urgencias.");
                    } else {
                        Gravedad gravedad = helper.pedirGravedad();
                        urgenciaService.registrarLlegada(dni, gravedad);
                        System.out.println("Paciente registrado en urgencias: " + gravedad);
                    }
                }
                case 2 -> {
                    System.out.println("\n  Lista de espera (orden de atención):");
                    urgenciaService.mostrarListaEspera(hospitalService);
                }

                case 3 -> {
                    Urgencia siguiente = urgenciaService.atenderSiguiente();
                    if (siguiente == null) {
                        System.out.println("  No hay pacientes en urgencias.");
                    } else {
                        String nombre = hospitalService.getNombrePaciente(siguiente.getDniPaciente());
                        System.out.println("\n Atendiendo a:");
                        System.out.println("Paciente : " + (nombre != null ? nombre : "—"));
                        System.out.println("DNI      : " + siguiente.getDniPaciente());
                        System.out.println("Gravedad : " + siguiente.getGravedad());
                        System.out.println("Quedan " + urgenciaService.totalEnEspera()
                                + " pacientes en espera.");

                        Urgencia proxSig = urgenciaService.verSiguiente();
                        if (proxSig != null) {
                            String nomSig = hospitalService.getNombrePaciente(proxSig.getDniPaciente());
                            System.out.println("  Próximo: " + (nomSig != null ? nomSig : proxSig.getDniPaciente())
                                    + " con estado: " + proxSig.getGravedad());
                        }
                    }
                }

                case 0 -> {
                    /* volver */ }
                default -> System.out.println("Opción incorrecta.");
            }
        } while (op != 0);
    }
}