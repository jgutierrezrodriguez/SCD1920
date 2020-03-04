/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author jgr97
 */
public class ResultTask extends FutureTask<String> {

	/**
	 * Name of the ResultTask. It's initialized with the name of the
	 * ExecutableTask that manages
	 */
	private String name;
	
	/**
	 * Constructor of the Class. Override one of the constructor of its parent class 
	 * @param callable The task this object manages
	 */
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name=((ExecutableTask)callable).getName();
	}

	/**
	 * Method that is called when the task finish.
	 */
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been cancelled\n",name);
		} else {
			System.out.printf("%s: Has finished\n",name);
		}
	}

}

