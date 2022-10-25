package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the accelerate GameWorld method based on action event
 */
public class AccelerateCommand extends Command
{
	private GameWorld tgw;
	public AccelerateCommand(GameWorld gw) 
	{
		super("Accelerate");
		tgw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.accelerate();  
	}
}