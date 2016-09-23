/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author guru
 */
public class EraseButton extends JButton{
public int indexButton;
FinestraIniziale finestra;
SetUpGUI toGUI;

MyPanel OggettiGUItoRemove;

   

    EraseButton(MyPanel panelOggettiGUI, FinestraIniziale fi, SetUpGUI g) {
        setText("x");
        finestra = fi;
        OggettiGUItoRemove = panelOggettiGUI;
        toGUI = g;
        this.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {           
            
            tryToErase();
            
            
             toGUI.ArrangeIndexes();
             if (finestra.mainContainer.getComponentCount() == 0) finestra.eraseAll();
             finestra.isToSave = true;   
             
             
            
                    
         }
     });
    }
    
    public void tryToErase()
    {   
    int dialogButton = 0;
    //UIManager.put("OptionPane.yesButtonText", "Si");
    int dialogResult = JOptionPane.showConfirmDialog (null, "Sei sicuro di elminare questo corso?"
            +"\n Non si può tornare indietro"
            +"\n Se non sei sicuro salva questo lavoro","Attenzione",dialogButton);
    
        if(dialogResult == JOptionPane.YES_OPTION){
        finestra.mainContainer.remove(OggettiGUItoRemove); 
        finestra.repaint();
        finestra.revalidate();
        
        
        
         
        
   }
    }

}
    

