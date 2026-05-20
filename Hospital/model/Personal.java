package Hospital.model;

public class Personal {
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;

    public Personal(int _id, String _nombre, String _apellidos, String _dni){
        this.id = _id;
        this.nombre = _nombre;
        this.apellidos = _apellidos;
        this.dni = _dni;
    }
    public int verId(){
       return this.id;
    }
   public String verNombre() {
        return this.nombre;
    }

    // public void cambiarNombre(String _nombre){
    // this.Nombre = _nombre;
    // }
    public String verApellidos() {
        return this.apellidos;
    }

    // public void cambiarApellidos(String _apellidos){
    // this.Apellidos = _apellidos;
    // }
    public String verDNI() {
        return this.dni;
    }

    // public void cambiardni(String _dni){
    // this.DNI= _dni;
    // }
  
@Override
public String toString() {
    return "ID: " + id + " | " + nombre + " " + apellidos + " | DNI: " + dni;
}

}
