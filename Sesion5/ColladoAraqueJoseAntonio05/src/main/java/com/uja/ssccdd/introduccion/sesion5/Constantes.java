/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import java.util.Random;

/**
 * Constantes y generador de numeros aleatorios necesarios  para la ejecucion de 
 * la aplicacion
 * @author fconde
 */
public class Constantes {
    
    // - Generador de numeros aleatorio ----------------------------------------
    public static final Random aleatorio = new Random();

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
