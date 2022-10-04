package com.mycompany.a1;

import com.codename1.charts.models.Point;

 /**
  * GameObject is the abstract master parent class of all of
  * the in-game objects. It holds the data fields of
  * size, color, and location
  * @author Trevor Blake
  */
public abstract class GameObject 
{	
	private final int size; // The size of the GameObject
	private int color; // The color of the GameObject
	private Point location; // The location of the GameObject
	
	/**
	 * Constructor method that is called upon instantiation of
	 * GameObject objects. Takes the parameters of size, color, 
	 * and location and sets them to the appropriate fields.
	 * @param size the size of the GameObject
	 * @param color the color of the GameObject
	 * @param location the location of the GameObject
	 */
	public GameObject(int size, int color, Point location) 
	{
		this.size = size;
		this.color = color;
		this.location = location;
	}
	
	/**
	 * Getter method to return the size of the GameObject
	 * @return the size of the GameObject
	 */
	public int getSize() 
	{
		return size;
	}
	
	/**
	 * Getter method to return the location of the GameObject
	 * @return the location of the GameObject
	 */
	public Point getLocation() 
	{
		return location;
	}
	
	/**
	 * Setter method to change the location of the GameObject
	 * @param newLoc new location of the GameObject
	 */
	public void setLocation(Point newLoc) 
	{
		location = newLoc;
	}
	
	/**
	 * Getter method to return the color of the GameObject
	 * @return the color of the GameObject
	 */
	public int getColor() 
	{
		return color;
	}
	
	/**
	 * Setter method to change the color of the GameObject
	 * @param newColor new color of the GameObject
	 */
	public void setColor(int newColor) 
	{
		color = newColor;
	}
	
	/**
	 * A string method that returns a string holding the location
	 * of the GameObject rounded to the first decimal point
	 * @return
	 */
	public String locationToString() 
	{
		float x = (float) (Math.round(location.getX() * 10.0) / 10.0);
		float y = (float) (Math.round(location.getY() * 10.0) / 10.0);	
		return x + ", " + y;
	}
}
