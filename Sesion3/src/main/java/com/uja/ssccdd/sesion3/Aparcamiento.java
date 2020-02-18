package com.uja.ssccdd.sesion3;


import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author José Antonio
 */
public class Aparcamiento {

    private final Lock cola = new ReentrantLock();
    private final ArrayList coches = new ArrayList();
    private final int capacidad;

    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean entrada(Coche car) {
        cola.lock();
        try {
            if (coches.size() < capacidad) {
                coches.add(car);
                return true;
            } else {
                return false;
            }
        } finally {
            cola.unlock();
        }
    }

    public void salida(Coche car) throws Exception {
        cola.lock();
        try {
            if (coches.contains(car)) {
                coches.remove(car);
            } else {
                throw new Exception("El array no contiene al coche");
            }
        } finally {
            cola.unlock();
        }
    }

}
