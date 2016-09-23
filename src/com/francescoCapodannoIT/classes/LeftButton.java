/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author guru
 */
public class LeftButton extends JButton {
public int indexButton;
    FinestraIniziale finestra;
    SetUpGUI toGUI;
   

    LeftButton(SetUpGUI aThis, FinestraIniziale fi, int componentCount) {
       /*
        Quando è instanziato indexButton contiene la posizione del pannello iniziale.
        */
     indexButton = componentCount;      
     finestra = fi;
     toGUI = aThis;
     setText("<--");
     this.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
            
            tryToMove();
         }
     });  
    }
    
    public void tryToMove()
    {
        if (indexButton > 0)
        {
            LeftButton precButton = this.toGUI.buttons_left.get(indexButton - 1);
            int precButtonIndex = precButton.indexButton;
            
            
            if (indexButton > precButtonIndex)
            {
                /*
                              *  Allora fai lo spostamento
                              */
                try
                {
                JPanel temp1 = (JPanel) finestra.mainContainer.getComponent(indexButton);
                JPanel temp2 = (JPanel) finestra.mainContainer.getComponent(precButtonIndex);
                
                finestra.mainContainer.add(temp2, indexButton);
                finestra.mainContainer.add(temp1, precButtonIndex);       
                indexButton--;
                precButton.indexButton = indexButton + 1;
                
                finestra.mainContainer.repaint();
                finestra.mainContainer.updateUI();
                finestra.mainContainer.validate();
        
                repaint();
                updateUI();
                validate();
                
                LeftButton temp_button2 = precButton;        
                int indexButton_first = indexButton + 1;
                RightButton temp_buttonR  = this.toGUI.buttons_right.get(indexButton_first);
                RightButton temp_buttonR_next = this.toGUI.buttons_right.get(indexButton);
                AddButton temp_addB = this.toGUI.buttons_add.get(indexButton_first);
                AddButton temp_addB_next = this.toGUI.buttons_add.get(indexButton);
                temp_buttonR.indexButton = indexButton;
                temp_buttonR_next.indexButton = indexButton_first;
                temp_addB.indexButton = indexButton;
                temp_addB_next.indexButton = indexButton_first;
        
                this.toGUI.buttons_right.set(indexButton_first, temp_buttonR_next);
                this.toGUI.buttons_right.set(indexButton, temp_buttonR);
                this.toGUI.buttons_left.set(indexButton_first, temp_button2);
                this.toGUI.buttons_left.set(indexButton, this);
                this.toGUI.buttons_add.set(indexButton_first, temp_addB_next);
                this.toGUI.buttons_add.set(indexButton,temp_addB);
                finestra.isToSave = true;
                }
                catch (Exception e)
                {
                    
                }
                
                
            }
            
        }
        
    }
    
}
