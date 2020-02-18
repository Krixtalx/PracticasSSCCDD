/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3;

/**
 *
 * @author José Antonio
 */
public class Reader implements Runnable {
    private PricesInfo PricesInfo;

    public Reader(PricesInfo PricesInfo) {
        this.PricesInfo = PricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" Price1: "+PricesInfo.getPrice1());
            System.out.println(Thread.currentThread().getName()+" Price2: "+PricesInfo.getPrice2());
        }
    }
    
    
}
