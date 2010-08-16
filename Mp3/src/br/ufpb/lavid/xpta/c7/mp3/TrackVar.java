package br.ufpb.lavid.xpta.c7.mp3;

import java.io.File;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class TrackVar {

	// Arquivo de áudio será carregado nessa variável:
	public File file = null;

	// Caminho do arquivo:
	public String filepath = null;

	// Stream de entrada e stream codificado:
	public AudioInputStream input = null;
	public AudioInputStream decodedInput = null;

	// Variáveis de áudio
	public AudioFileFormat audioFileFormat = null;
	public AudioFormat audioFormat = null;

	// Propriedades do arquivo de áudio:
	public Map properties = null;

	public SourceDataLine line = null;

	// Controlador de volume, pan, etc.:
	public FloatControl controladorFloat = null;

	// Variável que armazena o volume anterior:
	public float previousVolume = 0;

	// Inicializalção genérica de bytes por segundo:
	public int bytesPerSec = 40960;

	// Indica o ponto de início da execução música
	public int start = 0;
	
	public int seekOffset = 0;

	// Indica se a track está em mute ou não:
	public boolean isMute = false;

	// Indica se a track está em execução:
	public boolean isPlaying = false;
	
	// Indica se a track está pausada ou não.	
	public boolean isPaused = false;
	
	// Indica se a track está parada ou não.	
	public boolean isStoped = false;
	
	// Número de bytes lidos:
	public int nBytesRead = 0;

	// MAX: 4608; Original: 4096;
	public byte[] data = new byte[4096];

	// Variáveis utilizadas para o cálculo e atualização do tempo de execução:
	public int seconds = 0;
	public int minutes = 0;
	public long bytesRead = 0;
	
	// Controlador utilizado para reflexão:
	public Controlador controlador = null;

	
}
