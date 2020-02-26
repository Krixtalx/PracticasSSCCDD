/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Sesion4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Ejecucion iniciada");
        ExecutorService servicio = Executors.newCachedThreadPool();
        List<TareaSalaCine> lista = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            lista.add(new TareaSalaCine("Sala de cine " + i));
            System.out.println("Sala de cine "+i +" añadida");
        }
        try {
            List<Future<Resultado>> lista2;
            lista2 = servicio.invokeAll(lista);
            servicio.shutdown();
            servicio.awaitTermination(10, TimeUnit.DAYS);
            lista2.forEach((resultado) -> {
                try {
                    System.out.println("Resultado: " + resultado.get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(Sesion4.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Sesion4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
