/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;


import java.awt.Component;
import java.awt.HeadlessException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author guru
 */
public class ListenerMenuCrea implements ActionListener {
    FinestraIniziale finestra;
    JPanel myPanel;
    JPanel myPanel_start;
    JLabel label1, label2, label3;
    JLabel label1_start;
    JComboBox hhList;
    JComboBox mmList;
    JComboBox hhList_start;
    JComboBox mmList_start;
    
     String[] hhString;   
     
     String[] mmString;
    
    public ListenerMenuCrea(FinestraIniziale frame)
    {
        this.mmString = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        this.hhString = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        this.mmList_start = new JComboBox(mmString);
        this.hhList_start = new JComboBox(hhString);
        this.mmList = new JComboBox(mmString);
        this.hhList = new JComboBox(hhString);       
        this.label1 = new JLabel(Translator.str_FinestraIniziale("Orario di chiusura della scuola"),
                JLabel.CENTER);
        this.label2 = new JLabel(Translator.str_FinestraIniziale("Ore"), JLabel.CENTER);
        this.label3 = new JLabel(Translator.str_FinestraIniziale("Minuti"), JLabel.CENTER);
        this.label1_start = new JLabel(Translator.str_FinestraIniziale("Orario di apertura della scuola"),
                JLabel.CENTER);
        
        this.myPanel = new JPanel();
        this.myPanel_start = new JPanel();
        finestra = frame;
        myPanel.add(label1);
        myPanel.add(label2);
        myPanel.add(hhList);
        myPanel.add(label3);
        myPanel.add(mmList);
     
        myPanel_start.add(label1_start);
        myPanel_start.add(label2);
       myPanel_start.add(hhList_start);
       myPanel_start.add(label3);
       myPanel_start.add(mmList_start);
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        dialogCrea(); // Il numero di corsi da creare
    }
    
    public final void dialogCrea()
    {Component fr = null;    
        Icon icon = null; // default
        int castS;
        int casthh;
        int castmm;
        int casthh_start;
        int castmm_start;
        
        boolean err1,err2,err3;
    label1.setText(Translator.str_FinestraIniziale("Orario di chiusura della scuola"));
    label1_start.setText(Translator.str_FinestraIniziale("Orario di apertura della scuola"));
    label2.setText(Translator.str_FinestraIniziale("Ore"));
    label2.setText(Translator.str_FinestraIniziale("Minuti"));
     
     hhList.setSelectedIndex(18);
     mmList.setSelectedIndex(0);
     
     
     hhList_start.setSelectedIndex(8);
     mmList_start.setSelectedIndex(0);
     
    if (finestra.isCreated == false)  
      {
          if (!finestra.isConfigured)
          {
         // Translator.init_str_FinestraIniziale();
          JOptionPane.showMessageDialog(fr,
            Translator.str_FinestraIniziale("Non hai creato una configurazione generale dei corsi,")
           +Translator.str_FinestraIniziale("\nutilizzerò una configurazione di default standard")
           + "\n"
           + Translator.str_FinestraIniziale("\n1)sabato e domenica non si fanno corsi")
           + Translator.str_FinestraIniziale("\n2)tutte le aule sono disponibili(50).")
           + Translator.str_FinestraIniziale("\n3)nessuna data specifica è esclusa dal calendario")
                   ,
             Translator.str_FinestraIniziale("Errore"),
           JOptionPane.ERROR_MESSAGE);
          finestra.isConfigured = false;
          finestra.isDefault = true;
       
          
      }
        
        
     Object[] possibilities = {"1", "2", "3", "4", "5", "6", 
         "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
     String s = (String)JOptionPane.showInputDialog(fr,
                    Translator.str_FinestraIniziale("Quanti corsi devo programmare?"),
                    Translator.str_FinestraIniziale("Crea programmazione corsi"),
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "1");
       
      if (s != null)
      {
      JOptionPane.showMessageDialog(null, myPanel_start);
      JOptionPane.showMessageDialog(null, myPanel);
      }
      
     
     try
     {         
      /* Prova a convertire se non riesce vuol dire che non è stato inserito nulla quindi nulla si fa */  
     
     castS = Integer.parseInt(s); // La variabile è string per utilizzarla conviene convertirla in int
    
     casthh = Integer.parseInt(hhList.getSelectedItem().toString());
     castmm = Integer.parseInt(mmList.getSelectedItem().toString());
     casthh_start = Integer.parseInt(hhList_start.getSelectedItem().toString());
     castmm_start = Integer.parseInt(mmList_start.getSelectedItem().toString());
     
     
     
     err2 = ((casthh - casthh_start) < (finestra.getMininumHours));  
     int maxOrePerDay = casthh - casthh_start;
     
     if (err2)
     {
          fr = null;
                 JOptionPane.showMessageDialog(fr,
            "Number Hours exceeded:"
          + "Sono stati rilevati alcuni errori gravi durante l'inserimento \n"
          + "delle ore lezioni. La scuola ha un minimo di ore di apertura e\n "
           +"e l'ora di inizio non può essere più grande dell'ora di fine."
                         ,
            "Error",
           JOptionPane.ERROR_MESSAGE);
     } else
     {
     
     finestra.hhStartLessonsString = hhList_start.getSelectedItem().toString();
     finestra.mmStartLessonsString = mmList_start.getSelectedItem().toString();
     finestra.hhEndLessons = casthh;
     finestra.mmEndLessons = castmm;
     finestra.hhStartLessons = casthh_start;
     finestra.mmStartLessons = castmm_start;
     finestra.oreLezMaxPerDay_oneRoom = maxOrePerDay;
     finestra.creaProgrammazione(true, castS);
     finestra.isCreated = true;     
     finestra.creaItemMnu.setVisible(false);
     //finestra.creaItemMnu.setText("Aggiungi corso");
     //finestra.configuraItemMnu.setBackground(Color.red);
    
     
     
     }
     }
     catch (NumberFormatException | HeadlessException e)
     {
       System.out.println(Arrays.toString(e.getStackTrace())); 
      
     }
     
      }
      else 
      {
         
          
      }
  
    }
    
}
