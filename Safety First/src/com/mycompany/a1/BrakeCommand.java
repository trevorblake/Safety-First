package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command
{
	private GameWorld tgw;
	public BrakeCommand(GameWorld gw) 
	{
		super("Brake");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.brake(); 
		System.out.println("\nThe ant has slowed down.");
	}
}
