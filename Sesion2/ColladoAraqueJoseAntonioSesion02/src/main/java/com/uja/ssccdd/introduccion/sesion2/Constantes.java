/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion2;

import java.util.Random;

/**
 *
 * @author fconde
 */
public class Constantes {
    // Generador aleatorio
    public static final Random aleatorio = new Random();

    // Enumerado para el tipo de necesidades de un fotograma / capacidades de
    // un ordenador
    public enum TipoCapacidadGrafica {
        BASICAS(10, 3), MEDIAS(40, 5), AVANZADAS(160, 7);
        
        private final int valor;
        // - Lo que se tarda en asignar un fotograma con estas necesidades a
        //   un ordenador
        private final int tiempo;

        private TipoCapacidadGrafica(int valor, int tiempo) {
            this.valor = valor;
            this.tiempo = tiempo;
        }
        
        /**
         * Obtenemos una capacidad/necesiad grafica aleatoriamente.
         * @return el TipoCapacidadGrafica con el valor de construcción
         */
        public static TipoCapacidadGrafica getCapacidad() {
            int valor = aleatorio.nextInt(MAX_VALOR_CAPACIDAD);
            TipoCapacidadGrafica resultado = null;
            TipoCapacidadGrafica[] capacidades = TipoCapacidadGrafica.values();
            int i = 0;
            
            while( (i < capacidades.length) && (resultado == null) ) {
                if ( capacidades[i].valor >= valor )
                    resultado = capacidades[i];
                
                i++;
            }
            
            return resultado;
        } 
        
        /**
         * Calcula el tiempo de asignacion para un fotograma, con una variacion
         * aleatoria
         * @return 
         *      el tiempo de asignacion para ese sensor
         */
        public int tiempoAsignacion() {
            return aleatorio.nextInt(VARIACION) + tiempo;
        }


        @Override
        public String toString() {
            return "TipoCapacidadGrafica{" + this.name() + ", valor=" + valor + '}';
        }  
    }

    public static final int NUM_ASIGNADORES = 10;
    public static final int TIEMPO_ESPERA = 35;
    // - Se usa para calcular un tiempo de asignacion aleatorio, un numero de
    //   fotogramas aleatorio y un numero de ordenadores aleatorio.
    public static final int VARIACION = 3;
    public static final int MAX_VALOR_CAPACIDAD = 161;
    public static final int MIN_ORDENADORES = 3;
    public static final int MIN_FOTOGRAMAS = 5;
}
