/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio5;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author José Antonio
 */
public class MainMatriz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        // Initializes the object for the results
        Results results = new Results(ROWS);

        // Creates an Grouper object
        Grouper grouper = new Grouper(results);

        // Creates the CyclicBarrier object. It has 5 participants and, when
        // they finish, the CyclicBarrier will execute the grouper object
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // Creates, initializes and starts 5 Searcher objects
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, barrier, 5);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");

    }

}
