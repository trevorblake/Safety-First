package com.mycompany.a1;

import com.codename1.charts.models.Point;

/**
 * Fixed abstract class is the parent class of any
 * in-game objects that must not change location after
 * being instantiated. Parent class is GameObject.
 * @author Trevor Blake
 *
 */
public abstract class Fixed extends GameObject implements ISelectable
{

	private boolean selected;
	/**
	 * Constructor for fixed game objects, takes
	 * the parameters of size, color, and location
	 * of the object and sends them to GameObject to
	 * be built
	 * @param size the size of the object
	 * @param color the color of the object
	 * @param location the location of the object
	 */
	public Fixed(int size, int color, Point location) 
	{	
		super(size,color,location);
		selected = false;
	}
	
	/**
	 * Setter method for setting if the object is selected
	 */
	@Override
	public void setSelected(boolean b) 
	{
		selected = b; 	
	}

	/**
	 * Getter method for checking if the object is selected
	 */
	@Override
	public boolean isSelected() 
	{
		return selected;
	}
	
	/**
	 * Method that checks to see if the pointer was within
	 * the objects shape and returns true if it is 
	 */
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) 
	{
		int px = (int)pPtrRelPrnt.getX();
		int py = (int)pPtrRelPrnt.getY();
		int iShapeX = (int)(getLocation().getX() - getSize()/2);		
		int iShapeY = (int)(getLocation().getY() - getSize()/2);
		
		int xLoc = (int)(pCmpRelPrnt.getX() + iShapeX);
		int yLoc = (int)(pCmpRelPrnt.getY() + iShapeY);
		
		if ((px >= xLoc) && (px <= xLoc + getSize()) 
		 && (py >= yLoc) && (py <= yLoc + getSize()))
		{
			return true;		
		}
		else
		{
			return false;
		}
	}

}
