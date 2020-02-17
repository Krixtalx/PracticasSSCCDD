/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion2;

import com.uja.ssccdd.introduccion.sesion2.Constantes.TipoCapacidadGrafica;
import java.util.ArrayList;

/**
 *
 * @author fconde
 */
public class Ordenador {
   
    private TipoCapacidadGrafica capacidad;
    // - Se usa un ArrayList ya que el numero de fotogramas que se van a asignar
    //   a un ordenador concreto no se conoce en tiempo de compilacion.
    private ArrayList<Fotograma> fotogramasAsignados;

    public Ordenador(TipoCapacidadGrafica capacidad) {
        this.capacidad = capacidad;
        this.fotogramasAsignados = new ArrayList<>();
    }
    
    // - Si el fotograma es compatible con las capacidades de este ordenador
    //   entonces se asigna y devuelve true, en caso contrario no hace nada y
    //   devuelve false.
    public boolean asignaFotograma(Fotograma fotograma) {
        if (capacidad == fotograma.getNecesidad()) {
            fotogramasAsignados.add(fotograma);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String cadena = "Ordenador{" + "capacidad=" + capacidad + ", fotogramasAsignados=" + 
                        fotogramasAsignados.size() + "}\n";
        // - Se usa un foreach porque es una estructura de control muy practica
        //   para colecciones (como ArrayList) con tamaño no conocido de antemano.
        for (Fotograma f: fotogramasAsignados) {
            cadena = cadena + f + "\n";
        }
        return cadena;
    }  
}
