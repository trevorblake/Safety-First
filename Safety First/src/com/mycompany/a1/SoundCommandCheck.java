package com.mycompany.a1;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommandCheck extends Command
{
	private Game myForm;
	public SoundCommandCheck (Game fForm)
	{
		super("Sound");
		myForm = fForm;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (((CheckBox)evt.getComponent()).isSelected())
		{
			myForm.setCheckStatusVal(true);
		}
		else
		{
			myForm.setCheckStatusVal(false);
		}
	}
}
