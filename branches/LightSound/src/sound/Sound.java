/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Carlos Peixoto e Antonio Deusany
 */
public class Sound {

    private String fileName;
    private int nChannel;
    private static final int DEFAULT_EXTERNAL_BUFFER_SIZE = 88200;
    private float nBalance;
    private float framesSkpR;
    private float framesSkpL;
    private int nGain;
    private SourceDataLine line;
    private AudioFormat audioFormat;
    private AudioInputStream audioIn;
    private FloatControl masterGain,  balance;
    private MusicThread musicThread;

    public Sound(String fileName, int nChannel) throws UnsupportedAudioFileException, IOException {
        this.fileName = fileName;
        this.nChannel = nChannel;
        nBalance = 0;
        framesSkpL = 1;
        framesSkpR = 1;
        nGain = -10;


        File file = new File(fileName);
        audioIn = AudioSystem.getAudioInputStream(file);
        audioFormat = audioIn.getFormat();
        System.out.println("Audio Format " + audioFormat.toString() + "\n");
        audioFormat = new AudioFormat(audioFormat.getSampleRate(),
                audioFormat.getSampleSizeInBits(),
                nChannel, //audioFormat.getChannels(),
                false,
                audioFormat.isBigEndian());
        line = getSourceDataLine(null, audioFormat, DEFAULT_EXTERNAL_BUFFER_SIZE * 2);

        Control controls[] = line.getControls();
        for (int i = 0; i < controls.length; i++) {
            System.out.println("Controle " + i + " " + controls[i].toString());
        }


        masterGain = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        balance = (FloatControl) line.getControl(FloatControl.Type.BALANCE);

        start();
    }

    public void start() throws UnsupportedAudioFileException, IOException {

        int bufferSize = DEFAULT_EXTERNAL_BUFFER_SIZE;
        line.start();
        int musicSize = audioIn.available();
        byte[] bufferMusic = new byte[musicSize];

        byte[] outBuffer = new byte[bufferSize];
        // carrega a musica na memoria
        audioIn.read(bufferMusic, 0, audioIn.available());

        int indexR = -1;
        int indexL = -1;

        int halfBuffer = (outBuffer.length / 2);

        try {
            line.open();

        } catch (LineUnavailableException ex) {
            System.out.println("cant open");
        }

        masterGain.setValue(nGain);
//        balance.setValue(nBalance);

        musicThread = new MusicThread(halfBuffer, indexR, indexL, musicSize, bufferSize, outBuffer, bufferMusic);
        musicThread.start();
    }

    private static SourceDataLine getSourceDataLine(String strMixerName,
            AudioFormat audioFormat,
            int nBufferSize) {
        SourceDataLine line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                audioFormat, nBufferSize);
        try {
            if (strMixerName != null) {
                Mixer.Info mixerInfo = AudioCommon.getMixerInfo(strMixerName);
                if (mixerInfo == null) {
                    System.exit(1);
                }
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                line = (SourceDataLine) mixer.getLine(info);
            } else {
                line = (SourceDataLine) AudioSystem.getLine(info);
            }

            line.open(audioFormat, nBufferSize);
        } catch (LineUnavailableException e) {
        } catch (Exception e) {
        }
        return line;
    }

    public void setNewBalanceAndVolume(float percentR, float percentL) {

        nBalance *= 0.75f;
        nBalance += (percentR - percentL);
        if (nBalance < -1) {
            nBalance = -1;
        }
        if (nBalance > 1) {
            nBalance = 1;
        }
        balance.setValue(nBalance);

        float value = masterGain.getValue() + (percentL + percentR - 0.20f) * 2f;

        if (value < -10) {
            value = -10;
        }
        if (value > 6) {
            value = 6;
        }
        masterGain.setValue(value);
        System.out.println("PercentL " + percentL + ", PercentR " + percentR);
        System.out.println("Novo balanco " + nBalance + ", Novo volume " + value);
    }

    public void setNewSpeed(float percentR, float percentL) {

        float valueR = framesSkpR + (percentR - 0.20f) * 2f;

        if (valueR < 0.5f) {
            valueR = 0.5f;
        }
        if (valueR > 3) {
            valueR = 3;
        }
        framesSkpR = valueR;

        float valueL = framesSkpL + (percentL - 0.20f) * 2f;

        if (valueL < 0.5f) {
            valueL = 0.5f;
        }
        if (valueL > 3) {
            valueL = 3;
        }
        framesSkpL = valueL;

        System.out.println("SpeedR "+ framesSkpR + ", SpeedL " + framesSkpL);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public float getNBalance() {
        return nBalance;
    }

    public void setNBalance(float nBalance) {
        this.nBalance = nBalance;
    }

    public int getNChannel() {
        return nChannel;
    }

    public void setNChannel(int nChannel) {
        this.nChannel = nChannel;
    }

    public void stop() {
        musicThread.stopThread();
        line.stop();
    }

    private class MusicThread extends Thread {

        private boolean running;
        private int halfBuffer, musicSize,  bufferSize;
        private float indexR,  indexL;
        private byte[] outBuffer,  bufferMusic;

        public MusicThread(int halfBuffer, int indexR, int indexL, int musicSize, int bufferSize, byte[] outBuffer, byte[] bufferMusic) {
            this.halfBuffer = halfBuffer;
            this.indexR = indexR;
            this.indexL = indexL;
            this.musicSize = musicSize;
            this.bufferSize = bufferSize;
            this.outBuffer = outBuffer;
            this.bufferMusic = bufferMusic;
            running = true;
        }

        public void stopThread() {
            this.running = false;
        }

        @Override
        public void run() {
            while (running) {
                for (int i = 0; i < halfBuffer; i++) {
                    indexR += framesSkpR;
                    if (indexR >= musicSize) {
                        indexR -= musicSize;
                    }
                    outBuffer[i * 2] = bufferMusic[(int)indexR];
                    indexL += framesSkpL;
                    if (indexL >= musicSize) {
                        indexL -= musicSize;
                    }
                    outBuffer[i * 2 + 1] = bufferMusic[(int)indexL];
                }
                line.write(outBuffer, 0, bufferSize);
            }
        }
    }
}

