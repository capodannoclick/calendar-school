/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Francesco Capodanno
 */
public class ListenerTranslator_itCrea implements ActionListener {
    
    FinestraConfigurazione finestraConf;
    FinestraIniziale finestra;
    ResultsJFrame resultsFrame;
    
    
    
    
    public ListenerTranslator_itCrea(FinestraConfigurazione fc, 
            FinestraIniziale fi, ResultsJFrame rf){
        finestraConf = fc;
        finestra = fi;
        resultsFrame = rf;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
              
    }
    
}
