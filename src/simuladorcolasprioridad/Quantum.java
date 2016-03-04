/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML
    private Label avisoInt;//label que avisa si ocurre una interrupcion 
    @FXML
    private Label avisoBloq;//label que avisa si ocurre un bloqueo
    @FXML
    private TableView<Proceso> mayorPrioridad; // tabla que contiene datos del proceso con mayor prioridad
     @FXML
    private TableColumn<Proceso, String> id;
    @FXML
    private TableColumn<Proceso, String> nombre;
    @FXML
    private TableColumn<Proceso, String> estado;
    @FXML
    private TableColumn<Proceso, String> prioridad;
    @FXML
    private ListView menorPrioridad; // lista con los procesos de menor 
    
    
    ObservableList<Proceso> lista ;
    ObservableList<String> listaMenor;
   
    private AnimBarra barra;  //hilo que anima la barra de Quantum  
    private boolean encendido;  //si el hilo que anima la barra de quantum esta encendido  
    private int velocidad; //velocidad del quantum
    private TablaProcesos tabla;
    
    public Quantum() {
        super();
        this.encendido = false; 
        lista = FXCollections.observableArrayList();
       
       
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
            barra.setAvisoInt(avisoInt);
            barra.setAvisoBloq(avisoBloq);
            barra.setControlador(this);
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
              tabla.limpiar();
               info.setText("Simulacion Detenida");
          }
    }
    
    @FXML
    public void agregarProceso(){
        tabla.crear_proceso(nombreProceso.getText().trim(), velocidad);
        info.setText("El proceso ha sido Creado");
        nombreProceso.clear();
        mostrar();
    }
    
    
    public void debug(){
        System.out.println(tabla.toString());
        tabla.crear_proceso("FireFox", velocidad);
        tabla.crear_proceso("Chrome", velocidad);
        tabla.crear_proceso("Explorer", velocidad);
        tabla.crear_proceso("Safari", velocidad);
        tabla.crear_proceso("Opera", velocidad);
        tabla.crear_proceso("Maxthon", velocidad);
        mostrar();
    }
    
    public void mostrar(){
        
        for(Proceso pro : tabla.getListaP()){
            if(pro.getPrioridad() == 0){
                lista =  FXCollections.observableArrayList(pro);
            }
        
        }
        listaMenor =  FXCollections.observableArrayList();
        for(Proceso pro : tabla.getListaP()){
            if(pro.getPrioridad() == 1){
                listaMenor.add(pro.getId() + "             " + pro.getNombre() + "                        " + pro.gBloqueado().get() + "               " + pro.getPrioridad());
            }
        
        }
        
        id.setCellValueFactory(cellData -> cellData.getValue().gId());
        nombre.setCellValueFactory(cellData -> cellData.getValue().gNombre());
        estado.setCellValueFactory(cellData -> cellData.getValue().gBloqueado());
        prioridad.setCellValueFactory(cellData -> cellData.getValue().gPrioridad());
        
        mayorPrioridad.setItems(lista);
        menorPrioridad.setItems(listaMenor);
    }

}
