/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * Clase Controladora Principal
 * @author Raul
 */
public class Quantum {

    @FXML
    private ProgressBar quantum; // barra de Quatum
    @FXML
    private ProgressBar proceso; //barra de proceso
    @FXML
    private ToggleButton botonIA; // boton de iniciar/apagar
    @FXML
    private Slider sliderVel; // Slider que controla la velocidad
    @FXML 
    private TextField nombreProceso; //TextField donde se ingresa el nombre del proceso
    @FXML
    private Label procesoEjec;// Label que indica el proceso en ejecucion
    @FXML
    private Label info;//label que despliega informacion
    
   
    private AnimBarra barra;  //hilo que anima la barra de Quantum  
    private boolean encendido;  //si el hilo que anima la barra de quantum esta encendido  
    private int velocidad; //velocidad del quantum
    private TablaProcesos tabla;
    
    public Quantum() {
        super();
        this.encendido = false;   
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        this.tabla = new TablaProcesos(velocidad);
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
    /**
     * Inicia o finaliza el hilo que anima la barra de Quantum
     */
    @FXML
    private void botonIniciarApagar(){
        if(botonIA.isSelected() == true){
            botonIA.setText("Apagar");
            iniciar();
        }
        else{
            botonIA.setText("Iniciar");
            detener();
        }
    }
    
    /**
     * Inicia el hilo que anima la barra de Quantum
     */
    public void iniciar(){
        if(this.encendido == false){
            this.velocidad = (int)sliderVel.getValue() * 10; //obtiene el valor de velocidad del slider de velocidad
            sliderVel.setDisable(true);
            barra = new AnimBarra(this.velocidad, this.quantum, this.proceso, this.procesoEjec);
            barra.setTabla(tabla);
            barra.start();
            this.encendido = true;
            info.setText("Simulacion Iniciada");
        }
    }
    
    
    /**
     * detiene el hilo que anima la barra de Quantum
     */
    public void detener() {
          if(this.encendido == true){
              barra.detener();
              this.encendido = false;
              sliderVel.setDisable(false);
               info.setText("Simulacion Detenida");
          }
    }
    
    @FXML
    public void agregarProceso(){
        tabla.crear_proceso(nombreProceso.getText().trim(), velocidad);
        info.setText("El proceso ha sido Creado");
        nombreProceso.clear();
    }
    
    
    public void debug(){
        System.out.println(tabla.toString());
    }
    

}
