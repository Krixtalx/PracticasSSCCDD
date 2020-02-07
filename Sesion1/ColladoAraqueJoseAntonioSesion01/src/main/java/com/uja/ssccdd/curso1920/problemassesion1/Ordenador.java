/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.curso1920.problemassesion1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Niskp
 */
public class Ordenador {

    private final int id;
    private ArrayList<Fotograma> lista = new ArrayList<Fotograma>();
    private ColladoAraqueJoseAntonioSesion01.TipoCapacidadGrafica capacidad;

    public Ordenador() {
        this.id = 0;
    }

    public Ordenador(int id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param valorCapacidad
     */
    public Ordenador(int id, int valorCapacidad) {
        this.id = id;
        capacidad = ColladoAraqueJoseAntonioSesion01.TipoCapacidadGrafica.values()[valorCapacidad];
    }

    /**
     * @brief Intenta añadir un fotograma a la lista de fotogramas del ordenador
     * @param fotograma
     * @return true si se ha añadido, false en caso contrario
     */
    public boolean addFotograma(Fotograma fotograma) {
        if (fotograma.getCapacidadGrafica() == this.capacidad) {
            lista.add(fotograma);
            System.out.println("Se ha añadido el Fotograma: " + fotograma + " al ordenador " + id);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String aux;
        aux = "\nOrdenador " + id + " " + capacidad + "\n";
        for (int i = 0; i < lista.size(); i++) {
            aux = aux + ("Fotograma " + lista.get(i) + "\n");
        }
        return aux;
    }

    /**
     * Renderiza el primer fotograma de la lista
     */
    public void renderizarFotograma() {
        if (!lista.isEmpty()) {
            lista.remove(0);
        }
    }

    /**
     * Muestra por salida estandar los fotogramas de la lista
     */
    public void mostrarLista() {
        System.out.println();
        System.out.println(this);
        System.out.println();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Fotograma " + lista.get(i));
        }
    }

}
