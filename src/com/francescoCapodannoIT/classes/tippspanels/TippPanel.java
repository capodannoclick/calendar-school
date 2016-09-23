/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes.tippspanels;

import com.francescoCapodannoIT.classes.HarburTipps;
import com.francescoCapodannoIT.classes.HarburTipps.Route;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

/**
 *
 * @author Francesco Capodanno
 */
public class TippPanel implements Tippable {
    
    private String message;
    
    
    /* COSTRUTTORI */
    public TippPanel()
    {
        System.out.println("TippPanel Default instanziato.");
    }
    
    public TippPanel(JFrame p, String s, Route isinSeq){
         
         if (isinSeq == HarburTipps.route)
        { 
        System.out.println("Instanziamento pannello per un JFrame...");
        message = s;
        tippPanelPerJFrame(p, isinSeq);         
        } else {System.out.println("Questo tipp non è avviabile per questa sequenza"); }
       
    }
    
    public TippPanel(JMenu m, String s, Route isinSeq)
    {if (isinSeq == HarburTipps.route)
        { 
       message = s;
       tippPanelPerJMenu(m, isinSeq);         
        } else {System.out.println("Questo tipp non è avviabile per questa sequenza"); }
    }
    
    public TippPanel(JComponent c, String s, Route isinSeq)
    {
     if (isinSeq == HarburTipps.route)
        {   
     message = s;
     tippPanelPerJComponent(c, isinSeq);         
        } else {System.out.println("Questo tipp non è avviabile per questa sequenza"); }      
    }
    
    public TippPanel(JPanel j, String s, Route isinSeq)
    {
        if (isinSeq == HarburTipps.route)
        {
       message = s;
       tippPanelPerJPanel(j, isinSeq);
       } 
    }
    
    /**/

    @Override
    public void tippPanelPerJFrame(JFrame p, Route inSeq) {
        //Composizione                   
           
            TippPanelJFrame tippPanel = new TippPanelJFrame(p,message);
     
    }

    @Override
    public void tippPanelPerJMenu(JMenu m, Route inSeq) {
       //Composizione
         TippPanelJMenu tippPanel = new TippPanelJMenu(m, message);
    }

    @Override
    public void tippPanelPerJPanel(JPanel p, Route inSeq) {
        //Composizione
          TippPanelJPanel tippPanel = new TippPanelJPanel(p, message);
    }
    
    @Override
    public void tippPanelPerJComponent(JComponent c, Route inSeq) {
        //Composizione
         TippPanelJComponent tippPanel = new TippPanelJComponent(c ,message);
    }

   
    public void setTextTipp(String s) {
        //Composizione
       message = s;
    }

    

    
    
}
