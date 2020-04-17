/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

/**
 *
 * @author jgr97
 */
public class Company implements Runnable{
    private Account account;
	
	/**
	 * Constructor of the class. Initializes the account
	 * @param account the account affected by the operations
	 */
	public Company(Account account) {
		this.account=account;
	}
	
	/**
	 * Core method of the Runnable
	 */
	@Override
	public void run() {
		for (int i=0; i<10; i++){
			account.addAmount(1000);
		}
	}

}
