package sesion9;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Sesion9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Se inicia la ejecución del Main");
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicIntegerArray array = new AtomicIntegerArray(3);
        AtomicInteger mutex = new AtomicInteger(1);
        ArrayList<Future<?>> tareas = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            tareas.add(executor.submit(new TareaCrearComponente(array)));
        }

        ArrayList<TareaCrearOrdenador> listaTareasOrdenador = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            listaTareasOrdenador.add(new TareaCrearOrdenador(array, mutex));
        }

        try {
            executor.invokeAny(listaTareasOrdenador);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Sesion9.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Se interrumpen las tareas");
        for (int i = 0; i < 8; i++) {
            tareas.get(i).cancel(true);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finaliza ejecución del Main");

    }

}
