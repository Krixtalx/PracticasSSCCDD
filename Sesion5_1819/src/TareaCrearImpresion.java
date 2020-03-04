
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedroj
 */
public class TareaCrearImpresion implements Runnable {

    private final CompletionService servicio;
    private final List<Future<?>> lista;

    public TareaCrearImpresion(CompletionService servicio, List<Future<?>> lista) {
        this.servicio = servicio;
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("Añadiendo nueva tarea...");
        TareaImpresion tarea = new TareaImpresion("Tarea", new TrabajoImpresion(new Date()));
        lista.add(servicio.submit(tarea));
    }
}
