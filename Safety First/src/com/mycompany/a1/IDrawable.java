package com.mycompany.a1;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * Interface that implements a method used to draw GameObjects
 */
public interface IDrawable 
{
	public void draw(Graphics g, Point pCmpRelPrnt);
	
}
