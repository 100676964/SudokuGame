import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundEffects 
{
	
	
	public void Click()
	{
		try 
		{
			File clickfile = new File("./SoundEffects/Click.wav");
			AudioInputStream clickinput = AudioSystem.getAudioInputStream(clickfile);
			Clip click = AudioSystem.getClip();
			click.open(clickinput);
			FloatControl volume = (FloatControl) click.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-10);
			click.start();
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
		{
			e.printStackTrace();
		}
	}
	public void Hover()
	{
		try 
		{
			File Hoverfile = new File("./SoundEffects/Hover.wav");
			AudioInputStream Hoverinput = AudioSystem.getAudioInputStream(Hoverfile);
			Clip Hover = AudioSystem.getClip();
			
			Hover.open(Hoverinput);
			FloatControl volume = (FloatControl) Hover.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-30);
			
			Hover.start();
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
		{
			e.printStackTrace();
		}
	}
}
