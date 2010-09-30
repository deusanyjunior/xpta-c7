package br.ufpb.lavid.xpta.services;

import java.util.ArrayList;

/**
 * Esta classe é responsável pelos métodos relacionados à gerência do
 * servidor live55mediastream encarregado pelo streaming RTP.
 * 
 * @author pedroguimaraes
 * 
 */
public class Streamming {

	/**
	 * Inicializa o servidor live555mediaserver de streaming localizado na
	 * máquina do servidor do sistema. Deve-se observar que o dirtório base para
	 * inserção dos arquivos de áudio para streamming utilizando o
	 * live555mediaserver deve ser o diretório de execução do projeto java.
	 * 
	 * @param live555path
	 *            diretório do executável do servidor de streaming no
	 *            sistema.(Ex.: /usr/colaboke/play/)
	 */
	public static ArrayList<String> inicializaServidor(String live555path) {

		// Corrige o formato do caminho para ficar com / antes e depois, caso
		// não esteja.
		if (!(live555path.endsWith("/")))
			live555path = live555path + "/";
		if (!(live555path.startsWith("/")))
			live555path = "/" + live555path;

		return Fachada.command("." + live555path + "live555MediaServer");
	}
}
