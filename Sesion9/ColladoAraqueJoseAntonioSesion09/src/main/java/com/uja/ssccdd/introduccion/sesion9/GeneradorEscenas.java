package com.uja.ssccdd.introduccion.sesion9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tarea que simula la generacion de escenas
 *
 * @author fconde
 */
public class GeneradorEscenas implements Callable<List<Escena>> {

    private String id;
    private PriorityBlockingQueue<Escena> cola;

    public GeneradorEscenas(String id, PriorityBlockingQueue<Escena> cola) {
        this.id = id;
        this.cola = cola;
        ThreadLocalRandom.current();
    }

    @Override
    public List<Escena> call() {
        System.out.println(id + " ha comenzado su ejecucion");

        ArrayList<Escena> resultado = new ArrayList<>();
        int numEscenasGenerar = Constantes.MIN_ESCENAS_X_GENERADOR + ThreadLocalRandom.current().nextInt(Constantes.VARIACION_ESCENAS_X_GENERADOR);

        for (int i = 0; i < numEscenasGenerar; i++) {
            try {
                Escena aux = generarEscena(i);
                resultado.add(aux);
                cola.add(aux);
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneradorEscenas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println(id + " ha finalizado su ejecucion");
        return resultado;
    }

    private Escena generarEscena(int i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(Constantes.MIN_TIEMPO_GENERACION + ThreadLocalRandom.current().nextInt(Constantes.VARIACION_GENERACION));
        Escena escena = new Escena("Escena-" + i + "-" + id, Constantes.MIN_DURACION_ESCENA + ThreadLocalRandom.current().nextInt(Constantes.VARIACION_DURACION), Constantes.Prioridad.getPrioridad());
        return escena;
    }
}
