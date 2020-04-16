package com.uja.ssccdd.introduccion.sesion9;

import com.uja.ssccdd.introduccion.sesion9.Constantes.Prioridad;

/**
 * Escena. Hay que implementar el metodo compareTo.
 *
 * @author fconde
 */
public class Escena implements Comparable<Escena> {

    private final String ID;
    private final int renderTime;
    private final Prioridad priority;

    public Escena(String ID, int renderTime, Prioridad priority) {
        this.ID = ID;
        this.renderTime = renderTime;
        this.priority = priority;
    }

    public String getID() {
        return ID;
    }

    public Prioridad getPriority() {
        return priority;
    }

    public int getRenderTime() {
        return renderTime;
    }

    @Override
    public String toString() {
        return "Escena(" + ID + ", render time: " + renderTime + ", prioridad: " + priority + ")";
    }

    @Override
    public int compareTo(Escena o) {
        if (this.priority.ordinal() > o.getPriority().ordinal()) {
            return -1;
        } else if (this.priority.ordinal() < o.getPriority().ordinal()) {
            return 1;
        } else {
            return 0;
        }

    }
}
