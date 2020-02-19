/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3_1819;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author José Antonio
 */
public class GeneradorEscenas implements Runnable {

    LinkedList<Escenas> lista;
    Lock bloqueo;

    public GeneradorEscenas() {
    }

    public GeneradorEscenas(LinkedList<Escenas> lista, Lock bloqueo) {
        this.lista = lista;
        this.bloqueo = bloqueo;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ha iniciado la ejecución.");
        try {
            while (true) {
                TimeUnit.SECONDS.sleep((long) (1 + Math.random() * 2));
                bloqueo.lock();
                System.out.println(Thread.currentThread().getName() + " ha bloqueado");
                try {
                    lista.addLast(new Escenas());
                    System.out.println(Thread.currentThread().getName() + " ha introducido una escena.");
                } finally {
                    bloqueo.unlock();
                    System.out.println(Thread.currentThread().getName() + " ha desbloqueado");
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName() + " ha sido interrumpido.");
        }
    }
}
