package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the turnAnt GameWorld method based on action event
 * and gives the parameter of -10 for turnAnt()
 */
public class LeftTurnCommand extends Command
{
	private GameWorld tgw;
	public LeftTurnCommand(GameWorld gw) 
	{
		super("Left");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.turnAnt(-10);
	}
}
