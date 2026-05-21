package Hospital.service;

import java.util.ArrayList;

import Hospital.model.Administrativo;
import Hospital.model.Enfermero;
import Hospital.model.Medico;
import Hospital.model.Personal;
import Hospital.model.PersonalSanitario;

public class PersonalService {
    ArrayList<Personal> personal = new ArrayList<>();
    private int contadorIdPersonal = 1;

    public void registrarMedico(String nombre, String apellidos, String dni, String especialidad) {
        personal.add(new Medico(contadorIdPersonal, nombre, apellidos, dni, especialidad));
        contadorIdPersonal++;
    }

    public void registrarEnfermero(String nombre, String apellidos, String dni, String planta) {
        personal.add(new Enfermero(contadorIdPersonal, nombre, apellidos, dni, planta));
        contadorIdPersonal++;
    }

    public void registrarAdministrativo(String nombre, String apellidos, String dni, String departamento) {
        personal.add(new Administrativo(contadorIdPersonal, nombre, apellidos, dni, departamento));
        contadorIdPersonal++;
    }

    public void listarPersonal() {
        if (personal.isEmpty()) {
            System.out.println("No hay personal registrado.");
            return;
        }
        for (Personal p : personal) {
            System.out.println(p.toString());
        }
    }
    public void listarPersonalSanitario(){
         if (personal.isEmpty()) {
            System.out.println("No hay personal registrado.");
            return;
        }
        System.out.println("---- MÉDICOS ----");
        listarMedicos();
          System.out.println("---- ENFERMEROS ----");
        listarEnfermeros();
    }
     public void listarMedicos(){
         if (personal.isEmpty()) {
            System.out.println("No hay personal registrado.");
            return;
        }
        for (Personal p : personal) {
            if(p instanceof PersonalSanitario){
               if(p instanceof Medico){
                 System.out.println(p.toString());
               }
            }
        }
    }
       public void listarEnfermeros(){
         if (personal.isEmpty()) {
            System.out.println("No hay personal registrado.");
            return;
        }
        for (Personal p : personal) {
            if(p instanceof PersonalSanitario){
               if(p instanceof Enfermero){
                 System.out.println(p.toString());
               }
            }
        }
    }

    public boolean existePersonal(String dni) {
        for (Personal p : personal) {
            if (p.verDNI().equals(dni)) {
                return true;
            }
        }

        return false;
    }
    public void agregarAUrgencias(int id) {
        for (Personal p : personal) {
            if (p.verId() == id && p instanceof PersonalSanitario) {
                ((PersonalSanitario) p).asignarAUrgencias();
                 System.out.println("Añadido a Urgencias con éxito.");
            return;
            }
        }
         System.out.println("ID no encontrado.");
        return;
    }
      public void desasignarDeUrgencias(int id) {
        for (Personal p : personal) {
            if (p.verId() == id && p instanceof PersonalSanitario) {
                ((PersonalSanitario) p).desasignarDeUrgencias();
                 System.out.println("Desasignado de Urgencias con éxito.");
            return;
            }
        }
         System.out.println("ID no encontrado.");
        return;
    }

  public PersonalSanitario buscarSanitarioPorId(int id) {
    for (Personal p : personal) {
        if (p.verId() == id && p instanceof PersonalSanitario) {
            return (PersonalSanitario) p;
        }
    }
    System.out.println("Personal no encontrado.");
    return null;
}

}
