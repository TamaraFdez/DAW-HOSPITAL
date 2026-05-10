package Hospital.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {

    public enum Estado {
        PENDIENTE, ATENDIDA, CANCELADA
    }

    private int idCita;
    private LocalDateTime fechaHora;
    private String especialidad;
    private Estado estado;
    private Paciente paciente;

    public Cita(int idCita, LocalDateTime fechaHora, String especialidad, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        this.estado = Estado.PENDIENTE;
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter fechaYHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return  "\n--- Cita #" + idCita + " ---" +
            "\nFecha:       " + fechaHora.format(fechaYHora) +
            "\nEspecialidad:" + especialidad +
            "\nEstado:      " + estado +
            "\nPaciente:    " + paciente.verNombre() + " " + paciente.verApellidos() +
            "\nDNI:         " + paciente.verDNI() +
            "\n";
    }

}