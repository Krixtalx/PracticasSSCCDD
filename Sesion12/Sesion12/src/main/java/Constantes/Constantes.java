package Constantes;
import java.util.Random;

/**
 *
 * @author José Antonio
 */
public interface Constantes {
    public static final Random rnd = new Random();
    
    public static final int NUM_MSG = 4;
    public static final int TIME_SLEEP = 1;
    public static final int TIME_WORK = 2;
    public static final int DICE_100 = 100;
    public static final int PRODUCER = 3;
    public static final int PUBLISHER = 3;
    public static final int CONSUMER = 3;
    
    public static final String QUEUE = "uja.ssccdd.sesion12";
    public static final String TOPIC = "uja.ssccdd.sesion12";
    public static final String BROKER_URL = "tcp://suleiman.ujaen.es:8018";
    
    // Constantes cuarto ejemplo
    public enum Direccion {NORTE, SUR, LIBRE}
    public enum Tipo {NORTE, SUR}
    public static final Tipo[] TIPOS = Tipo.values();
    public static final int MAX_COCHES = 3;
    public static final int COCHES = 20;
    public static final int TIEMPO_LLEGADA = 3;
    public static final int TIEMPO_PUENTE = 4;
    public enum Servicio {
        ENTRADA(".entradaPuente."),
        SALIDA(".salidaPuente."),
        RESPUESTA(".respuesta.");
        
        private final String valor;

        private Servicio(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }
    public static final Servicio[] SERVICIOS = Servicio.values();
}

