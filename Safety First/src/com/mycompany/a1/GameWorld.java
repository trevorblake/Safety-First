package com.mycompany.a1;

import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;

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
	private boolean sound; // Determines if sound is off or on 
	private boolean paused; // Determines if the game is paused
	private boolean positionOn; // Determines if position button is selected
	private BGSound backgroundSound; // Sound for background music
	private Sound foodSound, spiderSound, flagSound; // Object collision sounds
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
		sound = false;
		paused = false;
		positionOn = false;
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
		worldObjects.add(new Flag(1, 100, ColorUtil.GREEN, new Point(200, 100)));
		worldObjects.add(new Flag(2, 100, ColorUtil.GREEN, new Point(width-200, 200)));
		worldObjects.add(new Flag(3, 100, ColorUtil.GREEN, new Point(300, height-200)));
		worldObjects.add(new Flag(4, 100, ColorUtil.GREEN, new Point(width-300, 800)));
		Ant a = Ant.getAnt(100, ColorUtil.rgb(255, 0, 0), new Point(200, 100), 0, 100);
		worldObjects.add(a);
		worldObjects.add(new Spider(100 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (100+rand.nextInt(width-200), 100+rand.nextInt(height-200)), 
				rand.nextInt(360), 150 + rand.nextInt(150)));
		worldObjects.add(new Spider(100 + rand.nextInt(41),ColorUtil.rgb(0, 0, 0), 
				new Point (100+rand.nextInt(width-200), 100+rand.nextInt(height-200)), 
				rand.nextInt(360), 100 + rand.nextInt(150)));
		worldObjects.add(new FoodStation(100 + rand.nextInt(51),ColorUtil.rgb(0,255,0), 
				new Point (100+rand.nextInt(width-200), 100+rand.nextInt(height-200))));
		worldObjects.add(new FoodStation(100 + rand.nextInt(51),ColorUtil.rgb(0,255,0), 
				new Point (100+rand.nextInt(width-200), 150+rand.nextInt(height-200))));
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
		int increaseBy = 10 + rand.nextInt(3);
		
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
		int decreaseBy = 10 + rand.nextInt(3);
		
		IIterator theElements = worldObjects.getIterator();
		while ( theElements.hasNext() ) {  
		GameObject wgo = theElements.getNext();
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
		GameObject wgo = theElements.getNext();
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
	 * Updates the game world by increasing the gameClock by time,
	 * reducing the ant's food level by 1, moving all Movable objects,
	 * checks for new collisions by using an internal GameObject vector,
	 * and checking to see if the ant runs out of food or health, which 
	 * could consume a life and end the game if all 3 lives have been
	 * consumed then notifies observers of Ant and GameWorld changes
	 * @param dCmpSize 
	 */
	public void tick(int time, Dimension dCmpSize) 
	{
		gameClock+=time;
		Ant aObj = null;
		
		IIterator theElements = worldObjects.getIterator();
		while (theElements.hasNext()) 
		{  
		GameObject wgo = theElements.getNext();
	  		if (wgo instanceof Ant) 
	  		{ 
	  			aObj = (Ant)wgo; 
	  			aObj.setFoodLevel(aObj.getFoodLevel() - 1);
	  			aObj.move(time, dCmpSize);
	  		}   
	  		
	  		if (wgo instanceof Spider) 
		  	{ 
		  		Spider sObj = (Spider)wgo; 
		  		sObj.move(time, dCmpSize);				
		  	}
		}
		
		theElements = worldObjects.getIterator();
		while(theElements.hasNext()){
			GameObject curObj = theElements.getNext();
			IIterator theElements2 = worldObjects.getIterator();
			while(theElements2.hasNext())
			{	
				GameObject otherObj = theElements2.getNext();
				if(otherObj!=curObj)
				{
					//if colliding but not on colliding list
					if(curObj.collidesWith(otherObj) && !curObj.checkList(otherObj))
					{
						curObj.handleCollision(otherObj, this);
						curObj.addToList(otherObj);
						otherObj.addToList(curObj);
					}
					
					//if not colliding but on colliding list
					if(!curObj.collidesWith(otherObj) && curObj.checkList(otherObj))
					{
						curObj.removeFromList(otherObj);
						otherObj.removeFromList(curObj);
					}
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
			aObj.setSpeed(100);
			aObj.setLocation(new Point(200, 100));
			aObj.setFoodLevel(3000);
			aObj.setLastFlagReached(1);
			aObj.setMaximumSpeed(300);
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
	 * Ant-flag collision that first checks to see if the flag is next in 
	 * the ant's sequence, if so it then increases the flagSequenceNumber
	 * of the ant object, and checking to see if the ant has passed through
	 * the final flag object, which ends the game. Notifies observers
	 * of the change to the Ant object if the lastFlagReached is updated.
	 * Plays a sound when invoked and ant collides with proper flag.
	 * @param x flag number given
	 */
	public void collidedFlag(Ant aObj, Flag fObj) 
	{
		int totalFlags = 0;

		IIterator theElements = worldObjects.getIterator();
		while (theElements.hasNext()) 
		{  
			GameObject wgo = theElements.getNext();
			if (wgo instanceof Flag) 
			{ 
				totalFlags++; 
			}
		}

		if(aObj.getLastFlagReached() == fObj.getSequenceNumber() - 1)
		{
			aObj.setLastFlagReached(fObj.getSequenceNumber());
			setChanged();
			notifyObservers(aObj);
			if(sound)
			{
				flagSound.play();
			}
			
			if(aObj.getLastFlagReached() == totalFlags)
			{
				exitSuccess();
			}
		}	 

	}
	
	/**
	 *  Handles ant-foodstation collision by adding the capacity
	 *  of the foodstation to ant's food level and then sets the
	 *  foodstation capacity to 0 and adds a new foodstation
	 *  into the gameworld. Plays a sound when invoked.
	 */
	public void collidedFood(Ant aObj, FoodStation fObj) 
	{
		if(fObj.getCapacity() != 0) {
			Random rand = new Random();
			aObj.setFoodLevel(aObj.getFoodLevel() + fObj.getCapacity());
			setChanged();
			notifyObservers(aObj);
			fObj.setColor(ColorUtil.rgb(0, 100, 0));
			fObj.setCapacity(0);
			worldObjects.add(new FoodStation(100 + rand.nextInt(51),ColorUtil.rgb(0,255,0), 
					new Point (100+rand.nextInt(width-200), 100+rand.nextInt(height-200))));	
			setChanged();
			notifyObservers(this);
			if (sound)
			{
				foodSound.play();
			}
		}
	}
	
	/**
	 * Handles collision between ant-spider. Reduces the health level of the ant
	 * by 1 and checks to see if the ant is now under the maximum speed based on 
	 * health threshold and reduces the speed of the ant to the maximum speed based
	 * on health, then checks to see if the ant has reached 0 health and reduces the
	 * ant lives by 1. If ant lives reach 0, forces the game to end. Notifies the
	 * observers that Ant has changed. Plays a sound when invoked.
	 */
	public void collidedSpider(Ant aObj) 
	{
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
				aObj.setSpeed(100);
				aObj.setLocation(new Point(200, 100));
				aObj.setFoodLevel(3000);
				aObj.setLastFlagReached(1);
				aObj.setMaximumSpeed(300);
				if(antLives == 0)
				{
					exitFailure();
				}
		    }	
			setChanged();
			notifyObservers(this);
  			setChanged();
  			notifyObservers(aObj);
  			if (sound)
  			{
  				spiderSound.play();
  			}

	}
	
	/**
	 * Creates the sound objects to be used in other methods
	 */
	public void createSounds() 
	{
		backgroundSound = new BGSound("background.mp3");
		foodSound = new Sound("food.mp3");
		spiderSound = new Sound("spider.mp3");
		flagSound = new Sound("flag.mp3");
	}
	
	/**
	 * Getter method for the backgroundSound
	 * @return backgroundSound
	 */
	public BGSound getBackgroundSound()
	{
		return backgroundSound;
	}
	
	/**
	 * Method used to grab the collection of GameObjects
	 * @return GameObject collection
	 */
	public GameObjectCollection getWorldObjects() 
	{
		return worldObjects;
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
		System.out.println("\nGame over, you win! Total time: " + gameClock + "ms");
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
		GameObject wgo = theElements.getNext();
			description += wgo;
		}
		return description;
	}

	/**
	 * Setter method for paused boolean
	 * @param paused given to change this paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	/**
	 * Getter method for paused boolean
	 * @return paused boolean
	 */
	public boolean getPaused() {
		return paused;
	}
	
	/**
	 * Setter method for positionOn boolean
	 * @param position given to change this positionOn
	 */
	public void setPositionOn(boolean position) {
		this.positionOn = position;
	}
	
	/**
	 * Getter method for positionOn boolean
	 * @return positionOn boolean
	 */
	public boolean getPositionOn() {
		return positionOn;
	}
}
