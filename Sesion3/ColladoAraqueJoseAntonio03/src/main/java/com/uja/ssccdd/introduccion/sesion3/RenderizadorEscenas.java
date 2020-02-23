/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Tarea que simula la renderizacion de escenas.
 *
 * @author fconde
 */
public class RenderizadorEscenas implements Runnable {

    private final LinkedList<Escena> lista;
    private final Lock bloqueo;
    private final CyclicBarrier barrera;

    public RenderizadorEscenas(LinkedList<Escena> lista, Lock bloqueo, CyclicBarrier barrera) {
        this.lista = lista;
        this.bloqueo = bloqueo;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ha iniciado su ejecución.");
        try {
            for (int i = 0; i < Constantes.ESCENAS_ANTES_DE_TERMINAR; i++) {
                bloqueo.lock();
                try {
                    if (!renderizarEscena()) {
                        i--;
                    }
                } finally {
                    bloqueo.unlock();
                }
                TimeUnit.SECONDS.sleep(Constantes.MIN_DURACION_RENDERING + Constantes.aleatorio.nextInt(Constantes.VARIACION_DURACION));

            }
            System.out.println(Thread.currentThread().getName() + " ha finalizado su ejecución.");
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            System.out.println(Thread.currentThread().getName() + " ha interrumpido su ejecución.");
        }
    }

    private boolean renderizarEscena() {
        if (!lista.isEmpty()) {
            Escena escena = lista.pop();
            System.out.println(Thread.currentThread().getName() + " ha renderizado " + escena);
            return true;
        }
        return false;
    }
}
