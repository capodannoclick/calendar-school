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
class MainPanel extends JPanel {

    public MainPanel() {
       // setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        setBorder(BorderFactory.createBevelBorder(WIDTH, Color.lightGray, Color.gray));
        this.setBackground(Color.lightGray);
        
    }
    
    public Dimension setExtendedSize()
    {
        return new Dimension(600,1800);
    }
    
    public Dimension setMediumSize()
    {
        return new Dimension(600,800);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(780,2000);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        //g.drawString("Questo è il mio Pannello!",10,20);
    }  
}
