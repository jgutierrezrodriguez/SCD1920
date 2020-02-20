/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg3;

/**
 *
 * @author jgr97
 */
public class Producer implements Runnable {

	/**
	 * Simulated File
	 */
	private FileMock mock;
	
	/**
	 * Buffer
	 */
	private Buffer buffer;
	
	/**
	 * Constructor of the class. Initialize the objects
	 * @param mock Simulated file
	 * @param buffer Buffer
	 */
	public Producer (FileMock mock, Buffer buffer){
		this.mock=mock;
		this.buffer=buffer;	
	}
	
	/**
	 * Core method of the producer. While are pending lines in the
	 * simulated file, reads one and try to store it in the buffer.
	 */
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while (mock.hasMoreLines()){
			String line=mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

    
}
