package com.uja.ssccdd.introduccion.sesion9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tarea que simula la renderizacion de escenas.
 *
 * @author fconde
 */
public class RenderizadorEscenas implements Callable<List<Escena>> {
    
    private String id;
    private PriorityBlockingQueue<Escena> cola;
    private AtomicBoolean fin;
    
    public RenderizadorEscenas(String id, PriorityBlockingQueue<Escena> cola, AtomicBoolean fin) {
        this.id = id;
        this.cola = cola;
        this.fin = fin;
        ThreadLocalRandom.current();
    }
    
    @Override
    public List<Escena> call() {
        System.out.println(id + " ha comenzado su ejecucion");
        ArrayList<Escena> resultado = new ArrayList<>();
        while (!fin.get() || !cola.isEmpty()) {
            try {
                Escena escenaRenderizar = cola.take();
                resultado.add(escenaRenderizar);
                TimeUnit.SECONDS.sleep(escenaRenderizar.getRenderTime());
                
            } catch (InterruptedException ex) {
                Logger.getLogger(RenderizadorEscenas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        System.out.println(id + " ha finalizado su ejecucion");
        return resultado;
    }
    
}
