/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion12.Ej1;


import static Constantes.Constantes.*;
import Constantes.GsonUtil;
import Constantes.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author José Antonio
 */
public class Producer implements Runnable {

    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public Producer(String iD) {
        this.iD = iD;
    }

    @Override
    public void run() {
        System.out.println("HILO-" + getiD() + " Starting example Q Producer now...");

        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD()
                    + " Caught an exception during the example: " + e.getMessage());
        } finally {
            after();
            System.out.println("HILO-" + getiD() + " Finished running the sample Q Producer");
        }
    }

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(QUEUE);
        connection.start();
    }

    public void after() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException ex) {
            // No hacer nada
        }
    }

    public void execution() throws Exception {
        MessageProducer producer = session.createProducer(destination);
        // Creamos la instancia del objeto para enviar el mensaje
        GsonUtil<Resource> gsonUtil = new GsonUtil();

        for (int i = 0; i < NUM_MSG; ++i) {
            Resource resource = new Resource(iD + "-" + i, "uja_resource");
            TextMessage message = session.createTextMessage();
            message.setText(gsonUtil.encode(resource, Resource.class));
            producer.send(message);
            System.out.println(iD + " sent Job(" + i + ") " + resource);
        }

        producer.close();
    }

    public String getiD() {
        return iD;
    }
}
