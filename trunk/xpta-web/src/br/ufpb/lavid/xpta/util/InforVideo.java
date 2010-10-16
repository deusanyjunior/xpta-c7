package br.ufpb.lavid.xpta.util;



import java.io.InputStream;
import java.math.BigDecimal;
import java.util.StringTokenizer;
;

public class InforVideo {

	private String duracao = "";

	public InforVideo() {
	}

	public static void main(String[] s) {
		try {
			
			String dir = "/home/jorge/projetos5/Xpta/WebContent/audios/";

			String arquivoAlvo = dir + "gmtRKUaB.mp3";

			InforVideo inforVideo = new InforVideo();

			String duracao = inforVideo.exec(arquivoAlvo);

			//inforVideo.exec(arquivoAlvo);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public String exec(String arquivoAlvo) throws Exception {

		String FFMPEG;
		
		if(System.getProperty("os.name").equals("Linux")){
			
			FFMPEG = "ffmpeg";
			
		}else{
			
			FFMPEG = "C:\\opt\\ffmpeg\\ffmpeg.exe";
			
		}

		String valorDeSaida = "";

		String dir = "/home/jorge/projetos5/Xpta/WebContent/audios/";

		String comando = FFMPEG + " -i " + arquivoAlvo;

		Process processoDeConversao = Runtime.getRuntime().exec(comando);

		valorDeSaida = doWaitFor(processoDeConversao);
		
		return valorDeSaida;

	}

	private String doWaitFor(Process p) {
		
		String retorno = "";

		String valorDeSaida = "";

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
						System.out.print(c);

					}

					//valorDeSaida = p.exitValue();
					terminado = true;
					
				} catch (IllegalThreadStateException e) {
					
					Thread.currentThread().sleep(500);
					
				}
				
			}

			StringTokenizer s = new StringTokenizer(normaliza(retorno), " ");
			String duracao = null;
			String bitrade = null;
			String valorBase = null;

			while (s.hasMoreTokens()) {

				valorBase = s.nextToken();

				if (valorBase.equals("DURATION:")) {

					duracao = s.nextToken().substring(0, 8);
					
					System.out.println("DURATION = "+duracao);

				} else if (valorBase.equals("BITRATE:")) {

					bitrade = s.nextToken();

				}

			}

			StringTokenizer s1 = new StringTokenizer(duracao, ":");

			int h = Integer.parseInt(s1.nextToken());
			int m = Integer.parseInt(s1.nextToken());

			double seg = Double.parseDouble(s1.nextToken());
			int decimalPlace = 0;
			BigDecimal bd = new BigDecimal(seg);
			bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
			seg = bd.doubleValue();

			int auxSeg = (int) seg;

			int duracaoFinal = ((3600 * h) + (60 * m) + auxSeg);
			
			duracao = duracaoFinal+"";
			
			valorDeSaida = duracao;
			
			System.out.println("duracao = "+duracao);

		} catch (Exception e) {
			
			System.err.println("doWaitFor(): unexpected exception - "+ e.getMessage());
			
		}
		
		return valorDeSaida;
		
	}

	public String normaliza(String nome) {
		
		String ret;
		
		ret = nome.toUpperCase();
		
		ret = ret.replaceAll("\\s+", " ");
		
		ret = ret.replaceAll("^\\s+", "");
		
		ret = ret.replaceAll("\\s+$", "");
		
		return ret;
		
	}

}