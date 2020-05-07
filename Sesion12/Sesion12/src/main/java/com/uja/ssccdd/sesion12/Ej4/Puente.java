package com.uja.ssccdd.sesion12.Ej4;

import Constantes.Constantes.*;
import static Constantes.Constantes.MAX_COCHES;
import static Constantes.Constantes.TIPOS;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pedroj
 */
public class Puente {

    private final int[] cochesEnPuente;
    private final int[] cochesSeguidos;
    private final int[] pendienteEntrada;
    private Direccion ocupado;
    private final Lock exm;
    private final Condition[] acceso;

    // Constantes
    private static final int NINGUNO = 0;
    private static final int PRIMERO = 1;

    public Puente() {
        cochesEnPuente = new int[TIPOS.length];
        cochesSeguidos = new int[TIPOS.length];
        pendienteEntrada = new int[TIPOS.length];
        acceso = new Condition[TIPOS.length];
        exm = new ReentrantLock();
        for (int i = 0; i < TIPOS.length; i++) {
            cochesEnPuente[i] = NINGUNO;
            cochesSeguidos[i] = NINGUNO;
            pendienteEntrada[i] = NINGUNO;
            acceso[i] = exm.newCondition();
        }

        ocupado = Direccion.LIBRE;
    }

    private boolean entradaPermitida(Tipo tipoCoche) {
        // Conseguimos el sentido de circulación del coche
        Direccion sentido = Direccion.valueOf(tipoCoche.name());

        return ocupado.equals(sentido) || ocupado.equals(Direccion.LIBRE);
    }

    private Tipo sentidoOpuesto(Tipo tipoCoche) {
        if (tipoCoche.equals(Tipo.NORTE)) {
            return Tipo.SUR;
        }

        return Tipo.NORTE;
    }

    /**
     * Se registran las peticiones pendientes para el acceso al puente
     *
     * @param tipoCoche Sentido que lleva el coche para cruzar el puente
     */
    public void pendienteEntrada(Tipo tipoCoche) {
        exm.lock();

        try {
            pendienteEntrada[tipoCoche.ordinal()]++;
        } finally {
            exm.unlock();
        }
    }

    public void entradaPuente(Tipo tipoCoche) throws InterruptedException {
        exm.lock();

        try {
            // Comprobamos el sentido de tráfico y acceso máximo
            while (!entradaPermitida(tipoCoche) || (cochesSeguidos[tipoCoche.ordinal()] == MAX_COCHES)) {
                acceso[tipoCoche.ordinal()].await();
            }

            cochesEnPuente[tipoCoche.ordinal()]++;
            cochesSeguidos[tipoCoche.ordinal()]++;
            pendienteEntrada[tipoCoche.ordinal()]--;
            if (cochesEnPuente[tipoCoche.ordinal()] == PRIMERO) {
                ocupado = Direccion.valueOf(tipoCoche.name());
            }

            acceso[tipoCoche.ordinal()].signal();
        } finally {
            exm.unlock();
        }
    }

    public void salidaPuente(Tipo tipoCoche) {
        exm.lock();

        try {
            cochesEnPuente[tipoCoche.ordinal()]--;
            if (cochesEnPuente[tipoCoche.ordinal()] == NINGUNO) {
                ocupado = Direccion.LIBRE;
                Tipo opuesto = sentidoOpuesto(tipoCoche);
                if (pendienteEntrada[opuesto.ordinal()] == NINGUNO) {
                    for (Tipo tipo : TIPOS) {
                        cochesSeguidos[tipo.ordinal()] = NINGUNO;
                    }
                    acceso[tipoCoche.ordinal()].signal();
                } else {
                    cochesSeguidos[opuesto.ordinal()] = NINGUNO;
                    acceso[opuesto.ordinal()].signal();
                }
            }
        } finally {
            exm.unlock();
        }
    }
}
