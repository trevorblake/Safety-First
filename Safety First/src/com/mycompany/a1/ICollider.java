package com.mycompany.a1;

/**
 * Interface that implements methods used in GameObject collision handling
 */
public interface ICollider 
{
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject, GameWorld gw);
}
