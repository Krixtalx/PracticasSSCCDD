/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tarea que simula la generacion de escenas
 *
 * @author fconde
 */
public class GeneradorEscenas implements Callable<List<Escena>> {

    private final String nombre;
    private final List<Escena> pendientes;
    private final int escenasGenerar;
    private final Lock bloqueo;
    private final CyclicBarrier barrera;

    public GeneradorEscenas(String nombre, List<Escena> pendientes, Lock bloqueo, CyclicBarrier barrera) {
        this.nombre = nombre;
        this.pendientes = pendientes;
        this.bloqueo = bloqueo;
        this.barrera = barrera;
        this.escenasGenerar = (int) (Constantes.MIN_ESCENAS_X_GENERADOR + Math.random() * Constantes.VARIACION_ESCENAS_X_GENERADOR);
    }

    @Override
    public List<Escena> call() {
        System.out.println(nombre + " inicia su ejecución");
        ArrayList<Escena> resultado = new ArrayList<>();
        Escena aux;
        for (int i = 0; i < escenasGenerar; i++) {
            aux = generarEscena(i);
            if (aux != null) {
                System.out.println(nombre + " ha generado " + aux.getNombre() + " durante " + aux.getDuracion() + " segundos " + i+"/"+escenasGenerar);
                bloqueo.lock();
                try {
                    pendientes.add(aux);
                } finally {
                    bloqueo.unlock();
                }
                resultado.add(aux);
            }
        }
        System.out.println(nombre + " finaliza su ejecución");
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GeneradorEscenas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    private Escena generarEscena(int num) {
        try {
            TimeUnit.SECONDS.sleep((long) (Constantes.MIN_TIEMPO_GENERACION + Math.random() * Constantes.VARIACION_GENERACION));
            return new Escena(nombre + "-Escena " + num);
        } catch (InterruptedException ex) {
            System.out.println(nombre + " ha sido interrumpido");
        }
        return null;
    }
}
