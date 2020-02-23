/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion4.Ejemplo3;

import java.util.concurrent.Callable;

/**
 *
 * @author José Antonio
 */
public class FactorialCalculator implements Callable<Integer> {

    private Integer entero;

    public FactorialCalculator(Integer entero) {
        this.entero = entero;
    }

    @Override
    public Integer call() throws Exception {

        int num, result;

        num = entero.intValue();
        result = 1;

        // If the number is 0 or 1, return the 1 value
        if ((num == 0) || (num == 1)) {
            result = 1;
        } else {
            // Else, calculate the factorial
            for (int i = 2; i <= entero; i++) {
                result *= i;
                Thread.sleep(20);
            }
        }
        System.out.printf("%s: %d de %d\n", Thread.currentThread().getName(), result, entero);
        // Return the value
        return result;

    }

}
