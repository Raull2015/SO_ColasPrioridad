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
    private String nombre;
    private int id;
    private int duracion_total;
    private int duracion_actual;
    private boolean bloqueado;

    
    public Proceso(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        Random rm = new Random();
        duracion_total = (rm.nextInt(9)+1)*1000; //duracion en milisegundos.
    }

    public String getNombre() {
        return nombre;
    }
  
    public int getId() {
        return id;
    }

    public int getDuracion_total() {
        return duracion_total;
    }

    public int getDuracion_actual() {
        return duracion_actual;
    }

    public void setDuracion_actual(int duracion_actual) {
        this.duracion_actual = duracion_actual;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    
    
}
