package br.ufpb.lavid.xpta.c7.waveform;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;

public class WaveformDisplaySimulator {

    public static void newWaveformDisplaySimulator(AudioInputStream audioInputStream) {
            JFrame frame = new JFrame("Waveform Simulator");
            frame.setBounds(200,200, 500, 350);

            WaveformPanelContainer container = new WaveformPanelContainer();
            container.setAudioToDisplay(audioInputStream);

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(container, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
            frame.validate();
            frame.repaint();
    }
}
