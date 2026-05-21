package Hospital.model;

public class Medico extends PersonalSanitario {
    private String especialidad;
    public Medico(int id, String nombre, String apellidos, String dni, String especialidad){
        super(id, nombre, apellidos, dni);
        this.especialidad = especialidad;
    }
    public String verEspecialidad(){
        return this.especialidad;
    }
    public void cambiarEspecialidad(String _especialidad){
        this.especialidad= _especialidad;
    }
 
    @Override
public String toString() {
    return super.toString() + " | Médico | Especialidad: " + especialidad;
}
}
