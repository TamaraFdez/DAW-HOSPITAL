package Hospital.model;

public class Enfermero extends PersonalSanitario {
    private String planta;

    public Enfermero(int id, String nombre, String apellidos, String dni, String planta){
        super(id, nombre, apellidos, dni);
        this.planta = planta;
    }
    public String verplanta(){
        return this.planta;
    }
    public void cambiarPlanta(String _planta){
        this.planta= _planta;
    }

    @Override
public String toString() {
    return super.toString() + " | Enfermero | Planta: " + planta ;
}
}

