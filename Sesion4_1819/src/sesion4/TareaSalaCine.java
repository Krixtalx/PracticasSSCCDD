/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion4;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class TareaSalaCine implements Callable<Resultado> {

    ExecutorService executor;
    String nombre;

    public TareaSalaCine(String nombre) {
        this.executor = Executors.newFixedThreadPool((int) (4 + Math.random() * 2));
        this.nombre = nombre;
    }

    @Override
    public Resultado call() throws Exception {
        System.out.println("Inicio de ejecucion de " + nombre);
        int nTareas = (int) (Math.random() * 5 + 5);
        List<TareaVentaEntradas> lista = new LinkedList<>();
        for (int i = 0; i < nTareas; i++) {
            lista.add(new TareaVentaEntradas(nombre + " - TareaVentaEntradas - " + i, 5, 7));
        }
        String resultado = executor.invokeAny(lista);
        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.DAYS);
        
        String[] elementosResultado = resultado.split(";");
        String nombreTarea = elementosResultado[0];
        System.out.println(elementosResultado[1]);
        int importeRecaudado = Integer.parseInt(elementosResultado[1]);
        return new Resultado(nombreTarea, importeRecaudado);
    }

}
