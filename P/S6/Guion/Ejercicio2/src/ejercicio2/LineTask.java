/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class LineTask extends RecursiveTask<Integer>{
    private static final long serialVersionUID = 1L;
	
	/**
	 * A line of the document
	 */
	private String line[];
	
	/**
	 * Range of positions the task has to process
	 */
	private int start, end;
	
	/**
	 * Word we are looking for
	 */
	private String word;
	
	/**
	 * Constructor of the class
	 * @param line A line of the document
	 * @param start Position of the line where the task starts its process
	 * @param end Position of the line where the task starts its process
	 * @param word Work we are looking for
	 */
	public LineTask(String line[], int start, int end, String word) {
		this.line=line;
		this.start=start;
		this.end=end;
		this.word=word;
	}

	/**
	 * If the part of the line it has to process is smaller that 100, it
	 * calculates the number of appearances of the word in the block. Else,
	 * it divides the block in two blocks and throws to LineTask to calculate
	 * the number of appearances.
	 */
	@Override
	protected Integer compute() {
		Integer result=null;
		if (end-start<100) {
			result=count(line, start, end, word);
		} else {
			int mid=(start+end)/2;
			LineTask task1=new LineTask(line, start, mid, word);
			LineTask task2=new LineTask(line, mid, end, word);
			invokeAll(task1, task2);
			try {
				result=groupResults(task1.get(),task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Groups the results of two LineTasks
	 * @param number1 The result of the first LineTask
	 * @param number2 The result of the second LineTask
	 * @return The sum of the numbers
	 */
	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		
		result=number1+number2;
		return result;
	}

	/**
	 * Count the appearances of a word in a part of a line of a document
	 * @param line A line of the document
	 * @param start Position of the line where the method begin to count
	 * @param end Position of the line where the method finish the count
	 * @param word Word the method looks for
	 * @return The number of appearances of the word in the part of the line
	 */
	private Integer count(String[] line, int start, int end, String word) {
		int counter;
		counter=0;
		for (int i=start; i<end; i++){
			if (line[i].equals(word)){
				counter++;
			}
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return counter;
	}

}
