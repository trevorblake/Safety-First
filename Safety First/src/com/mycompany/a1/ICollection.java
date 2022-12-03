package com.mycompany.a1;

/**
 * Interface that implements methods used in GameObjectCollection
 */
public interface ICollection 
{
	public void add(GameObject newObject);
	public IIterator getIterator();
	public void empty();	
}
