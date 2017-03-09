//For the sound
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.Clip;

public class SoundPlayer{
	
	public void playSound(String soundFileName){
			try {
				File playedSound = new File(soundFileName);
				AudioInputStream stream;
				AudioFormat format;
				DataLine.Info info;
				Clip clip;
			
				stream = AudioSystem.getAudioInputStream(playedSound);
				format = stream.getFormat();
				info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(stream);
				clip.start();
			}
			//Works only with 16 bit signed WAV file
			catch (Exception e) {
				System.err.println("An unexpected audio error has occured! Perhaps check to see if there are WAV in your game folder.");
			}
	}
	
	
}