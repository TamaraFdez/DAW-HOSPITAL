package Hospital.model;

public abstract class PersonalSanitario extends Personal {
    private boolean asignadoAUrgencias = false;

    public PersonalSanitario(int id, String nombre, String apellidos, String dni) {
        super(id, nombre, apellidos, dni);
    }
    // A futuro me irá mejor el toggle si hacemos la web porque será visible
    // public void toggleUrgencias() {
    // this.asignadoAUrgencias = !this.asignadoAUrgencias;
    // } 
    public void asignarAUrgencias() {
        this.asignadoAUrgencias = true;
    }

    public void desasignarDeUrgencias() {
        this.asignadoAUrgencias = false;
    }

    @Override
    public String toString() {
        return super.toString() + " | Asignado a Urgencias: " + asignadoAUrgencias;
    }
}
