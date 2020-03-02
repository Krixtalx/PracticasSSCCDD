/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class TareaVentaEntradas implements Callable<String> {

    private final String nombre;
    private int entradas;
    private final int precio;
    private int recaudacion = 0;

    public TareaVentaEntradas(String nombre, int entradas, int precio) {
        this.nombre = nombre;
        this.entradas = entradas;
        this.precio = precio;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Inicio de ejecucion de "+nombre);
        try {
            while (entradas > 0) {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 2 + 2));
                entradas--;
                recaudacion += precio;
                System.out.println(nombre + ": "+entradas +" entradas restantes con "+ recaudacion + " $ recaudados");
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrupcion en " + nombre);
        } finally {
            return nombre +";"+ recaudacion;
        }
    }

}
