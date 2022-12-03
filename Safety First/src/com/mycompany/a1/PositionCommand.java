package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the collidedFlag GameWorld method based on action event
 * Uses a dialog box to ask for the next flag number, and if it
 * is the correct next flag, displays another dialog box indicating
 * the flag was hit, and displays a dialog box if the flag was 
 * incorrect as well
 */
public class PositionCommand extends Command
{
	private GameWorld tgw;
	public PositionCommand(GameWorld gw) 
	{
		super("Position");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.setPositionOn(!tgw.getPositionOn());
	}
}
