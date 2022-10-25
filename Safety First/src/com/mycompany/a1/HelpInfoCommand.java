package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Displays a dialog box with information regarding
 * keyboard shortcuts with an "Ok" button to exit the 
 * dialog box
 */
public class HelpInfoCommand extends Command
{

	public HelpInfoCommand() {
		super("Help");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Dialog.show("Commands", "a: accelerate the ant, b: brake the ant,"
				+ " l: left turn, r: right turn, f: collide with foodstation, "
				+ "g: collide with spider, t: tick, x: exit", "Ok", null);
	}
}