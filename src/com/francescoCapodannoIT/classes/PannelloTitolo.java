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
 * @author guru
 */
public class PannelloTitolo extends JPanel {
 
    /**
     *
     * 
     */
    int weight;
    int height;
    public PannelloTitolo(String s, int a, int b)
            {
                weight = b;
                height = a;
               
               setBorder(BorderFactory.createTitledBorder(null, s, 
                         javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                         javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                         null, Color.blue));
                
            }
    
    public void setTitle(String s)
    {
        this.setBorder(BorderFactory.createTitledBorder(null, s, 
                         javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                         javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                         null, Color.blue));
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(height,weight);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        //g.drawString("Questo è il mio Pannello!",10,20);
    }  
    
}
