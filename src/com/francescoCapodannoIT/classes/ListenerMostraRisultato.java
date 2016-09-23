/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Francesco Capodanno (c)
 */
public class ListenerMostraRisultato implements ActionListener {
    
    FinestraIniziale finestra;
    /**
     *
     * @param frame
     */
    public ListenerMostraRisultato(FinestraIniziale frame)
            {
                finestra = frame;
                
                
            }

    @Override
    public void actionPerformed(ActionEvent e) {
       elaboraDati();
       
    }

    private void elaboraDati() {
       finestra.elaboraDati();
    }
    
}
