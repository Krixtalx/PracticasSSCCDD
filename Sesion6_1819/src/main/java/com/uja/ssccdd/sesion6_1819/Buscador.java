/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion6_1819;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Buscador extends RecursiveTask<Integer[]> {

    private static final long serialVersionUID = 1L;

    private final int n_fila_inicio, n_fila_final, n_col_inicio, n_col_final;
    private final int[][] matriz;

    public Buscador(int n_fila_final, int n_col_final, int[][] matriz) {
        this.n_fila_inicio = 0;
        this.n_fila_final = n_fila_final;
        this.n_col_inicio = 0;
        this.n_col_final = n_col_final;
        this.matriz = matriz;
    }

    public Buscador(int n_fila_inicio, int n_fila_final, int n_col_inicio, int n_col_final, int[][] matriz) {
        this.n_fila_inicio = n_fila_inicio;
        this.n_fila_final = n_fila_final;
        this.n_col_inicio = n_col_inicio;
        this.n_col_final = n_col_final;
        this.matriz = matriz;
    }

    @Override
    protected Integer[] compute() {
        Integer[] resultado = new Integer[4];
        resultado[0] = -1;
        resultado[1] = 0;
        resultado[2] = 101;
        resultado[3] = 0;
        if (n_fila_final - n_fila_inicio > 2 && n_col_final - n_col_inicio > 2) {
            int media_fila = (n_fila_inicio + n_fila_final) / 2;
            int media_col = (n_col_inicio + n_col_final) / 2;
            Buscador task1 = new Buscador(n_fila_inicio, media_fila, n_col_inicio, media_col, matriz);
            Buscador task2 = new Buscador(n_fila_inicio, media_fila, media_col, n_col_final, matriz);
            Buscador task3 = new Buscador(media_fila, n_fila_final, n_col_inicio, media_col, matriz);
            Buscador task4 = new Buscador(media_fila, n_fila_final, media_col, n_col_final, matriz);
            invokeAll(task1, task2, task3, task4);
            try {
                Integer[] resultado1, resultado2;
                resultado1 = agruparResultados(task1.get(), task2.get());
                resultado2 = agruparResultados(task3.get(), task4.get());
                resultado = agruparResultados(resultado1, resultado2);
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            for (int i = n_fila_inicio; i < n_fila_final; i++) {
                for (int j = n_col_inicio; j < n_col_final; j++) {
                    if (resultado[0] < matriz[i][j]) {
                        resultado[0] = matriz[i][j];
                        resultado[1] = 1;
                    } else if (resultado[0] == matriz[i][j]) {
                        resultado[1]++;
                    }

                    if (resultado[2] > matriz[i][j]) {
                        resultado[2] = matriz[i][j];
                        resultado[3] = 1;
                    } else if (resultado[2] == matriz[i][j]) {
                        resultado[3]++;
                    }
                }
            }
        }
        return resultado;
    }

    private Integer[] agruparResultados(Integer[] task1, Integer[] task2) {
        Integer[] resultado = new Integer[4];
        if (task1[0].compareTo(task2[0]) > 0) {
            resultado[0] = task1[0];
            resultado[1] = task1[1];
        } else if (task1[0].compareTo(task2[0]) == 0) {
            resultado[0] = task1[0];
            resultado[1] = task1[1] + task2[1];
        } else {
            resultado[0] = task2[0];
            resultado[1] = task2[1];
        }

        if (task1[2].compareTo(task2[2]) < 0) {
            resultado[2] = task1[2];
            resultado[3] = task1[3];
        } else if (task1[2].compareTo(task2[2]) == 0) {
            resultado[2] = task1[2];
            resultado[3] = task1[3] + task2[3];
        } else {
            resultado[2] = task2[2];
            resultado[3] = task2[3];
        }
        return resultado;
    }
}
