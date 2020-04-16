package com.uja.ssccdd.introduccion.sesion9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal.
 *
 * @author fconde
 */
public class Sesion9 {

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("** Hilo(PRINCIPAL): Ha iniciado la ejecucion\n");

        PriorityBlockingQueue<Escena> cola = new PriorityBlockingQueue<>();
        AtomicBoolean fin = new AtomicBoolean(false);
        ExecutorService executor = Executors.newCachedThreadPool();

        ArrayList<Future<List<Escena>>> listaRenderizadores = new ArrayList<>();

        for (int i = 0; i < Constantes.NUM_RENDERIZADORES; i++) {
            listaRenderizadores.add(executor.submit(new RenderizadorEscenas("Renderizador " + i, cola, fin)));
        }

        ArrayList<GeneradorEscenas> generadores = new ArrayList<>();

        for (int i = 0; i < Constantes.NUM_GENERADORES; i++) {
            generadores.add(new GeneradorEscenas("Generador " + i, cola));
        }

        ArrayList<Future<List<Escena>>> listaGeneradores = new ArrayList<>();
        try {
            listaGeneradores.addAll(executor.invokeAll(generadores));
            executor.submit(new Finalizador(fin));
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion9.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;
        for (Future<List<Escena>> future : listaRenderizadores) {
            System.out.println(ANSI_CYAN + "------------------" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Renderizador " + i);
            System.out.println(ANSI_CYAN + "------------------" + ANSI_RESET);
            i++;
            try {
                for (Escena escena : future.get()) {
                    System.out.println(escena);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Sesion9.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        i = 0;
        for (Future<List<Escena>> future : listaGeneradores) {
            System.out.println(ANSI_CYAN + "------------------" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Generador " + i + ANSI_RESET);
            System.out.println(ANSI_CYAN + "------------------" + ANSI_RESET);
            i++;
            try {
                for (Escena escena : future.get()) {
                    System.out.println(escena);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Sesion9.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println(ANSI_CYAN + "---------------------------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "Escenas sin renderizar" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "---------------------------" + ANSI_RESET);
        for (Escena escena : cola) {
            System.out.println(escena);
        }

        System.out.println("** Hilo(PRINCIPAL): Ha finalizado la ejecucion");
    }
}
