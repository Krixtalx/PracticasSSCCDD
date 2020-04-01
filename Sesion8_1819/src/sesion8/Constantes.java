/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion8;

import java.util.Random;

/**
 *
 * @author pedroj
 */
public interface Constantes {
    public enum Prioridad {
        ALTA(1), NORMAL(2), BAJA(3);
        
        private final int penalizacion;

        private Prioridad(int penalizacion) {
            this.penalizacion = penalizacion;
        }

        public int getPenalizacion() {
            return penalizacion;
        }
    }
    
    // Generador aleatorio
    public final static Random generador = new Random();
    
    
    // Constantes
    public final static int MENOR = -1;
    public final static int IGUAL = 0;
    public final static int MAYOR = 1;
    public final static int GENERAR_PRIORIDAD = 100;
    public final static int PRIORIDAD_ALTA = 10;
    public final static int PRIORIDAD_BAJA = 25;
    public final static int TIEMPO_MINIMO = 1;
    public final static int VARIACION_TIEMPO = 2;
    public final static int MINIMO_PETICIONES = 40;
    public final static int VARIACION_PETICIONES = 11;
    public final static int TOTAL_CLIENTES = 10;
}
