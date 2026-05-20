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

    private final String        dniPaciente;
    private final Gravedad      gravedad;
    private final LocalDateTime horaLlegada;
    private final int           ordenLlegada; // desempate: menor = llegó antes

    public Urgencia(String dniPaciente, Gravedad gravedad, int ordenLlegada) {
        this.dniPaciente  = dniPaciente;
        this.gravedad     = gravedad;
        this.horaLlegada  = LocalDateTime.now();
        this.ordenLlegada = ordenLlegada;
    }


    public String getDniPaciente()  { return dniPaciente; }
    public Gravedad getGravedad()   { return gravedad; }
    public LocalDateTime getHoraLlegada() { return horaLlegada; }
    public int getOrdenLlegada()    { return ordenLlegada; }

    @Override
    public String toString() {
        return String.format("DNI: %-10s | Gravedad: %-14s | Llegada: %s",
                dniPaciente, gravedad, horaLlegada.format(FORMATO));
    }
}
