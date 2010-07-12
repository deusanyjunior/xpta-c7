package br.ufpb.lavid.xpta.c7.waveform;

import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.util.ArrayList;

public class WaveformPanelContainer extends JPanel {
    private ArrayList singleChannelWaveformPanels = new ArrayList();
    private AudioInfo audioInfo = null;

    public WaveformPanelContainer() {
        setLayout(new GridLayout(0,1));
    }

    public void setAudioToDisplay(AudioInputStream audioInputStream){
        singleChannelWaveformPanels = new ArrayList();
        audioInfo = new AudioInfo(audioInputStream);
        for (int t=0; t<audioInfo.getNumberOfChannels(); t++){
            SingleWaveformPanel waveformPanel
                    = new SingleWaveformPanel(audioInfo, t);
            singleChannelWaveformPanels.add(waveformPanel);
            add(createChannelDisplay(waveformPanel, t));
        }
    }

    private JComponent createChannelDisplay(SingleWaveformPanel waveformPanel, int index) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(waveformPanel, BorderLayout.CENTER);

        JLabel label = new JLabel("Channel " + ++index);
        panel.add(label, BorderLayout.NORTH);

        return panel;
    }
}