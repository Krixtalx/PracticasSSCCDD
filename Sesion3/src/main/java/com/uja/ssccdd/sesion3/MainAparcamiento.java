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
public class MainAparcamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Aparcamiento parking = new Aparcamiento(100);
        Thread[] hilos = new Thread[200];
        
        for (int i = 0; i < 200; i++) {
            hilos[i]=new Thread(new Coche(parking));
        }
        
        for (Thread hilo : hilos) {
            hilo.start();
        }
    }
}
