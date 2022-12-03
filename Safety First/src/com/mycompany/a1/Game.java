package com.mycompany.a1;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.util.UITimer;

/**
 * Game class is called upon from the Starter class
 * and is used to build and manipulate the GameWorld object
 * using various commands
 * @author Trevor Blake
 */
public class Game extends Form implements Runnable
{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private static int TIME=20;
	private Toolbar myToolbar = new Toolbar();
	private MyButton bPosition, bPausePlay, bAccelerate, bBrake, bLeft, bRight;
	private Command cPosition, cPausePlay, cAccelerate, cBrake, cLeft, cRight,
					cExit, cToggleSound, cAboutInfo, cHelpInfo;
	private CheckBox checkToggleSound;
	private boolean soundEnabled; // used to check if sound was enabled prior to pressing pause
	
	
	/**
	 * Constructor method for Game that initializes the GameWorld,
	 * MapView, ScoreView, adds observers of GameWorld, and builds 
	 * the general user interface for the user. 
	 */
	public Game() 
	{
		timer = new UITimer(this);
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/ant state data
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		//Create the various commands
		cAccelerate = new AccelerateCommand(gw);
		cBrake = new BrakeCommand(gw);
		cLeft = new LeftTurnCommand(gw);
		cRight = new RightTurnCommand(gw);
		cPosition = new PositionCommand(gw);

		cPausePlay = new PausePlayCommand(this);
		cExit = new ExitCommand(gw);
		cToggleSound = new SoundCommandCheck(this);
		cAboutInfo = new AboutInfoCommand();
		cHelpInfo = new HelpInfoCommand();

		//Create and build the toolbar
		this.setToolbar(myToolbar);
		myToolbar.setTitle("WalkIt Game");
		checkToggleSound = new CheckBox("Sound");
		checkToggleSound.getAllStyles().setBgTransparency(255);
		checkToggleSound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		checkToggleSound.setCommand(cToggleSound);
		soundEnabled = true;
		myToolbar.addComponentToLeftSideMenu(checkToggleSound);
		myToolbar.addCommandToLeftSideMenu(cAboutInfo);
		myToolbar.addCommandToLeftSideMenu(cExit);
		myToolbar.addCommandToRightBar(cHelpInfo);
		
		//Create and build the westContainer
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		westContainer.getAllStyles().setBgTransparency(255);
		westContainer.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		westContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
		bAccelerate = new MyButton("Accelerate");
		bAccelerate.setCommand(cAccelerate);
		bLeft = new MyButton("Left");
		bLeft.setCommand(cLeft);
		westContainer.add(bAccelerate).add(bLeft);
		
		//Create and build the southContainer
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		southContainer.getAllStyles().setBgTransparency(255);
		southContainer.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		southContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
		bPosition = new MyButton("Position");
		bPosition.setCommand(cPosition);
		bPausePlay = new MyButton("Pause");
		bPausePlay.setCommand(cPausePlay);
		southContainer.add(bPosition).add(bPausePlay);
		
		//Create and build the eastContainer
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBgTransparency(255);
		eastContainer.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
		bBrake = new MyButton("Brake");
		bBrake.setCommand(cBrake);
		bRight = new MyButton("Right");
		bRight.setCommand(cRight);
		eastContainer.add(bBrake).add(bRight);
		
		//Builds the Game GUI using the containers and ScoreView and MapView
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.CENTER, mv);
		this.show();

		//Sets the size of the GameWorld based on the MapView size
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init();
		gw.createSounds();
		setCheckStatusVal(true);
		revalidate();
		play();
		
		
	}

	/**
	 * Takes the bVal and changes the GameWorld sound value and 
	 * plays/pauses the background sound and also updates the ScoreView label
	 * @param bVal comes from the SoundCommandCheck call
	 */
	public void setCheckStatusVal(boolean bVal)
	{
		gw.setSound(bVal);
		if(bVal)
			gw.getBackgroundSound().play();
		else
			gw.getBackgroundSound().pause();
		revalidate();
	}
	
	/**
	 * Setter method to set soundEnabled and also
	 * change the GameWorld's sound as well
	 * @param soundEnabled sound value
	 */
	public void setSoundEnabled(boolean soundEnabled) 
	{
		this.soundEnabled = soundEnabled;
		gw.setSound(soundEnabled);
	}
	
	/**
	 * Updates every time the timer goes off from the play method
	 * and creates a dimension of the GameWorld size and width and 
	 * passes it to the GameWorld tick method along with the TIME
	 */
	@Override
	public void run() 
	{
		Dimension dCmpSize = new Dimension(gw.getWidth(), gw.getHeight());
		gw.tick(TIME, dCmpSize);
	}
	
	/**
	 * Pauses the game by canceling the timer and disables 
	 * various buttons and keyboard presses
	 */
	public void pause() 
	{
		//stop timer and disable buttons
		this.timer.cancel();
		bPausePlay.setText("Play");
		bAccelerate.disable();
		bBrake.disable();
		bLeft.disable();
		bRight.disable();
		bPosition.enable();
		checkToggleSound.setEnabled(false);
		
		//deselect the sound checkbox and turn off sound
		checkToggleSound.setSelected(false);
		setCheckStatusVal(false);
		
		//remove keyboard listeners
		this.removeKeyListener('a', cAccelerate);
		this.removeKeyListener('b', cBrake);
		this.removeKeyListener('l', cLeft);
		this.removeKeyListener('r', cRight);
		
		//remove accelerate from toolbar
		myToolbar.removeCommand(cAccelerate);
		
		//set gameworld paused to true
		gw.setPaused(true);
		revalidate();
	}
		
	/**
	 * Plays the game by scheduling the timer and  
	 * enables various buttons and keyboard presses
	 */
	public void play() 
	{
		//start timer and enable buttons
		timer.schedule(TIME, true, this);
		bPausePlay.setText("Pause");
		bAccelerate.enable();
		bBrake.enable();
		bLeft.enable();
		bRight.enable();
		bPosition.disable();
		checkToggleSound.setEnabled(true);
		
		//if sound is enabled, set checkbox to checked and turn on sound
		if (soundEnabled) 
		{
			setCheckStatusVal(true);
			checkToggleSound.setSelected(true);
		}
		
		//add keyboard listeners
		this.addKeyListener('a', cAccelerate);
		this.addKeyListener('b', cBrake);
		this.addKeyListener('l', cLeft);
		this.addKeyListener('r', cRight);
		this.addKeyListener('x', cExit);
		
		//add accelerate command to toolbar
		myToolbar.addCommandToLeftSideMenu(cAccelerate);
		
		//set gameworld paused to false
		gw.setPaused(false);
		
		//deselects all fixed objects
		IIterator objects = gw.getWorldObjects().getIterator();
		while(objects.hasNext())
		{
			GameObject obj = objects.getNext();
			if(obj instanceof Fixed)
			{
				((Fixed)obj).setSelected(false);
			}
		}
		revalidate();
	}
}