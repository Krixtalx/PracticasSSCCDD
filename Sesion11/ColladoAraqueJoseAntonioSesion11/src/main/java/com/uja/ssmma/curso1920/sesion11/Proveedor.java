package com.uja.ssmma.curso1920.sesion11;

import static com.uja.ssmma.curso1920.sesion11.Constantes.BROKER_URL;
import static com.uja.ssmma.curso1920.sesion11.Constantes.QUEUE;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class Proveedor implements Runnable {

    private final String iD;
    private final ArrayList<Ordenador> listaOrdenadores;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session sesion;
    private ArrayList<Destination> listaDestinos;
    private ArrayList<MessageConsumer> listaConsumers;

    public Proveedor(String iD) {
        this.iD = iD;
        listaOrdenadores = new ArrayList<>();
        listaDestinos = new ArrayList<>();
        listaConsumers = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("HILO-" + iD + " iniciando produccion...");

        try {
            abrirConexion();
            Ejecucion();
        } catch (Exception e) {
            System.out.println(iD + " se ha interrumpido");
        } finally {
            cerrarConexion();
            System.out.println("HILO-" + iD + " finalizando produccion...");
        }
    }

    private void abrirConexion() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        sesion = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        for (Constantes.TipoComponente COMPONENTES : Constantes.COMPONENTES) {
            listaDestinos.add(sesion.createQueue(QUEUE + COMPONENTES.name()));
        }
        for (Destination listaDestino : listaDestinos) {
            listaConsumers.add(sesion.createConsumer(listaDestino));
        }
    }

    private void Ejecucion() throws JMSException, InterruptedException {
        int i = 0;

        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            Ordenador ordenador = new Ordenador();
            ordenador.setiD(iD + "-Ordenador" + i);
            i++;
            listaOrdenadores.add(ordenador);
            TextMessage mensaje;
            for (MessageConsumer listaConsumer : listaConsumers) {
                mensaje = (TextMessage) listaConsumer.receive();
                ordenador.addComponente(Constantes.TipoComponente.valueOf(mensaje.getText()));
                System.out.println("Se ha añadido un " + mensaje.getText() + " a " + ordenador.getiD());
            }
            TimeUnit.SECONDS.sleep(ordenador.tiempoMontaje());
        }

    }

    private void cerrarConexion() {
        try {
            connection.stop();
            
            for (MessageConsumer listaConsumer : listaConsumers) {
                listaConsumer.close();
            }
            if (connection != null) {
                connection.close();
            }
            
        } catch (JMSException ex) {

        }

        for (Ordenador ordenador : listaOrdenadores) {
            System.out.println(ordenador);
        }
    }
}
