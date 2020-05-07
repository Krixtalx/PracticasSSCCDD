package com.uja.ssccdd.curso1920.sesion12;

import static com.uja.ssccdd.curso1920.sesion12.Constantes.BROKER_URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Proveedor implements Runnable {

    private final String iD;
    private final ArrayList<Ordenador> listaOrdenadores;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session sesion;
    private Destination Destino;

    public Proveedor(String iD) {
        this.iD = iD;
        listaOrdenadores = new ArrayList<>();
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
        Destino = sesion.createQueue(Constantes.QUEUE);

    }

    private void Ejecucion() throws JMSException, InterruptedException {
        MessageConsumer consumidor = sesion.createConsumer(Destino);

        consumidor.setMessageListener(new ComponenteListener(iD, listaOrdenadores));

        TimeUnit.MINUTES.sleep(Constantes.TIEMPO_PROVEEDOR);

        consumidor.close();

    }

    private void cerrarConexion() {
        try {
            connection.stop();

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
