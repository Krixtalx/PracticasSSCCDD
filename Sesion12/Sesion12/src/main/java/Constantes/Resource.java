/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constantes;

import static Constantes.Constantes.DICE_100;
import static Constantes.Constantes.rnd;

/**
 *
 * @author José Antonio
 */
/**
 * clase de ejemplo de envio de objetos
 * @author javier medina
 *
 */
public class Resource {
    private String iD;
    private String name;
	
    public Resource(String iD, String name) {
        this.iD = iD;
	this.name = name;
    }
    
    public String getiD() {
        return iD;
    }
	
    public void setiD(String iD) {
	this.iD = iD;
    }
	
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int pruebaRecurso() {
        return rnd.nextInt(DICE_100);
    }
    
    @Override
    public String toString() {
        return "Resource [id=" + iD + ", name=" + name + "]";
    }	
}

