package com.uja.ssccdd.curso1920.sesion12;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class ComponenteListener implements MessageListener {

    private String nombreProveedor;
    private ArrayList<Ordenador> listaPedidos;

    public ComponenteListener(String nombreProveedor, ArrayList<Ordenador> listaPedidos) {
        this.nombreProveedor = nombreProveedor;
        this.listaPedidos = listaPedidos;
    }

    @Override
    public void onMessage(Message msg) {
        GsonUtil<Componente> gsonUtil = new GsonUtil<>();
        try {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;
                Componente c = gsonUtil.decode(textMessage.getText(), Componente.class);
                System.out.println(nombreProveedor + " ha recibido " + c);
                boolean encontrado = false;
                for (int i = 0; i < listaPedidos.size() && !encontrado; i++) {
                    Constantes.TipoComponente[] componentes = listaPedidos.get(i).getComponentes();
                    if (componentes[c.getComponente().ordinal()] == null) {
                        listaPedidos.get(i).addComponente(c.getComponente());
                        if (listaPedidos.get(i).ordenadorCompleto()) {
                            System.out.println(listaPedidos.get(i).getiD() + " completo, realizando montaje...");
                            try {
                                TimeUnit.SECONDS.sleep(listaPedidos.get(i).tiempoMontaje());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ComponenteListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    Ordenador nuevoOrdenador = new Ordenador();
                    nuevoOrdenador.setiD(nombreProveedor + "-Ordenador" + listaPedidos.size());
                    nuevoOrdenador.addComponente(c.getComponente());
                    listaPedidos.add(nuevoOrdenador);
                }
            } else {
                System.out.println("Mensaje desconocido");
            }
        } catch (JMSException e) {
        }
    }
}
