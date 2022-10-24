package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

/**
 * The GameWorld class handles the initialization of the game by
 * instantiating all of the game objects, adding the additional 
 * fields of a gameClock and antLives. This class also is responsible for
 * holding the methods of which to manipulate the various game objects
 * @author Trevor Blake
 */
public class GameWorld extends Observable
{
	private ArrayList <GameObject> worldObjects = new ArrayList<GameObject>(); // Holds all game objects
	private int gameClock; // The amount of time/ticks the game has been played, starting at 0
	private int antLives; // The number of lives the ant has, starting at 3
	private int height;
	private int width;
	private int nextFlag;
	private boolean sound;
	
	public GameWorld()
	{
		gameClock = 0;
		antLives = 3;
		height = 0;
		width = 0;
		nextFlag = 2;
		sound = false;
	}
	 
	/**
	 * Initializes the game by instantiating all of the game objects, setting distinct flag sequence numbers
	 * and flag locations, and making sure the ant object starts at the first flag. Randomizes many 
	 * of the different fields of each of the object types. Look to specific object
	 * class constructors for description of each parameter field setting
	 */
	public void init() 
	{ 
		Random rand = new Random();
		worldObjects.add(new Flag(1, 10, ColorUtil.GREEN, new Point(200, 100)));
		worldObjects.add(new Flag(2, 10, ColorUtil.GREEN, new Point(700, 200)));
		worldObjects.add(new Flag(3, 10, ColorUtil.GREEN, new Point(300, 700)));
		worldObjects.add(new Flag(4, 10, ColorUtil.GREEN, new Point(700, 800)));
		Ant a = Ant.getAnt(8, ColorUtil.rgb(255, 0, 0), new Point(worldObjects.get(0).getLocation().getX(), 
				worldObjects.get(0).getLocation().getY()), 0, 4);
		worldObjects.add(a);
		worldObjects.add(new Spider(10 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (rand.nextInt(width), rand.nextInt(height)), rand.nextInt(360), 5 + rand.nextInt(6)));
		worldObjects.add(new Spider(10 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (rand.nextInt(width), rand.nextInt(height)), rand.nextInt(360), 5 + rand.nextInt(6)));
		worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
				new Point (rand.nextInt(width), rand.nextInt(height))));
		worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
				new Point (rand.nextInt(width), rand.nextInt(height))));
		System.out.println(width + " gw " + height);
		setChanged();
		notifyObservers(a);
		setChanged();
		notifyObservers(this);
		
	}
	
	public int getTime()
	{
		return gameClock;
	}
	
	public int getLives()
	{
		return antLives;
	}
	
	public boolean getSound()
	{
		return sound;
	}
	
	public void setSound(boolean newSound)
	{
		sound = newSound;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Finds the ant object, then increases the speed by a random amount
	 * between 1 and 3, and checks to make sure ant speed is not over the 
	 * maximum speed based on the ant's health level. If the new speed is
	 * over the maximum limit based on health, sets speed to maximum limit
	 * based on health
	 */
	public void accelerate() 
	{
		Random rand = new Random();
		int increaseBy = 1 + rand.nextInt(3);
		
		for (int i = 0; i < worldObjects.size(); i++)
		{
			if(worldObjects.get(i) instanceof Ant)
			{
				Ant aObj = (Ant) worldObjects.get(i);
				double realMax = aObj.getHealthLevel() * .1 * aObj.getMaximumSpeed();
				
				if(aObj.getSpeed() + increaseBy >= realMax)
				{
					aObj.setSpeed((int) realMax);
					
				}
				
				else
				{
					aObj.setSpeed(increaseBy + aObj.getSpeed());
				}
				
				setChanged();
				notifyObservers(aObj);
			}
		}

	}
	
	/**
	 * Finds the ant object, then decreases the ant's speed between 
	 * 1 and 3, and checks to make sure the ant's speed is not less than 0,
	 * if it is the speed is set to 0
	 */
	public void brake() 
	{
		Random rand = new Random();
		int decreaseBy = 1 + rand.nextInt(3);
		
		for (int i = 0; i < worldObjects.size(); i++)
		{
			if(worldObjects.get(i) instanceof Ant)
			{
				Ant aObj = (Ant) worldObjects.get(i);
				
				if(aObj.getSpeed() - decreaseBy <= 0)
				{
					aObj.setSpeed(0);
				}
				
				else
				{
					aObj.setSpeed(aObj.getSpeed() - decreaseBy);
				}
				
				setChanged();
				notifyObservers(aObj);
			}
		}

	}
	
	/**
	 * Method that is used to change the ant object's heading by
	 * any amount given within the parameter, can be negative or positive
	 * @param amount number to add to the current heading of the ant object
	 */
	public void turnAnt(int amount) 
	{
		for(int i = 0; i < worldObjects.size(); i++) 
		{
			if(worldObjects.get(i) instanceof Ant)
			{
				Ant aObj = (Ant) worldObjects.get(i);
				aObj.adjustHeading(amount);
				setChanged();
				notifyObservers(aObj);
			}
		}
	}

	/**
	 * Updates the game world by increasing the gameClock by 1,
	 * reducing the ant's food level by 1, moving all Movable objects,
	 * and checking to see if the ant runs out of food or health, which 
	 * could consume a life and end the game if all 3 lives have been
	 * consumed
	 */
	public void update() 
	{
		gameClock++;
		Ant aObj = null;
		int headingChange;
		
	  	for (int i = 0; i < worldObjects.size(); i++) 
	  	{ 
	  		if (worldObjects.get(i) instanceof Ant) 
	  		{ 
	  			aObj = (Ant) worldObjects.get(i); 
	  			aObj.setFoodLevel(aObj.getFoodLevel() - 1);
	  			aObj.move();
	  			setChanged();
	  			notifyObservers(aObj);
	  		}   
	  	} 
			
		for (int i = 0; i < worldObjects.size(); i++) 
		{ 
			if (worldObjects.get(i) instanceof Spider) 
		  	{ 
		  		Spider sObj = (Spider) worldObjects.get(i); 
		  		sObj.move();
		  		
				if(sObj.getLocation().getX() >= width || sObj.getLocation().getY() >= height || 
						sObj.getLocation().getX() <= 0 || sObj.getLocation().getY() <= 0)
				{
					headingChange = sObj.getHeading() + 180;
					sObj.setHeading(headingChange);	
					sObj.move();

				}				
		  	}   
		} 
		
		setChanged();
		notifyObservers(aObj);
		setChanged();
		notifyObservers(this);
		
		if(aObj.getHealthLevel() <= 0 || aObj.getFoodLevel() <= 0)
		{
			antLives--;
			System.out.println("\nA life has been consumed! " + antLives + " left!");
			if(antLives == 0)
			{
				exitFailure();
			}
			else 
			{
				worldObjects.clear();
				init();
			}
		}	

	}
	
	public int getNextFlag()
	{
		return nextFlag;
	}
	
	/**
	 * Forces an ant-flag collision, increasing the flagSequenceNumber
	 * of the ant object, and checking to see if the ant has passed through
	 * the final flag object, which ends the game
	 * @param x
	 */
	public void collidedFlag(int x) 
	{
		  Ant aObj = null;
		  Flag fObj;
		  int totalFlags = 0;
		  
		  for (int i = 0; i < worldObjects.size(); i++) 
		  { 
			  if (worldObjects.get(i) instanceof Flag) 
			  { 
				  totalFlags++; 
			  }
		  }
		  
		  for (int i = 0; i < worldObjects.size(); i++) 
		  { 
			  if (worldObjects.get(i) instanceof Ant) 
			  { 
				  aObj = (Ant) worldObjects.get(i); 
			  }
		  }
		  
		  for (int i = 0; i < worldObjects.size(); i++)
		  {
			  if (worldObjects.get(i) instanceof Flag)
			  {
				  fObj = (Flag) worldObjects.get(i);
				  if(x == fObj.getSequenceNumber() && aObj.getLastFlagReached() == fObj.getSequenceNumber() - 1)
				  {
					  aObj.setLastFlagReached(x);
					  System.out.println("\nProper flagSequence number: lastFlagReached of " + 
						(aObj.getLastFlagReached()-1) + " increased by 1. \nlastFlagReached is now: "
							  + aObj.getLastFlagReached());
			  		  setChanged();
			  		  notifyObservers(aObj);
				  }	  
			  }
		  }   
		  nextFlag++;
		  
		  if(aObj.getLastFlagReached() == totalFlags)
		  {
			  exitSuccess();
		  }
	}
	
	/**
	 * Forced collision between ant-food. Uses a nested do-while to find a non-empty 
	 * FoodStation, adds the food capacity to the food level of the ant object, then
	 * sets that FoodStation capacity to 0 and adds another random FoodStation object
	 * to the game
	 */
	public void collidedFood() 
	{
		  Random rand = new Random();
		  Ant aObj = null;
		  FoodStation fObj;
		  for (int i = 0; i < worldObjects.size(); i++) 
		  { 
			  if (worldObjects.get(i) instanceof Ant) 
			  { 
				  aObj = (Ant) worldObjects.get(i); 
			  }
		  }
		  
		  int i = 0;
		  do {
			  do {
				  i = rand.nextInt(worldObjects.size() - 1);
			  } while(!(worldObjects.get(i) instanceof FoodStation));
			  fObj = (FoodStation) worldObjects.get(i);
		  } while(fObj.getCapacity() == 0);

		  aObj.setFoodLevel(aObj.getFoodLevel() + fObj.getCapacity());
		setChanged();
		notifyObservers(aObj);
		  fObj.setColor(ColorUtil.rgb(0, 100, 0));
		  fObj.setCapacity(0);
		  worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
					new Point (rand.nextInt(width), rand.nextInt(height))));	

	}
	
	/**
	 * Forced collision between ant-spider. Reduces the health level of the ant
	 * by 1 and checks to see if the ant is now under the maximum speed based on 
	 * health threshold and reduces the speed of the ant to the maximum speed based
	 * on health, then checks to see if the ant has reached 0 health and reduces the
	 * ant lives by 1. If ant lives reach 0, forces the game to end
	 */
	public void collidedSpider() 
	{
		Ant aObj = null;
		  	for (int i = 0; i < worldObjects.size(); i++) 
		  	{ 
		  		if (worldObjects.get(i) instanceof Ant) 
		  		{	 
		  			aObj = (Ant) worldObjects.get(i); 
		  		}
		  	}

		  	aObj.setHealthLevel(aObj.getHealthLevel() - 1);
		  	double realMax = aObj.getHealthLevel() * .1 * aObj.getMaximumSpeed();
		  	aObj.setColor(ColorUtil.rgb(ColorUtil.red(aObj.getColor()) - 25, 0, 0));
			
			if(aObj.getSpeed() > realMax)
			{
				aObj.setSpeed((int) realMax);
	  			setChanged();
	  			notifyObservers(aObj);
			}
			
  			setChanged();
  			notifyObservers(aObj);
		  
			if(aObj.getHealthLevel() <= 0)
			{
				antLives--;
				System.out.println("\nA life has been consumed! " + antLives + " left!");
				if(antLives == 0)
				{
					exitFailure();
				}
				else 
				{
					worldObjects.clear();
					init();
				}
		   }

	}
	
	/**
	 * String method that returns a string that holds GameWorld/Ant info
	 * such as the antLives, gameClock, ant's lastFlagReached, ant's 
	 * foodLevel, and ant's healthLevel
	 * @return
	 */
	
	/**
	 * Forces the game to exit when the ant lives has reached 0 and leaves a
	 * failure message in the console
	 */
	public void exitFailure() 
	{
		System.out.println("\nGame over, you failed!");
		Display.getInstance().exitApplication();
	}
	
	/**
	 * Forces the game to exit when the ant reaches the last flag and leaves
	 * a success message in the console
	 */
	public void exitSuccess() 
	{
		System.out.println("\nGame over, you win! Total time: " + gameClock);
		Display.getInstance().exitApplication();
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int newHeight) 
	{
		height = newHeight;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int newWidth) 
	{
		width = newWidth;
	}
	
	/**
	 * toString method that bundles together all of the game objects toString methods 
	 * in order to display a readable list of information on all game objects
	 */
	public String toString() 
	{
		String description = "\n";
		for(int i = 0; i < worldObjects.size(); i++)
		{
			description += worldObjects.get(i);
		}
		return description;
	}
}
