/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *This class Load all the files that you need.
 * @author Francesco Capodanno
 */
public class LoadAll {
    private  FinestraIniziale finestra;
    private  FinestraConfigurazione finestraconf; 
    private  JFileChooser jFileOpen;
    private  List<String> lines;

    /**
     *logic errors in loading
     */
    public boolean logicErr;
    
    public LoadAll(FinestraIniziale fi, FinestraConfigurazione fc)
    {        
        this.logicErr = false;
        finestra = fi;       
        finestraconf = fc;
        jFileOpen = new JFileChooser();
        finestra.eraseAll();
        if (whereLoad()) if (whatLoad()) load();
        else nothingToLoad();
        finestra.isToSave = false;
        finestra.isToSave();
    }

    public void setLoad(FinestraIniziale aThis, FinestraConfigurazione fc) {
       finestra = aThis;
       finestraconf = fc;
       jFileOpen = new JFileChooser();
       finestra.eraseAll();
       if (whereLoad()) if (whatLoad()) load();
       else nothingToLoad();
       finestra.isToSave = false;
       finestra.isToSave();
    }

    private void load() {
        int i=0;
        List<String> dataPanels = new ArrayList();
        int panelsToLoad = 0;
        
        for (String line : lines) {
            
            if (line.startsWith("#")){ }
            else if (line.startsWith("<openHour>"))
                    {                     
                      try
                      {
                      finestra.hhStartLessons = Integer.parseInt(lines.get(i+1));
                      }catch(Exception e)
                      {
                       logicErr = true; 
                      }
                      if (!(lines.get(i+2).startsWith("</openHour>"))) 
                          logicErr = true;
                    }
            else if(line.startsWith("<openMinutes>"))
                   {
                     lines.get(i+1);
                     
                     try
                     {
                      finestra.mmStartLessons = Integer.parseInt(lines.get(i+1));
                     }catch (Exception e)
                     {
                         logicErr = true;
                     }
                     if (!(lines.get(i+2).startsWith("</openMinutes>"))) 
                         logicErr = true;                     
                   }
            else if(line.startsWith("<endHour>"))
                   {
                       try
                       {
                           finestra.hhEndLessons = Integer.parseInt(lines.get(i+1));
                       } catch (Exception e)
                       {
                           logicErr = true;
                       }
                       if (!(lines.get(i+2).startsWith("</endHour>"))) 
                          logicErr = true;
                   }
            else if(line.startsWith("<endMinutes>"))
                    {
                      try
                      {
                        finestra.mmEndLessons = Integer.parseInt(lines.get(i+1));
                      }catch (Exception e)
                      {
                          logicErr = true;
                      }
                      if (!(lines.get(i+2).startsWith("</endMinutes>"))) 
                          logicErr = true;
                    }
            else if(line.startsWith("<isConfigured>"))
            {
                 if (lines.get(i+1).equals("no")) {
                     finestra.isDefault = true;
                     if (finestraconf != null) finestraconf.dispose();                     
                 }
                 else 
                 {
                    String path = lines.get(i+1);
                    List<String> records = new ArrayList<>();
                    if (finestraconf != null) finestraconf.dispose();
                    finestraconf = finestra.initConfigurazione_forLoad();
                    finestraconf.setVisible(false);
                    finestra.setVisible(true);
                    /* Si carica la finestra salvata*/
                    finestraconf.configurationFile = path;                    
                    try
                    {
                    BufferedReader reader = new BufferedReader(new FileReader(path)); 
                    String line_to_read;
                    while ((line_to_read = reader.readLine()) != null)
                    {
                    records.add(line_to_read);
                    }
                    finestraconf.setLoadedConfiguration(records);
                    finestra.isConfigured = true;
                    reader.close();
                    
                    }catch(Exception e)
                      {
                      JOptionPane.showMessageDialog(null,
                      e.getLocalizedMessage(),
                      "Errore nel caricamento",
                      JOptionPane.ERROR_MESSAGE);
                      logicErr = true;    
                      finestra.isConfigured = false;
                      }
                    
                 }             
                if (!(lines.get(i+2).startsWith("</isConfigured>"))) 
                          logicErr = true;
            }
            else if(line.startsWith("<myPanel>"))
            {
             dataPanels.add(lines.get(i+1));             
             dataPanels.add(lines.get(i+2));
             dataPanels.add(lines.get(i+3));
             dataPanels.add(lines.get(i+4));
             dataPanels.add(lines.get(i+5));
             dataPanels.add(lines.get(i+6));
             dataPanels.add(lines.get(i+7));
             dataPanels.add(lines.get(i+8));
             dataPanels.add(lines.get(i+9));
             dataPanels.add(lines.get(i+10));
             dataPanels.add(lines.get(i+11));
             dataPanels.add(lines.get(i+12));
             dataPanels.add(lines.get(i+13));
             if (!(lines.get(i+14).equals("</myPanel>"))) logicErr = true;  
             panelsToLoad++;
            }
            
            
            i++;   
        }
      if (logicErr == false) {
          /* pass the data of panels to FinestraIniziale */
          finestra.loadingPanels(dataPanels,panelsToLoad);  
          finestra.mostraItemMnu.setVisible(true);
          finestra.mostraItemMnu.setBackground(Color.green);
          if (finestraconf != null) finestraconf.isConfigured = true;
          finestra.isConfigured = true;
          finestra.isSetUpGui = true;
          finestra.cancellaItemMnu.setVisible(true);
         
      }  else
      {
           JOptionPane.showMessageDialog(null,
                   "Purtroppo il caricamento non è andato a buon fine.\n"
                  +"Sono stati rilevati alcuni errori gravi.",
                    "Errore nel caricamento",
                     JOptionPane.ERROR_MESSAGE);
      }
    }

    private boolean whatLoad() {
        boolean isOk;
        isOk = true;
        
        File file = jFileOpen.getSelectedFile();
            if (file.canRead())
            {
                lines = new ArrayList<>();
                try
                {
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
               
                String line;
                while ((line = reader.readLine()) != null)
                {
                    lines.add(line);
                }
                reader.close();
                }
                catch (Exception e)
                    {
                       
                    JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage(),
                    "Configurazione",
                     JOptionPane.ERROR_MESSAGE);
                        
                     }
            }
        
        return isOk;
        
    }

    private boolean whereLoad() {
        boolean isOk;
        isOk = false;
        
        jFileOpen.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileOpen.setDialogTitle("Carica Progetto");
        FileFilter filter = new FileNameExtensionFilter("OSS One Year School Schedule Files (*.osp)","osp");
        jFileOpen.addChoosableFileFilter(filter);
        jFileOpen.setFileFilter(filter);
        int returnVal = jFileOpen.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
          isOk = true;  
        }
       return isOk;
    }

    private void nothingToLoad() {
        
    }
    
    
}
