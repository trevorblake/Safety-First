package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

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
	 * Empty setter method to change the color so that other 
	 * classes are not able to alter the color of Spider objects
	 * after instantiation.
	 */
	public void setColor(int x) {}
	
	/**
	 * From the Movable abstract parent class, this method
	 * moves the spider object using simple trig functions. 
	 * First checks to make sure spider's next move is within bounds
	 * of the gameWorld (width, height). 
	 * Adds an additional random change of heading ranging 
	 * from -5 to 5. Also checks if new heading is within the 
	 * range of 0 to 359. This method is invoked each game tick.
	 */
	public void move(int time, Dimension dCmpSize)
	{
		Random rand = new Random();
		int headingChange;
		int dist = getSpeed()*time/1000;
		double deltaX = Math.cos(Math.toRadians(90 - getHeading())) * dist;
		double deltaY = Math.sin(Math.toRadians(90 - getHeading())) * dist;
		float newX = (float) (getLocation().getX() + deltaX);
		float newY = (float) (getLocation().getY() + deltaY);
		headingChange = getHeading() - 5 + rand.nextInt(11);
		getLocation().setX(newX);
		getLocation().setY(newY);
  		
  		/*
  		 * Checks to see if the Spider is declared to be
  		 * outside of bounds, and if it is, changes the 
  		 * heading 180 degrees then calls move again.
  		 * Added buffers of 50/120/etc to make it look better
  		 * in game
  		 */
		if(getLocation().getX() >= dCmpSize.getWidth()-50 || 
				getLocation().getY() >= dCmpSize.getHeight()-120 || 
				getLocation().getX() <= 50 || getLocation().getY() <= 0)
		{
			headingChange = getHeading() + 180;
			setHeading(headingChange);	
			move(time, dCmpSize);
		}
			
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

	/**
	 * Draw method that creates the spider's triangle
	 *  shape based on location
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		int x = (int)(this.getLocation().getX() + pCmpRelPrnt.getX());
		int y = (int)(this.getLocation().getY() + pCmpRelPrnt.getY());
		int x1 = x - (getSize()/2);
		int x2 = x + (getSize()/2);
		int x3 = x;
		int y1 = y;
		int y2 = y;
		int y3 = y + getSize();
		int[] xPoints = {x1,x2,x3};
		int[] yPoints = {y1,y2,y3};
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, 3);	
	}

	/**
	 * Used to call the collidesWith method of ant. 
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) 
	{
		if(otherObject instanceof Ant)
		{
			return (((Ant)otherObject).collidesWith(this));
		}
		return false;
	}

	/**
	 * Used to call the handleCollision method of ant.
	 */
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) 
	{
		if (otherObject instanceof Ant)
		{
			((Ant)otherObject).handleCollision(this, gw);
		}
	}
	
}
