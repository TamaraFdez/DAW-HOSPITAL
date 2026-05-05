import java.util.HashMap;
import java.util.Map;

public class HospitalService {

    private Map<String, Paciente> pacientes = new HashMap<>();

    public boolean registrarPaciente(String nombre, String apellidos, String dni, int edad, int numHistorial) {

        if (pacientes.containsKey(dni)) {
            System.out.println("Error: DNI duplicado");
            return false;
        }

        Paciente nuevo = new Paciente(nombre, apellidos, dni, edad, numHistorial);
        pacientes.put(dni, nuevo);

        return true;
    }

    public void listarPacientes() {
        for (Paciente p : pacientes.values()) {
            System.out.println(p);
        }
    }
}