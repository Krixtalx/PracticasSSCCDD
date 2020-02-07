package com.uja.ssccdd.introduccion.sesion1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class GestorImpresion {

    private final Impresora[] impresoras;
    private final ArrayList<TrabajoImpresion> trabajos;
    Random generador;

    public GestorImpresion() {
        this.impresoras = null;
        this.trabajos = null;
        generador = new Random();
    }

    public GestorImpresion(Impresora[] impresoras, ArrayList<TrabajoImpresion> trabajos) {
        this.impresoras = impresoras;
        this.trabajos = trabajos;
        generador = new Random();
    }

    /**
     * Nos añade un nuevo trabajo de impresión a la lista de trabajos pendientes
     *
     * @param aux TrabajoImpresion que se introducirá en la cola
     */
    public void agregarTrabajo(TrabajoImpresion aux) {
        trabajos.add(aux);
        System.out.println("Se ha añadido el trabajo " + aux + " para su impresion");
    }

    /**
     * Realiza el primer trabajo pendiente de la lista
     *
     * @throws InterruptedException
     */
    public void realizarTrabajo() throws InterruptedException {
        int idImpresora;
        TrabajoImpresion siguienteTrabajo;

        idImpresora = seleccionarImpresora();
        siguienteTrabajo = trabajos.remove(0);
        TimeUnit.SECONDS.sleep(siguienteTrabajo.getTiempoTrabajo());

        System.out.println("Se ha realizado el trabajo: " + siguienteTrabajo + " en la impresora: " + impresoras[idImpresora]);
    }

    /**
     * Nos devuelve si hay trabajos pendientes en el gestor de impresión
     *
     * @return true si hay trabajos pendientes, false en otro caso
     */
    public boolean hayTrabajos() {
        return !trabajos.isEmpty();
    }

    /**
     * Selecciona la impresora de manera aleatoria
     *
     * @return id de la impresora seleccionada
     */
    public int seleccionarImpresora() {
        return generador.nextInt(impresoras.length);
    }

}
