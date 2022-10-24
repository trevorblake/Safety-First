package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

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
		System.out.println("\nThe ant has changed heading 5 degrees to the right.");
	}
}
