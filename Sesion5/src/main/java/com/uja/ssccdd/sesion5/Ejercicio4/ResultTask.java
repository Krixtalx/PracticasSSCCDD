/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio4;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author José Antonio
 */
public class ResultTask extends FutureTask {

    private String nombre;

    public ResultTask(Callable<String> callable) {
        super(callable);
        this.nombre = ((ExecutableTask) callable).getNombre();
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s: Has been cancelled\n", nombre);
        } else {
            System.out.printf("%s: Has finished\n", nombre);
        }

    }

}
