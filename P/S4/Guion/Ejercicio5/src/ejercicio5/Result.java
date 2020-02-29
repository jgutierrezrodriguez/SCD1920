/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

/**
 *
 * @author jgr97
 */
public class Result {
    private String name;
	/**
	 * The value of the task that generates the result 
	 */
	private int value;
	
	/**
	 * Returns the name of the task
	 * @return Name of the task that generates the result
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Establish the name of the task
	 * @param name The name of the task that generates the result
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the value of the result
	 * @return The value of the result
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Establish the value of the result
	 * @param value The value of the result
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
