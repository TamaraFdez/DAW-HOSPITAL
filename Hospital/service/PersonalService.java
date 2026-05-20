package Hospital.service;
import java.util.ArrayList;

import Hospital.model.Administrativo;
import Hospital.model.Enfermero;
import Hospital.model.Medico;
import Hospital.model.Personal;
public class PersonalService {
    ArrayList<Personal> personal = new ArrayList<>();
     private int contadorIdPersonal = 1;

     public void registrarMedico(String nombre, String apellidos, String dni, String especialidad){
        personal.add(new Medico(contadorIdPersonal, nombre, apellidos, dni, especialidad));
        contadorIdPersonal++;
     }
      public void registrarEnfermero(String nombre, String apellidos, String dni, String planta){
        personal.add(new Enfermero(contadorIdPersonal, nombre, apellidos, dni, planta));
        contadorIdPersonal++;
     }
      public void registrarAdministrativo(String nombre, String apellidos, String dni, String departamento){
        personal.add(new Administrativo(contadorIdPersonal, nombre, apellidos, dni, departamento));
        contadorIdPersonal++;
     }
     public void listarPersonal(){
         if (personal.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Personal p : personal) {
            System.out.println(p.toString());
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
}
