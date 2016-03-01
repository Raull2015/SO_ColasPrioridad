package simuladorcolasprioridad;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brandon
 */
public class Proceso {
    private final String nombre;
    private final int id;
    private final int duracionTotal;
    private int duracionActual;
    private boolean bloqueado;
    private int tiempoBloqueo;
    private int tActualBloqueo;
    private int prioridad;

    
    public Proceso(String nombre, int id, int velocidad) {
        this.nombre = nombre;
        this.id = id;
        Random rm = new Random();
        this.duracionTotal = (rm.nextInt(9)+1)*(velocidad*100);
    }
    
    public Proceso(String nombre, int duracion_total){
        this.id = -1;
        this.nombre = nombre;
        this.duracionTotal = duracion_total;
    }

    public String getNombre() {
        return nombre;
    }
  
    public int getId() {
        return id;
    }

    public int getDuracionTotal() {
        return duracionTotal;
    }

    public int getDuracionActual() {
        return duracionActual;
    }

    public void setDuracionActual(int duracion_actual) {
        this.duracionActual = duracion_actual;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoBloqueo() {
        return tiempoBloqueo;
    }

    public int gettActualBloqueo() {
        return tActualBloqueo;
    }

    public void settActualBloqueo(int tActualBloqueo) {
        this.tActualBloqueo = tActualBloqueo;
    }
    
    public void agregarTiempoBloqueo(int tiempo){
        this.tActualBloqueo = this.tActualBloqueo + tiempo;
    }
    
    public void agregarDuracionActual(int tiempo){
        this.duracionActual = this.duracionActual + tiempo;
    }
    public double getPorcentaje(){
        return (double)duracionActual / (double)duracionTotal;
    }
    @Override
    public String toString(){
        return id + " " + nombre + " actual " + duracionActual + " total " + duracionTotal + " prioridad " + prioridad; 
    }
    
}
