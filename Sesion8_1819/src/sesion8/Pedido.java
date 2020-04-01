/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion8;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Pedido implements Constantes {
    private final String idCliente;
    private final Date inicioPedido;
    private final Date finPedido;

    public Pedido(String idCliente, Date inicioPedido, Date finPedido) {
        this.idCliente = idCliente;
        this.inicioPedido = inicioPedido;
        this.finPedido = finPedido;
    }

    @Override
    public String toString() {
        long tiempoServicio;
        
        tiempoServicio = finPedido.getTime() - inicioPedido.getTime();
        tiempoServicio = TimeUnit.SECONDS.convert(tiempoServicio, TimeUnit.MILLISECONDS);
        return "Pedido{" + "idCliente=" + idCliente + " tiempoServicio=" + tiempoServicio + " segundos}";
    }

    
    
}
