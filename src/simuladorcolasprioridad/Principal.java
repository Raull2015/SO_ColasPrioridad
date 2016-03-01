/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Raul
 */

public class Principal extends Application {
    //clase controladora del Quantum
    private Quantum quantumControl;
    
    private int velocidad = 10; // en milisegundos * 100
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            //carga la scene de Interfaz.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("Interfaz.fxml"));
            AnchorPane raiz = (AnchorPane) loader.load();

            //define la scene principal
            Scene scene = new Scene(raiz);
            primaryStage.setScene(scene);
            primaryStage.show();

            //carga la clase controladora del Quantum
            quantumControl = loader.getController();
            quantumControl.setVelocidad(velocidad);
             
           

        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void stop(){
        quantumControl.detener();
    }

    public Principal() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
