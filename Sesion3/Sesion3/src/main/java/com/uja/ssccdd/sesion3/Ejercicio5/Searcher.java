/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author José Antonio
 */
public class Searcher implements Runnable {

    private final int inicio;
    private final int last;
    private final MatrixMock matriz;
    private final Results resultado;
    private final CyclicBarrier barrera;
    private final int nBusca;

    public Searcher(int inicio, int last, MatrixMock matriz, Results resultado, CyclicBarrier barrera, int nBusca) {
        this.inicio = inicio;
        this.last = last;
        this.matriz = matriz;
        this.resultado = resultado;
        this.barrera = barrera;
        this.nBusca = nBusca;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), inicio, last);
        for (int i = inicio; i < last; i++) {
            int row[] = matriz.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == nBusca) {
                    counter++;
                }
            }
            resultado.setData(i, counter);
        }
        System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

}
