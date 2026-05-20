package Hospital.model;

public class Administrativo extends Personal {
    private String departamento;
    public Administrativo(int id, String nombre, String apellidos, String dni, String departamento){
        super(id, nombre, apellidos, dni);
        this.departamento = departamento;
    }
    public String verdepartamento(){
        return this.departamento;
    }
    public void cambiarDepartamento(String _departamento){
        this.departamento= _departamento;
    }
    @Override
public String toString() {
    return super.toString() + "| Administrativo | Departamento: " + departamento;
}
}


