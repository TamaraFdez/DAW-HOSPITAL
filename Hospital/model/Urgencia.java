package Hospital.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// /  Representa la entrada de un paciente al módulo de urgencias.
//    - El DNI del paciente (debe existir previamente en el sistema)
//    - El nivel de gravedad asignado en triaje
//    - El momento de llegada (para desempatar entre misma gravedad)
//    - Un número de orden de llegada (secundario al anterior)

public class Urgencia {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    private final String dniPaciente;
    private final Gravedad gravedad;
    private final LocalDateTime horaLlegada;
    private final int ordenLlegada; 
    private Personal personalAsignado = null;

    public Urgencia(String dniPaciente, Gravedad gravedad, int ordenLlegada) {
        this.dniPaciente = dniPaciente;
        this.gravedad = gravedad;
        this.horaLlegada = LocalDateTime.now();
        this.ordenLlegada = ordenLlegada;
 
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public Gravedad getGravedad() {
        return gravedad;
    }

    public LocalDateTime getHoraLlegada() {
        return horaLlegada;
    }

    public int getOrdenLlegada() {
        return ordenLlegada;
    }
    public void setPersonalAsignado(PersonalSanitario personal) {
    this.personalAsignado = personal;
}

    @Override
    public String toString() {
        if(personalAsignado == null){
        return "DNI: " + this.dniPaciente + " | Gravedad: "+ this.gravedad+" | Llegada: "+ this.horaLlegada.format(FORMATO) + " | No hay personal asignado";
    }
    return "DNI: " + this.dniPaciente + " | Gravedad: "+ this.gravedad+" | Llegada: "+ this.ordenLlegada +" | Personal Asignado con el ID:" + personalAsignado.verId() +" y Nombre: " + personalAsignado.verNombre() + " "+ personalAsignado.verApellidos();
                
        
    }
}
