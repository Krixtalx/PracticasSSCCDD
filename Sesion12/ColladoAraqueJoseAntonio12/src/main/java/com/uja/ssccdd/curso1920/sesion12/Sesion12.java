package com.uja.ssccdd.curso1920.sesion12;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Sesion12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hilo principal inicia su ejecución");
        
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<?>> listaTareas = new ArrayList<>();

        for (int i = 0; i < Constantes.TipoComponente.values().length; i++) {
            listaTareas.add(executor.submit(new Fabricante("Fabricante " + i, Constantes.COMPONENTES[i])));
        }

        for (int i = 0; i < Constantes.PROVEEDORES; i++) {
            listaTareas.add(executor.submit(new Proveedor("Proveedor " + i)));
        }

        try {
            TimeUnit.MINUTES.sleep(Constantes.TIEMPO_ESPERA);
        } catch (InterruptedException ex) {
        }

        System.out.println("Cancelando tareas...");
        for (Future<?> listaTarea : listaTareas) {
            listaTarea.cancel(true);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }
        
        System.out.println("Hilo principal finaliza su ejecución");
    }

}
