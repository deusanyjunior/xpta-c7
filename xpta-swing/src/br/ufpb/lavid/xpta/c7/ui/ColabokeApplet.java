/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufpb.lavid.xpta.c7.ui;

import java.applet.Applet;
import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author rennan
 */
public class ColabokeApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        setSize(415, 360);
        setVisible(true);
        // TODO start asynchronous download of heavy resources
    }

    @Override
    public void start() {
        add(new JPanelUpload());
        this.setVisible(true);
        final Applet a = this;
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    a.validate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }

            }
        }.start();
        super.start();
    }

    @Override
    public void paint(Graphics g) {
        this.getComponent(0).paint(g);
    }




    // TODO overwrite start(), stop() and destroy() methods
}
