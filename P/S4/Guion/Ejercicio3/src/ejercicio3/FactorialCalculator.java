/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.concurrent.Callable;

/**
 *
 * @author jgr97
 */
public class FactorialCalculator implements Callable<Integer>{
    private Integer number;
	
	/**
	 * Constructor of the class. Initializes the attributes
	 * @param number Number to calculate the factorial
	 */
	public FactorialCalculator(Integer number){
		this.number=number;
	}
	
	/**
	 * Method called by the executor to execute this task and calculate the factorial of a
	 * number
	 */
	@Override
	public Integer call() throws Exception {
		int num, result;
		
		num=number.intValue();
		result=1;
		
		// If the number is 0 or 1, return the 1 value
		if ((num==0)||(num==1)) {
			result=1;
		} else {
			// Else, calculate the factorial
			for (int i=2; i<=number; i++) {
				result*=i;
				Thread.sleep(20);
			}
		}
		System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
		// Return the value
		return result;
	}

}
