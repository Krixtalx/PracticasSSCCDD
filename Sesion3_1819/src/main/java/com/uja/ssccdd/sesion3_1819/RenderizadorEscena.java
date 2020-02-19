/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3_1819;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class RenderizadorEscena implements Runnable {

    LinkedList<Escenas> lista;
    Lock bloqueo;
    CyclicBarrier barrera;

    public RenderizadorEscena(LinkedList<Escenas> lista, Lock bloqueo, CyclicBarrier barrera) {
        this.lista = lista;
        this.bloqueo = bloqueo;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ha iniciado la ejecución.");
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep((long) (2 + Math.random() * 2));
                bloqueo.lock();
                System.out.println(Thread.currentThread().getName() + " ha bloqueado");
                try {
                    if (!lista.isEmpty()) {
                        lista.pop();
                        System.out.println(Thread.currentThread().getName() + " ha renderizado una escena. Ciclo " + (i + 1) + " de 10");
                    }
                } finally {
                    bloqueo.unlock();
                    System.out.println(Thread.currentThread().getName() + " ha desbloqueado");
                }
            }
            barrera.await();
        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName() + " ha finalizado por una interrupción. (Renderizador de escena)");
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(RenderizadorEscena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
