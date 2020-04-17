/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Event implements Delayed {

	/**
	 * Date when we want to activate the event
	 */
	private Date startDate;
	
	/**
	 * Constructor of the class
	 * @param startDate Date when we want to activate the event
	 */
	public Event (Date startDate) {
		this.startDate=startDate;
	}
	
	/**
	 * Method to compare two events
	 */
	@Override
	public int compareTo(Delayed o) {
		long result=this.getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
		if (result<0) {
			return -1;
		} else if (result>0) {
			return 1;
		}
		return 0;
	}

	/**
	 * Method that returns the remaining time to the activation of the event
	 */
	@Override
	public long getDelay(TimeUnit unit) {	
		Date now=new Date();
		long diff=startDate.getTime()-now.getTime();
		return unit.convert(diff,TimeUnit.MILLISECONDS);
	}

}

