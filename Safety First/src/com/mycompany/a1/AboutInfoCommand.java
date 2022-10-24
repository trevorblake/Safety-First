package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;


public class AboutInfoCommand extends Command
{
	public AboutInfoCommand(Game game) 
	{
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("About command is invoked...");
		Dialog.show("About", "Trevor Blake A2Prj \n CSC 133 Object-Oriented Computer"
				+ " Graphics Programming - Fall 2022", "Ok", null);
	}
}
