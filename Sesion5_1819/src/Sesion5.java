
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedroj
 */
public class Sesion5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CountDownLatch bloqueo = new CountDownLatch(1);
        List<Future<?>> lista = new ArrayList<>();
        ScheduledExecutorService ejecutorPrincipal = new ScheduledThreadPoolExecutor(3);
        ExecutorService ejecutorCrea = (ExecutorService) Executors.newFixedThreadPool(3);
        CompletionService servicio = new ExecutorCompletionService(ejecutorCrea);

        System.out.println("Hilo principal iniciado...");

        TareaCrearImpresion crearImpresion = new TareaCrearImpresion(servicio, lista);
        lista.add((Future<?>) ejecutorPrincipal.scheduleAtFixedRate(crearImpresion, 0, 2, TimeUnit.SECONDS));

        TareaProcesarResultado procesarResultado = new TareaProcesarResultado(servicio);
        lista.add((Future<?>) ejecutorPrincipal.scheduleAtFixedRate(procesarResultado, 4, 4, TimeUnit.SECONDS));

        TareaFinalizacion finalizador = new TareaFinalizacion(bloqueo, lista);
        ejecutorPrincipal.schedule(finalizador, 15, TimeUnit.SECONDS);
        System.out.println("Hilo principal esperando finalizacion...");
        try {
            bloqueo.await();
            ejecutorCrea.shutdown();
            ejecutorPrincipal.shutdown();

            System.out.println("Hilo principal finalizado.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
