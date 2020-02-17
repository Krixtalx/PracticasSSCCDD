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
public class Sesion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Asignador[] asignadores = new Asignador[Constantes.NUM_ASIGNADORES];
        Thread[] hilos = new Thread[Constantes.NUM_ASIGNADORES];

        for (int i = 0; i < Constantes.NUM_ASIGNADORES; i++) {
            Fotograma[] fotogramas = new Fotograma[Constantes.aleatorio.nextInt(Constantes.VARIACION) + Constantes.MIN_FOTOGRAMAS];
            for (int j = 0; j < fotogramas.length; j++) {
                fotogramas[j] = new Fotograma(j, Constantes.TipoCapacidadGrafica.getCapacidad());
            }
            Ordenador[] ordenadores = new Ordenador[Constantes.aleatorio.nextInt(Constantes.VARIACION) + Constantes.MIN_ORDENADORES];
            for (int j = 0; j < ordenadores.length; j++) {
                ordenadores[j] = new Ordenador(Constantes.TipoCapacidadGrafica.getCapacidad());
            }

            asignadores[i] = new Asignador(i, fotogramas, ordenadores);
            hilos[i] = new Thread(asignadores[i]);
        }
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");
        for (int i = 0; i < Constantes.NUM_ASIGNADORES; i++) {
            hilos[i].start();
        }

        try {
            TimeUnit.SECONDS.sleep(Constantes.TIEMPO_ESPERA);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion2.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < Constantes.NUM_ASIGNADORES; i++) {
            hilos[i].interrupt();
        }

        for (int i = 0; i < Constantes.NUM_ASIGNADORES; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sesion2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }
}
