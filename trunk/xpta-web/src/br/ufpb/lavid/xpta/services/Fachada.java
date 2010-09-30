package br.ufpb.lavid.xpta.services;

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
	
        /**
         * Soma os bytes de todas as faixas. Precisa tratar o caso da soma de byte estourar os valores. (THRESHOLD)
         * @param tracks
         * @return
         */
        public static byte[] somaFaixas(ArrayList<byte[]> tracks) {

            // Pega o número de bytes da maior track:
            int maior = 0;
            for (byte[] t: tracks)
                if (maior < t.length)
                    maior = t.length;

            byte[] soma = new byte[maior];

            for (byte[] t: tracks) {
                for (int i = 0 ; i < t.length ; i++)
                    soma[i] += t[i];
            }
            return soma;
        }
}

