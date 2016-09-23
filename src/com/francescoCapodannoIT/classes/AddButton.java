/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author guru
 */
public class AddButton extends JButton {

    /**
     * Posizione del Bottone: deve cambiare quando si muove il pannello. +1 a destra -1 a sinistra.
     */
    public int indexButton;
    MyPanel panelGUI;
    FinestraIniziale finestra;
    SetUpGUI toGUI;

    AddButton(FinestraIniziale fi, SetUpGUI aThis) {
      
       setText("+");       
       finestra = fi; 
       toGUI = aThis;
       indexButton = finestra.mainContainer.getComponentCount();
       
       this.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {           
            
            addPanel();
            finestra.isToSave = true;
            finestra.isToSave();
         }
     });
    }
    
    
    
    public void addPanel()
    {
          
          int num_course_add;
          int choiche;
          num_course_add = (FinestraIniziale.MAX_COURSES - finestra.mainContainer.getComponentCount()) ;
          if (num_course_add > 0)
          {
          System.out.println("Da crea: numeri  di pannelli aggiungibili:" + num_course_add);
          List<Object> possibilities = new ArrayList<>();
          for (int i=1; i<= num_course_add; i++)
          {
              String possibile;
              possibile = Integer.toString(i);
              possibilities.add(possibile);
          }
          Object[] array = possibilities.toArray(new Object[possibilities.size()]);
          
          
          String s;
              s = (String)JOptionPane.showInputDialog(null,
                      "Quanti corsi devo aggiungere?",
                      "Crea programmazione corsi",
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      array,
                      "1");
            try
            {
               choiche = Integer.parseInt(s);               
            }
            catch(Exception e)
            {
             choiche = 0;
            }
            if (choiche > 0) addnPanels(choiche);
          }
          else
          {
             
             JOptionPane.showMessageDialog(null,
            "Questa applicazione non gestisce più di 16 corsi diversi\n"
                    + "\nserve solo per mostrare uno scenario\n",
            "Error",
           JOptionPane.ERROR_MESSAGE);  }
    }

    private void addnPanels(int choiche) {
      System.out.println("add panels");
       /*
             Bisogna implementare le classi su SetUpGUI per l'add Button.
             bisognaggiornare gli indici di tutti i bottoni seguenti all'inserimento.
            */
      toGUI.createGui_position(indexButton, choiche);
      
    }
   

    
    
   
    
    
}
