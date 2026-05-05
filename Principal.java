
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        HospitalService service = new HospitalService();
        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n---Bienvenido a DAW HOSPITAL ---");
            System.out.println("1. Dar de alta un Paciente");
            System.out.println("2. Mostrar todos los pacientes");
            System.out.println("0. Salir");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    boolean correcto = false;

                    while (!correcto) {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Apellidos: ");
                        String apellidos = sc.nextLine();

                        System.out.print("DNI: ");
                        String dni = sc.nextLine();

                        if (!Validacion.ValidandoDNI(dni)) {
                            System.out.println("DNI inválido. Inténtalo de nuevo.\n");
                            continue;
                        }

                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());

                        if (!Validacion.ValidandoEdad(edad)) {
                            System.out.println("Edad inválida. Inténtalo de nuevo.\n");
                            continue;
                        }

                        System.out.print("Número de historial: ");
                        int numHistorial = Integer.parseInt(sc.nextLine());

                        boolean registrado = service.registrarPaciente(nombre, apellidos, dni, edad, numHistorial);

                        if (!registrado) {
                            System.out.println("DNI duplicado. Inténtalo de nuevo.\n");
                            continue;
                        }

                        System.out.println("Paciente registrado con éxito.");
                        correcto = true;
                    }
                    break;
                case 2:
                    service.listarPacientes();
                    break;
                case 0:
                    System.out.println("Saliendo del Hospital...!Que tenga un buen día¡");
                    break;

                default:
                    System.out.println("Opción incorrecta");

            }
        } while (opcion != 0);
        sc.close();
    }
}
