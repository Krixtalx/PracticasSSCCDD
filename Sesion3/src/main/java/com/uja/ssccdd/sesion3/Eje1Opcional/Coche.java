package com.uja.ssccdd.sesion3.Eje1Opcional;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Coche implements Runnable {

    private final Aparcamiento aparcamiento;

    public Coche(Aparcamiento aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        boolean entrada = false;
        do {
            if (aparcamiento.entrada(this)) {
                entrada = true;
                System.out.println(Thread.currentThread().getName() + " he entrado al aparcamiento");
                try {
                    TimeUnit.SECONDS.sleep((long) (Math.random() * 15 + 3));
                    try {
                        aparcamiento.salida(this);
                        System.out.println(Thread.currentThread().getName() + " acabo de salir del aparcamiento");
                    } catch (Exception ex) {
                        Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (Math.random() > 0.2 && !entrada);

        if (!entrada) {
            System.out.println(Thread.currentThread().getName() + " he abandonado la cola");
        }
    }
}
