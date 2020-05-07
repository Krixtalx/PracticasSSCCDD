/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion12.Ej4;

import static Constantes.Constantes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class GuionSesion12Async {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecucion;
        List<Future<?>> listaTareas;

        // Ejecución del hilo principal
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");

        // Inicialización de las variables del ejemplo
        ejecucion = Executors.newCachedThreadPool();
        listaTareas = new ArrayList();

        // Creamos la tarea controlador del puente
        CountDownLatch finControlador = new CountDownLatch(1);
        TareaControlador controlador = new TareaControlador("Controlador", finControlador);
        ejecucion.execute(controlador);

        // Creamos diferentes tareas coche de forma aleatoria
        for (int i = 0; i < COCHES; i++) {
            int tipo = rnd.nextInt(TIPOS.length);
            TareaCoche tareaCoche = new TareaCoche("Coche-" + i, TIPOS[tipo]);
            listaTareas.add(ejecucion.submit(tareaCoche));
        }

        // Esperamos un tiempo antes de finalizar
        TimeUnit.MINUTES.sleep(TIME_SLEEP);
        for (Future<?> tarea : listaTareas) {
            tarea.cancel(true);
        }
        finControlador.countDown();

        // Finalizamos el ejecutor y esperamos a que todas las tareas finalicen
        System.out.println("HILO(Principal) Espera a la finalización de las tareas");
        ejecucion.shutdown();
        ejecucion.awaitTermination(TIME_SLEEP, TimeUnit.DAYS);

        // Finalización del hilo principal
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }
}