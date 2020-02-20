/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg5;

/**
 *
 * @author jgr97
 */
public class Results {
    /**
         * Array to store the number of occurrences of the number in each row of the array
	 */
	private int data[];

	/**
	 * Constructor of the class. Initializes its attributes
	 * @param size Size of the array to store the results
	 */
	public Results(int size){
		data=new int[size];
	}

	/**
	 * Sets the value of one position in the array of results
	 * @param position Position in the array
	 * @param value Value to set in that position
	 */
	public void  setData(int position, int value){
		data[position]=value;
	}
	
	/**
	 * Returns the array of results
	 * @return the array of results
	 */
	public int[] getData(){
		return data;
	}

}
