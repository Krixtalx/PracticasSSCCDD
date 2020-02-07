package com.uja.ssccdd.introduccion.sesion1;

/**
 *
 * @author José Antonio
 */
public class TrabajoImpresion {

    private final int idTrabajo;
    private final int tiempoTrabajo;

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public int getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public TrabajoImpresion() {
        this.idTrabajo = 0;
        this.tiempoTrabajo = 0;
    }

    public TrabajoImpresion(int idTrabajo, int tiempoTrabajo) {
        this.idTrabajo = idTrabajo;
        this.tiempoTrabajo = tiempoTrabajo;
    }

    @Override
    public String toString() {
        return "TrabajoImpresion{" + "idTrabajo=" + getIdTrabajo() + ", tiempoTrabajo=" + getTiempoTrabajo() + '}';
    }

}
