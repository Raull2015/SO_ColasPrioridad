/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import java.util.ArrayList;

/**
 *
 * @author Brandon
 */
public class TablaProcesos { 
    ArrayList<Proceso> listaP;
    private int nuevoId;

    public TablaProcesos() {
        listaP = new ArrayList<>();
        nuevoId = 1;
    }
    
    public void crear_proceso(String nombre,int velocidad){
        
        Proceso nuevoP = new Proceso(nombre, nuevoId, velocidad);
        nuevoP.setPrioridad(0);
        corregir_prioridades();
        listaP.add(nuevoP);
        
        nuevoId++;
    }
    
    public void corregir_prioridades(){
        for (Proceso proceso : listaP) {
            proceso.setPrioridad(proceso.getPrioridad() + 1);
        }
        
    }
    
    public void destruir_proceso(int id){
        for(Proceso proceso: listaP){
            if(proceso.getId() == id){
                listaP.remove(proceso);
            }
        }
    
    }
    
}
