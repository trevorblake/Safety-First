package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

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
		tgw.turnAnt(-5);
		System.out.println("\nThe ant has changed heading 5 degrees to the left.");
	}
}
