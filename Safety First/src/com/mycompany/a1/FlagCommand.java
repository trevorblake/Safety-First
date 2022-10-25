package com.mycompany.a1;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

/**
 * Calls the collidedFlag GameWorld method based on action event
 * Uses a dialog box to ask for the next flag number, and if it
 * is the correct next flag, displays another dialog box indicating
 * the flag was hit, and displays a dialog box if the flag was 
 * incorrect as well
 */
public class FlagCommand extends Command
{
	private GameWorld tgw;
	public FlagCommand(GameWorld gw) 
	{
		super("Collide With Flag");
		tgw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[]{cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter the flag number:", myTF, cmds);
		if (c == cOk)
		{
			if (!myTF.getText().equals(Integer.toString(tgw.getNextFlag())))
			{
				Dialog.show("Incorrect Input", "Type \"" + tgw.getNextFlag() +
						"\" to hit the correct flag.", "Ok", null);
			}
			
			else
			{
				tgw.collidedFlag(Integer.parseInt(myTF.getText())); 
				Dialog.show("Flag hit!", "Ant has moved onto flag " + 
				myTF.getText() + ".", "Ok", null);
			}
		}	
	}
}
