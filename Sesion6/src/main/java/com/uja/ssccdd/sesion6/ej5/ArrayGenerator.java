/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion6.ej5;

import java.util.Random;

/**
 * Class that generates an array of integer numbers between 0 and 10 with a size
 * specified as parameter
 *
 */
public class ArrayGenerator {

    /**
     * Method that generates an array of integer numbers between 0 and 10 with
     * the specified size
     *
     * @param size The size of the array
     * @return An array of random integer numbers between 0 and 10
     */
    public int[] generateArray(int size) {
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

}
