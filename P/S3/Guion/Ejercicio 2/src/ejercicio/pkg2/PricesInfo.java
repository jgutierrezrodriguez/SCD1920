/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author jgr97
 */
public class PricesInfo {
    
    private double price1, price2;
    
    private ReadWriteLock lock;

    public PricesInfo() {
        this.price1 = 1.0;
        this.price2 = 2.0;
        this.lock = new  ReentrantReadWriteLock();
    }

    public double getPrice1() {
        
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        
        return value;
    }
    
    public double getPrice2() {
        
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        
        return value;
    }
    
    public void setPrices(double price1, double price2){
        lock.writeLock().lock();
        this.price1=price1;
        this.price2=price2;
        lock.writeLock().unlock();
    }
    
}
