/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion6_1819;

import java.util.Random;

/**
 *
 * @author José Antonio
 */
public class MatrizGenerator {

    private final int n_filas;
    private final int n_col;
    private final Random generador;

    public MatrizGenerator(int n_filas, int n_col) {
        this.n_filas = n_filas;
        this.n_col = n_col;
        generador = new Random();
    }

    public int[][] generar() {
        int[][] array = new int[n_filas][n_col];
        int max = -1, vecesMax = 0;
        int min = 101, vecesMin = 0;
        for (int i = 0; i < n_filas; i++) {
            for (int j = 0; j < n_col; j++) {
                array[i][j] = generador.nextInt(100);
                if (array[i][j] > max) {
                    max = array[i][j];
                    vecesMax = 1;
                } else if (array[i][j] == max) {
                    vecesMax++;
                }

                if (array[i][j] < min) {
                    min = array[i][j];
                    vecesMin = 1;
                } else if (array[i][j] == min) {
                    vecesMin++;
                }
            }
        }
        System.out.println("El mayor es " + max + " repitiendose " + vecesMax + " veces");
        System.out.println("El menor es " + min + " repitiendose " + vecesMin + " veces");
        return array;
    }

}
