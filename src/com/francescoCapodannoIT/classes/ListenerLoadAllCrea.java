/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Francesco Capodanno
 */
class ListenerLoadAllCrea implements ActionListener {
FinestraIniziale finestra;
    public ListenerLoadAllCrea(FinestraIniziale aThis) {
        finestra = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int dialogButton = 0;
        if (finestra.isToSave)
        {
        int dialogResult = JOptionPane.showConfirmDialog (null, 
               "Vuoi abbandonare il progetto seguente e caricarne uno nuovo?",
               "Attenzione",dialogButton);
    
        if(dialogResult == JOptionPane.YES_OPTION)  finestra.loadAll(); 
        } else finestra.loadAll();
       
        
           
        
       
    }
    
}
