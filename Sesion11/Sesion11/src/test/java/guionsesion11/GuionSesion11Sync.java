package guionsesion11;

import static guionsesion11.Constantes.CONSUMER;
import static guionsesion11.Constantes.PRODUCER;
import static guionsesion11.Constantes.TIME_SLEEP;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class GuionSesion11Sync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecucion;

        // Ejecución del hilo principal
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");

        // Inicialización de las variables del ejemplo
        ejecucion = Executors.newCachedThreadPool();

        // Se ejecutan los consumidores
        for (int i = 0; i < CONSUMER; i++) {
            SyncConsumer consumidor = new SyncConsumer("Consumidor(" + i + ")");
            ejecucion.execute(consumidor);
        }

        // Se ejecutan los productores
        for (int i = 0; i < PRODUCER; i++) {
            Producer productor = new Producer("Productor(" + i + ")");
            ejecucion.execute(productor);
        }

        // Finalizamos el ejecutor y esperamos a que todas las tareas finalicen
        System.out.println("HILO(Principal) Espera a la finalización de las tareas");
        ejecucion.shutdown();
        ejecucion.awaitTermination(TIME_SLEEP, TimeUnit.DAYS);

        // Finalización del hilo principal
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }
}
