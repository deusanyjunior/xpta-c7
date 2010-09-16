package br.ufpb.lavid.xpta.c7.services;

import java.util.ArrayList;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Classe que encapsula os métodos utilizados para a conversão entre diversos
 * formatos de áudio padrão do projeto.
 * 
 * @author pedroguimaraes
 * 
 */
public class Encode {

	/**
	 * Utiliza a biblioteca de conversão do JLayer para transformar um áuidio
	 * .mp3 em .wav
	 * 
	 * @param in
	 *            caminho do arquivo de .mp3 de entrada
	 * @param out
	 *            caminho do wav que será gerado na conversão
	 * @return null se não houver erro na execução da conversão ou uma string
	 *         com a excessão, em caso contrário
	 */
	public static String mp3ToWavJLayer(String in, String out) {
		Converter c = new Converter();
		try {
			c.convert(in, out);
		} catch (JavaLayerException e) {
			return e.getMessage();
		}
		return null;
	}

	/**
	 * Utiliza o pacpl (Perl audio converter) para converter uma .mp3 em .wav
	 * 
	 * @param in
	 *            caminho da .wav de entrada
	 * @param outputDir
	 *            pasta onde deve ser armazenada a saída da conversão, cujo nome
	 *            do arquivo de saída será igual ao do arquivo de entrada, só
	 *            que com a nova extensão
	 * @return lista de strings com as mensagens do processo.
	 */
	public static ArrayList<String> wavToMp3Pacpl(String in, String outputDir) {
		ArrayList<String> msgs = new ArrayList<String>();
		String commandLine = "pacpl -t mp3 --outdir --overwrite " + outputDir
				+ " " + in;
		return Fachada.command(commandLine);
	}

	/**
	 * Executa o flvtool2 para garantir que o keyframe esteja no início do
	 * arquivo .flv, para que o mesmo possa ser transofrmado em pseudostream.
	 * 
	 * @param filepath
	 * @return lista de strings com as mensagens do processo.
	 */
	public static ArrayList<String> flvTool2(String filepath) {
		ArrayList<String> msgs = new ArrayList<String>();
		String commandLine = "flvtool2 -U " + filepath;
		return Fachada.command(commandLine);
	}

	/**
	 * Converte um arquivo .wav em um arquivo .flv utilizando o ffmpeg
	 * 
	 * @param in
	 *            caminho do arquivo .wav de entrada
	 * @param out
	 *            caminho completo do arquivo de saída, inclusive com o formato
	 *            .flv no final do arquivo
	 * @param codec
	 *            tipo de codec adequado para realizar o encode e a conversão
	 *            com o ffmpeg. Ver ffmpeg -formats
	 * @return lista de strings com as mensagens do processo.
	 */
	public static ArrayList<String> ffmpegWavToFlv(String in, String out,
			String codec) {
		ArrayList<String> msgs = new ArrayList<String>();
		String padrao = null;
		if (codec == null)
			padrao = "pcm_s16le"; // Codec padrão
		String commandLine = "ffmpeg -y -i " + in + " -f flv -acodec " + padrao
				+ " " + out;
		return Fachada.command(commandLine);
	}
}

