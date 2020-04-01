/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductListGenerator generator=new ProductListGenerator();
		List<Product> products=generator.generate(10000);
		
		// Craete a task
		Task task=new Task(products, 0, products.size(), 0.20);
		
		// Create a ForkJoinPool
		ForkJoinPool pool=new ForkJoinPool();
		
		// Execute the Task
		pool.execute(task);

		// Write information about the pool
		do {
			System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
			System.out.printf("Main: Paralelism: %d\n",pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
	
		// Shutdown the pool
		pool.shutdown();
		
		// Check if the task has completed normally
		if (task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
		}

		// Expected result: 12. Write products which price is not 12
		for (int i=0; i<products.size(); i++){
			Product product=products.get(i);
			if (product.getPrice()!=12) {
				System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
		}
		
		// End of the program
		System.out.println("Main: End of the program.\n");

    }
    
}
