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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.printf("Prioridad: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Prioridad: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Prioridad: %s\n", Thread.MAX_PRIORITY);

        Thread[] hilos = new Thread[10];
        Thread.State[] estados = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                hilos[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                hilos[i].setPriority(Thread.MIN_PRIORITY);
            }

            hilos[i].setName("Hilo " + i);
        }

        try {
            FileWriter archivo = new FileWriter(".\\log.txt");
            PrintWriter pw = new PrintWriter(archivo);
            for (int i = 0; i < 10; i++) {
                pw.println("Main: Estado del hilo " + i + ": " + hilos[i].getState());
                estados[i] = hilos[i].getState();
            }
            for (int j = 0; j < 10; j++) {
                hilos[j].start();
            }
            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (hilos[i].getState() != estados[i]) {
                        writeThreadInfo(pw, hilos[i], estados[i]);
                        estados[i] = hilos[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (hilos[i].getState() == State.TERMINATED);
                }
            }
            archivo.close();

        } catch (IOException ex) {
            System.err.println("Error en apertura del fichero");
        }

        Thread tarea = new PrimeGenerator();
        tarea.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        tarea.interrupt();
    }

    
    
    
    
    private static void writeThreadInfo(PrintWriter pw, Thread hilo, Thread.State estado) {
        pw.printf("Main : Id %d - %s\n", hilo.getId(), hilo.getName());
        pw.printf("Main : Prioridad: %d\n", hilo.getPriority());
        pw.printf("Main : Estado antiguo: %s\n", estado);
        pw.printf("Main : Nuevo estado: %s\n", hilo.getState());
        pw.printf("\n *************************************************\n\n");
    }
}
