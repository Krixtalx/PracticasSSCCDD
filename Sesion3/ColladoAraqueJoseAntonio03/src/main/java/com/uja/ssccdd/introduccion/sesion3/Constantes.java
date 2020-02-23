/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion3;

import java.util.Random;

/**
 * Constantes y generador de numeros aleatorios necesarios para la ejecucion
 * de la aplicacion
 * @author fconde
 */
public class Constantes {
    // - Generador aleatorio ---------------------------------------------------
    
    public static final Random aleatorio = new Random();
    
    // - Constantes ------------------------------------------------------------
    
    public static final int NUM_GENERADORES = 4;
    public static final int NUM_RENDERIZADORES = 8;
    public static final int TAREAS_ANTES_DE_CANCELAR = 4;
    public static final int MIN_DURACION_GENERACION = 1;
    public static final int MIN_DURACION_RENDERING = 4;
    public static final int VARIACION_DURACION = 5;
    public static final int ESCENAS_ANTES_DE_TERMINAR = 5;
}
