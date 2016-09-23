/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Francesco Capodanno(c) 2014
 */
public class ListenerConfiguraCrea implements ActionListener{
    FinestraIniziale finestra;
    
    
    public ListenerConfiguraCrea(FinestraIniziale frame)
    {
        finestra = frame;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                Translator.init_str_FinestraConfigurazione(); // Inizializza le stringhe
                if ((!finestra.isConfigured)|| (finestra.isDefault)){        
                finestra.initConfigurazione();
                finestra.finestraconf.setVisible(true);  
                finestra.finestraconf.validate();
                        finestra.finestraconf.container.validate();
                        finestra.finestraconf.container.updateUI();
        
                
                }
                else 
                    if ((!finestra.isDefault) || (finestra.isConfigured))
                    {   finestra.setVisible(false);
                        finestra.finestraconf.setVisible(true);
                        finestra.finestraconf.validate();
                        finestra.finestraconf.container.validate();
                        finestra.finestraconf.container.updateUI();}
                
               
          
    }
    
    /**
     *
     * @return restituisce alla FinestraIniziale la FinestraConfigurazione
     */
   
    
}
