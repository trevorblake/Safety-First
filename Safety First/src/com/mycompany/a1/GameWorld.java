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
	private GameObjectCollection worldObjects; // Holds all game objects
	private int gameClock; // The amount of time/ticks the game has been played, starting at 0
	private int antLives; // The number of lives the ant has, starting at 3
	private int height; // The height of the GameWorld
	private int width; // The width of the GameWorld
	private int nextFlag; // The nextFlag for the Ant to reach
	private boolean sound; // Determines if sound is off or on (boolean value)
	
	/**
	 * Public Constructor that initializes the private fields
	 */
	public GameWorld()
	{
		worldObjects = new GameObjectCollection();
		gameClock = 0;
		antLives = 3;
		height = 0;
		width = 0;
		nextFlag = 2;
		sound = false;
	}
	 
	/**
	 * Initializes the game by instantiating all of the game objects into a GameObjectCollection
	 * for future use in Iterator pattern calls, setting distinct flag sequence numbers
	 * and flag locations, and getting the Ant object using Singleton pattern. Randomizes many 
	 * of the different fields of each of the object types. Look to specific object
	 * class constructors for description of each parameter field setting. Also notifies the observers
	 * that updates have happened to Ant and the GameWorld.
	 */
	public void init() 
	{ 
		Random rand = new Random();
		worldObjects.add(new Flag(1, 10, ColorUtil.GREEN, new Point(200, 100)));
		worldObjects.add(new Flag(2, 10, ColorUtil.GREEN, new Point(700, 200)));
		worldObjects.add(new Flag(3, 10, ColorUtil.GREEN, new Point(300, 700)));
		worldObjects.add(new Flag(4, 10, ColorUtil.GREEN, new Point(700, 800)));
		Ant a = Ant.getAnt(8, ColorUtil.rgb(255, 0, 0), new Point(200, 100), 0, 4);
		worldObjects.add(a);
		worldObjects.add(new Spider(10 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (rand.nextInt(width), rand.nextInt(height)), rand.nextInt(360), 5 + rand.nextInt(6)));
		worldObjects.add(new Spider(10 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (rand.nextInt(width), rand.nextInt(height)), rand.nextInt(360), 5 + rand.nextInt(6)));
		worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
				new Point (rand.nextInt(width), rand.nextInt(height))));
		worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
				new Point (rand.nextInt(width), rand.nextInt(height))));
		setChanged();
		notifyObservers(a);
		setChanged();
		notifyObservers(this);	
	}
	
	/**
	 * Getter method to get the gameClock value
	 * @return number of ticks in GameWorld
	 */
	public int getTime()
	{
		return gameClock;
	}
	
	/**
	 * Getter method to get the antLives value
	 * @return number of antLives left
	 */
	public int getLives()
	{
		return antLives;
	}
	
	/**
	 * Getter method to get the nextFlag value
	 * @return number of next flag
	 */
	public int getNextFlag()
	{
		return nextFlag;
	}
	
	/**
	 * Getter method to get the sound value
	 * @return Whether sound is off or on
	 */
	public boolean getSound()
	{
		return sound;
	}
	
	/**
	 * Setter method to set the sound value and also
	 * notifies the observers that sound has changed
	 * 
	 * @param newSound change in the boolean sound value
	 */
	public void setSound(boolean newSound)
	{
		sound = newSound;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Getter method that gets the height value
	 * @return height value of GameWorld
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Setter method that changes the height value
	 * @param newHeight new height value of GameWorld
	 */
	public void setHeight(int newHeight) 
	{
		height = newHeight;
	}
	
	/**
	 * Getter method that gets the width value
	 * @return width value of GameWorld
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Setter method that changes the width value
	 * @param newWidth new width value of GameWorld
	 */
	public void setWidth(int newWidth) 
	{
		width = newWidth;
	}
	
	/**
	 * Finds the ant object, then increases the speed by a random amount
	 * between 1 and 3, and checks to make sure ant speed is not over the 
	 * maximum speed based on the ant's health level. If the new speed is
	 * over the maximum limit based on health, sets speed to maximum limit
	 * based on health. Also notifies the observers that updates have 
	 * happened to Ant.
	 */
	public void accelerate() 
	{
		Random rand = new Random();
		int increaseBy = 1 + rand.nextInt(3);
		
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
			if(wgo instanceof Ant)
			{
				Ant aObj = (Ant)wgo;
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
	 * if it is the speed is set to 0. Also notifies the observers that updates 
	 * have happened to Ant.
	 */
	public void brake() 
	{
		Random rand = new Random();
		int decreaseBy = 1 + rand.nextInt(3);
		
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
			if(wgo instanceof Ant)
			{
				Ant aObj = (Ant)wgo;
				
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
	 * Also notifies the observers that updates have happened to Ant.
	 * @param amount number to add to the current heading of the ant object
	 */
	public void turnAnt(int amount) 
	{
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
			if(wgo instanceof Ant)
			{
				Ant aObj = (Ant)wgo;
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
	 * consumed then notifies observers of Ant and GameWorld changes
	 */
	public void tick() 
	{
		gameClock++;
		Ant aObj = null;
		int headingChange;
		
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
	  		if (wgo instanceof Ant) 
	  		{ 
	  			aObj = (Ant)wgo; 
	  			aObj.setFoodLevel(aObj.getFoodLevel() - 1);
	  			aObj.move();
	  		}   
	  	} 
			
		theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
			if (wgo instanceof Spider) 
		  	{ 
		  		Spider sObj = (Spider)wgo; 
		  		sObj.move();
		  		
		  		/*
		  		 * Checks to see if the Spider is declared to be
		  		 * outside of bounds, and if it is, changes the 
		  		 * heading 180 degrees then calls move again.
		  		 */
				if(sObj.getLocation().getX() >= width || sObj.getLocation().getY() >= height || 
						sObj.getLocation().getX() <= 0 || sObj.getLocation().getY() <= 0)
				{
					headingChange = sObj.getHeading() + 180;
					sObj.setHeading(headingChange);	
					sObj.move();

				}				
		  	}   
		} 
		
		if(aObj.getHealthLevel() <= 0 || aObj.getFoodLevel() <= 0)
		{
			antLives--;
			worldObjects.empty();
			init();
			aObj.setHealthLevel(10);
			aObj.setColor(ColorUtil.rgb(255, 0, 0));
			aObj.setHeading(0);
			aObj.setSpeed(0);
			aObj.setLocation(new Point(200, 100));
			aObj.setFoodLevel(15);
			aObj.setLastFlagReached(1);
			aObj.setMaximumSpeed(20);
			if(antLives == 0)
			{
				exitFailure();
			}
		}	
		
		setChanged();
		notifyObservers(this);
		setChanged();
		notifyObservers(aObj);

	}
	
	/**
	 * Forces an ant-flag collision, increasing the flagSequenceNumber
	 * of the ant object, and checking to see if the ant has passed through
	 * the final flag object, which ends the game. Notifies observers
	 * of the change to the Ant object if the lastFlagReached is updated
	 * @param x flag number given
	 */
	public void collidedFlag(int x) 
	{
		  Ant aObj = null;
		  Flag fObj;
		  int totalFlags = 0;
		  
			IIterator theElements = worldObjects.getIterator();
			while ( theElements.hasNext() ) {  
			GameObject wgo = (GameObject) theElements.getNext();
			if (wgo instanceof Flag) 
			{ 
				  totalFlags++; 
			}
		  }
		  
			theElements = worldObjects.getIterator();
			while ( theElements.hasNext() ) {  
			GameObject wgo = (GameObject) theElements.getNext();
			  if (wgo instanceof Ant) 
			  { 
				  aObj = (Ant)wgo; 
			  }
		  }
		  
			theElements = worldObjects.getIterator();
			while ( theElements.hasNext() ) {  
			GameObject wgo = (GameObject) theElements.getNext();
			  if (wgo instanceof Flag)
			  {
				  fObj = (Flag)wgo;
				  if(x == fObj.getSequenceNumber() && aObj.getLastFlagReached() == fObj.getSequenceNumber() - 1)
				  {
					  aObj.setLastFlagReached(x);
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
	 * Forced collision between ant-food. Uses a do-while to find a non-empty 
	 * FoodStation, adds the food capacity to the food level of the ant object, then
	 * sets that FoodStation capacity to 0 and adds another random FoodStation object
	 * to the game then notifies observers that Ant and GameWorld have changed.
	 */
	public void collidedFood() 
	{
		  Random rand = new Random();
		  Ant aObj = null;
		  FoodStation fObj;
		  ArrayList<FoodStation> foodList = new ArrayList<>();
		  
			IIterator theElements = worldObjects.getIterator();
			while ( theElements.hasNext() ) {  
			GameObject wgo = (GameObject) theElements.getNext(); 
			  if (wgo instanceof Ant) 
			  { 
				  aObj = (Ant)wgo; 
			  }
		  }
		  
			theElements = worldObjects.getIterator();
			while ( theElements.hasNext() ) {  
			GameObject wgo = (GameObject) theElements.getNext(); 
			  if (wgo instanceof FoodStation) 
			  { 
				  foodList.add((FoodStation)wgo); 
				  
			  }
		  }

		 do {
			  int i = rand.nextInt(foodList.size() - 1);
			  fObj = foodList.get(i);
		 } while(fObj.getCapacity() == 0);

		aObj.setFoodLevel(aObj.getFoodLevel() + fObj.getCapacity());
		setChanged();
		notifyObservers(aObj);
		fObj.setColor(ColorUtil.rgb(0, 100, 0));
		fObj.setCapacity(0);
		worldObjects.add(new FoodStation(10 + rand.nextInt(41),ColorUtil.rgb(0,255,0), 
					new Point (rand.nextInt(width), rand.nextInt(height))));	
		setChanged();
		notifyObservers(this);

	}
	
	/**
	 * Forced collision between ant-spider. Reduces the health level of the ant
	 * by 1 and checks to see if the ant is now under the maximum speed based on 
	 * health threshold and reduces the speed of the ant to the maximum speed based
	 * on health, then checks to see if the ant has reached 0 health and reduces the
	 * ant lives by 1. If ant lives reach 0, forces the game to end. Notifies the
	 * observers that Ant has changed.
	 */
	public void collidedSpider() 
	{
		Ant aObj = null;
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
		  		if (wgo instanceof Ant) 
		  		{	 
		  			aObj = (Ant)wgo; 
		  		}
		  	}

			if(aObj.getHealthLevel() > 0)
			{
			  	aObj.setHealthLevel(aObj.getHealthLevel() - 1);
			}

		  	double realMax = aObj.getHealthLevel() * .1 * aObj.getMaximumSpeed();
		  	aObj.setColor(ColorUtil.rgb(ColorUtil.red(aObj.getColor()) - 25, 0, 0));
			
			if(aObj.getSpeed() > realMax)
			{
				aObj.setSpeed((int) realMax);
			}
		  
			if(aObj.getHealthLevel() <= 0)
			{
				antLives--;
				worldObjects.empty();
				init();
				aObj.setHealthLevel(10);
				aObj.setColor(ColorUtil.rgb(255, 0, 0));
				aObj.setHeading(0);
				aObj.setSpeed(0);
				aObj.setLocation(new Point(200, 100));
				aObj.setFoodLevel(15);
				aObj.setLastFlagReached(1);
				aObj.setMaximumSpeed(20);
				if(antLives == 0)
				{
					exitFailure();
				}
		    }
			
			setChanged();
			notifyObservers(this);
  			setChanged();
  			notifyObservers(aObj);

	}
	
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
	
	/**
	 * toString method that bundles together all of the game objects toString methods 
	 * in order to display a readable list of information on all game objects
	 */
	public String toString() 
	{
		String description = "\n";
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = (GameObject) theElements.getNext();
			description += wgo;
		}
		return description;
	}
}
