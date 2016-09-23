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
public class RightButton extends JButton{
    
    /**
     * Posizione del Bottone: deve cambiare quando si muove il pannello. +1 a destra -1 a sinistra.
     */
    public int indexButton;
    FinestraIniziale finestra;
    SetUpGUI toGUI;
    

    RightButton(SetUpGUI aThis, FinestraIniziale fi, int componentCount) {
     /*
        Quando è instanziato indexButton contiene la posizione del pannello iniziale.
        */
     indexButton = componentCount;      
     finestra = fi;
     toGUI = aThis;
     setText("-->");
     this.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
            
            tryToMove();
         }
     });
    }
    
    public void tryToMove()
    {
       if(indexButton < finestra.mainContainer.getComponentCount())
       {
        try
        {
        RightButton nextButton = this.toGUI.buttons_right.get(indexButton + 1);
        int indexNext = nextButton.indexButton;
        
      
        
        if (indexButton < indexNext)
        {        
        JPanel temp1 = (JPanel) finestra.mainContainer.getComponent(indexButton);
        JPanel temp2 = (JPanel) finestra.mainContainer.getComponent(indexNext);
        
        
        
        finestra.mainContainer.add(temp2, indexButton);
        finestra.mainContainer.add(temp1, indexNext);       
        indexButton++;
        nextButton.indexButton = indexButton - 1;        
        //finestra.mainContainer.add(temp1, indexNext);
        
        finestra.mainContainer.repaint();
        finestra.mainContainer.updateUI();
        finestra.mainContainer.validate();
        
        repaint();
        updateUI();
        validate();
       
        
       
        
        RightButton temp_button2 = nextButton;        
        int indexButton_first = indexButton - 1;
        LeftButton temp_buttonL  = this.toGUI.buttons_left.get(indexButton_first);
        LeftButton temp_buttonL_next = this.toGUI.buttons_left.get(indexButton);
        AddButton temp_buttonA = this.toGUI.buttons_add.get(indexButton_first);
        AddButton temp_buttonA_next = this.toGUI.buttons_add.get(indexButton);
        temp_buttonL.indexButton = indexButton;
        temp_buttonL_next.indexButton = indexButton_first;
        temp_buttonA.indexButton = indexButton;
        temp_buttonA_next.indexButton = indexButton_first;
        
        
        this.toGUI.buttons_left.set(indexButton_first, temp_buttonL_next);
        this.toGUI.buttons_left.set(indexButton, temp_buttonL);
        this.toGUI.buttons_add.set(indexButton_first, temp_buttonA_next);
        this.toGUI.buttons_add.set(indexButton, temp_buttonA);
        this.toGUI.buttons_right.set(indexButton_first, temp_button2);
        this.toGUI.buttons_right.set(indexButton, this);
        this.finestra.isToSave = true;
        } 
        
        }
        catch (Exception e)
                {
                 
                }
       }
       else
       {
           
       }
    }
    
}
