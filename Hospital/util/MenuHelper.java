package Hospital.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Hospital.model.Cita;

public class MenuHelper {
    private Scanner sc;

    public MenuHelper(Scanner sc) {
        this.sc = sc;
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        String valor = sc.nextLine().trim();
        while (valor.isEmpty()) {
            System.out.println("Este campo no puede estar vacío.");
            System.out.print(mensaje);
            valor = sc.nextLine().trim();
        }
        return valor;
    }

    public String pedirDNI() {
        System.out.print("DNI: ");
        String dni = sc.nextLine().trim().toUpperCase();
        while (!Validacion.ValidandoDNI(dni)) {
            System.out.println("DNI inválido. Inténtalo de nuevo.");
            System.out.print("DNI: ");
            dni = sc.nextLine().trim();
        }
        return dni;
    }

    public int pedirEdad() {
        while (true) {
            try {
                System.out.print("Edad: ");
                int edad = Integer.parseInt(sc.nextLine());
                if (Validacion.ValidandoEdad(edad))
                    return edad;
                System.out.println("Edad inválida. Inténtalo de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número entero.");
            }
        }
    }

    public LocalDateTime pedirFechaHora() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
        while (true) {
            try {
                System.out.print("Fecha (dd/MM/yyyy): ");
                String fecha = sc.nextLine().trim();

                System.out.print("Hora (HH:mm): ");
                String hora = sc.nextLine().trim();

                LocalDateTime fechaHora = LocalDateTime.parse(fecha + " " + hora, fmt);

                if (fechaHora.isBefore(LocalDateTime.now())) {
                    System.out.println("La fecha debe ser futura. Inténtalo de nuevo.");
                    continue;
                }

                return fechaHora;

            } catch (Exception e) {
                System.out.println("Formato inválido. Inténtalo de nuevo.");
            }
        }
    }

    public int pedirIdCita() {
        while (true) {
            try {
                System.out.print("ID de la cita: ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número entero.");
            }
        }
    }

    public Cita.Estado pedirEstado() {
        while (true) {
            System.out.println("Estado de la cita:");
            System.out.println("1. PENDIENTE");
            System.out.println("2. ATENDIDA");
            System.out.println("3. CANCELADA");
            System.out.print("Opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        return Cita.Estado.PENDIENTE;
                    case 2:
                        return Cita.Estado.ATENDIDA;
                    case 3:
                        return Cita.Estado.CANCELADA;
                    default:
                        System.out.println("Opción inválida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

}
