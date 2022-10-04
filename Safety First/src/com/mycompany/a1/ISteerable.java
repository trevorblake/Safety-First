package com.mycompany.a1;

/**
 * ISteerable interface helps with steering manipulation
 * of in-game objects. Useful for when other classes want
 * to change the heading of objects
 * @author Trevor Blake
 *
 */
public interface ISteerable 
{	/**
	* This method is built so that other classes are able to 
	* change the heading of any object implementing ISteerable 
	* by a positive or negative integer denoted by headingAdjust
	*/
	public void adjustHeading(int headingAdjust);
}
