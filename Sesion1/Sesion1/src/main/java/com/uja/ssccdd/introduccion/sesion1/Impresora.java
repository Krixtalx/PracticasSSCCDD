package com.uja.ssccdd.introduccion.sesion1;

/**
 *
 * @author José Antonio
 */
public class Impresora {

    private final int id;

    public Impresora() {
        this.id = 0;
    }

    public Impresora(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Impresora{" + "id=" + id + '}';
    }

}
