/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio3;

import java.util.concurrent.Callable;

/**
 *
 * @author José Antonio
 */
public class Task implements Callable<String>{

    public Task() {
    }
    

    @Override
    public String call() throws Exception {
        while(true){
            System.out.println("Task : Test");
            Thread.sleep(100);
        }
    }
    
}
