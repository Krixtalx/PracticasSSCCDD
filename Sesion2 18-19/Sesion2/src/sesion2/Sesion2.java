/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion2;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Sesion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread[] hilos = new Thread[10];
        Tarea[] tareas = new Tarea[10];
        float[] array = new float[1000];

        for (int i = 0; i < 1000; i++) {
            array[i] = 0;
        }

        System.out.println("Inicio de ejecución del programa");

        for (int i = 0; i < 10; i++) {
            tareas[i] = new Tarea(array, i * 10, (i + 1) * 10);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion2.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 10; i++) {
            hilos[i].interrupt();
        }

        for (int i = 0; i < 10; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sesion2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
