// una clase Paciente con los siguientes atributos privados:
//  nombre, apellidos, dni, edad y numHistorial. 
// La clase deberá incluir un constructor con parámetros, métodos getters para
//  todos los atributos y un método toString() 
// que permita mostrar la información del paciente de forma clara y legible.
public class Paciente {

    private String Nombre;
    private String Apellidos;
    private String DNI;
    private int Edad;
    private int NumHistorial;

    public Paciente(String _nombre, String _apellidos, String _dni, int _edad, int _numHistorial){
        this.Nombre = _nombre;
        this.Apellidos = _apellidos;
        this.DNI = _dni;
        this.Edad = _edad;
        this.NumHistorial = _numHistorial;
    }
    public String verNombre(){
        return this.Nombre;
    }
    // public void cambiarNombre(String _nombre){
    //     this.Nombre = _nombre;
    // }
     public String verApellidos(){
        return this.Apellidos;
    }
    // public void cambiarApellidos(String _apellidos){
    //     this.Apellidos = _apellidos;
    // }
     public String verDNI(){
        return this.DNI;
    }
    // public void cambiardni(String _dni){
    //     this.DNI= _dni;
    // }
     public int verEdad(){
        return this.Edad;
    }
    // public void cambiarEdad(int _Edad){
    //     this.Edad = _Edad;
    // }
      public int verNumHistorial(){
        return this.NumHistorial;
    }
    // public void cambiarNumHistorial(int _NumHistorial){
    //     this.NumHistorial = _NumHistorial;
    // }
    public String toString(){
        return "Paciente: Nombre: " + this.Nombre + ", Apellidos: " + this.Apellidos + ", Edad: " + this.Edad + ", Número de historial: "+this.NumHistorial;
    }
}