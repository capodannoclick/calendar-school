/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes.tippspanels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Francesco Capodanno
 */
public class TippPanelJFrame extends JDialog {
    
    private final JFrame jFrame;
    private Timer timeForDisplay; 
    private final String message; // qui c'è il messaggio da visualizzare
    private JPanel container;
    private JDialog dialogMessage;
    
    TippPanelJFrame(JFrame p, String s)
    {
      jFrame = p;
      message = s;
      
      init_Dialog();
      System.out.println("E' stato instanziato un pannello per un JFrame ")  ;
    }
    
    private void init_Dialog(){
        init_Timer();
        
        container = new JPanel();
        JLabel message_out = new JLabel("<html><h3>" + message +"</h3><html>");        
        add(container);
        container.add(message_out);
        
        JOptionPane.showMessageDialog(jFrame,
    message_out,
    "Tipp",
    JOptionPane.PLAIN_MESSAGE);
        
        
        
        
        
        
    }
    
    private void init_Timer()
    {
        
    }
    
    
    
}
