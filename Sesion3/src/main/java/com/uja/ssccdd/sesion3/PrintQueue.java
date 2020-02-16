/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author José Antonio
 */
public class PrintQueue {
    private final Lock queueLock=new ReentrantLock();
    public void printJob(Object document){
        queueLock.lock();
        try{
            Long duracion=(long)(Math.random()*10000);
            System.out.println("Imprimiendo el trabajo en "+Thread.currentThread().getName()+ " con una duracion de "+duracion);
            Thread.sleep(duracion);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }finally{
            queueLock.unlock();
        }
    }
}
