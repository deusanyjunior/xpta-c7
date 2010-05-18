package mp3;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Mp3 implements Runnable {
	
	// Número de bytes correspondentes a um segundo; 
	private final int offset = 40000;
	
	private File file = null;
	private String filepath = null;
	private AudioInputStream in = null;
	private AudioInputStream din = null;
	private AudioFormat decodedFormat = null;
	private SourceDataLine line = null;
	private int start;
	
	public Mp3(String filepath, int start)
	{
		this.filepath = filepath;
		this.start = start;
	}

	public void run()
	{
		loadTrack();
	}

	public void loadTrack()
	{
		try
		{
			file = new File(filepath);
			in = AudioSystem.getAudioInputStream(file);
	
			AudioFormat baseFormat = in.getFormat();
			decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			System.out.println(baseFormat.getSampleRate());
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			// Salta para iniciar a música no startº byte.
			din.skip(start*offset);
			
			play();
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException f)
		{
			f.printStackTrace();
		}
	}
	
	public void play() {
		try {
			// Play now.
			rawplay(decodedFormat, din);
			in.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		
		byte[] data = new byte[4096];
		line = getLine(targetFormat);
		if (line != null) {
			// Start playing
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)
					nBytesWritten = line.write(data, 0, nBytesRead);
			}
			// Stop playing
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

	/** Método utilizado para equalizar a faixa.
	 *  
	 * @param values Array do tipo float com 32 posições,
	 *  correspondentes às fresquências de equalização que deverão ser alteradas.
	 */
	public void equalizer(float[] values) {
		if (din instanceof javazoom.spi.PropertiesContainer) {
			Map properties = ((javazoom.spi.PropertiesContainer) din)
					.properties();
			float[] equalizer = (float[]) properties.get("mp3.equalizer");

			for (int i = 0; i < 32; i++) {
				equalizer[i] = values[i];
			}
		}
	}
}