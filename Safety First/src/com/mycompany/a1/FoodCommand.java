package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


/**
 * Calls the collidedFood GameWorld method based on action event
 */
public class FoodCommand extends Command
{
	private GameWorld tgw;
	public FoodCommand(GameWorld gw) 
	{
		super("Collide With Food");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.collidedFood();
	}
}
