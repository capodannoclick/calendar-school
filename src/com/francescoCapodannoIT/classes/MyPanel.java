/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Francesco Capodanno
 * Questa classe personalizza la grafica dei singoli pannelli da aggiungere alla GUI
 */
class MyPanel extends JPanel {

    public MyPanel() {
        
        setBorder(BorderFactory.createBevelBorder(WIDTH, Color.lightGray, Color.lightGray, Color.white, Color.orange));
    }

    MyPanel(MyPanel panelOggettiGUI) {
        /*
              Duplica il pannello
              */
        for (int i=0; i <panelOggettiGUI.getComponentCount();i++)
        {
        this.add(panelOggettiGUI.getComponent(i));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(380,242);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        //g.drawString("Questo è il mio Pannello!",10,20);
    }  
}
