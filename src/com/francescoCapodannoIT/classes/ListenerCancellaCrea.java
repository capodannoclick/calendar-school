/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Francesco Capodanno
 */

public class ListenerCancellaCrea implements ActionListener {
    FinestraIniziale finestra;
    FinestraConfigurazione finestraconf;
    boolean isEraseAll;
    
    public ListenerCancellaCrea(FinestraIniziale frame)
    {
        finestra = frame;
    }    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TO DO
        eraseAll();      
              
    }

    private boolean message() {
        boolean eraseAll;
        eraseAll = false;
        if (finestra.mainContainer.getComponentCount()>0)
        {
        int dialogButton = 0;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Sei sicuro di elminare tutti questi corsi?"
            +"\nNon si può tornare indietro"
            +"\nSe non sei sicuro salva questo lavoro","Attenzione",dialogButton);
    
        if(dialogResult == JOptionPane.YES_OPTION) eraseAll = true; 
        }
        return eraseAll;
    }

    private boolean message2() {
       boolean eraseAll;
        eraseAll = false;
        int dialogButton = 0;
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi resettare la configurazione?","Attenzione",dialogButton);
    
        if(dialogResult == JOptionPane.YES_OPTION) eraseAll = true; 
        return eraseAll;
    }
    
    public void eraseAll()
    {
     isEraseAll = message();
     if (isEraseAll)
     {
        if (finestra.isCreated == true){
        finestra.mainContainer.removeAll();
        finestra.mainContainer.updateUI(); 
        finestra.oggettiGUI.clear(); 
        finestra.isConfigured = false;
        finestra.isCreated = false;
        //finestra.isConfigured = false;
        finestra.isDefault = false;
        finestra.isSetUpGui = false;
        finestra.creaItemMnu.setText("Crea");
       
        if (finestra.finestraconf != null)
        {
      
        boolean isEraseAll2 = message2();
        if (isEraseAll2)
         {
            finestra.finestraconf.setVisible(false);
            finestra.finestraconf.dispose();      
            finestra.finestraconf = null;
         } else finestra.isConfigured = true;
        }
        finestra.removeResults();
        /*
        SETTAGGIO COLORI
        */
        finestra.cancellaItemMnu.setBackground(Color.red);
        finestra.configuraItemMnu.setBackground(Color.green);
        finestra.creaItemMnu.setBackground(Color.YELLOW);
        finestra.mostraItemMnu.setBackground(Color.red);
        finestra.creaItemMnu.setVisible(true);
        finestra.saveItemMnu.setBackground(Color.red);
        
        }      
        else
        {
            Component fr = null;
            
          JOptionPane.showMessageDialog(fr,
            "Non c'è nulla da cancellare.",
            "Errore",
           JOptionPane.ERROR_MESSAGE);
        }
     }
    }
    
}
