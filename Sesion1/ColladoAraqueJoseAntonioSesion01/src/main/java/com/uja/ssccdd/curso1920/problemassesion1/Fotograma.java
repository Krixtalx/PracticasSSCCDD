/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.curso1920.problemassesion1;

/**
 *
 * @author Niskp
 */
public class Fotograma {

    private final int id;
    private final ColladoAraqueJoseAntonioSesion01.TipoCapacidadGrafica capacidadGrafica;

    public Fotograma() {
        id = -1;
        capacidadGrafica = null;
    }

    public Fotograma(int id, int valorCapacidad) {
        this.id = id;
        capacidadGrafica = ColladoAraqueJoseAntonioSesion01.TipoCapacidadGrafica.values()[valorCapacidad];
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + getCapacidadGrafica(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the capacidadGrafica
     */
    public ColladoAraqueJoseAntonioSesion01.TipoCapacidadGrafica getCapacidadGrafica() {
        return capacidadGrafica;
    }

}
