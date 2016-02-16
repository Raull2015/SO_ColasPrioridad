/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcolasprioridad;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Raul
 */
public class Quantum extends Thread {

    @FXML
    private ProgressBar quantum;

    private boolean encendido;
    private int velocidad;

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public Quantum() {
        super();

        this.encendido = true;
    }

    @Override
    public void run() {

        while (encendido == true) {
            for (double i = 0; i < 1.01; i = i + 0.01) {
                try {
                    quantum.setProgress(i);
                    Quantum.sleep(velocidad);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Quantum.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public void detener() {
            this.encendido = false;

    }

}
