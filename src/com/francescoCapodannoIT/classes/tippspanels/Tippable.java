/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes.tippspanels;

import com.francescoCapodannoIT.classes.HarburTipps.Route;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

/**
 *
 * @author Francesco Capodanno
 */
public interface Tippable {

    
    /**
     * This function make a TippPanel for a simple JFrame 
     * @param p JFrame that you want to add an Tipp
     * @param isinSeq if is false the tipp Panel is not showed
     */
    public void tippPanelPerJFrame(JFrame p, Route inSeq);

    /**
     *
     * @param p1 JFrame for reference
     * @param m  JMenu on you want to add an Tipp
     * @param isinSeq if is false the tipp Panel is not showed
     */
    public void tippPanelPerJMenu(JMenu m, Route inSeq);

    /**
     *
     * @param p  JFrame for reference
     * @param p2 JPanel to add
     * @param isinSeq if is false the tipp Panel is not showed
     */
    public void tippPanelPerJPanel(JPanel p, Route inSeq);
    
    /**
     * Adding a Tipp Panel for a JComponent
     * @param c
     * @param isinSeq
     */
    public void tippPanelPerJComponent(JComponent c, Route inSeq);
    

    
}
