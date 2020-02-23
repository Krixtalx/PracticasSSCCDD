/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Tarea que simula la generacion de escenas
 *
 * @author fconde
 */
public class GeneradorEscenas implements Runnable {

    private final int id;
    private final LinkedList<Escena> lista;
    private final Lock bloqueo;

    public GeneradorEscenas(int id, LinkedList<Escena> lista, Lock bloqueo) {
        this.id = id;
        this.lista = lista;
        this.bloqueo = bloqueo;
    }



    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ha iniciado su ejecución.");
        try {
            int i = 1;
            while (true) {
                TimeUnit.SECONDS.sleep(Constantes.MIN_DURACION_GENERACION + Constantes.aleatorio.nextInt(Constantes.VARIACION_DURACION));
                bloqueo.lock();
                try {
                    generarEscena(i);
                    i++;
                } finally {
                    bloqueo.unlock();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName() + " ha interrumpido su ejecución.");
        }
    }

    private void generarEscena(int i) {
        Escena escena = new Escena("Escena " + id + "-" + i);
        lista.addLast(escena);
        System.out.println(Thread.currentThread().getName() + " ha insertado "+escena);
    }
}
