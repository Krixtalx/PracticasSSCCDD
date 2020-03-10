/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class Producer implements Runnable {

    private final Buffer buffer;
    private final FileMock archivo;

    public Producer(Buffer buffer, FileMock archivo) {
        this.buffer = buffer;
        this.archivo = archivo;
    }

    @Override
    public void run() {
        buffer.setLineasPendientes(true);
        while (archivo.hasMoreLines()) {
            try {
                buffer.insert(archivo.getLine());
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        buffer.setLineasPendientes(false);
    }
}
