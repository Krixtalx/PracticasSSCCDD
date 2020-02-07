package com.uja.ssccdd.introduccion.sesion1;

import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author José Antonio
 */
public class Sesion1 {

    public static final int MAX_TRABAJOS_REALIZADOS = 10;
    public static final int NUM_IMPRESORAS = 3;
    public static final int MIN_TIEMPO_TRABAJO = 2;
    public static final int MAX_TIEMPO_TRABAJO = 4;
    public static final float PROB_REALIZAR_TRABAJO = 0.3f;

    public static void main(String[] args) throws PrinterException {
        
        Impresora[] impresoras = new Impresora[NUM_IMPRESORAS];
        ArrayList<TrabajoImpresion> trabajos;
        GestorImpresion gestor;
        Random generador = new Random();
        int idTrabajo = 0;
        int completados = 0;

        System.out.println("Se ha iniciado la ejecución en el Hilo Principal");

        for (int i = 0; i < NUM_IMPRESORAS; i++) {
            impresoras[i] = new Impresora(i);
        }

        trabajos = new ArrayList();
        gestor = new GestorImpresion(impresoras, trabajos);

        while (completados < MAX_TRABAJOS_REALIZADOS) {
            if (generador.nextFloat() < PROB_REALIZAR_TRABAJO && gestor.hayTrabajos()) {
                try {
                    completados++;
                    gestor.realizarTrabajo();
                    System.out.println("En Hilo Principal se han completado " + completados + " trabajos");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sesion1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                idTrabajo++;
                int tiempo = MIN_TIEMPO_TRABAJO + generador.nextInt(MAX_TIEMPO_TRABAJO - MIN_TIEMPO_TRABAJO);
                TrabajoImpresion nuevoTrabajo = new TrabajoImpresion(idTrabajo, tiempo);
                gestor.agregarTrabajo(nuevoTrabajo);
            }
        }
        System.out.println("Ha finalizado la ejecucion en el Hilo Principal");
    }
}
