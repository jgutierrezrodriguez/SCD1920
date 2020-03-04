/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.concurrent.CompletionService;

/**
 *
 * @author jgr97
 */
public class ReportRequest implements Runnable {

	/**
	 * Name of this ReportRequest
	 */
	private String name;
	
	/**
	 * CompletionService used for the execution of the ReportGenerator tasks
	 */
	private CompletionService<String> service;
	
	/**
	 * Constructor of the class. Initializes the parameters
	 * @param name Name of the ReportRequest
	 * @param service Service used for the execution of tasks
	 */
	public ReportRequest(String name, CompletionService<String> service){
		this.name=name;
		this.service=service;
	}

	/**
	 * Main method of the class. Create three ReportGenerator tasks and executes them
	 * through a CompletionService
	 */
	@Override
	public void run() {
			ReportGenerator reportGenerator=new ReportGenerator(name, "Report");
			service.submit(reportGenerator);
	}

}
