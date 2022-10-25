package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the turnAnt GameWorld method based on action event
 */
public class RightTurnCommand extends Command
{
	private GameWorld tgw;
	public RightTurnCommand(GameWorld gw) 
	{
		super("Right");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.turnAnt(5);
	}
}
