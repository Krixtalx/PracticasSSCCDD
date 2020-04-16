package com.uja.ssccdd.introduccion.sesion9;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Tarea encargada de avisar a los Renderiadores de que los Generadores ya han
 * terminado.
 *
 * @author fconde
 */
public class Finalizador implements Runnable {

    private AtomicBoolean fin;

    public Finalizador(AtomicBoolean fin) {
        this.fin = fin;
    }

    @Override
    public void run() {
        fin.set(true);
    }

}
