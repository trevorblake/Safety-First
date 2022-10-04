package com.mycompany.a1;
import com.codename1.ui.events.ActionListener; 
import com.codename1.ui.Label; 
import com.codename1.ui.TextField; 
import com.codename1.ui.events.ActionEvent; 
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
	
	/**
	 * Constructor method for Game that instantiates and
	 * initializes the GameWorld for the user.
	 */
	public Game() 
	{
		gw = new GameWorld();
		gw.init();
		play();
	}

	/**
	 * Play method runs while the GameWorld is active and
	 * is used to manipulate the GameWorld through various
	 * commands.
	 */
	@SuppressWarnings("rawtypes")
	private void play() 
	{ 
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel); 
		final TextField myTextField=new TextField(); 
		this.addComponent(myTextField); 
		this.show();
		
		myTextField.addActionListener(new ActionListener()
		{ 
			boolean xGiven = false; // If true, means user entered 'x'
			public void actionPerformed(ActionEvent evt) 
			{ 		

				String sCommand=myTextField.getText().toString(); 
				myTextField.clear();
				
				if(xGiven == true && sCommand.length() != 0) // Used when user has entered 'x'
				{											 // Forces user to either type 'y' or 'n'
															 // Other commands do not work until typing 'n'
					switch (sCommand.charAt(0))
					{
					case 'y': // Exits out of the game given a 'y' input
						System.out.println("\nSee you later!");
						gw.exit();
						break;
						
					case 'n': // Returns to the game given a 'x' input
						xGiven = false;
						System.out.println("\nYou are now back in the game!");
						break;
						
					default: 
						System.out.println("\nPlease type 'y' to exit or 'n' to continue...");
						break;
					}
				}
				else if (xGiven == false && sCommand.length() != 0) // Runs as long as user input is not 'x'
				{
					switch (sCommand.charAt(0)) 
					{ 
					case '1': case '2': case '3':
					case '4': case '5': case '6':
					case '7': case '8': case '9': // User enters '1...9' to force ant-flag collision
						gw.collidedFlag(sCommand.charAt(0) - '0'); 
						break;
					
					case 'a': // User enters 'a' to increase speed of the ant
						gw.accelerate();  
						System.out.println("\nThe ant has accelerated.");
						break;
						
					case 'b': // User enters 'b' to decrease speed of the ant
						gw.brake(); 
						System.out.println("\nThe ant has slowed down.");
						break;
						
					case 'd': // User enters 'd' to print additional information on the ant and game
						System.out.println(gw.gameInfo()); 
						break;
						
					case 'f': // User enters 'f' to force ant-foodstation collision
						gw.collidedFood();
						System.out.println("\nThe ant has collided with a food station.");
						break;
						
					case 'g': // User enters 'g' to force ant-spider collision
						gw.collidedSpider();
						System.out.println("\nThe ant has collided with a spider.");						
						break;
						
					case 'l': // User enters 'l' to turn ant 5 degrees left
						gw.turnAnt(-5);
						System.out.println("\nThe ant has changed heading 5 degrees to the left.");
						break;
						
					case 'm': // User enters 'm' to print information about the game world objects
						System.out.println(gw);
						break;
						
					case 'r': // User enters 'r' to turn ant 5 degrees right
						gw.turnAnt(5);
						System.out.println("\nThe ant has changed heading 5 degrees to the right.");						
						break;
						
					case 't': // User enters 't' to move the game clock up one tick
						gw.update();
						System.out.println("\nThe game clock has ticked.");
						break;
						
					case 'x': // User enters 'x' to begin the exit prompt
						xGiven = true;
						System.out.println("\nPlease type 'y' to exit or 'n' to continue...");
						break; 
					
					}
				}	//add code to handle rest of the commands  
					
			} //switch 
			
		} //actionPerformed 
		 //new ActionListener() 
		
	); //addActionListener 
	} //play 
}
