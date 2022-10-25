package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * Brings up a dialog box asking for a quit confirmation
 * with two options "Ok" and "Cancel"
 * @author Trevor Blake
 *
 */
public class ExitCommand extends Command
{
	public ExitCommand(GameWorld gw) 
	{
		super("Exit");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", 
		"Ok", "Cancel");
		if (bOk)
		{
		Display.getInstance().exitApplication();
		}
	}
}
