package Hospital.model;

//   Niveles de gravedad para el triaje de urgencias.
//   El orden del enum determina la prioridad:
//   CRITICO(0) > GRAVE(1) > MODERADO(2) > LEVE(3)
//   PriorityQueue usará ordinal(), por eso CRITICO va primero.
 
public enum Gravedad {
    CRITICO,
    GRAVE,
    MODERADO,
    LEVE;

    @Override
    public String toString() {
        return switch (this) {
            case CRITICO  -> "CRÍTICO";
            case GRAVE    -> "GRAVE";
            case MODERADO -> "MODERADO";
            case LEVE     -> "LEVE";
        };
    }
}