package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * FoodStation is a child class of the Fixed object 
 * parent class. FoodStations hold the new data field
 * of capacity which is the amount of food a FoodStation
 * holds, and is equivalent to the size given during
 * instantiation
 * @author Trevor Blake
 */
public class FoodStation extends Fixed 
{
	private int capacity; // The amount of food storage the FoodStation holds
							
	/**
	 * Constructor method for FoodStation. Takes random valued parameters upon
	 * instantiation. Randomizes the size between 10 and 50, gives all
	 * FoodStation's the color blue, and randomizes the location in a fixed
	 * 1000x1000 map. Capacity gets set to size
	 */
	public FoodStation(int size, int color, Point location) 
	{
		super(size, color, location);
		this.capacity = getSize();
	}
	
	/**
	 * Getter method to obtain the capacity of the FoodStation
	 * @return the capacity of the FoodStation
	 */
	public int getCapacity() 
	{
		return capacity;
	}
	
	/**
	 * Setter method to change the capacity of the FoodStation
	 * @param newCapacity the capacity of the FoodStation
	 */
	public void setCapacity(int newCapacity) 
	{
		this.capacity = newCapacity;
	}
	
	/**
	 * toString method that returns a string that lists 
	 * various attributes of a FoodStation object. Such
	 * as the location, color, size, and capacity
	 */
	public String toString() 
	{
		return "FoodStation: loc=" + locationToString() + 
				" color=[" + ColorUtil.red(getColor()) + 
				"," + ColorUtil.green(getColor()) + "," + 
				ColorUtil.blue(getColor()) + "]" + 
				" size=" + getSize() + 
				" capacity=" + capacity + "\n";
	}
}
