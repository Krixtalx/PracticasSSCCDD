package com.uja.ssccdd.sesion3;

/**
 *
 * @author José Antonio
 */
public class Results {
    int[] array;

    public Results(int tam) {
        array = new int [tam];
    }
    
    public void setData(int pos, int num){
        array[pos]=num;
    }
    
    public int[] getData(){
        return array;
    }
    
}
