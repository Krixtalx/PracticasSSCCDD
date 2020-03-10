/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecutor = new ScheduledThreadPoolExecutor(2);
        Future<String> resultado = ejecutor.submit(new Task());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Cancelando...");
        resultado.cancel(true);
        System.out.println("Cancelada: " + resultado.isCancelled());
        System.out.println("Terminada: " + resultado.isDone());
        
        ejecutor.shutdown();
        System.out.println("Fin");
        
    }
    
}
