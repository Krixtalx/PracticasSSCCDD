/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class ExecutableTask implements Callable<String> {

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    private final String nombre;

    public ExecutableTask(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String call() throws Exception {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.getNombre(), duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
        }
        return "Hello, world. I'm " + getNombre();

    }

}
