package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command
{
	private GameWorld tgw;
	public TickCommand(GameWorld gw) 
	{
		super("Tick");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.update();
		System.out.println(tgw.toString());
		System.out.println("\nThe game clock has ticked.");
	}
}
