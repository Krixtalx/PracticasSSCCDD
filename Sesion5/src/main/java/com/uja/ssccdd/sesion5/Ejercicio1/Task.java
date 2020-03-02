/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio1;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 *
 * @author Niskp
 */
public class Task implements Callable<String> {

    private final String nombre;

    public Task(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String call() throws Exception {
        System.out.println(nombre + " empezando en " + new Date());
        return "Hola mundo";
    }

}
