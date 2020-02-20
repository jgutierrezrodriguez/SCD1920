/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg2;

/**
 *
 * @author jgr97
 */
public class Reader implements Runnable{
    
    private PricesInfo pricesinfo;

    public Reader(PricesInfo pricesinfo) {
        this.pricesinfo = pricesinfo;
    }
    
    @Override
    public void run() {
        for (int i=0; i<10; i++){
            System.out.printf("%s: Price 1: %f\n",Thread.currentThread().getName(),pricesinfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n",Thread.currentThread().getName(),pricesinfo.getPrice2());
        }
    }

    
}
