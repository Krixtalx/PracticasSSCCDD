/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio6;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class Task implements Runnable {

    /**
     * Name of the task
     */
    private final String name;

    /**
     * Constructor of the class. It initializes the attributes of the class
     *
     * @param name The name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Main method of the task. Waits a random period of time
     */
    @Override
    public void run() {
        System.out.printf("Task %s: Starting\n", name);
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending\n", name);
    }

    /**
     * Returns the name of the task
     */
    @Override
    public String toString() {
        return name;
    }

}
