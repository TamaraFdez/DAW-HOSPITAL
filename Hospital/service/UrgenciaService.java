package Hospital.service;

import Hospital.model.Gravedad;
import Hospital.model.Urgencia;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class UrgenciaService {

    private PriorityQueue<Urgencia> colaPrioridad;
    private HashMap<String, Urgencia> mapaUrgencias;
    private int contadorLlegada;

    public UrgenciaService() {
        // Comparator: CRITICO tiene ordinal 0, LEVE tiene 3
        // PriorityQueue saca el menor primero --> CRITICO sale antes que LEVE
        // Si tienen la misma gravedad, sale el que lleva más tiempo esperando
        colaPrioridad = new PriorityQueue<>(
                Comparator.comparingInt((Urgencia u) -> u.getGravedad().ordinal())
                        .thenComparingInt(u -> u.getOrdenLlegada()));
        mapaUrgencias = new HashMap<>();
        contadorLlegada = 0;
    }

    // Registra la llegada de un paciente a urgencias
    public boolean registrarLlegada(String dni, Gravedad gravedad) {
        if (mapaUrgencias.containsKey(dni)) {
            return false;
        }
        Urgencia u = new Urgencia(dni, gravedad, contadorLlegada++);
        mapaUrgencias.put(dni, u);
        colaPrioridad.add(u);
        return true;
    }

    // Atiende al siguiente paciente (el más grave primero)
    public Urgencia atenderSiguiente() {
        Urgencia u = colaPrioridad.poll();
        if (u != null) {
            mapaUrgencias.remove(u.getDniPaciente());
        }
        return u;
    }

    // Muestra la lista de espera en orden de prioridad
    public void mostrarListaEspera(HospitalService hospitalService) {
        if (colaPrioridad.isEmpty()) {
            System.out.println("No hay pacientes en urgencias.");
            return;
        }

        // Hacemos una copia para no vaciar la cola real al iterar
        PriorityQueue<Urgencia> copia = new PriorityQueue<>(
                Comparator.comparingInt((Urgencia u) -> u.getGravedad().ordinal())
                        .thenComparingInt(u -> u.getOrdenLlegada()));
        copia.addAll(colaPrioridad);

        System.out.println("\nLista de espera:");
        int pos = 1;
        while (!copia.isEmpty()) {
            Urgencia u = copia.poll();
            String nombre = hospitalService.getNombrePaciente(u.getDniPaciente());
            System.out.println(pos + ". " + nombre + " | DNI: " + u.getDniPaciente()
                    + " | Gravedad: " + u.getGravedad());
            pos++;
        }
    }

    public boolean estaEnUrgencias(String dni) {
        return mapaUrgencias.containsKey(dni);
    }

    public int totalEnEspera() {
        return colaPrioridad.size();
    }

    public Urgencia verSiguiente() {
        return colaPrioridad.peek();
    }
}