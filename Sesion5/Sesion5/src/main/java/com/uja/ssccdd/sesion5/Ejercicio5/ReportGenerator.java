package com.uja.ssccdd.sesion5.Ejercicio5;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author José Antonio
 */
public class ReportGenerator implements Callable<String> {

    private String nombre, titulo;

    public ReportGenerator(String nombre, String titulo) {
        this.nombre = nombre;
        this.titulo = titulo;
    }

    @Override
    public String call() throws Exception {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.nombre, this.titulo, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ret = nombre + ": " + titulo;
        return ret;

    }

}
