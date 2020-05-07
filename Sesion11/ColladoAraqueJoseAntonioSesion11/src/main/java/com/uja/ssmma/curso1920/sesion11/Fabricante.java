package com.uja.ssmma.curso1920.sesion11;

import static com.uja.ssmma.curso1920.sesion11.Constantes.BROKER_URL;
import static com.uja.ssmma.curso1920.sesion11.Constantes.QUEUE;
import com.uja.ssmma.curso1920.sesion11.Constantes.TipoComponente;
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
        destination = sesion.createQueue(QUEUE + tipoComponente.name());
    }

    private void Ejecucion() throws JMSException, InterruptedException {
        MessageProducer productor = sesion.createProducer(destination);

        for (int i = 0; i < Constantes.UNIDADES; i++) {
            TimeUnit.SECONDS.sleep(tipoComponente.tiempoFabricacion());
            TextMessage mensaje = sesion.createTextMessage(tipoComponente.toString());
            productor.send(mensaje);
            System.out.println("Se ha enviado un " + mensaje.getText());
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
