/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3_1819;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
        LinkedList<Escenas> lista = new LinkedList<>();
        Lock bloqueo = new ReentrantLock();
        CyclicBarrier barrera = new CyclicBarrier(5);
        
        Thread[] hilosGen = new Thread[10];
        Thread[] hilosRen = new Thread[20];
        
        for (int i = 0; i < 10; i++) {
            hilosGen[i] = new Thread(new GeneradorEscenas(lista, bloqueo), "Generador de escenas " + i);
            hilosGen[i].start();
        }
        
        for (int i = 0; i < 20; i++) {
            hilosRen[i] = new Thread(new RenderizadorEscena(lista, bloqueo, barrera), "Renderizador de escenas " + i);
            hilosRen[i].start();
        }
        
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Thread thread : hilosRen) {
            thread.interrupt();
        }
        
        for (Thread thread : hilosGen) {
            thread.interrupt();
        }
        
        for (Thread thread : hilosRen) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (Thread thread : hilosGen) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
