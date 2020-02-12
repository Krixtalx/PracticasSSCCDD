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

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class SafeTask implements Runnable {

    private static ThreadLocal<Date> fecha = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("Comienzo del hilo: %s : %s\n", Thread.currentThread().getId(), fecha.get());
        Random generador = new Random();
        try {
            TimeUnit.SECONDS.sleep(generador.nextInt(4)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Writes the start date
        System.out.printf("Fin del hilo: %s : %s\n", Thread.currentThread().getId(), fecha.get());

    }

}
