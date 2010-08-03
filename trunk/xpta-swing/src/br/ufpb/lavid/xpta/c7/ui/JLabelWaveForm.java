/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufpb.lavid.xpta.c7.ui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author rennan
 */
public class JLabelWaveForm extends JLabel {

    private JPanelTrack panelTrack;

    public JLabelWaveForm(JPanelTrack panelTrack) {
        super((new ImageIcon("wave_form.png")));
        this.panelTrack = panelTrack;
        setBounds(JPanelUpload.getRedLine(), panelTrack.getBounds().y, 700, panelTrack.getHeight());
        panelTrack.getTrack().setOffset((JPanelUpload.getRedLine()-415)*500);
        addMouseMotionListener(new DragAdapter());
    }

    private class DragAdapter extends MouseMotionAdapter {

        int oldX = -1;

        @Override
        public void mouseMoved(MouseEvent e) {
            oldX = e.getXOnScreen();
            super.mouseMoved(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(oldX != -1) {
                Rectangle r = getBounds();
                int newX = 415 + (e.getXOnScreen()-oldX);
                if(newX < 415)
                    newX = 415;
                setBounds(newX, r.y, r.width, r.height);
                panelTrack.getTrack().setOffset((newX-415)*500);
            }
        }

    }

}
