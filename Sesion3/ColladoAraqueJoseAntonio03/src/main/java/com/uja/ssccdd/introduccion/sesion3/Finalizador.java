/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

/**
 *
 * @author Niskp
 */
public class Finalizador implements Runnable {

    Thread[] hilosGen;
    Thread[] hilosRen;

    public Finalizador(Thread[] hilosGen, Thread[] hilosRen) {
        this.hilosGen = hilosGen;
        this.hilosRen = hilosRen;
    }

    @Override
    public void run() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Se han completado 4 tareas de renderizado. Procediendo a interrumpir los hilos restantes...");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Thread thread : hilosGen) {
            thread.interrupt();
        }
        for (Thread thread : hilosRen) {
            thread.interrupt();
        }
    }
}
