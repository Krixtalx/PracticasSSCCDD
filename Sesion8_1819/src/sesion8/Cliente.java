package sesion8;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedroj
 */
public class Cliente implements Constantes, Runnable {

    private final String idCliente;
    private final DelayQueue<Peticion> listaPeticiones;
    private final ConcurrentLinkedDeque<Pedido> listaPedidos;

    public Cliente(String idCliente, DelayQueue<Peticion> listaPeticiones) {
        this.idCliente = idCliente;
        this.listaPeticiones = listaPeticiones;
        this.listaPedidos = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void run() {
        try {
            int num = MINIMO_PETICIONES + generador.nextInt(VARIACION_PETICIONES);
            addPeticiones(num);
            procesarPedidos(num);
        } catch (InterruptedException ex) {
            System.out.println(idCliente + " ha sido interrumpido");
        }
    }

    private void addPeticiones(int num) {
        for (int i = 0; i < num; i++) {
            Constantes.Prioridad prioridad = generarPrioridad();
            Date inicio = new Date();
            Date retardo = new Date();
            int tiempoRetardo = TIEMPO_MINIMO + generador.nextInt(VARIACION_TIEMPO);
            tiempoRetardo = tiempoRetardo * prioridad.getPenalizacion();
            retardo.setTime(inicio.getTime() + TimeUnit.MILLISECONDS.convert(tiempoRetardo, TimeUnit.SECONDS));
            listaPeticiones.add(new Peticion(prioridad, "Peticion " + idCliente + "-" + i, listaPedidos, inicio, retardo));

        }
    }

    private void procesarPedidos(int totalPeticiones) throws InterruptedException {
        Pedido pedido;
        int tiempoProcesado;

        for (int i = 0; i < totalPeticiones; i++) {
            // Comprobamos que no hay que finalizar
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            // Simulamos el tiempo de procesamiento
            tiempoProcesado = TIEMPO_MINIMO + generador.nextInt(VARIACION_TIEMPO);
            TimeUnit.SECONDS.sleep(tiempoProcesado);

            try {
                pedido = listaPedidos.removeFirst();
                System.out.println("HILO(" + idCliente + ") " + pedido);
            } catch (NoSuchElementException ex) {
                // Eliminamos la primera ocurrencia de las peticiones del cliente
                Peticion peticion = new Peticion(idCliente);
                if (listaPeticiones.remove(peticion)) {
                    System.out.println("HILO(" + idCliente + ") elimina una petición");
                }
            }
        }
    }

    public Constantes.Prioridad generarPrioridad() {
        int num = generador.nextInt(100);
        if (num <= 15) {
            return Constantes.Prioridad.BAJA;
        } else if (num >= 90) {
            return Constantes.Prioridad.ALTA;
        } else {
            return Constantes.Prioridad.NORMAL;
        }
    }

}
