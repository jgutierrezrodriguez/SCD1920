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
public class PrimeGenerator extends Thread{

    public PrimeGenerator() {
    }

    @Override
    public void run() {
         long number=1L;
		
        // This bucle never ends... until is interrupted
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n",number);
            }
            
            if(isInterrupted()){
                System.out.printf("The Prime Generator has been Interrupted\n");
				return;

            }
            
            number++;
        }
    }
        
    private boolean isPrime(long number) {
		if (number <=2) {
			return true;
		}
		for (long i=2; i<number; i++){
			if ((number % i)==0) {
				return false;
			}
		}
		return true;
	}

    
}
