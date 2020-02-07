/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.curso1920.problemassesion1;

import java.util.Random;

/**
 *
 * @author rellenar
 */
public class ColladoAraqueJoseAntonioSesion01 {

    public static final int NUM_ORDENADORES = 10;
    public static final int NUM_FOTOGRAMAS = 20;
    public static final int NUM_TIPOSCAPACIDADGRAFICA = TipoCapacidadGrafica.values().length;

    // Enumerado para el tipo de necesidades de un fotograma / capacidades de
    // un ordenador
    public enum TipoCapacidadGrafica {
        BASICAS(10), MEDIAS(40), AVANZADAS(160);

        private final int valor;

        private TipoCapacidadGrafica(int valor) {
            this.valor = valor;
        }

        /**
         * Obtenemos una capacidad/necesidad grafica relacionada con su valor de
         * construcción (es su valor asociado)
         *
         * @param valor de constucción de la capacidad gráfica 10, 40, o 100
         * @return el TipoCapacidadGrafica con el valor de construcción
         */
        public static TipoCapacidadGrafica getCapacidad(int valor) {
            TipoCapacidadGrafica resultado = null;
            TipoCapacidadGrafica[] capacidades = TipoCapacidadGrafica.values();
            int i = 0;

            while ((i < capacidades.length) && (resultado == null)) {
                if (capacidades[i].valor >= valor) {
                    resultado = capacidades[i];
                }

                i++;
            }

            return resultado;
        }

        @Override
        public String toString() {
            return "Tipo capacidad Gráfica: " + this.name() + ", valor: " + this.valor;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Se ha iniciado la ejecución");
        Random generador = new Random();

        Ordenador[] arrayOrdenadores = new Ordenador[NUM_ORDENADORES];
        for (int i = 0; i < NUM_ORDENADORES; i++) {
            arrayOrdenadores[i] = new Ordenador(i, generador.nextInt(NUM_TIPOSCAPACIDADGRAFICA));
        }

        int aux = 0;
        int posArray = 0;
        Fotograma fotograma;
        while (aux < NUM_FOTOGRAMAS) {
            aux++;
            fotograma = new Fotograma(aux, generador.nextInt(NUM_TIPOSCAPACIDADGRAFICA));

            for (int i = 0; i < NUM_ORDENADORES; i++) {
                if (arrayOrdenadores[(i + posArray) % NUM_ORDENADORES].addFotograma(fotograma)) {
                    posArray = (i + posArray + 1);
                    break;
                }
            }
        }

        for (int i = 0; i < NUM_ORDENADORES; i++) {
            System.out.println(arrayOrdenadores[i]);
        }

        System.out.println("Se ha finalizado la ejecución");
    }

}
