/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Tarea que simula la renderizacion de escenas.
 *
 * @author fconde
 */
public class RenderizadorEscenas implements Callable<List<Escena>> {

    private final String nombre;
    private final List<Escena> pendientes;
    private final Lock bloqueo;
    private boolean generadoresEnded;

    public RenderizadorEscenas(String nombre, List<Escena> pendientes, Lock bloqueo, boolean gen) {
        this.nombre = nombre;
        this.pendientes = pendientes;
        this.bloqueo = bloqueo;
        this.generadoresEnded = gen;
    }

    @Override
    public List<Escena> call() {
        System.out.println(nombre + " inicia su ejecución");
        ArrayList<Escena> resultado = new ArrayList<>();
        Escena aux = null;
        boolean tengoEscena;
        boolean acabado = false;

        while (true) {
            tengoEscena = false;
            bloqueo.lock();
            try {
                if (!pendientes.isEmpty()) {
                    aux = pendientes.remove(0);
                    tengoEscena = true;
                }
            } finally {
                bloqueo.unlock();
            }
            if (tengoEscena) {
                resultado.add(aux);
                try {
                    TimeUnit.SECONDS.sleep(aux.getDuracion());
                } catch (InterruptedException ex) {
                    System.out.println(nombre + " ha sido interrumpido");
                }
                System.out.println(nombre + " ha renderizado " + aux.getNombre() + " durante " + aux.getDuracion() + " segundos");
            } else if (generadoresEnded) {
                System.out.println(nombre + " finaliza su ejecución");
                return resultado;
            }
//            System.out.println(nombre + "tengoEscena: "+tengoEscena + "   generadoresEnded: "+generadoresEnded);
        }
    }

    /**
     * @param generadoresEnded the generadoresEnded to set
     */
    public void setGen(boolean generadoresEnded) {
        this.generadoresEnded = generadoresEnded;
    }
}
