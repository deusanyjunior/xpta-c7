/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPanelTrack.java
 *
 * Created on 08/07/2010, 09:02:01
 */
package br.ufpb.lavid.xpta.c7.ui;

import br.ufpb.lavid.xpta.c7.mp3.Track;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JToggleButton;

/**
 *
 * @author rennan
 */
public class JPanelTrack extends javax.swing.JPanel {

    private static int base;
    private Track track;
    private static JPanelTrack solo;
    private static LinkedList<JPanelTrack> instances = new LinkedList<JPanelTrack>();

    /** Creates new form JPanelTrack */
    public JPanelTrack(String trackName) {
        initComponents();
        track = new Track("Tracks/"+trackName);
//        track.resume();
        jLabelNome.setText(trackName);
        setSize(getPreferredSize());
        setBounds(0, base+(instances.size())*getHeight(), getWidth(), getHeight());
        instances.add(this);
    }

    public static LinkedList<JPanelTrack> getInstances() {
        return instances;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if(jToolBar1 != null)
            jToolBar1.setBackground(bg);
    }

    public Track getTrack() {
        return track;
    }

    public void updateVolume() {
        boolean someoneElseSolo = (solo != null && solo != this);
        if(jToggleButtonMudo.isSelected() || jSlider1.getValue() == -20 || someoneElseSolo ) {
            track.setVolume(-80);
        } else {
            track.setVolume(jSlider1.getValue());
        }
    }

    public void remove() {
        track.stop();
        setVisible(false);
        instances.remove(this);
        for(int i = 0; i < instances.size(); i++) {
            JPanelTrack jpt = instances.get(i);
            jpt.setBounds(0, base+(i)*jpt.getHeight(), jpt.getWidth(), jpt.getHeight());
        }
    }

    public static void setBase(int base) {
        JPanelTrack.base = base;
    }

    public static void setSolo(JPanelTrack panelTrack) {
        if(solo != null)
            solo.jToggleButtonSolo.setSelected(false);
        solo = (solo == panelTrack)? null : panelTrack;
        for(JPanelTrack t : instances) {
            t.updateVolume();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jToggleButtonMudo = new javax.swing.JToggleButton();
        jToggleButtonSolo = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);

        jToggleButtonMudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufpb/lavid/xpta/c7/ui/mute.png"))); // NOI18N
        jToggleButtonMudo.setToolTipText("Mudo");
        jToggleButtonMudo.setFocusable(false);
        jToggleButtonMudo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonMudo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jToggleButtonMudo.setPreferredSize(new java.awt.Dimension(26, 26));
        jToggleButtonMudo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonMudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMudoActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButtonMudo);

        jToggleButtonSolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufpb/lavid/xpta/c7/ui/solo.png"))); // NOI18N
        jToggleButtonSolo.setToolTipText("Solo");
        jToggleButtonSolo.setFocusable(false);
        jToggleButtonSolo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonSolo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jToggleButtonSolo.setPreferredSize(new java.awt.Dimension(26, 26));
        jToggleButtonSolo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSoloActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButtonSolo);

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufpb/lavid/xpta/c7/ui/close.png"))); // NOI18N
        jToggleButton3.setToolTipText("Remover track");
        jToggleButton3.setFocusable(false);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(26, 26));
        jToggleButton3.setSize(new java.awt.Dimension(26, 26));
        jToggleButton3.setVerticalTextPosition(3);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton3);

        jSlider1.setMaximum(0);
        jSlider1.setMinimum(-20);
        jSlider1.setValue(-10);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jSlider2.setMaximum(10);
        jSlider2.setMinimum(-10);
        jSlider2.setValue(0);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel1.setText("L");

        jLabel2.setText("R");

        jLabel3.setText("Volume:");

        jLabel4.setText("Balanço:");

        jLabelNome.setText("Nome da track");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabelNome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, 0)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 203, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel1)
                        .add(0, 0, 0)
                        .add(jSlider2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, 0)
                        .add(jLabel2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel3)
                    .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelNome))
                .add(0, 0, 0)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel4)
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSlider2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jLabel1))
                .add(0, 0, 0))
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        updateVolume();
    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        track.setBalance(jSlider2.getValue()/10f);
    }//GEN-LAST:event_jSlider2StateChanged

    private void jToggleButtonMudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMudoActionPerformed
        updateVolume();
    }//GEN-LAST:event_jToggleButtonMudoActionPerformed

    private void jToggleButtonSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSoloActionPerformed
        setSolo(this);
    }//GEN-LAST:event_jToggleButtonSoloActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        remove();
        jToggleButton3.setSelected(false);
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButtonMudo;
    private javax.swing.JToggleButton jToggleButtonSolo;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
