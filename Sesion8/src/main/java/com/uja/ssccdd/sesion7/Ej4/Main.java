/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion7.Ej4;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        /*
        * Delayed queue to store the events
         */
        DelayQueue<Event> queue = new DelayQueue<>();

        /*
        * An array to store the Thread objects that execute the tasks 
         */
        Thread threads[] = new Thread[5];

        /*
        * Create the five tasks
         */
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i + 1, queue);
            threads[i] = new Thread(task);
        }

        /*
        * Execute the five tasks
         */
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        /*
        * Wait for the finalization of the five tasks 
         */
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        /*
        * Write the results to the console
         */
        do {
            int counter = 0;
            Event event;
            do {
                event = queue.poll();
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            System.out.printf("At %s you have read %d events\n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }

}
