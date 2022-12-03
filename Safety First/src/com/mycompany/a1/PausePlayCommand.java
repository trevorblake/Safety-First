package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the play or pause method of Game
 */
public class PausePlayCommand extends Command
{
	private Game tg;
	private boolean play;
	public PausePlayCommand(Game game) 
	{
		super("Pause");
		tg = game;
		play = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (play)
		{
			tg.pause();
			play = false;
		}
		
		else 
		{
			tg.play();
			play = true;
		}
	}
}
