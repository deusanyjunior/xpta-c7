package br.ufpb.lavid.xpta.c7.services;

import br.ufpb.lavid.xpta.c7.mp3.Track;
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


                // Teste da carga de bytes do áudio para byte:
		Track t1 = new Track("/home/pedroguimaraes/Download/mp3/01.mp3");
                Track t2 = new Track("/home/pedroguimaraes/Download/mp3/08.mp3");

                byte b1[] = t1.loadTrackByteArray();
                byte b2[] = t2.loadTrackByteArray();

                t1.playFromByteArray(b1);
                t2.playFromByteArray(b2);
	}
}

