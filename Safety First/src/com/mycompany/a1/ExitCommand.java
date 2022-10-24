package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command
{
	public ExitCommand(GameWorld gw) 
	{
		super("Exit");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("Exit command is invoked...");
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", 
		"Ok", "Cancel");
		if (bOk)
		{
		Display.getInstance().exitApplication();
		}
	}
}
