/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion2;

import com.uja.ssccdd.introduccion.sesion2.Constantes.TipoCapacidadGrafica;

/**
 *
 * @author fconde
 */
public class Fotograma {

    private int id;
    private TipoCapacidadGrafica necesidad;

    public Fotograma(int id, TipoCapacidadGrafica necesidad) {
        this.id = id;
        this.necesidad = necesidad;
    }

    @Override
    public String toString() {
        return "Fotograma{" + "id=" + id + ", necesidad=" + necesidad + '}';
    }

    public TipoCapacidadGrafica getNecesidad() {
        return necesidad;
    }
    
    public int getTiempoAsignacion() {
        return necesidad.tiempoAsignacion();
    }
}
