package com.uja.ssccdd.sesion3.Ejercicio2;

/**
 *
 * @author José Antonio
 */
public class MainReaderWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PricesInfo info = new PricesInfo();
        Reader[] lectores = new Reader[5];
        Writer escritor = new Writer(info);
        Thread[] hilos = new Thread[6];
        hilos[0] = new Thread(escritor);

        for (int i = 1; i < 6; i++) {
            lectores[i - 1] = new Reader(info);
            hilos[i] = new Thread(lectores[i - 1]);
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }
    }

}
