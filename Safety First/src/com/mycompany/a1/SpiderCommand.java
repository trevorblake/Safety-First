package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCommand extends Command
{
	private GameWorld tgw;
	public SpiderCommand(GameWorld gw) 
	{
		super("Collide With Spider");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		tgw.collidedSpider();
		System.out.println("\nThe ant has collided with a spider.");
	}
}