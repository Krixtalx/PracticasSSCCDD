package com.uja.ssccdd.curso1920.sesion12;

import com.uja.ssccdd.curso1920.sesion12.Constantes.TipoComponente;

public class Componente {

    private final String iD;
    private final TipoComponente componente;

    public Componente(String iD, TipoComponente componente) {
        this.iD = iD;
        this.componente = componente;
    }

    public String getiD() {
        return iD;
    }

    public TipoComponente getComponente() {
        return componente;
    }

    public int tiempoFabricacion() {
        return componente.tiempoFabricacion();
    }

    @Override
    public String toString() {
        return "Vendedor{" + "iD=" + iD + ", componente=" + componente + '}';
    }
}
