/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion6.ej5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class of the program.
 */
public class Main {

    /**
     * Main method of the example
     *
     * @param args
     */
    public static void main(String[] args) {

        // Generate an array of 1000 integers
        ArrayGenerator generator = new ArrayGenerator();
        int array[] = generator.generateArray(1000);

        // Create a TaskManager object
        TaskManager manager = new TaskManager();

        // Create a ForkJoinPool with the default constructor
        ForkJoinPool pool = new ForkJoinPool();

        // Create a Task to process the array
        SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);

        // Execute the task
        pool.execute(task);

        // Shutdown the pool
        pool.shutdown();

        try {
            // Wait for the finalization of the task
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Write a message to indicate the end of the program
        System.out.printf("Main: The program has finished\n");
    }

}
