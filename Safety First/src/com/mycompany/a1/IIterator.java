package com.mycompany.a1;

/**
 * Interface that implements methods used in GameObjectCollection
 * specifically in the private inner class GameArrayListIterator
 * @author Trevor Blake
 *
 */
public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
}
