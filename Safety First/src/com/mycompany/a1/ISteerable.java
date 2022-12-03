package com.mycompany.a1;

/**
 * ISteerable interface helps with steering manipulation
 * of in-game objects. Useful for when other classes want
 * to change the heading of objects
 */
public interface ISteerable 
{	
	public void adjustHeading(int headingAdjust);
}
