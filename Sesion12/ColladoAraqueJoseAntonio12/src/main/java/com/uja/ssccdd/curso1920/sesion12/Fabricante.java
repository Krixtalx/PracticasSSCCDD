package com.uja.ssccdd.curso1920.sesion12;

import static com.uja.ssccdd.curso1920.sesion12.Constantes.BROKER_URL;
import static com.uja.ssccdd.curso1920.sesion12.Constantes.QUEUE;
import com.uja.ssccdd.curso1920.sesion12.Constantes.TipoComponente;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class Fabricante implements Runnable {

    private final String iD;
    private final TipoComponente tipoComponente;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session sesion;
    private Destination destination;

    public Fabricante(String iD, TipoComponente tipoComponente) {
        this.iD = iD;
        this.tipoComponente = tipoComponente;
    }

    @Override
    public void run() {
        System.out.println("HILO-" + iD + " iniciando fabricacion...");

        try {
            abrirConexion();
            Ejecucion();
        } catch (Exception e) {
            System.out.println(iD + " se ha interrumpido");
        } finally {
            cerrarConexion();
            System.out.println("HILO-" + iD + " finalizando fabricacion...");
        }
    }

    private void abrirConexion() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        sesion = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = sesion.createQueue(QUEUE);
    }

    private void Ejecucion() throws JMSException, InterruptedException {
        MessageProducer productor = sesion.createProducer(destination);
        GsonUtil<Componente> gsonUtil = new GsonUtil<>();

        for (int i = 0; i < Constantes.UNIDADES; i++) {
            Componente c = new Componente(iD + "-Componente" + i, tipoComponente);
            TimeUnit.SECONDS.sleep(c.tiempoFabricacion());

            TextMessage mensaje = sesion.createTextMessage(gsonUtil.encode(c, Componente.class));

            productor.send(mensaje);
            System.out.println(iD + " ha enviado un " + tipoComponente.name());
        }

        productor.close();
    }

    private void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException ex) {

        }
    }

}
