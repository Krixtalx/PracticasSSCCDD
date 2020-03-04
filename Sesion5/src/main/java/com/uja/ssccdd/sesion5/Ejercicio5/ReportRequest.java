/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio5;

import java.util.concurrent.CompletionService;

/**
 *
 * @author José Antonio
 */
public class ReportRequest implements Runnable {

    private final String nombre;
    private final CompletionService servicio;

    public ReportRequest(String nombre, CompletionService servicio) {
        this.nombre = nombre;
        this.servicio = servicio;
    }

    @Override
    public void run() {
        servicio.submit(new ReportGenerator(nombre, "Report"));
    }

}
