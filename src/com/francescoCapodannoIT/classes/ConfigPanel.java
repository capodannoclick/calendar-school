/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author guru
 */
public class ConfigPanel extends JPanel {
    public ConfigPanel() {
       // setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        setBorder(BorderFactory.createBevelBorder(WIDTH, Color.lightGray, Color.gray));
       
        
    }
    
    
}
