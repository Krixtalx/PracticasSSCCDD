package com.uja.ssccdd.sesion12.Ej4;

import static Constantes.Constantes.*;
import static Constantes.Constantes.Servicio.ENTRADA;
import static Constantes.Constantes.Servicio.RESPUESTA;
import static Constantes.Constantes.Servicio.SALIDA;
import Constantes.GsonUtil;
import java.util.concurrent.TimeUnit;
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
 * @author José Antonio
 */
public class TareaCoche implements Runnable {

    private final String name;
    private final Tipo tipoCoche;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination[] destination;

    public TareaCoche(String name, Tipo tipoCoche) {
        this.name = name;
        this.tipoCoche = tipoCoche;
        this.destination = new Destination[SERVICIOS.length];
    }

    @Override
    public void run() {
        System.out.println(name + " Comienza su ejecución...");

        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println(name + " Problemas en la ejecución: " + e.getMessage());
        } finally {
            after();
            System.out.println(name + " Finaliza su ejecución");
        }
    }

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        for (Servicio servicio : SERVICIOS) {
            if (!servicio.equals(RESPUESTA)) {
                destination[servicio.ordinal()] = session.createQueue(QUEUE + servicio.getValor() + tipoCoche);
            } else {
                destination[servicio.ordinal()] = session.createQueue(QUEUE + servicio.getValor() + name);
            }
        }

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
        MessageProducer producer;
        MessageConsumer consumer;
        MsgCoche contenido;
        TextMessage msg;

        // Creamos la instancia del objeto para enviar el mensaje
        GsonUtil<MsgCoche> gsonUtil = new GsonUtil();
        contenido = new MsgCoche(name, tipoCoche);

        // Simulamos tiempo para llegar al puente
        TimeUnit.SECONDS.sleep(rnd.nextInt(TIEMPO_LLEGADA) + 1);

        // Acceso al Puente
        producer = session.createProducer(destination[ENTRADA.ordinal()]);
        msg = session.createTextMessage(gsonUtil.encode(contenido, MsgCoche.class));
        producer.send(msg);
        producer.close();
        System.out.println(name + " Espera acceso al puente " + contenido);

        // Espera respuesta para el acceso
        consumer = session.createConsumer(destination[RESPUESTA.ordinal()]);
        msg = (TextMessage) consumer.receive();
        contenido = gsonUtil.decode(msg.getText(), MsgCoche.class);
        System.out.println(name + " Circula por el puente " + contenido);

        // Simulamos el tiempo de paso del puente
        TimeUnit.SECONDS.sleep(rnd.nextInt(TIEMPO_PUENTE) + 1);

        // Abandona el puente
        producer = session.createProducer(destination[SALIDA.ordinal()]);
        msg = session.createTextMessage(gsonUtil.encode(contenido, MsgCoche.class));
        producer.send(msg);
        producer.close();
        System.out.println(name + " Abandona el puente " + contenido);
    }
}