package com.mycompany.a1;

import java.util.ArrayList;

/**
 * Uses the Iterator design pattern in order to create an
 * iterable collection of GameObjects (used in GameWorld)
 * using the ICollection interface
 * @author Trevor Blake
 *
 */
public class GameObjectCollection implements ICollection {
	
	private ArrayList<GameObject> worldObjects; // Collection holder
	
	/**
	 * Constructor that initializes the collection holder
	 */
	public GameObjectCollection() {
		worldObjects = new ArrayList<>();
	}

	/**
	 * Adds new objects to the collection
	 */
	@Override
	public void add(GameObject newObject) {
		worldObjects.add(newObject);	
	}
	
	/**
	 * Returns the GameArrayListIterator
	 */
	@Override
	public IIterator getIterator() {
		return new GameArrayListIterator();
	}
	
	/**
	 * Method used to clear the collection
	 */
	@Override
	public void empty() {
		worldObjects.clear();
	}
	
	/**
	 * Private inner class that handles the collection methods
	 * using the Iterator design pattern
	 * @author Trevor Blake
	 *
	 */
	private class GameArrayListIterator implements IIterator 
	{
		private int currElementIndex;
		public GameArrayListIterator() 
		{
		currElementIndex = -1;
		}
		public boolean hasNext() 
		{
		if (worldObjects.size() <= 0)  
			return false;
		if (currElementIndex == worldObjects.size() - 1)
			return false;
		return true;
		}
		public GameObject getNext ( ) {
		currElementIndex++;
		return(worldObjects.get(currElementIndex));
		}
	}



}
