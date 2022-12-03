package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * Flag class is a child of the Fixed object class
 * and takes the additional field of sequenceNumber
 * @author Trevor Blake
 *
 */
public class Flag extends Fixed 
{
	private int sequenceNumber; // The flag's distinct sequence number

	/**
	 * Constructor for the flag class that takes the sequence number
	 * and location given at instantiation and also gives parameters
	 * to the Fixed parent class such as size of 10, green color, 
	 * and the location that was given at instantiation.
	 * @param sequenceNumber the flag's distinct number to signify the 
	 * 						 order the ant should follow through
	 * @param location the location of the flag
	 */
	public Flag(int sequenceNumber, int size, int color, Point location) 
	{
		super(size, color, location);		
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Getter method to return the sequence number of the flag
	 * @return the sequence number of the flag
	 */
	public int getSequenceNumber() 
	{
		return sequenceNumber;
	}	
	
	/**
	 * Empty setter method to change the color so that other 
	 * classes are not able to alter the color of Flag objects
	 * after instantiation.
	 */
	public void setColor(int x) {}
	
	/**
	 * toString method that returns a string of the data
	 * associated with flags. Such as a flag's location,
	 * color, size, and sequence number.
	 */
	public String toString() 
	{
		return "Flag: loc=" + locationToString() + 
				" color=[" + ColorUtil.red(getColor()) + 
				"," + ColorUtil.green(getColor()) + 
				"," + ColorUtil.blue(getColor()) + 
				"]" + " size=" + getSize() + 
				" seqNum=" + sequenceNumber + "\n";
	}

	/**
	 * Draw method that creates the flag's triangle
	 * shape based on location and adds the sequence
	 * number to the center
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		int x = (int)(this.getLocation().getX() + pCmpRelPrnt.getX());
		int y = (int)(this.getLocation().getY() + pCmpRelPrnt.getY());
		int x1 = x - (getSize()/2);
		int x2 = x + (getSize()/2);
		int x3 = x;
		int y1 = y;
		int y2 = y;
		int y3 = y + getSize();
		int[] xPoints = {x1,x2,x3};
		int[] yPoints = {y1,y2,y3};
		g.setColor(this.getColor());
		if(isSelected())
		{
			g.drawPolygon(xPoints, yPoints, 3);
		}
		else
		{
			g.fillPolygon(xPoints, yPoints, 3);
			setSelected(false);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+sequenceNumber, x-10, y+15);
	}

	/**
	 * Used to call the collidesWith method of ant.
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) 
	{
		if(otherObject instanceof Ant)
		{
			return (((Ant)otherObject).collidesWith(this));
		}
		return false;
	}

	/**
	 * Used to call the handleCollision method of ant.
	 */
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) 
	{
		if (otherObject instanceof Ant)
		{
			((Ant)otherObject).handleCollision(this, gw);
		}
	}

	/**
	 * Setter method for setting if the object is selected
	 */
	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
	}

	/**
	 * Getter method for checking if the object is selected
	 */
	@Override
	public boolean isSelected() {
		return super.isSelected();
	}

	/**
	 * Calls the contains method of Fixed with same parameters
	 */
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		return super.contains(pPtrRelPrnt, pCmpRelPrnt);
	}
}
