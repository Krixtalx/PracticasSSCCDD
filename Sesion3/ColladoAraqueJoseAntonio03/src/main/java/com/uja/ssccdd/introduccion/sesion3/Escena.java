/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

/**
 * Escena. No hay que añadirle nada mas. En este ejercicio, de la escena solo
 * interesa poder distinguir unas de otras por su nombre.
 * @author fconde
 */
public class Escena {
    
    private final String nombre;

    public Escena(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Escena(" + nombre + ")";
    }
}
