package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

/**
 * Ant Singleton class that holds all distinct fields related
 * to the in-game ant, such as maximumSpeed, foodLevel, 
 * foodConsumptionRate, healthLevel. Parent class is Movable.
 * Also implements ISteerable interface, making it a movable 
 * and steerable object.
 * @author Trevor Blake
 */
public class Ant extends Movable implements ISteerable 
{
	private int maximumSpeed; // Top speed the ant can reach
	private int foodLevel; // Amount of food the ant has stored
	private int foodConsumptionRate; // How quickly the ant loses foodLevel
	private int healthLevel; // The health of the ant
	private int lastFlagReached; // The last flag reached by the ant
	private static Ant theAnt; //Ant object returned (Singleton Design)
	
	/**
	 * Private Constructor for the Ant object. Calls to Movable parent class to create
	 * a Movable Ant object. Set parameters to Movable as 8 for size, red for
	 * color, heading at 0, speed of 4, location based on first flag instantiation
	 * @param location when instantiated in the GameWorld class, the ant location
	 * 		  must begin at the first flag location
	 */
	private Ant(int size, int color, Point location, int heading, int speed) 
	{
		super(size, color, location, heading, speed);
		maximumSpeed = 300;
		foodLevel = 3000;
		foodConsumptionRate = 1;
		healthLevel = 10;
		lastFlagReached = 1;
	}
	
	/**
	 * getter method for the Ant object. Calls to Movable parent class to create
	 * a Movable Ant object. Calls to private constructor if there is no Ant object
	 * otherwise returns first Ant object
	 */
	public static Ant getAnt(int size, int color, Point location, int heading, int speed)
	{
		if (theAnt == null)
		{
			theAnt = new Ant(size, color, location, heading, speed);
		}
		
		return theAnt;
	}

	/**
	 * Getter method to obtain the maximum speed of the ant
	 * @return the maximum speed of the ant
	 */
	public int getMaximumSpeed() 
	{
		return maximumSpeed;
	}

	/**
	 * Empty setter method to force other classes to not
	 * be able to change the maximumSpeed
	 * @param maximumSpeed
	 */
	public void setMaximumSpeed(int maximumSpeed) {}

	/**
	 * Getter method to obtain the food level of the ant
	 * @return the food level of the ant
	 */
	public int getFoodLevel() 
	{
		return foodLevel;
	}

	/**
	 * Setter method to change the food level of the ant
	 * @param foodLevel new amount of food the ant has stored
	 */
	public void setFoodLevel(int foodLevel) 
	{
		this.foodLevel = foodLevel;
	}

	/**
	 * Getter method to obtain the food consumption rate
	 * @return the food consumption rate per tick
	 */
	public int getFoodConsumptionRate() 
	{
		return foodConsumptionRate;
	}

	/**
	 * Empty setter method so that other classes cannot change
	 * the food consumption rate of the ant when set
	 * @param foodConsumptionRate new food consumption rate per tick
	 */
	public void setFoodConsumptionRate(int foodConsumptionRate) {}

	/**
	 * Getter method to return the current health level of the ant
	 * @return the current health level of the ant
	 */
	public int getHealthLevel() 
	{
		return healthLevel;
	}

	/**
	 * Setter method to change the health level of the ant
	 * @param healthLevel new amount of health the ant has
	 */
	public void setHealthLevel(int healthLevel) 
	{
		this.healthLevel = healthLevel;
	}

	/**
	 * Getter method to obtain the flag number last reached
	 * by the ant
	 * @return the last flag number reached by the ant
	 */
	public int getLastFlagReached() 
	{
		return lastFlagReached;
	}

	/**
	 * Setter method to set the last flag number reached
	 * @param lastFlagReached new last flag number reached
	 */
	public void setLastFlagReached(int lastFlagReached) 
	{
		this.lastFlagReached = lastFlagReached;
	}
	
	/**
	 * From the Movable abstract parent class this method moves the ant
	 * using the heading and the speed of the ant,  it determines
	 * the next location of the ant on the next tick using trig functions
	 * @param dCmpSize 
	 */
	public void move(int time, Dimension dCmpSize) 
	{
		int dist = getSpeed()*time/1000;
		double deltaX = Math.cos(Math.toRadians(90 - getHeading())) * dist;
		double deltaY = Math.sin(Math.toRadians(90 - getHeading())) * dist;
		float newX = (float) (getLocation().getX() + deltaX);
		float newY = (float) (getLocation().getY() + deltaY);
		getLocation().setX(newX);
		getLocation().setY(newY);
	}
	
	/**
	 * From the ISteerable interface, this is built so that
	 * other classes are able to change the heading of the ant
	 * by a positive or negative integer denoted by headingAdjust.
	 * headingChange is used to make sure the new heading value
	 * is within the range of 0 to 359
	 */
	@Override
	public void adjustHeading(int headingAdjust) 
	{
		int headingChange = getHeading() + headingAdjust;
		
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
	 * toString method that returns a string of characteristics 
	 * held by ants. Including location, color, heading, speed,
	 * size, maximum speed, and food consumption rate.
	 */
	public String toString() 
	{
		return "Ant: loc=" + locationToString() + 
				" color=[" + ColorUtil.red(getColor()) + "," + 
				ColorUtil.green(getColor()) + "," + 
				ColorUtil.blue(getColor()) + "]" + 
				" heading=" + getHeading() + 
				" speed=" + getSpeed() + 
				" size=" + getSize() + "\n     " + 
				"maxSpeed=" + getMaximumSpeed() + 
				" foodConsumptionRate=" + getFoodConsumptionRate() + "\n";
	}

	/**
	 * Draws the ant object on the screen as a red circle
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		int x = (int)(this.getLocation().getX() + pCmpRelPrnt.getX());
		int y = (int)(this.getLocation().getY() + pCmpRelPrnt.getY());
		g.setColor(this.getColor());
		g.fillArc(x, y, getSize(), getSize(), 0, 360);
		
	}

	/**
	 * Checks if the ant is colliding with another object, if so then
	 * it returns true
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) 
	{
		int r1 = (int)(getLocation().getX() + getSize()/2);
		int l1 = (int)(getLocation().getX() - getSize()/2);
		int t1 = (int)(getLocation().getY() + getSize()/2);
		int b1 = (int)(getLocation().getY() - getSize()/2);

		//since spiders and flags are triangles, this makes collisions more accurate looking
		if (otherObject instanceof Spider || otherObject instanceof Flag)
		{
			int r2 = (int)(otherObject.getLocation().getX() + otherObject.getSize()/4);
			int l2 = (int)(otherObject.getLocation().getX() - otherObject.getSize());
			int t2 = (int)(otherObject.getLocation().getY() + otherObject.getSize()/2);
			int b2 = (int)(otherObject.getLocation().getY() - otherObject.getSize()/2);
			
			if(r1 < l2 || l1 > r2 || t2 < b1 || t1 < b2)
			{
				return false;
			}
		}
		
		//collision detection for boxes, which as of now are only the foodstation
		if(otherObject instanceof FoodStation)
		{
			int r2 = (int)(otherObject.getLocation().getX() + otherObject.getSize()/2);
			int l2 = (int)(otherObject.getLocation().getX() - otherObject.getSize()/2);
			int t2 = (int)(otherObject.getLocation().getY() + otherObject.getSize()/2);
			int b2 = (int)(otherObject.getLocation().getY() - otherObject.getSize()/2);
			
			if(r1 < l2 || l1 > r2 || t2 < b1 || t1 < b2)
			{
				return false;
			}
		}		
		return true;

	}

	/**
	 * Handles collision between the ant and different GameObjects by
	 * calling the respective collision method in GameWorld
	 */
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) 
	{	    
		if(otherObject instanceof Flag)
		{
			gw.collidedFlag(this, ((Flag)otherObject));
		}
		
		if(otherObject instanceof FoodStation)
		{
			gw.collidedFood(this, ((FoodStation)otherObject));
		}
		
		if(otherObject instanceof Spider)
		{
			gw.collidedSpider(this);
		}
	}


}
