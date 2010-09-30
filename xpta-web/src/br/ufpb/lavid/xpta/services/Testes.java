package br.ufpb.lavid.xpta.services;

import br.ufpb.lavid.xpta.mp3.Track;
import java.util.ArrayList;

/**
 * Classe utilizada para realização de testes.
 * 
 * @author pedroguimaraes
 * 
 */
public class Testes {
	/**
	 * Método principal utilizado para testes:
	 * 
	 * @param args
	 */

	public static void hello() {
		System.out.println("Memória livre: "
				+ Runtime.getRuntime().freeMemory());
	}

	public static void main(String args[]) {
		/*
		 * ArrayList<String> s; s =
		 * Encode.ffmpegWavToFlv("/home/xpta-c7/03.mp3", //
		 * "/home/xpta-c7/02.flv" , null); s =
		 * Encode.wavToMp3Pacpl("/home/xpta-c7/02.wav", "/home/xpta-c7"); for
		 * (String e : s) System.out.println(e);
		 * 
		 * // Inicializa o servidor no diretório de execução do projeto. s =
		 * Streamming.inicializaServidor("lib/"); if (s.size() > 0) for (String
		 * ss: s) System.out.println(ss);
		 */

		/*
		 * Teste da carga de bytes do áudio para byte: Track t1 = new
		 * Track("/home/pedroguimaraes/Download/mp3/01.wav"); Track t2 = new
		 * Track("/home/pedroguimaraes/Download/mp3/08.wav");
		 * 
		 * byte b1[] = t1.loadTrackByteArray(); byte b2[] =
		 * t2.loadTrackByteArray();
		 * 
		 * ArrayList<byte[]> tracks = new ArrayList<byte[]>(); tracks.add(b1);
		 * tracks.add(b2);
		 * 
		 * byte[] soma = Fachada.somaFaixas(tracks);
		 * System.out.println("Tamanho da track somada: " +
		 * (double)soma.length/1000000);
		 */

		Encode
				.mp3ToWavJLayer(
						"/var/lib/tomcat6/webapps/Projetos/22/10 - Sorriso maroto - Não mereço ser amante.mp3",
						"/var/lib/tomcat6/webapps/Projetos/22/10 - Sorriso maroto - Não mereço ser amante.wav");
	}
}
