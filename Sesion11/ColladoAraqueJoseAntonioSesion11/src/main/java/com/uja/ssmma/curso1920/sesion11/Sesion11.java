package com.uja.ssmma.curso1920.sesion11;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Sesion11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            Logger.getLogger(Sesion11.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Future<?> listaTarea : listaTareas) {
            listaTarea.cancel(true);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion11.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
