package com.uja.ssccdd.sesion3.Ejercicio4;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Videoconference implements Runnable {

    CountDownLatch contador;

    public Videoconference(int numero) {
        contador = new CountDownLatch(numero);
    }

    public void arrive(String name) {
        System.out.println("Ha llegado " + name);
        contador.countDown();
        System.out.println("Quedan " + contador.getCount() + " participantes");
    }

    @Override
    public void run() {
        System.out.println("Comenzando videoconferencia. Hay " + contador.getCount() + " participantes");
        try {
            contador.await();
            System.out.println("Ya se encuentran listos todos los participantes. Comenzando retransmisión...");
        } catch (InterruptedException ex) {
            Logger.getLogger(Videoconference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
