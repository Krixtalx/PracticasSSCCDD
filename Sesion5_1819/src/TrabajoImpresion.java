/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class TrabajoImpresion {
    private final Date fechaCreacion;
    private final int tiempoImpresion;
    
    // Constantes
    private final int MIN_TIEMPO = 2;
    private final int VARIACION_TIEMPO = 5;

    public TrabajoImpresion(Date fecha) {
        Random generador = new Random();
        
        this.fechaCreacion = fecha;
        this.tiempoImpresion = MIN_TIEMPO + generador.nextInt(VARIACION_TIEMPO);
    }

    public Date getFecha() {
        return fechaCreacion;
    }
    
    /**
     * Simula el tiempo que tarda la impresión
     * @return la fechaCreacion de la finalización de la impresión
     * @throws InterruptedException 
     */
    public Date impresion() throws InterruptedException {
        
        TimeUnit.SECONDS.sleep(tiempoImpresion);
        
        return new Date();
    }

    @Override
    public String toString() {
        return "Impresion{" + "fechaCreacion=" + fechaCreacion + ", tiempoImpresion=" + tiempoImpresion + '}';
    }
}
