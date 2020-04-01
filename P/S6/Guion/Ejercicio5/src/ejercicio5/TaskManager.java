/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 *
 * @author jgr97
 */
public class TaskManager {
    private List<ForkJoinTask<Integer>> tasks;
	
	/**
	 * Constructor of the class. Initializes the list of tasks
	 */
	public TaskManager(){
		tasks=new ArrayList<>();
	}
	
	/**
	 * Method to add a new Task in the list
	 * @param task The new task
	 */
	public void addTask(ForkJoinTask<Integer> task){
		tasks.add(task);
	}

	/**
	 * Method that cancel all the tasks in the list
	 * @param cancelTask 
	 */
	public void cancelTasks(ForkJoinTask<Integer> cancelTask){
		for (ForkJoinTask<Integer> task  :tasks) {
			if (task!=cancelTask) {
				task.cancel(true);
				((SearchNumberTask)task).writeCancelMessage();
			}
		}
	}

}
