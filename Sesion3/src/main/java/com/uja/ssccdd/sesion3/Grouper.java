package com.uja.ssccdd.sesion3;

/**
 *
 * @author José Antonio
 */
public class Grouper implements Runnable {

    Results resultado;

    public Grouper(Results resultado) {
        this.resultado = resultado;
    }

    @Override
    public void run() {
        int resultadoFinal=0;
        for (int i = 0; i < resultado.getData().length; i++) {
            resultadoFinal+=resultado.getData()[i];
        }
        
        System.out.println("El resultado final es "+resultadoFinal);
    }
}
