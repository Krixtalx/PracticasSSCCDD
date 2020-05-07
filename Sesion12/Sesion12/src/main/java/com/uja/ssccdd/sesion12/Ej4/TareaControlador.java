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
public class TareaControlador implements Runnable {

    private final String name;
    private final Puente puente;
    private final CountDownLatch finControlador;
    private final ExecutorService ejecucion;
    private final List<Future<?>> listaTareas;

    public TareaControlador(String name, CountDownLatch finControlador) {
        this.name = name;
        this.finControlador = finControlador;
        this.puente = new Puente();
        this.ejecucion = Executors.newCachedThreadPool();
        this.listaTareas = new ArrayList();
    }

    @Override
    public void run() {
        System.out.println(name + " Comienza su ejecución...");

        for (Servicio servicio : SERVICIOS) {
            switch (servicio) {
                case ENTRADA:
                    for (Tipo tipoCoche : TIPOS) {
                        entrada(servicio, tipoCoche);
                    }
                    break;
                case SALIDA:
                    for (Tipo tipoCoche : TIPOS) {
                        salida(servicio, tipoCoche);
                    }
                    break;
            }
        }

        finControlador();

        System.out.println(name + " Finaliza su ejecución...");
    }

    /**
     * Tarea para gestionar las peticiones que realicen los coches para acceder
     * al puente
     *
     * @param servicio Identifica el servicio de ENTRADA al puente
     * @param tipoCoche Identifica el sentido de acceso al puente
     */
    private void entrada(Servicio servicio, Tipo tipoCoche) {
        String queue = QUEUE + servicio.getValor() + tipoCoche.name();
        EntradaPuente entrada = new EntradaPuente(name, queue, puente);
        listaTareas.add(ejecucion.submit(entrada));
    }

    /**
     * Tarea para gestionar las peticiones que realicen los coches para indicar
     * que han abandonado el puente
     *
     * @param servicio Identifica el servicio para la SALIDA del puente
     * @param tipoCoche Identifica el sentido de acceso al puente
     */
    private void salida(Servicio servicio, Tipo tipoCoche) {
        String queue = QUEUE + servicio.getValor() + tipoCoche.name();
        SalidaPuente salida = new SalidaPuente(name, queue, puente);
        listaTareas.add(ejecucion.submit(salida));
    }

    /**
     * Finaliza las tareas que se encargan de las peticiones control al puente
     */
    private void finControlador() {
        try {
            finControlador.await();
        } catch (InterruptedException ex) {
            // No hacer nada porque se está terminando
        }

        // Se solicita la finalización de las tareas de control
        for (Future<?> tarea : listaTareas) {
            tarea.cancel(true);
        }

        ejecucion.shutdown();
        try {
            ejecucion.awaitTermination(TIME_SLEEP, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            // No hacer nada porque se está terminando
        }
    }
}