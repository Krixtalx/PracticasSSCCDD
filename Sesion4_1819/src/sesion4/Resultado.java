/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion4;

/**
 *
 * @author pedroj
 */
public class Resultado {
    private final String nombreTarea;
    private final int importeEntradas;

    public Resultado(String nombreTarea, int importeEntradas) {
        this.nombreTarea = nombreTarea;
        this.importeEntradas = importeEntradas;
    }

    @Override
    public String toString() {
        return "Resultado{" + "nombreTarea=" + nombreTarea + ", importeEntradas=" + importeEntradas + '}';
    }
}
