/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion12.Ej4;

import Constantes.Constantes.*;


/**
 *
 * @author pedroj
 */
public class MsgCoche {
    private final String name;
    private final Tipo tipoCoche;

    public MsgCoche(String name, Tipo tipoCoche) {
        this.name = name;
        this.tipoCoche = tipoCoche;
    }

    public String getName() {
        return name;
    }

    public Tipo getTipoCoche() {
        return tipoCoche;
    }

    @Override
    public String toString() {
        return "MsgCoche{" + "name=" + name + ", tipoCoche=" + tipoCoche + '}';
    }
}

