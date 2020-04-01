/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion7.Ej1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * @author José Antonio
 */
public class PollTask implements Runnable {

    /**
     * List to delete the elements
     */
    private ConcurrentLinkedDeque<String> list;

    /**
     * Constructor of the class
     *
     * @param list List to delete the elements
     */
    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    /**
     * Main method of the task. Deletes 10000 elements from the list using the
     * pollFirst() that deletes the first element of the list and pollLast()
     * that deletes the last element of the list
     */
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }

}
