package com.ihm.frontend.search.dto;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class Slot implements Serializable, Comparator<Slot> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean active;
	private Date slotTime;
	
	public Slot() {
		
	}
	
	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Date getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(Date slotTime) {
		this.slotTime = slotTime;
	}


	@Override
	public int compare(Slot o1, Slot o2) {
		
		return o1.getSlotTime().compareTo(o2.getSlotTime());
	}
	
}
