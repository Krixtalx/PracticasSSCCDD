/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author pedroj
 */
public class Resultado {
    private final String nombreTarea;
    private final TrabajoImpresion impresion;
    private final Date fechaFinalizacion;

    public Resultado(String nombreTarea, TrabajoImpresion impresion, Date fechaFinalizacion) {
        this.nombreTarea = nombreTarea;
        this.impresion = impresion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    @Override
    public String toString() {
        return "Resultado{" + "nombreTarea=" + nombreTarea + ", trabajoImpresion=" + impresion 
                + ", fechaFinalizacion=" + fechaFinalizacion + '}';
    }
    
}
