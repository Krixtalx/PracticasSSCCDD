/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio4;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Participant implements Runnable{
    private final String name;
    private final Videoconference videoconferencia;

    public Participant(String name, Videoconference videoconferencia) {
        this.name = name;
        this.videoconferencia = videoconferencia;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" comenzando intento de conexión...");
        try {
            TimeUnit.SECONDS.sleep((long)(Math.random()*4));
        } catch (InterruptedException ex) {
            Logger.getLogger(Participant.class.getName()).log(Level.SEVERE, null, ex);
        }
        videoconferencia.arrive(name);
    }
    
    
}
