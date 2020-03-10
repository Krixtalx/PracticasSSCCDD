/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio4;

/**
 *
 * @author José Antonio
 */
public class MainVideoconferencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Videoconference videoconferencia = new Videoconference(10);
        Thread hiloConferencia = new Thread(videoconferencia);
        hiloConferencia.start();
        
        Participant[] participantes=new Participant[10];
        Thread[] hilos = new Thread[10];
        
        for (int i = 0; i < 10; i++) {
            participantes[i]=new Participant("Participante "+i, videoconferencia);
            hilos[i]=new Thread(participantes[i]);
        }
        
        for (Thread hilo:hilos){
            hilo.start();
        }
    }
    
}
