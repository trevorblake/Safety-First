package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * Flag class is a child of the Fixed object class
 * and takes the additional field of sequenceNumber
 * @author Trevor Blake
 *
 */
public class Flag extends Fixed 
{
	private int sequenceNumber; // The flag's distinct sequence number

	/**
	 * Constructor for the flag class that takes the sequence number
	 * and location given at instantiation and also gives parameters
	 * to the Fixed parent class such as size of 10, green color, 
	 * and the location that was given at instantiation.
	 * @param sequenceNumber the flag's distinct number to signify the 
	 * 						 order the ant should follow through
	 * @param location the location of the flag
	 */
	public Flag(int sequenceNumber, int size, int color, Point location) 
	{
		super(size, color, location);		
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Getter method to return the sequence number of the flag
	 * @return the sequence number of the flag
	 */
	public int getSequenceNumber() 
	{
		return sequenceNumber;
	}	
	
	/**
	 * toString method that returns a string of the data
	 * associated with flags. Such as a flag's location,
	 * color, size, and sequence number.
	 */
	public String toString() 
	{
		return "Flag: loc=" + locationToString() + 
				" color=[" + ColorUtil.red(getColor()) + 
				"," + ColorUtil.green(getColor()) + 
				"," + ColorUtil.blue(getColor()) + 
				"]" + " size=" + getSize() + 
				" seqNum=" + sequenceNumber + "\n";
	}
}
