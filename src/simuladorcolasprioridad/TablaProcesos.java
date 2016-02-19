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

    public TablaProcesos() {
        listaP = new ArrayList<>();
    }
    
    public void crear_proceso(String nombre){
        Proceso nuevoP = new Proceso(nombre, listaP.size()+1);
        listaP.add(nuevoP);
    }
    
    public void destruir_proceso(int id){
        
    
    }
    
}
