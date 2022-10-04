package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

/**
 * Spider class is the child class of Movable.
 * Spiders are a movable object, but are not steerable.
 * @author Trevor Blake
 */
public class Spider extends Movable 
{
	/**
	 * Constructor method for the Spider class that takes 
	 * field parameters upon instantiation. Builds the Spider 
	 * object by calling to Movable parent class with the parameters 
	 * of size, color, location, heading, speed. Color is static set 
	 * to black, size is random in the range of 10 to 50, location is 
	 * random within a 1000x1000 sized area, heading is random in the 
	 * range of 0 to 359, and speed is random in the range of 5 to 10
	 */
	public Spider(int size, int color, Point location, int heading, int speed) 
	{
		super(size, color, location, heading, speed);
	}
	
	/**
	 * Empty setter method to change the speed so that other 
	 * classes are not able to alter the speed of Spider objects
	 * after instantiation.
	 */
	public void setSpeed(int x) {}
	
	/**
	 * From the Movable abstract parent class, this method
	 * moves the spider object using simple trig functions. 
	 * First checks to make sure spider's next move is within bounds
	 * of the gameWorld (0...1000,0...1000). If it is going to be
	 * outside of bounds, it does not set the newX and newY and 
	 * instead changes the heading by 180 degrees and calls
	 * move() recursively until its next move is in the bounds.
	 * Adds an additional random change of heading ranging 
	 * from -5 to 5. Also checks if new heading is within the 
	 * range of 0 to 359. This method is invoked each game tick.
	 */
	public void move()
	{
		Random rand = new Random();
		int headingChange;
		double deltaX = Math.cos(Math.toRadians(90 - getHeading())) * getSpeed();
		double deltaY = Math.sin(Math.toRadians(90 - getHeading())) * getSpeed();
		float newX = (float) (getLocation().getX() + deltaX);
		float newY = (float) (getLocation().getY() + deltaY);
		headingChange = getHeading() - 5 + rand.nextInt(11);
		if(newX >= 1000 || newY >= 1000 || newX <= 0 || newY <= 0)
		{
			headingChange = getHeading() + 180;
			
			if(headingChange < 0)
			{
				headingChange += 360;
			}
			
			if(headingChange >= 360)
			{
				headingChange -= 360;
			}
			
			setHeading(headingChange);	
			
			move();
		}
		
		else 
		{
			getLocation().setX(newX);
			getLocation().setY(newY);
			
			if(headingChange < 0)
			{
				headingChange += 360;
			}
			
			if(headingChange >= 360)
			{
				headingChange -= 360;
			}	
			setHeading(headingChange);		
		}	
	}
	
	/**
	 * toString method that returns a string that gives detailed
	 * information about the Spider object. Such as location,
	 * color, heading, speed, and size
	 */
	public String toString() 
	{
		return "Spider: loc=" + locationToString() + 
				" color=[" + ColorUtil.red(getColor()) + "," + 
				ColorUtil.green(getColor()) + "," + 
				ColorUtil.blue(getColor()) + "]" + 
				" heading=" + getHeading() + 
				" speed=" + getSpeed() + 
				" size=" + getSize() + "\n";
	}
	
}
