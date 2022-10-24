package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpInfoCommand extends Command
{
	public HelpInfoCommand(Game game) 
	{
		super("Help");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("help command is invoked...");
		Dialog.show("Commands", "a: accelerate the ant, b: brake the ant, l: left turn, r: right turn"
				+ ", f: collide with foodstation, g: collide with spider, t: tick, x: exit", "Ok", null);
	}
}