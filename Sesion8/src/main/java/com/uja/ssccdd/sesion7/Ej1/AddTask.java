/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion7.Ej1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Task that add 10000 elements to a ConcurrentListDeque
 *
 */
public class AddTask implements Runnable {

    /**
     * List to add the elements
     */
    private final ConcurrentLinkedDeque<String> list;

    /**
     * Constructor of the class
     *
     * @param list List to add the elements
     */
    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    /**
     * Main method of the class. Add 10000 elements to the list using the add()
     * method that adds the element at the end of the list
     */
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }

}
