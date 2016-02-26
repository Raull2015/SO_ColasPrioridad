/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Brandon
 */
public class TablaProcesos {

    ArrayList<Proceso> listaP;
    private int nuevoId;
    private Proceso procesoAct;
    private int numeroEjec;

    public TablaProcesos() {
        listaP = new ArrayList<>();
        nuevoId = 1;
    }

    public ArrayList<Proceso> getListaP() {
        return listaP;
    }

    public Proceso getProcesoAct() {
        return procesoAct;
    }
    

    public void crear_proceso(String nombre, int velocidad) {

        Proceso nuevoP = new Proceso(nombre, nuevoId, velocidad);
        nuevoP.setPrioridad(0);
        corregir_prioridades();
        procesoAct = nuevoP;
        listaP.add(nuevoP);

        nuevoId++;
    }

    public void corregir_prioridades() {
        for (Proceso proceso : listaP) {
            proceso.setPrioridad(proceso.getPrioridad() + 1);
        }

    }

    public void destruir_proceso(int id) {
        for (Proceso proceso : listaP) {
            if (proceso.getId() == id) {
                listaP.remove(proceso);
            }
        }

    }

    /**
     * calcula el tiempo en que ocurrira una interrupcion del proceso.
     *
     * @param velocidad El tiempo que dura el Quantum.
     * @return -1 indica que no ocurre una interrupción. De lo contrario
     * devuelve el momento en que se genero la interrupcion.
     */
    public int tiempo_interrupcion(int velocidad) {
        Random rm = new Random();
        int probabilidad = rm.nextInt(99) + 1;
        if (probabilidad < 75) {
            return -1;
        } else {
            return rm.nextInt(999) + 1;
        }

    }

    /**
     * Calcula el tiempo en que el proceso se bloqueara.
     * @param velocidad El tiempo en milisegundos que dura el Quantum.
     * @return -1 indica que el proceso no se bloqueó de lo contrario es el momento en el que se va a bloquear.
     */
    public int tiempo_bloqueo(int velocidad) {
        Random rm = new Random();
        int probabilidad = rm.nextInt(99) + 1;
        if (probabilidad < 50) {
            return -1;
        } else {
            return rm.nextInt(999) + 1;
        }
    }

    /**
     * Bloquea el proceso Actual.
     */
    public void bloquear() {
        procesoAct.setBloqueado(true);
    }

    /**
     * Desbloquea el proceso.
     * @param id del proceso que va a desbloquear.
     */
    public void desbloquear(int id) {

        for (Proceso proc : listaP) {
            if (proc.getId() == id) {
                proc.setBloqueado(false);
            }
        }
    }

    /**
     * Revisa la Prioridad y en base a ella elige el siguiente proceso a ejecutar.
     */
    public void avanzar() {

        int prioridad;
        prioridad = procesoAct.getPrioridad();
        if (prioridad == 0) {
            numeroEjec++;
            if (numeroEjec == 2) {
                for (Proceso proceso : listaP) {
                    if ((proceso.getPrioridad() == 1) & (proceso.isBloqueado() == false)) {
                        procesoAct = proceso;
                        listaP.remove(procesoAct);
                        listaP.add(procesoAct);
                        numeroEjec = 0;
                        return;
                    }
                }
            }
        } else {
            numeroEjec++;
            if (numeroEjec == 1) {
                for (Proceso proceso : listaP) {
                    if ((proceso.getPrioridad() == 0) & (proceso.isBloqueado() == false)) {
                        procesoAct = proceso;
                        numeroEjec = 0;
                        return;
                    }
                }
            }

        }
    }

}
