/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion8;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Sesion8 implements Constantes {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hilo(PRINCIPAL) ha iniciado su ejecución");
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<?>> listaTareas = new ArrayList<>();
        DelayQueue<Peticion> listaPeticiones = new DelayQueue<>();

        listaTareas.add(executor.submit(new GestorPeticiones(executor, listaTareas, listaPeticiones)));

        for (int i = 0; i < TOTAL_CLIENTES; i++) {
            listaTareas.add(executor.submit(new Cliente("Cliente " + i, listaPeticiones)));
        }
        TimeUnit.SECONDS.sleep(30);
        for (Future<?> tarea : listaTareas) {
            tarea.cancel(true);
        }
        executor.shutdown();
        executor.awaitTermination(TIEMPO_MINIMO, TimeUnit.DAYS);

        System.out.println("Hilo(PRINCIPAL) ha finalizado su ejecución");
    }

}
