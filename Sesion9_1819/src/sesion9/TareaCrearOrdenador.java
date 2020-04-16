package sesion9;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedroj
 */
public class TareaCrearOrdenador implements Callable<String> {

    AtomicIntegerArray array;
    AtomicInteger mutex;

    public TareaCrearOrdenador(AtomicIntegerArray array, AtomicInteger mutex) {
        this.array = array;
        this.mutex = mutex;
        ThreadLocalRandom.current();
    }

    @Override
    public String call() throws Exception {

        System.out.println("Se inicia la ejecución de TareaCrearOrdenador");
        boolean fin = false;
        for (int i = 0; i < 10 && !fin; i++) {

            while (mutex.get() <= 0) {
            }
            mutex.decrementAndGet();

            for (int j = 0; j < 3; j++) {
                while (array.get(j) <= 0) {
                    //System.out.println("Estoy bloqueado en " + j);
                }
                array.getAndDecrement(j);
            }

            mutex.incrementAndGet();

            try {
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
            } catch (InterruptedException ex) {
                fin = true;
            }
            System.out.println("Se ha creado el ordenador " + i);

        }
        System.out.println("Finaliza la ejecución de TareaCrearOrdenador");
        return "Tarea completada";
    }
}
