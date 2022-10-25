package com.mycompany.a1;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Toolbar;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;

/**
 * Game class is called upon from the Starter class
 * and is used to build and manipulate the GameWorld object
 * using various commands
 * @author Trevor Blake
 */
public class Game extends Form 
{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Toolbar myToolbar = new Toolbar();
	
	
	/**
	 * Constructor method for Game that initializes the GameWorld,
	 * MapView, ScoreView, adds observers of GameWorld, and builds 
	 * the general user interface for the user. 
	 */
	public Game() 
	{
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(); // create an Observer for the map
		sv = new ScoreView(); // create an Observer for the game/ant state data
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		//Create the various commands
		Command cAccelerate = new AccelerateCommand(gw);
		Command cBrake = new BrakeCommand(gw);
		Command cLeftTurn = new LeftTurnCommand(gw);
		Command cRightTurn = new RightTurnCommand(gw);
		Command cCollideFlag = new FlagCommand(gw);
		Command cCollideSpider = new SpiderCommand(gw);
		Command cCollideFood = new FoodCommand(gw);
		Command cTick = new TickCommand(gw);
		Command cExit = new ExitCommand(gw);
		Command cToggleSound = new SoundCommandCheck(this);
		Command cAboutInfo = new AboutInfoCommand();
		Command cHelpInfo = new HelpInfoCommand();

		//Create and build the toolbar
		this.setToolbar(myToolbar);
		myToolbar.setTitle("WalkIt Game");
		myToolbar.addCommandToLeftSideMenu(cAccelerate);
		CheckBox checkToggleSound = new CheckBox("Sound");
		checkToggleSound.getAllStyles().setBgTransparency(255);
		checkToggleSound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		checkToggleSound.setCommand(cToggleSound);
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
		MyButton bAccelerate = new MyButton("Accelerate");
		bAccelerate.setCommand(cAccelerate);
		MyButton bLeftTurn = new MyButton("Left");
		bLeftTurn.setCommand(cLeftTurn);
		westContainer.add(bAccelerate).add(bLeftTurn);
		
		//Create and build the southContainer
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		southContainer.getAllStyles().setBgTransparency(255);
		southContainer.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		southContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
		MyButton bCollideFlag = new MyButton("Collide With Flag");
		bCollideFlag.setCommand(cCollideFlag);
		MyButton bCollideSpider = new MyButton("Collide With Spider");
		bCollideSpider.setCommand(cCollideSpider);
		MyButton bCollideFood = new MyButton("Collide With Food Station");
		bCollideFood.setCommand(cCollideFood);
		MyButton bTick = new MyButton("Tick");
		bTick.setCommand(cTick);
		southContainer.add(bCollideFlag).add(bCollideSpider).add(bCollideFood).add(bTick);
		
		//Create and build the eastContainer
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBgTransparency(255);
		eastContainer.getAllStyles().setBgColor(ColorUtil.rgb(240, 240, 240));
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
		MyButton bBrake = new MyButton("Brake");
		bBrake.setCommand(cBrake);
		MyButton bRightTurn = new MyButton("Right");
		bRightTurn.setCommand(cRightTurn);
		eastContainer.add(bBrake).add(bRightTurn);

		//Creates the keybindings for certain commands
		this.addKeyListener('a', cAccelerate);
		this.addKeyListener('b', cBrake);
		this.addKeyListener('l', cLeftTurn);
		this.addKeyListener('r', cRightTurn);
		this.addKeyListener('f', cCollideFood);
		this.addKeyListener('g', cCollideSpider);
		this.addKeyListener('t', cTick);
		this.addKeyListener('x', cExit);
		
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
	}

	/**
	 * Takes the bVal from the SoundCommandCheck and changes the
	 * GameWorld sound value in order to update the ScoreView label
	 * @param bVal comes from the SoundCommandCheck call
	 */
	public void setCheckStatusVal(boolean bVal)
	{
		if (bVal)
		gw.setSound(true);
		else
		gw.setSound(false); 
		revalidate();
	}
}