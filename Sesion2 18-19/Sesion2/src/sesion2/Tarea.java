package sesion2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Tarea implements Runnable {

    float[] array;
    int limiteInferior, limiteSuperior;
    float suma = 0;
    Random generador = new Random();

    public Tarea(float[] array, int limiteInferior, int limiteSuperior) {
        this.array = array;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    @Override
    public void run() {
        System.out.println("Ha iniciado la ejecución del hilo: " + Thread.currentThread().getName());
        try {
            for (int i = limiteInferior; i < limiteSuperior; i++) {
                array[i] = 2*generador.nextFloat();
                suma = suma + array[i];
                if (Thread.currentThread().isInterrupted() && suma >= limiteSuperior - limiteInferior) {
                    throw new InterruptedException();
                }
                TimeUnit.SECONDS.sleep((long) array[i]);
            }
            
            System.out.println("Ha finalizado la ejecución del hilo: " + Thread.currentThread().getName());
            System.out.println("El resultado de la suma es " + suma());

        } catch (InterruptedException ex) {
            System.out.println("Ha finalizado la ejecución del hilo: " + Thread.currentThread().getName() + " a causa de una interrupción");
            System.out.println("El resultado de la suma es " + suma());
        }
    }

    private float suma() {
        float sumatorio = 0;
        for (int i = limiteInferior; i < limiteSuperior; i++) {
            sumatorio = sumatorio + array[i];
        }
        return sumatorio;
    }

}
