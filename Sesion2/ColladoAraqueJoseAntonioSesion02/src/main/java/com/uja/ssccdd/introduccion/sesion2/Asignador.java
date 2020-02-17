/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion2;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fconde
 */
public class Asignador implements Runnable {

    int id;
    Fotograma[] fotogramas;
    Ordenador[] ordenadores;

    public Asignador(int id, Fotograma[] fotogramas, Ordenador[] ordenadores) {
        this.id = id;
        this.fotogramas = fotogramas;
        this.ordenadores = ordenadores;
    }

    @Override
    public void run() {
        int pos = 0;
        int contador = 0;
        int fotogramasAsignados=0;
        boolean interrumpido = false;
        System.out.println("Se ha iniciado la ejecución en " + Thread.currentThread().getName());
        while(!interrumpido && contador<fotogramas.length) {
            boolean asignado = false;
            int j = 0;
            while (!asignado && j < ordenadores.length) {
                asignado = ordenadores[pos % ordenadores.length].asignaFotograma(fotogramas[contador]);
                pos++;
                j++;
            }
            if(asignado)
                fotogramasAsignados++;
            pos = pos + 1;
            try {
                TimeUnit.SECONDS.sleep(fotogramas[contador].getTiempoAsignacion());
            } catch (InterruptedException ex) {
                if (((float)fotogramasAsignados / (float)fotogramas.length) >= 0.8 && ((float)fotogramasAsignados / (float)fotogramas.length) < 1.0) {
                    interrumpido = true;
                }
            }

            contador++;
        }

        System.out.println("Ha finalizado la ejecución de " + Thread.currentThread().getName() + " con " + fotogramasAsignados + " procesados de " + fotogramas.length+"\n"+"¿Ha sido interrumpido?: " + interrumpido);
        for (int i = 0; i < ordenadores.length; i++) {
            System.out.println(Thread.currentThread().getName() + ": "+i+"  "+ordenadores[i]);
        }
    }


}
