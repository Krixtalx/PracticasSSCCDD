
package guionsesion11;

import java.util.Random;

/**
 *
 * @author pedroj
 */
public interface Constantes {
    public static final Random rnd = new Random();
    
    public static final int NUM_MSG = 4;
    public static final int TIME_SLEEP = 1;
    public static final int TIME_WORK = 2;
    public static final int DICE_100 = 100;
    public static final int PRODUCER = 3;
    public static final int CONSUMER = 3;
    
    public static final String QUEUE = "uja.ssccdd.connection";
    public static final String BROKER_URL = "tcp://suleiman.ujaen.es:8018";
}
