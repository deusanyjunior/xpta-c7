package mp3;

public class Main
{
	public static void main (String args[])
	{
		// Construtor recebe nome do arquivo e o número de segundos pro offset.
		Mp3 mp3 = new Mp3("/media/FEC2-0D20/13.mp3", 25);
		mp3.run();
		
		float[] equalizar = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
							 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
							 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
							 0.0f, 0.0f};
		mp3.equalizer(equalizar);
	}
}
