package br.ufpb.lavid.xpta.c7.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Esta classe funciona como uma fachada que encapsula os métodos necessários
 * que são compartilhados pelas classes de serviços.
 * 
 * @author pedroguimaraes
 * 
 */
public class Fachada {

	/**
	 * Executa uma linha de comando no sistema UNIX, e retorna uma lista de
	 * strings com as mensagens retornadas pela execução do comando.
	 * 
	 * @param commandLine
	 * @return
	 */
	public static ArrayList<String> command(String commandLine) {
		ArrayList<String> msgs = new ArrayList<String>();
		try {
			Process p = Runtime.getRuntime().exec(commandLine);
			InputStream input = p.getInputStream();
			InputStream error = p.getErrorStream();

			byte[] b = new byte[1000];

			if (error.available() > 0) {
				msgs.add("ERRO:\n");
				while (error.read(b) != -1) {
					char c[] = new char[b.length];
					for (int i = 0; i < b.length; i++)
						c[i] = (char) b[i];
					msgs.add(String.copyValueOf(c));
				}
			} else {
				msgs.add("SAIDA:\n");
				while (input.read(b) != -1) {
					char c[] = new char[b.length];
					for (int i = 0; i < b.length; i++)
						c[i] = (char) b[i];
					msgs.add(String.copyValueOf(c));
				}
			}
		} catch (IOException e) {
			msgs.add("EXCEPTION:\n");
			msgs.add(e.getMessage());
		}
		return msgs;
	}
	
	/**
	 * Cria um novo diretório
	 * @return
	 */
	public static ArrayList<String> mkdir(String nomeDoDiretorio) {
		return command("mkdir "+nomeDoDiretorio);
	}
	
	public static Byte[] somaFaixas(ArrayList<ArrayList<Byte>> listaDeTracks) {
	
		int maxBytes = 0;
		// Verifica o maior número de bytes necessário:
		for (ArrayList<Byte> track: listaDeTracks) {
			if (track.size() > maxBytes)
				maxBytes = track.size();
		}
		
		Byte[] total = new Byte[maxBytes];

		for (ArrayList<Byte> track: listaDeTracks) {
			for (int i = 0 ; i < track.size() ; i++) {
				// TODO: implementar o limite depois (threshold?)
				// TODO: gambiarra da porra.
				if (total[i] == null)
					total[i] = track.get(i);
				else
					total[i] =   Byte.valueOf(Integer.toString(total[i].intValue() + track.get(i).intValue()));
			}
		}

		return total;
	}
}

