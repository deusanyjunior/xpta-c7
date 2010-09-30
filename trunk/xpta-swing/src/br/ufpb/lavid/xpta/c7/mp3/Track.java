package br.ufpb.lavid.xpta.c7.mp3;

import br.ufpb.lavid.xpta.c7.waveform.WaveformPanelContainer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

public class Track implements Runnable {

    private double bytesOnASecond = 6000;
    private File audioFile = null;
    private String filePath = null;
    private AudioInputStream in = null;
    private AudioInputStream din = null;
    private AudioFormat decodedFormat = null;
    private AudioFileFormat baseFileFormat = null;
    private SourceDataLine line = null;
    private FloatControl controladorFloat = null;
    private float previousVolume;
    private boolean isMute;
    private int start, end, offset;
    private long remaingBytesUntilEnd;
    private boolean pause = false;
    private boolean isPlaying = false;
    private WaveformPanelContainer container;
    // Número de bytes por ms necessários para representar a música.
    private double bytesPerMs = 0;
    // Número de bytes lidos:
    private int nBytesRead = 0;
    // Variável utilizada para implementar pause.
    // MAX: 4608; Original: 4096;
    byte[] data = new byte[4096];
    private float vol = -10;

    /**
     * Construtor da classe
     * @param filepath Caminho do arquivo da track
     * @param start Tempo em que começa o trecho da música (em ms)
     * @param end Tempo em que encerra o trecho da música (em ms)
     * @param offset Tempo em espera até tocar o trecho da música (em ms)
     */
    public Track(String filepath, int start, int end, int offset) {
        this.filePath = filepath;
        this.start = start;
        this.end = end;
        this.offset = offset;

        audioFile = new File(filePath);
        try {
            baseFileFormat = AudioSystem.getAudioFileFormat(audioFile);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            try {
                in = AudioSystem.getAudioInputStream(audioFile);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construtor da classe
     * @param filepath Caminho do arquivo da track
     * @param start Tempo em que começa o trecho da música (em ms)
     * @param offset Tempo em espera até tocar o trecho da música (em ms)
     */
    public Track(String filepath, int start, int offset) {
        this(filepath, start, -1, offset);
    }

    /**
     * Construtor da classe
     * @param filepath Caminho do arquivo da track
     */
    public Track(String filepath) {
        this(filepath, 0, -1, 0);
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void run() {
//        playTrack(decodedFormat, din);
//        try {
//            in.close();
//            din.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
        System.out.println(baseFileFormat.toString());
    }

    private void loadTrack() {

        AudioFormat baseFormat = in.getFormat();

        decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);

        // System.out.println(baseFormat.getSampleRate());
        din = AudioSystem.getAudioInputStream(decodedFormat, in);
        container = new WaveformPanelContainer();
        container.setAudioToDisplay(din);
        // Salta para iniciar a música no startº byte.
//            bytesOnASecond = ;
        try {
            din.skip(Math.round(bytesOnASecond * start / 1000.0));
        } catch (Exception e) {
            e.printStackTrace();
        }
//            System.out.println("s/s:"+ baseFormat.);
        System.out.println("start: " + start);
        System.out.println("skip: " + Math.round(start * bytesOnASecond / 1000.0));
        if (end >= 0) {
            remaingBytesUntilEnd = Math.round((end - start) * bytesOnASecond / 1000.0);
        } else {
            remaingBytesUntilEnd = Long.MAX_VALUE;
        }
        // Chamando via teclado :) com a classe Controlador
        // play();

    }

    public byte[] loadMp3ByteArray() {
        // Pega o tamanho da mp3 em bytes:
        Integer mp3SizeInBytes = null;

        if (baseFileFormat instanceof TAudioFileFormat) {
            Map properties = ((TAudioFileFormat) baseFileFormat).properties();
            String key = "mp3.length.bytes";
            mp3SizeInBytes = (Integer) properties.get(key);
            // System.out.println("Size in bytes: " + mp3SizeInBytes);
        }

        AudioFormat baseFormat = in.getFormat();

        decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);

        din = AudioSystem.getAudioInputStream(decodedFormat, in);

        byte[] track = new byte[mp3SizeInBytes];
        byte[] aux = new byte[5000];
        int bytesLidos = 0;
        int trackCount = 0;
        int total = 0;

        System.out.println("Size: " + (double) mp3SizeInBytes / 1000000 + " MB");

        while (bytesLidos != -1) {
            try {

                bytesLidos = din.read(aux);
                total += bytesLidos;
//              if (bytesLidos != -1)
//                  for (int i = 0 ; i < bytesLidos ; i++)
//                      track[trackCount++] = aux[i];

                /** Laço utilizado para visualizar a carga dos bytes:
                int lineBreaker = 0;
                for (byte b: aux) {
                System.out.print(b+" ");
                if (lineBreaker > 50) {
                System.out.println();
                lineBreaker = 0;
                }
                lineBreaker++;
                }
                 **/
            } catch (IOException ex) {
                Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Total de bytes lidos: " + (double) total / 1000000 + " | Tamanho da mp3 em bytes: " + (double) mp3SizeInBytes / 1000000);
        return track;
    }

    public byte[] loadTrackByteArray() {

        AudioFormat baseFormat = in.getFormat();

        decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);

        din = AudioSystem.getAudioInputStream(decodedFormat, in);

        Long size = audioFile.length();
        int smallSize = Integer.parseInt(Long.toString(size));
        byte[] track = new byte[smallSize];
        byte[] aux = new byte[5000];
        int bytesLidos = 0;
        int trackCount = 0;
        int total = 0;

        System.out.println("Size: " + (double) smallSize / 1000000 + " MB");

        while (bytesLidos != -1) {
            try {

                bytesLidos = din.read(aux);
                total += bytesLidos;
                if (bytesLidos != -1) {
                    for (int i = 0; i < bytesLidos; i++) {
                        track[trackCount++] = aux[i];
                    }
                }

                /** Laço utilizado para visualizar a carga dos bytes:
                int lineBreaker = 0;
                for (byte b: aux) {
                System.out.print(b+" ");
                if (lineBreaker > 50) {
                System.out.println();
                lineBreaker = 0;
                }
                lineBreaker++;
                }
                 **/
            } catch (IOException ex) {
                Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return track;
    }

    public void playFromByteArray(byte[] track) {
        createLine();
        int contadorDeBytes = 0;
        int bytesEscritos = 0;
        remaingBytesUntilEnd = track.length;
        while (isPlaying && remaingBytesUntilEnd > 0) {
            if (!pause) {
                nBytesRead = 4096;
                bytesEscritos += line.write(track, contadorDeBytes, nBytesRead);
                contadorDeBytes += nBytesRead;
                remaingBytesUntilEnd -= nBytesRead;
            }
            // Encerra a carga de bytes:
            System.out.println("Bytes escritos: " + bytesEscritos);
            killLine();
        }
    }

    private void playTrack(AudioFormat targetFormat, AudioInputStream din) {
        // Carrega a linha utilizada para escrever os dados na placa de áudio.
        try {
            line = getLine(targetFormat);
            try {
                if (offset > 0) {
                    Thread.sleep(offset);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } catch (LineUnavailableException l) {
            l.printStackTrace();
        }

        if (line != null) {
            // Inicializa a comunicação da linha:
            line.start();
            setVolume(vol);
            // Enquanto o número de bytes lidos não for igual a -1 (Fim dos
            // dados)
            isPlaying = true;
            while (nBytesRead != -1) {
                rawPlay();
            }
            isPlaying = false;
            // Stop playing
            line.drain();
            line.stop();
            line.close();
        }
    }

    private void createLine() {
        try {
            line = getLine(decodedFormat);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (line != null) {
            isPlaying = true;
            // Inicializa a comunicação da linha:
            line.start();
            setVolume(vol);
        }
    }

    private void killLine() {
        isPlaying = false;
        // Stop playing
        line.drain();
        line.stop();
        line.close();
    }

    private void rawPlay() {
        // Indica a execução do áudio para o sistema:
        while (isPlaying && remaingBytesUntilEnd > 0) {
            if (!pause) {
                try {
                    // Preencha o array com bytes do input stream de entrada.
                    nBytesRead = din.read(data, 0, data.length);
                    // Se ainda há bytes lidos, escreva-os na linha de saída:
                    if (nBytesRead != -1) {
                        if (nBytesRead > remaingBytesUntilEnd) {
                            nBytesRead = (int) remaingBytesUntilEnd;
                        }
                        line.write(data, 0, nBytesRead);
                        remaingBytesUntilEnd -= nBytesRead;
                    } else {
                        // Encerra a carga de bytes;
                        isPlaying = false;
                    }
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }
    }

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
     * Método utilizado para equalizar a faixa. Precisa ser testado; Até agora
     * não aparenta causar alterações perceptíveis.
     *
     * @param values
     *            Array do tipo float com 32 posições, correspondentes às
     *            fresquências de equalização que deverão ser alteradas.
     */
    public void equalizer(float[] values) {
//        if (din instanceof javazoom.spi.PropertiesContainer) {
//            Map properties = ((javazoom.spi.PropertiesContainer) din).properties();
//            float[] equalizer = (float[]) properties.get("mp3.equalizer");
//
//            for (int i = 0; i < 32; i++) {
//                equalizer[i] = values[i];
//            }
//        }
    }

    public WaveformPanelContainer getWaveFormPanel() {
        return container;
    }

    public float getBalance() {
        controladorFloat = (FloatControl) line.getControl(FloatControl.Type.PAN);
        return controladorFloat.getValue();
    }

    public void setBalance(float value) {
        controladorFloat = (FloatControl) line.getControl(FloatControl.Type.PAN);
        controladorFloat.setValue(value);
    }

    public float getVolume() {
        controladorFloat = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        return controladorFloat.getValue();
    }

    public void setVolume(float value) {
        if (line != null) {
            controladorFloat = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            controladorFloat.setValue(value);
        }
        vol = value;
    }

    public void mute() {
        if (!isMute) {
            previousVolume = controladorFloat.getValue();
            controladorFloat.setValue(0);
            isMute = true;
        }
    }

    public void unMute() {
        if (isMute) {
            controladorFloat.setValue(previousVolume);
            isMute = false;
        }
    }

    public void pause() {
        if (isPlaying) {
            pause = true;
        }
    }

    public void resume() {
        if (!isPlaying) {
            new Thread(this).start();
        } else {
            pause = false;
        }
    }

    public void stop() {
        isPlaying = false;
        pause = false;
    }

    /**
     * Retorna o número de bytes utilziados para representar um milisegundo de áudio no formato da track.
     * @return
     */
    public double bytesPerMs() {
        return decodedFormat.getChannels() * decodedFormat.getFrameRate() * decodedFormat.getFrameSize() / 1000;
    }
}
