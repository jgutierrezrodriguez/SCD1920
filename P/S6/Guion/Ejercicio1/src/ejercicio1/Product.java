/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author jgr97
 */
public class Product {
    /**
	 * Name of the product
	 */
	private String name;
	
	/**
	 * Price of the product
	 */
	private double price;
	
	/**
	 * This method returns the name of the product
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method establish the name of the product
	 * @param name the name of the product
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method returns the price of the product
	 * @return the price of the product
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * This method establish the price of the product
	 * @param price the price of the product
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
