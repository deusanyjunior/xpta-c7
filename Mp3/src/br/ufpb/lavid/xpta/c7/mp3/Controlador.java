package br.ufpb.lavid.xpta.c7.mp3;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;

import br.ufpb.lavid.xpta.c7.mp3encoder.Mp3Encoder;

public class Controlador implements Runnable {

	public Scanner scanner = new Scanner(System.in);

	private boolean on = true;
	
	// Recebe os comandos do teclado:
	public String entrada;

	public ArrayList<Track> trackList;
	public ArrayList<String> paths;

	/**
	 * Construtor da classe recebe um ArrayList de objetos e inicializa a classe
	 * de acordo com o objeto recebido.
	 * 
	 * @param lista
	 *            lista de objetos do tipo Track ou BasicPLayerTest
	 */
	public Controlador(ArrayList<String> arquivos) {
		paths = arquivos;
		trackList = new ArrayList<Track>();

		// Cria e inicializa as trheads das tracks:
		for (String s : paths)
			trackList.add(new Track(s, this));

		// Inicializa a trhead do controlador:
		new Thread(this).start();

	}

	public void run() {
		while (on) {
			check();
		}
	}

	public void check() {
		System.out.println("Digite sua opção:");

		entrada = scanner.nextLine();

		if (entrada.equalsIgnoreCase("pause"))
			for (Track t: trackList)
				t.pause();
		else if (entrada.equalsIgnoreCase("resume"))
			for (Track t: trackList)
				t.resume();
		else if (entrada.equalsIgnoreCase("play"))
			for (Track t: trackList)
				new Thread(t).start();
		else if (entrada.equalsIgnoreCase("stop"))
			for (Track t: trackList)
				t.stop();
		else if (entrada.equalsIgnoreCase("export"))
			for (String s: paths)
				Mp3Encoder.encode(new String[]{s, "-e", "-v", "-b", "256"});
		else if (entrada.equalsIgnoreCase("pacpl"))
			// Converte cada um dos arquivos de entrada em mp3.
			for (String s: paths)
				try {
					Runtime.getRuntime().exec("pacpl -t mp3 --outdir ./exp "+s);
				} catch (IOException e) {
					e.printStackTrace();
				}
		else if (entrada.equalsIgnoreCase("quit"))
			on = false;
	
		// Mistura as tracks em uma única .wav:
		else if (entrada.equalsIgnoreCase("mix"))
		{
			AudioFormat mixAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					44000, 16, 2, 4, 44000, false);
			byte[] buffer = new byte[16];
			//TODO: código para mixagem(soma das amplitudes) das tracks@
			for (Track t: trackList)
			{
				try {
					// Enquanto ainda houver bytes a serem lidos:
					int i = 0;
					while (t.decodedInput.available() > 0);
							//mix.add(i, t.decodedInput.read()+ (Byte)mix.get(i));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		else if (entrada.equalsIgnoreCase("seek")) {
			System.out.println("Digite o número de segundos a avançar: \n");
			int temp = scanner.nextInt();
			for (Track t : trackList)
				t.seek(temp);
		} else
			System.out.println("Opção inválida.");
	}

	public static void main(String args[]) {

		ArrayList<String> lista = new ArrayList<String>();

		lista.add("/media/dados/tigreped/Ufpb/Lavid/fluteriff.wav");
//		lista.add("/media/dados/tigreped/Mp3/Therion/Lemuria/01 Typhon.mp3");
//		lista.add("/media/dados/tigreped/Mp3/Therion/Lemuria/06 Quetzalcoatl.mp3");
//		lista.add("/media/dados/tigreped/Mp3/Therion/Lemuria/09 Abraxas.mp3");
		/*
		 * lista .add("/media/FEC2-0D20/mp3/Rock/Avenged Sevenfold - Unholy Confessions.mp3");
		 * 
		 * lista.add("/media/FEC2-0D20/mp3/Rock/Asian Kung Fu - Haruka Kanata.mp3");
		 * 
		 * lista.add("/media/FEC2-0D20/mp3/Rock/Avenged Sevenfold - Unholy Confessions.mp3");
		 */
		new Controlador(lista);
	}
}
