/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                ForkJoinPool pool=new ForkJoinPool();
		
		// Create three FolderProcessor tasks for three diferent folders
		FolderProcessor system=new FolderProcessor("C:\\Windows", "log");
		FolderProcessor apps=new FolderProcessor("C:\\Program Files","log");
		FolderProcessor documents=new FolderProcessor("C:\\Documents And Settings","log");
		
		// Execute the three tasks in the pool
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		
		// Write statistics of the pool until the three tasks end
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!system.isDone())||(!apps.isDone())||(!documents.isDone()));
		
		// Shutdown the pool
		pool.shutdown();
		
		// Write the number of results calculate by each task
		List<String> results;
		
		results=system.join();
		System.out.printf("System: %d files found.\n",results.size());
		
		results=apps.join();
		System.out.printf("Apps: %d files found.\n",results.size());
		
		results=documents.join();
		System.out.printf("Documents: %d files found.\n",results.size());

    }
    
}
