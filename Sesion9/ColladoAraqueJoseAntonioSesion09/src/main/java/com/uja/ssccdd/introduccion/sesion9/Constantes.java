package com.uja.ssccdd.introduccion.sesion9;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Constantes y tipo enumerado necesarios para la ejecucion de la aplicacion
 * 
 * OJO: NO ESTÁ PERMITIDO MODIFICAR ESTA CLASE
 * 
 * @author fconde
 */
public class Constantes {
    
    public enum Prioridad {
        ALTA, MEDIA, BAJA;
        
        // – Devuelve un valor de prioridad ALTA, MEDIA, BAJA de forma aleatoria
        public static Prioridad getPrioridad() {
            int value = ThreadLocalRandom.current().nextInt(3);
            
            return Prioridad.values()[value];
        }
        
        @Override
        public String toString() {
            return this.name();
        }
    }
    
    // - Constantes ------------------------------------------------------------
    public static final int MIN_DURACION_ESCENA = 3;
    public static final int VARIACION_DURACION = 3;

    public static final int NUM_GENERADORES = 4;    
    public static final int MIN_ESCENAS_X_GENERADOR = 6;
    public static final int VARIACION_ESCENAS_X_GENERADOR = 4;
    public static final int MIN_TIEMPO_GENERACION = 1;
    public static final int VARIACION_GENERACION = 2;
    
    public static final int NUM_RENDERIZADORES = 6;
}
