/*
 * Copyright (C) 2020 José Antonio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uja.ssccdd.sesion2;

import java.math.BigInteger;

/**
 *
 * @author José Antonio
 */
public class PrimeGenerator extends Thread {

    @Override
    public void run() {
        BigInteger number = new BigInteger("1");
        while (true) {
            if (isPrime(number)) {
                System.out.println("El número " + number + " es un primo");
            }
            if (isInterrupted()) {
                System.out.println("El generador se ha detenido");
                return;
            }
            number = number.add(BigInteger.ONE);
        }
    }

    private boolean isPrime(BigInteger numero) {
        return numero.isProbablePrime(1);
    }

}
