package Hospital.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Hospital.model.Cita;
import Hospital.model.Paciente;

public class HospitalService {

    private Map<String, Paciente> pacientes = new HashMap<>();
    private List<Cita> citas = new ArrayList<>();
    private int contadorCitas = 1;
    private int contadorHistorial = 1;

    public boolean registrarPaciente(String nombre, String apellidos, String dni, int edad) {
        if (pacientes.containsKey(dni))
            return false;

        Paciente nuevo = new Paciente(nombre, apellidos, dni, edad, contadorHistorial++);
        pacientes.put(dni, nuevo);
        return true;
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Paciente p : pacientes.values()) {
            long citasPendientes = 0;
            for (Cita c : citas) {
                if (c.getPaciente().verDNI().equals(p.verDNI()) &&
                        c.getEstado() == Cita.Estado.PENDIENTE) {
                    citasPendientes++;
                }
            }
            System.out.println(p + "Citas pendientes: " + citasPendientes + "\n");
        }
    }

    public boolean existePaciente(String dni) {
        return pacientes.containsKey(dni);
    }

    public boolean añadirCita(String dni, LocalDateTime fechaHora, String especialidad) {
        Paciente paciente = pacientes.get(dni);
        if (paciente == null)
            return false;

        Cita cita = new Cita(contadorCitas++, fechaHora, especialidad, paciente);
        citas.add(cita);
        return true;
    }

    public void listarTodasLasCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        for (Cita c : citas) {
            System.out.println(c);
        }
    }

    public void listarCitasPorPaciente(String dni) {
        if (!existePaciente(dni)) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        boolean tieneCitas = false;
        for (Cita c : citas) {
            if (c.getPaciente().verDNI().equals(dni)) {
                System.out.println(c);
                tieneCitas = true;
            }
        }

        if (!tieneCitas) {
            System.out.println("Este paciente no tiene citas registradas.");
        }
    }

    public boolean editarCita(int idCita, LocalDateTime nuevaFechaHora, Cita.Estado nuevoEstado) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                if (nuevaFechaHora != null)
                    c.setFechaHora(nuevaFechaHora);
                if (nuevoEstado != null)
                    c.setEstado(nuevoEstado);
                return true;
            }
        }
        System.out.println("Cita no encontrada.");
        return false;
    }

    public void cargarDatosPrueba() {
        registrarPaciente("Juan", "García López", "12345678A", 35);
        registrarPaciente("María", "Martínez Ruiz", "87654321B", 28);
        registrarPaciente("Pedro", "Sánchez Gil", "11223344C", 52);

        añadirCita("12345678A", LocalDateTime.of(2026, 6, 15, 10, 30), "Cardiología");
        añadirCita("12345678A", LocalDateTime.of(2026, 7, 20, 12, 0), "Traumatología");
        añadirCita("87654321B", LocalDateTime.of(2026, 6, 18, 9, 0), "Pediatría");
    }

}