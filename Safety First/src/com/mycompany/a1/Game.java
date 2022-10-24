package com.mycompany.a1;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Label; 
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import java.lang.String;

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
	 * Constructor method for Game that instantiates and
	 * initializes the GameWorld for the user.
	 */
	public Game() 
	{
		gw = new GameWorld(); // create “Observable” GameWorld
		mv = new MapView(); // create an “Observer” for the map
		sv = new ScoreView(); // create an “Observer” for the game/ant state data
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer
		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
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
		Command cAboutInfo = new AboutInfoCommand(this);
		Command cHelpInfo = new HelpInfoCommand(this);


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

		
		this.addKeyListener('a', cAccelerate);
		this.addKeyListener('b', cBrake);
		this.addKeyListener('l', cLeftTurn);
		this.addKeyListener('r', cRightTurn);
		this.addKeyListener('f', cCollideFood);
		this.addKeyListener('g', cCollideSpider);
		this.addKeyListener('t', cTick);
		this.addKeyListener('x', cExit);
		
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.CENTER, mv);
		this.show();

		System.out.println(mv.getWidth() + " mv " + mv.getHeight());
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init();
	}


	public void setCheckStatusVal(boolean bVal)
	{
		if (bVal)
		gw.setSound(true);
		else
		gw.setSound(false); 
		revalidate();
	}
}