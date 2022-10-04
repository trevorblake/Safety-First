package com.mycompany.a1;

import com.codename1.charts.models.Point;

/**
 * Fixed abstract class is the parent class of any
 * in-game objects that must not change location after
 * being instantiated. Parent class is GameObject.
 * @author Trevor Blake
 *
 */
public abstract class Fixed extends GameObject 
{

	/**
	 * Constructor for fixed game objects, takes
	 * the parameters of size, color, and location
	 * of the object and sends them to GameObject to
	 * be built
	 * @param size the size of the object
	 * @param color the color of the object
	 * @param location the location of the object
	 */
	public Fixed(int size, int color, Point location) 
	{	
		super(size,color,location);
	}
	
	/**
	 * Empty setter method to prevent fixed object child
	 * classes from being able to change their location
	 */
	public void setLocation(Point newLoc) {}

}
