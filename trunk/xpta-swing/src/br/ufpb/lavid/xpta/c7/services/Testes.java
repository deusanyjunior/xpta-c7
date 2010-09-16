package br.ufpb.lavid.xpta.c7.services;

import java.util.ArrayList;

/**
 * Classe utilizada para realização de testes. 
 * @author pedroguimaraes
 *
 */
public class Testes {
	/**
	 * Método principal utilizado para testes:
	 * @param args
	 */
	public static void main(String args[]) {
		/*
		ArrayList<String> s;
		s = Encode.ffmpegWavToFlv("/home/xpta-c7/03.mp3",
		// "/home/xpta-c7/02.flv" , null);
		s = Encode.wavToMp3Pacpl("/home/xpta-c7/02.wav", "/home/xpta-c7");
		for (String e : s)
			System.out.println(e);
		
		// Inicializa o servidor no diretório de execução do projeto.
		s = Streamming.inicializaServidor("lib/");
		if (s.size() > 0)
			for (String ss: s)
				System.out.println(ss);
		*/
		
		ArrayList<Byte> song1 = new ArrayList<Byte>();
		song1.add(new Byte("32"));
		song1.add(new Byte("22"));
		song1.add(new Byte("-119"));
		song1.add(new Byte("45"));
		song1.add(new Byte("-12"));
		
		ArrayList<Byte> song2 = new ArrayList<Byte>();
		song2.add(new Byte("52"));
		song2.add(new Byte("42"));
		song2.add(new Byte("-19"));
		song2.add(new Byte("65"));
		song2.add(new Byte("-72"));
		
		ArrayList<ArrayList<Byte>> tracks = new ArrayList<ArrayList<Byte>>();
		tracks.add(song1);
		tracks.add(song2);
		Byte[] output = Fachada.somaFaixas(tracks);
		for (Byte b: output)
			System.out.println(b.intValue());
	}
}

