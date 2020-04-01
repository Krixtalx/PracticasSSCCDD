/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion8;

import java.util.ArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class GestorPeticiones implements Constantes, Runnable {
    
    ExecutorService executor;
    ArrayList<Future<?>> listaTareas;
    DelayQueue<Peticion> listaPeticiones;

    public GestorPeticiones(ExecutorService executor, ArrayList<Future<?>> listaTareas, DelayQueue<Peticion> listaPeticiones) {
        this.executor = executor;
        this.listaTareas = listaTareas;
        this.listaPeticiones = listaPeticiones;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                ejecucion();
            } catch (InterruptedException ex) {
                System.out.println("El gestorPeticiones ha sido interrumpido");
            }
        }
    }
    
    private void ejecucion() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Peticion peticion = listaPeticiones.take();
        listaTareas.add(executor.submit(peticion));
        TimeUnit.SECONDS.sleep(TIEMPO_MINIMO);
    }
    
}
