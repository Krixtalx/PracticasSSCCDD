package sesion9;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author pedroj
 */
public class TareaCrearComponente implements Runnable {

    AtomicIntegerArray array;

    public TareaCrearComponente(AtomicIntegerArray array) {
        this.array = array;
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        System.out.println("Se inicia la ejecución de TareaCrearComponente");
        boolean fin = false;
        while (!fin) {
            try {
                TimeUnit.SECONDS.sleep(1 + ThreadLocalRandom.current().nextInt(2));
            } catch (InterruptedException ex) {
                System.out.println("TareaCrearComponente interrumpida");
                fin = true;
            }
            int comp = ThreadLocalRandom.current().nextInt(3);
            array.getAndIncrement(comp);
            System.out.println("Se ha creado un componente " + comp);
        }
    }
}
