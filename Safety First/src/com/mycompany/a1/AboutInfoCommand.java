package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Shows a dialog box that contains basic info about
 * the project version and the author and class
 * @author Trevor Blake
 *
 */
public class AboutInfoCommand extends Command
{
	public AboutInfoCommand() 
	{
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Dialog.show("About", "Trevor Blake A3Prj \n CSC 133 Object-Oriented Computer"
				+ " Graphics Programming - Fall 2022", "Ok", null);
	}
}
