package com.uja.ssccdd.sesion12.Ej4;

import static Constantes.Constantes.*;
import static Constantes.Constantes.Servicio.RESPUESTA;
import Constantes.GsonUtil;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class EntradaPuente implements Runnable {

    private final String nameControlador;
    private final String queue;
    private final Puente puente;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public EntradaPuente(String nameControlador, String queue, Puente puente) {
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
        } catch (JMSException ex) {
            // No hacer nada
        }
    }

    public void execution() throws Exception {
        TextMessage msg;

        MessageConsumer consumer = session.createConsumer(destination);
        // Creamos la utilizad para la recepción del objeto Java
        GsonUtil<MsgCoche> gsonUtil = new GsonUtil();

        // Solicitud de entrada al puente
        msg = (TextMessage) consumer.receive();
        MsgCoche coche = gsonUtil.decode(msg.getText(), MsgCoche.class);
        consumer.close();

        // Indica la intención para cruzar el puente
        puente.pendienteEntrada(coche.getTipoCoche());

        // Revuelve el acceso al puente
        puente.entradaPuente(coche.getTipoCoche());

        // Se confirma el acceso al puente
        Destination respuesta = session.createQueue(QUEUE + RESPUESTA.getValor() + coche.getName());
        MessageProducer producer = session.createProducer(respuesta);
        msg = session.createTextMessage(gsonUtil.encode(coche, MsgCoche.class));
        producer.send(msg);
        producer.close();
    }
}