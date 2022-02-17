

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;




public class Sound
{
	Clip background;
	
	public Sound()
	{
		try 
		{
			System.out.println("here");
			File backgroundfile = new File("./Music/Notturno02.wav");
			AudioInputStream backgroundinput = AudioSystem.getAudioInputStream(backgroundfile);
			this.background = AudioSystem.getClip();
			this.background.open(backgroundinput);
			
			
			
			
		} 
		catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) 
		{
			System.out.println(e);
		}
	}
	public void Background() 
	{
		
		this.background.loop(1);
		
	}	
	
}
