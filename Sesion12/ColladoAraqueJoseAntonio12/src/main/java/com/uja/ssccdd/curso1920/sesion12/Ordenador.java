/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.curso1920.sesion12;
import static com.uja.ssccdd.curso1920.sesion12.Constantes.COMPONENTES;
import com.uja.ssccdd.curso1920.sesion12.Constantes.TipoComponente;
import static com.uja.ssccdd.curso1920.sesion12.Constantes.aleatorio;
import java.util.Arrays;

/**
 *
 * @author pedroj
 */
public class Ordenador {
    private String iD;
    private final TipoComponente[] componentes;
    
    // Constantes
    private final int VARIACION = 3;
    private final int TIEMPO_MONTAJE = 3;

    /**
     * Crea un ordenador sin elementos de vendedor asignados
     */
    public Ordenador() {
        this.componentes = new TipoComponente[COMPONENTES.length];
        for( TipoComponente componente : componentes)
            componente = null;
        
        this.iD = null;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public TipoComponente[] getComponentes() {
        return componentes;
    }

    public int tiempoMontaje() {
        return aleatorio.nextInt(VARIACION) + TIEMPO_MONTAJE;
    }
    
    /**
     * Asigna el nuevo componente al ordenador
     * @param componente para añadir al ordenador
     * @return true si se a añadido false si ya tenía ese componente
     */
    public boolean addComponente( TipoComponente componente) {
        boolean resultado = false;
        
        if( componentes[componente.ordinal()] == null ) {
            componentes[componente.ordinal()] = componente;
            resultado = true;
        }
        
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = null;
        
        if ( ordenadorCompleto() )
            resultado = "Ordenador[" + iD + "]{" + 
                            "componentes=" + Arrays.toString(componentes) + '}';
        else
            resultado = "Ordenador[" + iD + "]{NO_COMPLETO}";
        
        return resultado; 
    }
    
    public boolean ordenadorCompleto() {
        boolean completo = true;
        int i = 0;
        
        while( (i < componentes.length) &&  completo ) 
            if ( componentes[i] != null )
                i++;
            else 
                completo = false;
        
        return componentes.length == i;
    }
}
