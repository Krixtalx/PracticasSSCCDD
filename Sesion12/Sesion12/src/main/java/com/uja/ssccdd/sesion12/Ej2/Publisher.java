package com.uja.ssccdd.sesion12.Ej2;

import static Constantes.Constantes.*;
import Constantes.GsonUtil;
import Constantes.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author José Antonio
 */
public class Publisher implements Runnable {
    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public Publisher(String iD) {
        this.iD = iD;
    }
    
    @Override
    public void run() {
        System.out.println("HILO-" + getiD() + " Starting example Q Producer now...");
        
        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD() + 
                               " Caught an exception during the example: " + e.getMessage());
        } finally {
            after();
            System.out.println("HILO-" + getiD() + " Finished running the sample Q Producer");
        }
    }
    
    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(TOPIC);
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
        MessageProducer producer = session.createProducer(destination);
        // Creamos la instancia del objeto para enviar el mensaje
        GsonUtil<Resource> gsonUtil = new GsonUtil();

        for (int i = 0; i < NUM_MSG; ++i) {
            Resource resource = new Resource(iD+"-"+i,"uja_resource");
            TextMessage message = session.createTextMessage();
            message.setText(gsonUtil.encode(resource,Resource.class));
            producer.send(message);
            System.out.println(iD + " sent Job("+i+") " + resource);
        }

        producer.close();
    }

    public String getiD() {
        return iD;
    }    
}

