/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import static com.uja.ssccdd.introduccion.sesion5.Constantes.MIN_DURACION_ESCENA;
import static com.uja.ssccdd.introduccion.sesion5.Constantes.VARIACION_DURACION;
import static com.uja.ssccdd.introduccion.sesion5.Constantes.aleatorio;

/**
 * Escena. No hay que añadirle nada mas. En este ejercicio, de la escena solo
 * interesa poder distinguir unas de otras por su nombre y cuando va a durar
 * el proceso de rendering.
 * @author fconde
 */
public class Escena {
    
    private final String nombre;
    private final int duracion;

    public Escena(String nombre) {
        this.nombre = nombre;
        this.duracion = MIN_DURACION_ESCENA + aleatorio.nextInt(VARIACION_DURACION);
    }

    public String getNombre() {
        return nombre;
    }
    
    /**
     * Devuelve la duracion del proceso de rendering de esta escena.
     */
    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return "Escena(" + nombre + ", dur: " + duracion + ")";
    }
}
