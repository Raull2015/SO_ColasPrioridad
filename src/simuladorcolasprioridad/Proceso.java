package simuladorcolasprioridad;

import java.util.Random;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private final StringProperty nombre;
    private final IntegerProperty id;
    private final int duracionTotal;
    private int duracionActual;
    private BooleanProperty bloqueado;
    private int tiempoBloqueo;
    private int tActualBloqueo;
    private IntegerProperty prioridad;

    
    public Proceso(String nombre, int id, int velocidad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleIntegerProperty( id);
        this.bloqueado = new SimpleBooleanProperty( false);
        Random rm = new Random();
        this.duracionTotal = (rm.nextInt(15)+3)*(velocidad*100);
    }
    
    public Proceso(String nombre, int duracion_total){
        this.id = new SimpleIntegerProperty(-1);
        this.nombre = new SimpleStringProperty(nombre);
        this.duracionTotal = duracion_total;
    }

    public String getNombre() {
        return nombre.get();
    }
    
    public StringProperty gNombre(){
        return nombre;
    }
  
    public int getId() {
        return id.get();
    }
    
    public StringProperty gId(){
        return new SimpleStringProperty(String.valueOf(id.get()));
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
        return bloqueado.get();
    }
    
    public StringProperty gBloqueado(){
        if(bloqueado.get() == true){
            return new SimpleStringProperty("Bloqueado");
        }
        else{
            return new SimpleStringProperty("Activo");
        }
        
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = new SimpleBooleanProperty(bloqueado);
    }

    public int getPrioridad() {
        return prioridad.get();
    }
    
    public StringProperty gPrioridad(){
        return new SimpleStringProperty(String.valueOf(prioridad.get()));
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = new SimpleIntegerProperty(  prioridad);
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
