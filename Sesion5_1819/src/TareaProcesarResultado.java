
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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
public class TareaProcesarResultado implements Runnable {

    private final CompletionService servicio;

    public TareaProcesarResultado(CompletionService servicio) {
        this.servicio = servicio;
    }

    @Override
    public void run() {
        Future f = servicio.poll();
        if (f != null) {
            System.out.println("Procesando resultado...");
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(TareaProcesarResultado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
