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

    private ArrayList<Proceso> listaP;
    private int nuevoId;
    private Proceso procesoAct;
    private int numeroEjec;
    private int velocidad;

    public TablaProcesos(int velocidad) {
        this.velocidad = velocidad * 100;
        this.listaP = new ArrayList<>();
        this.nuevoId = 1;
    }

    public ArrayList<Proceso> getListaP() {
        return listaP;
    }

    public Proceso getProcesoAct() {
        return procesoAct;
    }

    public String getNombreProcesoAct() {
        return procesoAct.getNombre();
    }

    public void crear_proceso(String nombre, int velocidad) {

        Proceso nuevoP = new Proceso(nombre, nuevoId, velocidad);
        nuevoP.setPrioridad(0);
        corregir_prioridades();
        procesoAct = nuevoP;
        listaP.add(nuevoP);
        nuevoId++;
    }

    /**
     * Corrige la prioridad de todos los demas procesos, antes de agregar uno
     * nuevo
     */
    private void corregir_prioridades() {
        for (Proceso proceso : listaP) {
            if (proceso.getPrioridad() == 0) {
                proceso.setPrioridad(1);
            }
        }

    }

    /**
     * Destruye el proceso
     *
     * @param id
     */
    public void destruir_proceso(int id) {
        for (Proceso proceso : listaP) {
            if (proceso.getId() == id) {
                listaP.remove(proceso);
            }
        }
    }

    /**
     * Crea un proceso de interrupcion
     *
     * @param velocidad El tiempo que dura el Quantum
     */
    public void crear_interrupcion(int velocidad) {
        Random rm = new Random();
        Proceso interrupcion = new Proceso("Interrupcion", (rm.nextInt(1) + 1) * velocidad);
        listaP.add(interrupcion);
        this.procesoAct = interrupcion;
    }

    /**
     * calcula el tiempo en que ocurrira una interrupcion del proceso.
     *
     * @param velocidad El tiempo que dura el Quantum.
     * @return -1 indica que no ocurre una interrupción. De lo contrario
     * devuelve el momento en que se genero la interrupcion.
     */
    public int tiempo_interrupcion() {
        Random rm = new Random();
        int probabilidad = rm.nextInt(99) + 1;
        if (probabilidad < 75) {
            return -1;
        } else {
            return rm.nextInt(99) + 1;
        }

    }

    /**
     * Revisa la Prioridad y en base a ella elige el siguiente proceso a
     * ejecutar.
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
        } else if (prioridad == 1) {
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

        } else {

        }

    }

    /**
     * Calcula el tiempo en que el proceso se bloqueara.
     *
     * @return -1 indica que el proceso no se bloqueó de lo contrario es el
     * momento en el que se va a bloquear.
     */
    public int tiempo_bloqueo() {
        Random rm = new Random();
        int probabilidad = rm.nextInt(99) + 1;
        if (probabilidad < 50) {
            return -1;
        } else {
            return rm.nextInt(99) + 1;
        }
    }

    /**
     * Aumenta el tiempo de bloqueo para todo proceso bloqueado
     *
     * @param tiempo - el tiempo que aumenta
     */
    public void aum_tiempo_bloqueo(int tiempo) {
        for (Proceso proc : listaP) {
            if (proc.isBloqueado() == true) {
                proc.agregarTiempoBloqueo(tiempo);
            }
        }
    }

    /**
     * Desbloquea todo proceso para el que su tiempo de bloqueo se haya cumplido
     */
    public void desbloquear_procesos() {
        for (Proceso proc : listaP) {
            if (proc.isBloqueado() == true) {
                if (proc.getTiempoBloqueo() <= proc.gettActualBloqueo()) {
                    proc.setBloqueado(false);
                }
            }
        }
    }

    /**
     * Bloquea el proceso Actual.
     */
    public void bloquear() {
        procesoAct.setBloqueado(true);
        Random rm = new Random();
        procesoAct.setTiempoBloqueo(rm.nextInt(2) * velocidad);

    }

    /**
     * aumenta el tiempo en ejecucion del proceso actual
     *
     * @param tiempo - el tiempo que aumenta (en milisegundos)
     */
    public void aum_tiempo_ejec(int tiempo) {
        if (procesoAct.isBloqueado() == false) {
            procesoAct.agregarDuracionActual(tiempo);
        }
    }

    /**
     * @return - La cantidad de procesos en la lista
     */
    public int tamaño() {
        return listaP.size();
    }

    /**
     * Retira los procesos que ya han cumplido su tiempo de ejecucion
     */
    public void terminar_procesos() {
        for (Proceso proc : listaP) {
            if (proc.getDuracionTotal() <= proc.getDuracionActual()) {
                listaP.remove(proc);

                if (proc.getPrioridad() == 0) {

                    for (Proceso proc2 : listaP) {
                        if (proc2.getId() != -1) {
                            proc2.setPrioridad(0);
                            procesoAct = proc2;
                            numeroEjec = 0;
                            return;
                        }
                    }
                } else if (proc.getPrioridad() == -1) {
                    for (Proceso proc2 : listaP) {

                        procesoAct = proc2;

                    }
                }
                return;

            }
        }
    }

    public void limpiar() {
        listaP.clear();
        numeroEjec = -1;
    }

    @Override
    public String toString() {
        String lista = "";
        for (Proceso proc : listaP) {
            lista += proc.toString() + "\n";
        }
        return lista;
    }

}
