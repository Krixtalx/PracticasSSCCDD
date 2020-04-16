/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion9.Ej2;

/**
 * Main class of the example. It creates three task and execute them
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        /*
		 * Create an array to store the threads 
         */
        Thread threads[] = new Thread[3];

        /*
		 * Launch three tasks
         */
        for (int i = 0; i < threads.length; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }

    }

}
