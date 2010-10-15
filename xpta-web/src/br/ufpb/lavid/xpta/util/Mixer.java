package br.ufpb.lavid.xpta.util;

import java.io.InputStream;

public class Mixer {

	public Mixer() {
	}

	public static void main(String[] s) 
	{
		
		try 
		{

			Mixer mixer = new Mixer();
			mixer.exec("");

		}catch (Exception ex) 
		{

			ex.printStackTrace();

		}

	}

	public String exec(String arquivoAlvo) throws Exception 
	{		

		int valorDeSaida = -1;

		String dir = "/home/jorge/projetos5/Xpta/WebContent/audios";
		
		String comando = "sox "+arquivoAlvo;

		System.out.println("# comando = " + comando);

		Process processoDeConversao = Runtime.getRuntime().exec(comando);

		valorDeSaida = doWaitFor(processoDeConversao);
		
		return "";

	}

	private int doWaitFor(Process p) {
		
		String retorno = "";

		int valorDeSaida = -1;

		try {
			
			InputStream in = p.getInputStream();
			InputStream err = p.getErrorStream();
			boolean terminado = false;

			while (!terminado) {

				try {
					
					while (in.available() > 0) {
						
						Character c = new Character((char) in.read());
						System.out.print(c);

					}

					while (err.available() > 0) {
						
						Character c = new Character((char) err.read());
						retorno = retorno + c;

					}

					valorDeSaida = p.exitValue();
					terminado = true;
					
				} catch (IllegalThreadStateException e) {
					
					Thread.currentThread().sleep(500);
					
				}
				
			}

		} catch (Exception e) {
			
			System.err.println("doWaitFor(): unexpected exception - "+ e.getMessage());
			
		}
		
		return valorDeSaida;
		
	}

}