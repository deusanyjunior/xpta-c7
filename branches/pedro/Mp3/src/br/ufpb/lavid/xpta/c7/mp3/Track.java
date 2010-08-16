/*
 * TODO: consertar o  problema. TrackVar parece ótima solução mas preciso acertar os detalhes.
 * TODO: Tentar acertar o método stop(), de modo que ao final da execução da faixa,
 *  ou caso o usuário decida, este método pare a execução da música e reinicialize
 *  os dados da música para o início(reset() não funciona).
 */

package br.ufpb.lavid.xpta.c7.mp3;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

import br.ufpb.lavid.xpta.c7.waveform.WaveformDisplaySimulator;

public class Track extends TrackVar implements Runnable {

	/**
	 * Construtor recebe o caminho da música e o offset que indica o início da
	 * execução da música e atribui os valores às variáveis globais
	 * correspondentes.
	 * 
	 * @param caminho
	 *            string com o caminho do arquivo da música
	 * @param inicio
	 *            int que indica o início da execução da música
	 */
	public Track(String caminho, Controlador controller) {
		filepath = caminho;
		controlador = controller;
	}

	/**
	 * Método run realiza a chamada dos métodos utilizados para carregar e
	 * iniciar a execução da música.
	 */
	public void run() {
		play();
	}

	/**
	 * Método carrega a música na memória e inicia sua execução.
	 */
	public void loadTrack() {
		AudioFormat baseFormat = null;
		try {
			file = new File(filepath);

			input = AudioSystem.getAudioInputStream(file);

			audioFileFormat = AudioSystem.getAudioFileFormat(file);

			baseFormat = audioFileFormat.getFormat();

			int lenghInBytes = 0;
			if (audioFileFormat instanceof TAudioFileFormat)
			{
				properties = ((TAudioFileFormat) audioFileFormat).properties();
				lenghInBytes = (Integer) properties.get("mp3.length.bytes");
				long duration = (Long) properties.get("duration");
				bytesPerSec = lenghInBytes / (int) (duration / 1000000);
				System.out.println("Propriedades: "+properties.toString());
			}
			
			audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);

			decodedInput = AudioSystem.getAudioInputStream(audioFormat, input);
			WaveformDisplaySimulator.newWaveformDisplaySimulator(decodedInput);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		}
	}

	/**
	 * Reserva a linha para ser utilizada pelo programa
	 * 
	 * @param audioFormat
	 * @return
	 * @throws LineUnavailableException
	 */
	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
	
	/**
	 * Método utilizado para avançar até uma quantidade de bytes específica da
	 * música.
	 * 
	 * @param segundos
	 */
	public void seek(int segundos) {
		try {
			decodedInput.skip(segundos * bytesPerSec);
			seconds += segundos; // Atualiza o valor dos segundos
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calcula a duração da música em segundos, à partir das propriedades.
	 * 
	 * @param properties
	 * @return
	 */
	private long durationInSec(Map properties) {
		return (Long) properties.get("duration") / 1000000;
	}

	/**
	 * Inicia a execução da música.
	 */
	public void play() {
		// Carrega o áudio na memória:
		loadTrack();

		// Carrega a linha utilizada para escrever os dados na placa de áudio:
		try {
			line = getLine(audioFormat);
		} catch (LineUnavailableException l) {
			l.printStackTrace();
		}

		if (line != null) {
			// Inicializa a comunicação da linha:
			line.start();

			// Enquanto o número de bytes lidos não for igual a -1 (Fim dos
			// dados)

			// Indica a execução do áudio para o sistema:
			isPlaying = true;

			while (nBytesRead != -1 && isPlaying()) {
				rawPlay();
			}
			// Indica o final da execução do áudio para o sistema:
			stop();

			// Stop playing:
			line.drain();
			line.stop();
			line.close();
		}
	}

	/**
	 * Realiza o início da escrita dos dados na placa de som.
	 */
	private void rawPlay() {
		while (isPlaying()) {
			if (isPaused) {
				;// Enquanto pausado, não faz nada.
			}
			else {
				updateTime();
				try {
					// Preencha o array com bytes do input stream de entrada.
					nBytesRead = decodedInput.read(data, 0, data.length);

					// Se ainda há bytes lidos, escreva-os na linha de saída:
					if (nBytesRead != -1) {
						line.write(data, 0, nBytesRead);
					}
					else {
						stop();
					}	
				} catch (IOException i) {
					i.printStackTrace();
				}
			}
		}
	}

	private void updateTime() {
		// Armazena o segundo atual da execução:
		int currentSecond = (int) line.getMicrosecondPosition() / 1000000;
		// Variável utilizada para pegar o nº de segundos separado do nº de
		// minutos(em segundos):
		int resto = currentSecond % 60;

		// Caso o segundo seja o mesmo da iteração anterior não faz nada:
		if (resto != seconds) {
			// Atualiza o valor do tempo de execução da música:
			if (currentSecond >= 60) {
				minutes = ((currentSecond - resto) / 60);
				seconds = resto;
			}
			// Para o primeiro minuto de execução:
			else {
				seconds = currentSecond;
			}
			// Imprime o tempo atual de execução:
			// imprimeTempo();
		}
	}

	private void imprimeTempo() {
		System.out.println(minutes + ":" + seconds);
	}

	/**
	 * Método utilizado para equalizar a faixa. Precisa ser testado; Até agora
	 * não aparenta causar alterações perceptíveis.
	 * 
	 * @param values
	 *            Array do tipo float com 32 posições, correspondentes às
	 *            fresquências de equalização que deverão ser alteradas.
	 */
	public void equalizar(float[] values) {
		float[] equalizer = (float[]) properties.get("mp3.equalizer");

		for (int i = 0; i < 32; i++) {
			equalizer[i] = values[i];
		}
	}

	/**
	 * Método carrega as informações sobre o estado do volume
	 * 
	 */
	public void loadVolume() {
		controladorFloat = (FloatControl) line
				.getControl(FloatControl.Type.VOLUME);
	}

	/**
	 * Muta a faixa.
	 */
	public void mute() {
		if (!isMute) {
			previousVolume = controladorFloat.getValue();
			controladorFloat.setValue(0);
			isMute = true;
		}
	}

	/**
	 * Remove o mute da faixa, retornando ao valor anterior ao mute.
	 */
	public void unMute() {
		if (isMute) {
			controladorFloat.setValue(previousVolume);
			isMute = false;
		}
	}

	/**
	 * Pausa a execução da música.
	 */
	public void pause() {
		isPaused = true;
	}

	/**
	 * Paralisa a execução e reinicializa o áudio para o início dos bytes;
	 */
	public void stop() {
		// Encerra a carga de bytes;
		isPlaying = false;
		isStoped = true;
	}

	/**
	 * Reinicia a execução de uma música em estado de pause().
	 */
	public void resume() {
		if (isPaused) {
			isPaused = false;
		}
	}

	/**
	 * Encerra os inputs de áudio.
	 */
	public void close() {
		try {
			input.close();
			decodedInput.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Indica se está executando um áudio ou não.
	 * @return booleano indicando o estado da execução do áudio.
	 */
	public boolean isPlaying() {
		return isPlaying;
	}
}