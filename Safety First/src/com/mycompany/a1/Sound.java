package com.mycompany.a1;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Class that takes and holds the sound from given filepath 
 * and has methods to play the specific sound when requested
 */
public class Sound 
{
	private Media m;

	public Sound(String fileName) 
	{
		if (Display.getInstance().getCurrent() == null)
		{
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}

		try
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audio/wav");
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void play() 
	{
		m.setTime(0); 
		m.play();
	}
}
