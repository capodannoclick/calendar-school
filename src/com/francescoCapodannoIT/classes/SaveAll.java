/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;


import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.swingx.JXDatePicker;



/**
 * This class save all the project. If finestraconf is not created it's save just the main panel.
 * If the finestraconf is created but not saved ask to the user to save
 * @author Francesco Capodanno
 */
public class SaveAll {
    
    private FinestraIniziale finestra;
    private FinestraConfigurazione finestraconf;
    private final JFileChooser jFileSave;
   // private final JFileChooser jFileOpen;
    List<String> lines; // linee da salvare;
    File file;
    
   public SaveAll(FinestraIniziale fi, FinestraConfigurazione fc)
           {
            finestra = fi;
            finestraconf = fc;    
            jFileSave = new JFileChooser(); 
            //jFileOpen = new JFileChooser();
            if ((finestra.isConfigured) && (finestra.mainContainer.getComponentCount() > 0))
           {
            if (whereSave()) if (whatSave()) save();
           } else nothingToSave();
            
           }

    private boolean whereSave() {
        boolean whereSave = true;
        
        FileFilter filter = new FileNameExtensionFilter("OSP One Year School Schedule Files Project (*.osp)","osp");
        jFileSave.addChoosableFileFilter(filter);
        jFileSave.setFileFilter(filter);
        
        jFileSave.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        
        jFileSave.setDialogTitle("Salva progetto");
        int returnVal = jFileSave.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {   
            String trovaext;
            trovaext = jFileSave.getSelectedFile().toString();
           
            /* Se troviamo l'estensione lo salviamo così com'è se invece non troviamo l'estensione l'aggiungiamo
                       * Notare che usiamo il metodo endsWith utile per comprendere come utilizzare le stringhe
                       */
            if (trovaext.endsWith(".osp"))
            {
                file = jFileSave.getSelectedFile(); 
            }
            else
            {
               file = new File(jFileSave.getSelectedFile()+".osp"); 
            }
        }
        else {
               
                whereSave = false;
            }
        return whereSave;
      
    }

    private boolean whatSave() {
        boolean okSave;
        lines = new ArrayList<>();
            lines.add("# File Progetto One Year School Scenario Schedule");
            lines.add("# E' consigliato non cambiare manualmente questo file ma adoperare il software");
            lines.add("# One Year School Scenario Schedule. Il seguente software è stato realizzato da");
            lines.add("# Francesco Capodanno(c) 2014");
            lines.add("<openHour>");
            lines.add(Integer.toString(finestra.hhStartLessons));
            lines.add("</openHour>");
            lines.add("<openMinutes>");
            lines.add(Integer.toString(finestra.mmStartLessons));
            lines.add("</openMinutes>");
            lines.add("<endHour>");
            lines.add(Integer.toString(finestra.hhEndLessons));
            lines.add("</endHour>");
            lines.add("<endMinutes>");
            lines.add(Integer.toString(finestra.mmEndLessons));
            lines.add("</endMinutes>");           
            
            if (finestraconf==null) 
            {
                lines.add("<isConfigured>");
                lines.add("no");
                lines.add("</isConfigured>");
            } else
            {
                if (finestraconf.isToSave) finestraconf.saveConfiguration();
                if (finestraconf.isToSave) {
                lines.add("<isConfigured>");
                lines.add("no");
                lines.add("</isConfigured>");
                    
                } else{
                lines.add("<isConfigured>");
                lines.add(finestraconf.configurationFile);
                lines.add("</isConfigured>");
                }
            }
            for (int i=0; i < finestra.mainContainer.getComponentCount();i++)
            {
             MyPanel raccPanel = (MyPanel) finestra.mainContainer.getComponent(i);
             JTextField raccNomeCorso = (JTextField) raccPanel.getComponent(1);
             JXDatePicker raccPicker = (JXDatePicker) raccPanel.getComponent(3); // Data inizio Corso
           JTextField raccOreTotCorso = (JTextField) raccPanel.getComponent(5);  // Ore Tot Corso
           JTextField raccOrePerLezione = (JTextField) raccPanel.getComponent(7); // Ore Per Lezione
           JComboBox raccFrequenza = (JComboBox) raccPanel.getComponent(9); //  Frequenza
           JTextField raccOraInizioCorso = (JTextField) raccPanel.getComponent(11); // ora Inizio Corso
           JCheckBox raccLun = (JCheckBox) raccPanel.getComponent(13);
           JCheckBox raccMar = (JCheckBox) raccPanel.getComponent(14);
           JCheckBox raccMer = (JCheckBox) raccPanel.getComponent(15);
           JCheckBox raccGio = (JCheckBox) raccPanel.getComponent(16);
           JCheckBox raccVen = (JCheckBox) raccPanel.getComponent(17);
           JCheckBox raccSab = (JCheckBox) raccPanel.getComponent(18);
           JCheckBox raccDom = (JCheckBox) raccPanel.getComponent(19); 
             lines.add("<myPanel>");
             lines.add(raccNomeCorso.getText());
             lines.add(raccPicker.getDate().toString());
             lines.add(raccOreTotCorso.getText());
             lines.add(raccOrePerLezione.getText());
             lines.add(raccFrequenza.getSelectedItem().toString());
             lines.add(raccOraInizioCorso.getText());
             if(raccLun.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccMar.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccMer.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccGio.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccVen.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccSab.isSelected()) lines.add("true");
             else lines.add("false");
             if(raccDom.isSelected()) lines.add("true");
             else lines.add("false");
             
             lines.add("</myPanel>");
                
            }
            
        okSave = true;
        return okSave;
       
    }

    private void save() {
        try
                {
                Files.write(file.toPath(), lines, StandardCharsets.UTF_8);                
                    JOptionPane.showMessageDialog(null,
                    "File salvato",
                    "Progetto",
                     JOptionPane.INFORMATION_MESSAGE);    
                    finestra.isToSave = false;
                        
                }
                    catch (IOException | HeadlessException e)
                     {                         
                    JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage(),
                    "Progetto",
                     JOptionPane.ERROR_MESSAGE);                           
                     }
        
    }

    void setSave(FinestraIniziale aThis, FinestraConfigurazione fc) {
      
      finestra = aThis;
      finestraconf = fc;
     if ((finestra.isConfigured) && (finestra.mainContainer.getComponentCount() > 0))
           {
            if (whereSave()) if (whatSave()) save();
           } else nothingToSave();
    }

    private void nothingToSave() {
        JOptionPane.showMessageDialog(null,
                    "Non c'è nulla da salvare",
                    "Progetto",
                     JOptionPane.ERROR_MESSAGE);  
    }
    
}
