/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion4.Ejemplo1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class Task implements Runnable {

    String nombre;
    Date fecha;

    public Task(String nombre) {
        this.nombre = nombre;
        this.fecha = new Date();
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), nombre, fecha);
        System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), nombre, new Date());

        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), nombre, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), nombre, new Date());

    }

}
