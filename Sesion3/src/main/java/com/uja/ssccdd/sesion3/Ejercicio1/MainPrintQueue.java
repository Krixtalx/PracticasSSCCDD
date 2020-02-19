package com.uja.ssccdd.sesion3.Ejercicio1;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niskp
 */
public class MainPrintQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintQueue cola = new PrintQueue();
        Thread[] hilos = new Thread[10];
        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(new Job(cola));
        }
        for (Thread hilo : hilos) {
            hilo.start();
        }
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainPrintQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Thread hilo : hilos) {
            hilo.interrupt();
        }
    }
}
