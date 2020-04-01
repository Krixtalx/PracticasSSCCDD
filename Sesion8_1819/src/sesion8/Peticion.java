/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion8;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Peticion implements Constantes, Delayed, Runnable {
    private final Prioridad prioridad;
    private final String idCliente;
    private final ConcurrentLinkedDeque<Pedido> listaPedidos;
    private final Date inicioPeticion;
    private final Date retardoPeticion;

    public Peticion(Prioridad prioridad, String idCliente, ConcurrentLinkedDeque<Pedido> listaPedidos, 
                                            Date inicioPeticion, Date retardo) {
        this.prioridad = prioridad;
        this.idCliente = idCliente;
        this.listaPedidos = listaPedidos;
        this.inicioPeticion = inicioPeticion;
        this.retardoPeticion = retardo;
    }
    
    /**
     * Constructor exclusivo para la búsqueda de ocurrencias de peticiones
     * @param idCliente 
     */
    public Peticion(String idCliente) {
        this.prioridad = null;
        this.idCliente = idCliente;
        this.listaPedidos = null;
        this.inicioPeticion = null;
        this.retardoPeticion = null;
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        Date actual = new Date();
        long diferencia = retardoPeticion.getTime() - actual.getTime();
        return unit.convert(diferencia, unit);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
            return -1;
        }else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void run() {
        Date inicio = new Date();
        try {
            TimeUnit.SECONDS.sleep(TIEMPO_MINIMO+generador.nextInt(VARIACION_TIEMPO));
        } catch (InterruptedException ex) {
            System.out.println(idCliente + "Ha sido interrumpido");
        }
        Date fin = new Date();
        listaPedidos.add(new Pedido(idCliente, inicio, fin));
        
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean resultado = false;
        
        if (obj instanceof Peticion)
            resultado = this.idCliente.equals(((Peticion)obj).getIdCliente());
        
        return resultado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "PETICION(" + idCliente + "," + prioridad + ")";
    }  
}
