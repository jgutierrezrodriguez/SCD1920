/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author jgr97
 */
public class Server {
    private ThreadPoolExecutor executor;
	
    /**
     * Constructor of the class. Creates the executor object
     */
    public Server(){
            executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
    }

    /**
     * This method is called when a request to the server is made. The 
     * server uses the executor to execute the request that it receives
     * @param task The request made to the server
     */
    public void executeTask(Task task){
            System.out.printf("Server: A new task has arrived\n");
            executor.execute(task);
            System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
            System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
            System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
    }

    /**
     * This method shuts down the executor
     */
    public void endServer() {
            executor.shutdown();
    }

}
