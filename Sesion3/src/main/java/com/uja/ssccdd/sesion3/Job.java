/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3;

/**
 *
 * @author José Antonio
 */
public class Job implements Runnable {

    private PrintQueue cola;

    public Job(PrintQueue cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        cola.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());

    }

}
