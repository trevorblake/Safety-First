package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

/**
 * MapView is an observer of GameWorld and calls update based on changes that
 * occur within GameWorld and is also a Container used in Game. 
 */
public class MapView extends Container implements Observer{
	
	private GameWorld tgw;
	/**
	 * Constructor that will implements the game map, for now
	 * just an empty box but will be drawn on afterwards
	 */
	public MapView() 
	{
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(204, 0, 0)));
	}
	
	/**
	 * Updates the MapView when the GameWorld changes
	 */
	public void update (Observable o, Object arg) 
	{
		if (arg instanceof GameWorld)
		{
			tgw = (GameWorld)arg;
		}
		repaint();
	}
	
	/**
	 * Add the various GameObjects to the MapView using
	 * their own respective draw methods
	 */
	@Override
	public void paint (Graphics G) 
	{
		super.paint(G);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator theElements = tgw.getWorldObjects().getIterator();
		while (theElements.hasNext()) 
		{  
			GameObject wgo = theElements.getNext();
			if (wgo instanceof IDrawable)
				((IDrawable)wgo).draw(G, pCmpRelPrnt);
		}
	}
	
	public void pointerPressed(int x, int y) {
		x-= getParent().getAbsoluteX();
		y-= getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y); 
		Point pCmpRelPrnt = new Point(getX(), getY());

		if(tgw.getPaused())
		{ 

				IIterator worldShapes = tgw.getWorldObjects().getIterator();
				while(worldShapes.hasNext()) 
				{
					GameObject obj = worldShapes.getNext();

					if(obj instanceof Fixed && ((Fixed)obj).isSelected() && tgw.getPositionOn())
					{
						((GameObject)obj).setLocation(new Point(pPtrRelPrnt.getX() - this.getX(), 
								pPtrRelPrnt.getY() - this.getY()));
						tgw.setPositionOn(false);
					}
				}

				worldShapes = tgw.getWorldObjects().getIterator();
				while(worldShapes.hasNext()) 
				{
					GameObject obj = worldShapes.getNext();
					if(obj instanceof Fixed)
					{ 
						if(((Fixed)obj).contains(pPtrRelPrnt, pCmpRelPrnt))
						{
							((Fixed)obj).setSelected(true);
						}
						else
						{ 
							((Fixed)obj).setSelected(false);
						}
					}
				}
			
		}
		repaint();
	}
}
