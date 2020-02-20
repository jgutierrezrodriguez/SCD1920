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
public class Grouper implements Runnable {

	/**
	 * Results object with the occurrences of the number in each row
	 */
	private Results results;
	
	/**
	 * Constructor of the class. Initializes its attributes
	 * @param results Results object with the ocurrences of the number in each row
	 */
	public Grouper(Results results){
		this.results=results;
	}
	
	/**
	 * Main method of the Grouper. Sum the values stored in the Results object 
	 */
	@Override
	public void run() {
		int finalResult=0;
		System.out.printf("Grouper: Processing results...\n");
		int data[]=results.getData();
		for (int number:data){
			finalResult+=number;
		}
		System.out.printf("Grouper: Total result: %d.\n",finalResult);
	}


    
}
