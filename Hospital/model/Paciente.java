package Hospital.model;


public class Paciente {

    String nombre;
String apellidos;
String dni;
int edad;
int numHistorial;
   

    public Paciente(String _nombre, String _apellidos, String _dni, int _edad, int _numHistorial){
        this.nombre = _nombre;
        this.apellidos = _apellidos;
        this.dni = _dni;
        this.edad = _edad;
        this.numHistorial = _numHistorial;
    }
    public String verNombre(){
        return this.nombre;
    }
    // public void cambiarNombre(String _nombre){
    //     this.Nombre = _nombre;
    // }
     public String verApellidos(){
        return this.apellidos;
    }
    // public void cambiarApellidos(String _apellidos){
    //     this.Apellidos = _apellidos;
    // }
     public String verDNI(){
        return this.dni;
    }
    // public void cambiardni(String _dni){
    //     this.DNI= _dni;
    // }
     public int verEdad(){
        return this.edad;
    }
    // public void cambiarEdad(int _Edad){
    //     this.Edad = _Edad;
    // }
      public int verNumHistorial(){
        return this.numHistorial;
    }
    // public void cambiarNumHistorial(int _NumHistorial){
    //     this.NumHistorial = _NumHistorial;
    // }
   @Override
public String toString() {
    return  "\n--- Paciente #" + numHistorial + " ---" +
            "\nNombre:    " + nombre + " " + apellidos +
            "\nDNI:       " + dni +
            "\nEdad:      " + edad +
            "\n";
}
    

}