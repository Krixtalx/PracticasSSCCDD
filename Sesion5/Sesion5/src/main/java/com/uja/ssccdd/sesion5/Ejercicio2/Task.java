/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio2;

import java.util.Date;

/**
 *
 * @author Niskp
 */
public class Task implements Runnable{
    private String nombre;

    public Task(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre + " ejecutado " + new Date());
    }
    
    
}
