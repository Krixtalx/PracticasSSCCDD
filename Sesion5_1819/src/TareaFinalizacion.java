
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedroj
 */
public class TareaFinalizacion implements Runnable {
    private final CountDownLatch bloqueo;
    private final List<Future<?>> lista;

    public TareaFinalizacion(CountDownLatch bloqueo, List<Future<?>> lista) {
        this.bloqueo = bloqueo;
        this.lista = lista;
    }


    
    @Override
    public void run() {
        System.out.println("Finalizador iniciado");
        lista.forEach((t) -> {
            t.cancel(true);
        });
        System.out.println("Finalizado acabando...");
        bloqueo.countDown();
    }
    
}
