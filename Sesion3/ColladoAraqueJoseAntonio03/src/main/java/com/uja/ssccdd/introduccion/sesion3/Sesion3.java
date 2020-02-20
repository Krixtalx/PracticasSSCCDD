/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo principal de la aplicacion
 *
 * @author fconde
 */
public class Sesion3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Escena> lista = new LinkedList<>();
        Lock bloqueo = new ReentrantLock();
        Thread[] hilosGen = new Thread[Constantes.NUM_GENERADORES];
        Thread[] hilosRen = new Thread[Constantes.NUM_RENDERIZADORES];
        CyclicBarrier barrera = new CyclicBarrier(Constantes.TAREAS_ANTES_DE_CANCELAR + 1, new Finalizador(hilosGen, hilosRen));

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("** Hilo(PRINCIPAL): Ha iniciado la ejecución");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < Constantes.NUM_GENERADORES; i++) {
            hilosGen[i] = new Thread(new GeneradorEscenas(i, lista, bloqueo), "Generador de escenas " + (i + 1));
        }

        for (int i = 0; i < Constantes.NUM_RENDERIZADORES; i++) {
            hilosRen[i] = new Thread(new RenderizadorEscenas(lista, bloqueo, barrera), "Renderizador de escenas " + (i + 1));
        }

        for (int i = 0; i < Constantes.NUM_GENERADORES; i++) {
            hilosGen[i].start();
        }

        for (int i = 0; i < Constantes.NUM_RENDERIZADORES; i++) {
            hilosRen[i].start();
        }

        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Sesion3.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Thread thread : hilosGen) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Finalizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Thread thread : hilosRen) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Finalizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("** Hilo(PRINCIPAL): Ha finalizado la ejecucion");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

}
