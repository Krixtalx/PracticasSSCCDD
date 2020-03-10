/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio5;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Antonio
 */
public class ReportProcessor implements Runnable {

    private final CompletionService servicio;
    private boolean finalizado;

    public ReportProcessor(CompletionService servicio) {
        this.servicio = servicio;
        this.finalizado = false;
    }

    @Override
    public void run() {
        while (!finalizado) {
            try {
                Future<String> resultado = servicio.poll(20, TimeUnit.SECONDS);
                if (resultado != null) {
                    System.out.println("ReportReceiver: Report Recived: " + resultado.get());
                }

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(ReportProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("ReportSender: END");
    }

    public void setEnd(boolean bool) {
        finalizado = bool;
    }
}
