package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

/**
 * MapView is an observer of GameWorld and calls update based on changes that
 * occur within GameWorld and is also a Container used in Game. 
 * @author Trevor Blake
 *
 */
public class MapView extends Container implements Observer{
	
	private String s = ""; // Empty string used for comparison
	
	/**
	 * Constructor that will eventually implement the game map, for now
	 * just an empty box
	 */
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(204, 0, 0)));
	}
	
	/**
	 * Updates the MapView only when the GameWorld toString() changes
	 */
	public void update (Observable o, Object arg) 
	{
		if(!o.toString().equals(s))
		{
			s = o.toString();
			System.out.println(s);
		}
	}
}
