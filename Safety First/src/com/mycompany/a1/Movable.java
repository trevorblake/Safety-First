package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Dimension;

/**
 * Movable is the abstract child class of GameObject, and 
 * holds all of the fields of in-game objects that are 
 * able to move around. Uses speed and heading to move the 
 * point that an object is at to another point.
 * @author Trevor Blake
 *
 */
public abstract class Movable extends GameObject 
{
	private int heading; // the direction the Movable object is facing
	private int speed; // the current speed of the Movable object

	/**
	 * Constructor that takes the parameters of size, color, location,
	 * heading, and speed when instantiated in order to create a Movable
	 * object. Calls upon GameObject and gives it the size, color, and 
	 * location, while setting the heading and speed of it's data fields
	 * @param size the size of the Movable object
	 * @param color the color of the Movable object
	 * @param location the location of the Movable object
	 * @param heading the heading of the Movable object
	 * @param speed the speed of the Movable object
	 */
	public Movable(int size, int color, Point location, int heading, int speed) 
	{
		super(size,color,location);
		this.heading = heading;
		this.speed = speed;
	}
	
	/**
	 * Getter method to obtain the heading of the Movable object
	 * @return the heading of the Movable object
	 */
	public int getHeading() 
	{
		return heading;
	}
	
	/**
	 * Setter method to change the heading of a Movable object
	 * @param newHeading new heading of a Movable object
	 */
	public void setHeading(int newHeading) 
	{
		heading = newHeading;
	}
	
	/**
	 * Getter method to obtain the speed of a Movable object
	 * @return the speed of a Movable object
	 */
	public int getSpeed() 
	{
		return speed;
	}
	
	/**
	 * Setter method to change the speed of a Movable object
	 * @param newSpeed new speed of a Movable object
	 */
	public void setSpeed(int newSpeed) 
	{
		speed = newSpeed;
	}
	
	/**
	 * Abstract method that forces all child classes of 
	 * Movable to have a move method
	 */
	public abstract void move(int time, Dimension dCmpSize);
}
