package br.ufpb.lavid.xpta.c7.waveform;

import javax.swing.*;
import java.awt.*;

public class SingleWaveformPanel extends JPanel {
    protected static final Color BACKGROUND_COLOR = Color.black;
    protected static final Color REFERENCE_LINE_COLOR = Color.red;
    protected static final Color WAVEFORM_COLOR = Color.yellow;


    private AudioInfo helper;
    private int channelIndex;

    public SingleWaveformPanel(AudioInfo helper, int channelIndex) {
        this.helper = helper;
        this.channelIndex = channelIndex;
        setBackground(BACKGROUND_COLOR);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int lineHeight = getHeight() / 2;
        g.setColor(REFERENCE_LINE_COLOR);
        g.drawLine(0, lineHeight, (int)getWidth(), lineHeight);

        drawWaveform(g, helper.getAudio(channelIndex));

    }

    protected void drawWaveform(Graphics g, int[] samples) {
        if (samples == null) {
            return;
        }

        int oldX = 0;
        int oldY = (int) (getHeight() / 2);
        int xIndex = 0;

        int increment = helper.getIncrement(helper.getXScaleFactor(getWidth()));
        g.setColor(WAVEFORM_COLOR);

        int t = 0;

        for (t = 0; t < increment; t += increment) {
            g.drawLine(oldX, oldY, xIndex, oldY);
            xIndex++;
            oldX = xIndex;
        }

        for (; t < samples.length; t += increment) {
            double scaleFactor = helper.getYScaleFactor(getHeight());
            double scaledSample = samples[t] * scaleFactor;
            int y = (int) ((getHeight() / 2) - (scaledSample));
            g.drawLine(oldX, oldY, xIndex, y);

            xIndex++;
            oldX = xIndex;
            oldY = y;
        }
    }
}