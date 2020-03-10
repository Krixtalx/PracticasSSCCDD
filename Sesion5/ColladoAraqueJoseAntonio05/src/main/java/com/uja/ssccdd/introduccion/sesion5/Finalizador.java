/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.introduccion.sesion5;

import java.util.List;

/**
 * Tarea encargada de avisar a los Renderiadores de que los Generadores ya han
 * terminado.
 *
 * @author fconde
 */
public class Finalizador implements Runnable {

    private List<RenderizadorEscenas> lista;

    public Finalizador(List<RenderizadorEscenas> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("Finalizador ejecutandose...");
        lista.forEach((renderizadorEscenas) -> {
            renderizadorEscenas.setGen(true);
        });
        System.out.println("Finalizador ejecutado");
    }
}
