/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import static com.uja.ssccdd.introduccion.sesion5.Constantes.NUM_GENERADORES;
import static com.uja.ssccdd.introduccion.sesion5.Constantes.NUM_RENDERIZADORES;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal.
 *
 * @author fconde
 */
public class Sesion5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Escena> pendientes = new ArrayList<>();

        boolean generadoresEnded = false;
        Lock bloqueo = new ReentrantLock();
        List<RenderizadorEscenas> listaRender = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_GENERADORES + NUM_RENDERIZADORES);
        ArrayList<Callable<List<Escena>>> tareas = new ArrayList<>();

        System.out.println("** Hilo(PRINCIPAL): Ha iniciado la ejecucion\n");

        for (int i = 0; i < NUM_RENDERIZADORES; i++) {
            RenderizadorEscenas renderizador = new RenderizadorEscenas("REND-" + i, pendientes, bloqueo, generadoresEnded);
            tareas.add(renderizador);

            listaRender.add(renderizador);
        }
        CyclicBarrier barrera = new CyclicBarrier(NUM_GENERADORES, new Finalizador(listaRender));
        for (int i = 0; i < NUM_GENERADORES; i++) {
            GeneradorEscenas generador = new GeneradorEscenas("GEN-" + i, pendientes, bloqueo, barrera);
            tareas.add(generador);
        }

        // - Construir la lista de Futures que recibiran los resultados de las
        //   tareas (en este ejemplo, las escenas que hayan generado / renderizado)
        //   Ejecutar las tareas esperando a que terminen todas ellas.
        List<Future<List<Escena>>> resultados = null;
        try {
            resultados = executor.invokeAll(tareas);
            System.out.println("Generadores acabados");
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion5.class.getName()).log(Level.SEVERE, null, ex);
        }
        executor.shutdown();

        System.out.println("\n** Hilo(PRINCIPAL): Todos los hilos han terminado\n");

        for (int i = 0; i < resultados.size(); i++) {
            Future<List<Escena>> resultado = resultados.get(i);
            List<Escena> res;
            try {
                res = resultado.get();
                System.out.println(res);
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Sesion5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // - Un ArrayList puede imprimirse directamente si los elementos que
        //   contienen han definido el metodo toString()
        System.out.println("\nEscenas sin procesar: " + pendientes + "\n");

        System.out.println("** Hilo(PRINCIPAL): Ha finalizado la ejecucion");
    }
}
