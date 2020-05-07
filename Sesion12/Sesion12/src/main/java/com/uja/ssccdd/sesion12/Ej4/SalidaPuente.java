/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion12.Ej4;

import static Constantes.Constantes.*;
import Constantes.GsonUtil;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class SalidaPuente implements Runnable {

    private final String nameControlador;
    private final String queue;
    private final Puente puente;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public SalidaPuente(String nameControlador, String queue, Puente puente) {
        this.nameControlador = nameControlador;
        this.queue = queue;
        this.puente = puente;
    }

    @Override
    public void run() {
        System.out.println(nameControlador + " Acceso(" + queue + ") activado...");

        try {
            before();

            while (true) {
                execution();
            }
        } catch (Exception ex) {
            System.out.println(nameControlador + " Acceso(" + queue + ") finalizado..." + "\n" + ex);
        } finally {
            after();
        }
    }

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        destination = session.createQueue(queue);
    }

    public void after() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            // No hacer nada
        }
    }

    public void execution() throws Exception {
        TextMessage msg;

        MessageConsumer consumer = session.createConsumer(destination);
        // Creamos la utilizad para la recepción del objeto Java
        GsonUtil<MsgCoche> gsonUtil = new GsonUtil();

        // Informa de la salida del puente
        msg = (TextMessage) consumer.receive();
        MsgCoche coche = gsonUtil.decode(msg.getText(), MsgCoche.class);
        consumer.close();

        // Se actualiza el estado del puente
        puente.salidaPuente(coche.getTipoCoche());
    }
}
