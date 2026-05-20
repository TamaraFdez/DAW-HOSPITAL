package Hospital.util;

import Hospital.model.Cita;
import Hospital.model.Gravedad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuHelper {

    private final Scanner sc;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public MenuHelper(Scanner sc) {
        this.sc = sc;
    }

   

    public String pedirTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) System.out.println("  El campo no puede estar vacío.");
        } while (texto.isEmpty());
        return texto;
    }

    public String pedirDNI() {
        String dni;
        do {
            System.out.print("DNI: ");
            dni = sc.nextLine().trim().toUpperCase();
            if (!dni.matches("[0-9]{8}[A-Z]")) {
                System.out.println("  Formato inválido. Ejemplo: 12345678A");
            }
        } while (!dni.matches("[0-9]{8}[A-Z]"));
        return dni;
    }

    public int pedirEdad() {
        while (true) {
            try {
                System.out.print("Edad: ");
                int edad = Integer.parseInt(sc.nextLine().trim());
                if (edad < 0 || edad > 150) {
                    System.out.println("  Edad no válida.");
                    continue;
                }
                return edad;
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un número.");
            }
        }
    }

    public LocalDateTime pedirFechaHora() {
        while (true) {
            try {
                System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
                return LocalDateTime.parse(sc.nextLine().trim(), FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("  Formato inválido. Ejemplo: 25/12/2025 09:30");
            }
        }
    }

    public int pedirIdCita() {
        while (true) {
            try {
                System.out.print("ID de la cita: ");
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un número válido.");
            }
        }
    }

    public Cita.Estado pedirEstado() {
        System.out.println("  Estados disponibles:");
        Cita.Estado[] estados = Cita.Estado.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println("  " + (i + 1) + ". " + estados[i]);
        }
        while (true) {
            try {
                System.out.print("  Elige (1-" + estados.length + "): ");
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op >= 1 && op <= estados.length) return estados[op - 1];
                System.out.println("  Opción fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un número.");
            }
        }
    }

   
    public Gravedad pedirGravedad() {
        Gravedad[] niveles = Gravedad.values();
        System.out.println("  Niveles de gravedad:");
        for (int i = 0; i < niveles.length; i++) {
            System.out.println("  " + (i + 1) + ". " + niveles[i]);
        }
        while (true) {
            try {
                System.out.print("  Elige gravedad (1-" + niveles.length + "): ");
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op >= 1 && op <= niveles.length) return niveles[op - 1];
                System.out.println("  Opción fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un número.");
            }
        }
    }
}