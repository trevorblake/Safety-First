package com.mycompany.a1;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Class that takes and holds the sound from given filepath and has methods to
 * play and pause the sound (BGSound uses Runnable to loop the sound)
 */
public class BGSound implements Runnable 
{
	private Media m;
	
	public BGSound(String fileName) 
	{
		if (Display.getInstance().getCurrent() == null)
		{
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
	
		try
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audio/wav", this);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void pause()
	{
		m.pause();
	}
	
	public void play() 
	{
		m.play();
	}
	
	@Override
	public void run() 
	{
		m.setTime(0); 
		m.play();
	}

}
