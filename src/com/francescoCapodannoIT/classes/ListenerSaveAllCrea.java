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
class ListenerSaveAllCrea implements ActionListener {

    FinestraIniziale finestra;
    public ListenerSaveAllCrea(FinestraIniziale aThis) {
        finestra = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (finestra.isCreated) finestra.saveAll();
        else 
        {
            JOptionPane.showMessageDialog(null,
            "Non è stato rilevato nulla da salvare \n",
            "Error",
           JOptionPane.ERROR_MESSAGE);
        }
      
    }
    
}
