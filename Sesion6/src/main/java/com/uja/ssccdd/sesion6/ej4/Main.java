/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion6.ej4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int array[] = new int[100];
        // Task to process the array
        Task task = new Task(array, 0, 100);
        // ForkJoinPool to execute the Task
        ForkJoinPool pool = new ForkJoinPool();

        // Execute the task
        pool.execute(task);

        // Shutdown the ForkJoinPool
        pool.shutdown();

        try {
            // Wait for the finalization of the task
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Check if the task has thrown an Exception. If it's the case, write it
        // to the console
        if (task.isCompletedAbnormally()) {
            System.out.printf("Main: An exception has ocurred\n");
            System.out.printf("Main: %s\n", task.getException());
        }

        System.out.printf("Main: Result: %d", task.join());
    }
}

