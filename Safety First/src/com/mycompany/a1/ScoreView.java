package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer 
{
	private Label lTime = new Label();
	private Label lLivesLeft = new Label();
	private Label lLastFlagReached = new Label();
	private Label lFoodLevel = new Label();
	private Label lHealthLevel = new Label();
	private Label lSound = new Label();
	public ScoreView() 
	{
		this.setLayout(new FlowLayout(Component.CENTER));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		this.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		lTime.setText("Time: ");
		lLivesLeft.setText("Lives Left: ");
		lLastFlagReached.setText("Last Flag Reached: ");
		lFoodLevel.setText("Food Level: ");
		lHealthLevel.setText("Health Level: ");
		lSound.setText("Sound: ");
		lTime.getAllStyles().setFgColor(ColorUtil.BLUE);
		lLivesLeft.getAllStyles().setFgColor(ColorUtil.BLUE);
		lLastFlagReached.getAllStyles().setFgColor(ColorUtil.BLUE);
		lFoodLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lHealthLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lSound.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(lTime).add(lLivesLeft).add(lLastFlagReached);
		this.add(lFoodLevel).add(lHealthLevel).add(lSound);
		revalidate();
	}
	public void update (Observable o, Object arg) 
	{
		// code here to update labels from the game/ant state data
		
		if(arg instanceof Ant)
		{
			int lastFlag = ((Ant) arg).getLastFlagReached();
			int foodLevel = ((Ant) arg).getFoodLevel();
			int healthLevel = ((Ant) arg).getHealthLevel();

			lLastFlagReached.setText("Last Flag Reached: " + lastFlag);
			lFoodLevel.setText("Food Level: " + foodLevel);
			lHealthLevel.setText("Health Level: " + healthLevel);
		}
		
		if(arg instanceof GameWorld)
		{
			int time = ((GameWorld) arg).getTime();
			int livesLeft = ((GameWorld) arg).getLives();
			boolean sound = ((GameWorld) arg).getSound();
			lTime.setText("Time: " + time);
			lLivesLeft.setText("Lives Left: " + livesLeft);
			if(sound)
			{
				lSound.setText("Sound: ON");
			}
			
			else
			{
				lSound.setText("Sound: OFF");
			}
			
		}
		revalidate();
	}

}
