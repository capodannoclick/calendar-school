/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JLabel;

import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Francesco Capodanno
 */
public class SetUpGUI {
    
    FinestraIniziale finestra;
    FinestraConfigurazione finestraconf;
    int numoggettiGUI; // numeri degli oggetti instanziati nella GUI
    int posInLoading; // For LoadAll class
    ArrayList oggettiGUI; // lista degli oggetti e dei riferimenti
       
    boolean exist_finestraconf;
    
    List<RightButton> buttons_right;
    List<LeftButton> buttons_left;
    List<AddButton> buttons_add;
    

    /**
     *Is created the GUI ?
     */
    public boolean isSetUpGUI;
    
    JCheckBox lunCheckBox;
    JCheckBox marCheckBox;
    JCheckBox merCheckBox;
    JCheckBox gioCheckBox;
    JCheckBox venCheckBox;
    JCheckBox sabCheckBox;
    JCheckBox domCheckBox;
    
    JTextField nomeCorsoText;
     JTextField dateText;
     JXDatePicker datePicker;
    JTextField totOreCorsoText;
    JComboBox frequenzalezioni;   
    JTextField orePerLezioneText;
    
    
   public SetUpGUI(boolean a,int numogg,FinestraIniziale frame) 
   {
        Translator.init_str_SetUpGUI(); // Inizializzatore delle stringhe 
        this.buttons_add = new ArrayList<>();
       
       buttons_left = new ArrayList<>();
       buttons_right = new ArrayList<>();
        
       
                finestra = frame;
                //finestraconf = config;
                numoggettiGUI = numogg;
                exist_finestraconf  = false;         
                oggettiGUI = new ArrayList();
                //panelModel = new UtilDateModel();  
                isSetUpGUI = false;
                
                createGui();
       
   }   
    public  SetUpGUI(boolean a,int numogg,FinestraIniziale frame, FinestraConfigurazione config)
            {
               Translator.init_str_SetUpGUI(); // Inizializzatore delle stringhe
               
        this.buttons_add = new ArrayList<>();
       
        this.buttons_left = new ArrayList<>();
        this.buttons_right = new ArrayList<>();
       
      
        
                finestra = frame;
                finestraconf = config;
                exist_finestraconf = true;
                numoggettiGUI = numogg;
                
                oggettiGUI = new ArrayList();
                //panelModel = new UtilDateModel();
                
                createGui();
               
            }

    /**
     *Empty Constructor!
     */
    public SetUpGUI()
    {
        this.buttons_add = new ArrayList<>();       
        this.buttons_left = new ArrayList<>();
        this.buttons_right = new ArrayList<>();
        oggettiGUI = new ArrayList();       
    }
    
    public void setFinestraIniziale(FinestraIniziale frame)
    {
        finestra = frame;
    }
    
    public void setFinestraConfigurazione(FinestraConfigurazione config)
    {
        finestraconf = config;
    }
    
    /**
     * Set the GUI with FInestraConfigurazione
     * @param a
     * @param numogg
     * @param frame
     * @param config
     */
    public void setGUI(boolean a,int numogg,FinestraIniziale frame, FinestraConfigurazione config){
        buttons_add = new ArrayList<>();
       
        buttons_left = new ArrayList<>();
        buttons_right = new ArrayList<>();
       
      
        
                finestra = frame;
                finestraconf = config;
                exist_finestraconf = true;
                numoggettiGUI = numogg;
                
                oggettiGUI = new ArrayList();
                //panelModel = new UtilDateModel();
                
                createGui();
       }
    
    /**
     *Set the Gui without FinestraConfigurazione
     * @param a
     * @param numogg
     * @param frame
     */
    public void setGUI(boolean a,int numogg,FinestraIniziale frame){
         this.buttons_add = new ArrayList<>();
       
       buttons_left = new ArrayList<>();
       buttons_right = new ArrayList<>();
        
       
                finestra = frame;
                //finestraconf = config;
                numoggettiGUI = numogg;
                exist_finestraconf  = false;         
                oggettiGUI = new ArrayList();
                //panelModel = new UtilDateModel();  
                isSetUpGUI = false;
                
                createGui();
    }
   
    private void createGui() {
        
       
       Translator.init_str_SetUpGUI();
       
       for (int i = 1; i <= numoggettiGUI; i++)
       {          
           unoCreateGUI(i);        // Creo n pannelli con i dati che deve inserire l'utente            
            
       }
       finestra.creaItemMnu.setBackground(Color.GREEN.darker());
       finestra.mostraItemMnu.setBackground(Color.GREEN);
       finestra.cancellaItemMnu.setBackground(Color.yellow);
       finestra.saveItemMnu.setBackground(Color.GREEN);
       finestra.saveItemMnu.setVisible(true);
       finestra.loadItemMnu.setBackground(Color.yellow);
       
      
       finestra.validate(); // validate è fondamentale se no non appare niente!!
       finestra.repaint();
       isSetUpGUI = true; 
       /* 
       Sono stati creati 1+ corsi
       */
    }
    
    private void unoCreateGUI(int i)
    {      
           MyPanel panelOggettiGUI;
           int JTextFieldSIZE = 15; // Grandezza delle caselle di testo
           int JTextFieldLITTLESIZE = 10;
           
           panelOggettiGUI=new MyPanel();
           JLabel corsi = new JLabel(Translator.str_SetUpGUI("Nome corso:")); 
           finestra.textSetUpGUI_1.add(corsi);
           panelOggettiGUI.add(corsi); 
           
      /* notare che Jframe si comporta come una lista si potrebbero 
           aggiungere gli oggetti direttamenta al JFrame. Ma è buona pratica
           utilizzare sempre un contenitore, JPanel (swing) è un contenitore leggero
           da preferire al classico Container (awt).
             */
            nomeCorsoText = new JTextField(JTextFieldSIZE);
            nomeCorsoText.setText(Translator.str_SetUpGUI("Corso") + i);
            finestra.oggettiGUI.add(nomeCorsoText);
             
            /*
           AGGIUNGO ALLA LISTA SOLO GLI OGGETTI CHE DEVO ELABORARE ESCLUDO LE LABEL
           attenzione utilizzando add l'indice di posizione partirà da 0 e non da 1
           */
            panelOggettiGUI.add(nomeCorsoText);
            dateText = new JTextField(JTextFieldSIZE);
          
            finestra.oggettiGUI.add(dateText); 
                                    
            /*
            DATA PICKER
            */
            final JLabel label = new JLabel(Translator.str_SetUpGUI("Data di inizio corso:"));
            finestra.textSetUpGUI_2.add(label);
            panelOggettiGUI.add(label);
 
            
            Date data_ora = new Date();
            datePicker = new JXDatePicker(data_ora);
            datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		//label.setText(datePicker.getDate().toString());
                
                }
                });
            /*
            FINE DATA PICKER
            */
            
            JButton datePickerButton = new JButton("Data");
            finestra.oggettiGUI.add(datePicker);
            panelOggettiGUI.add(datePicker);
            
            JLabel oreTotCorso = new JLabel(Translator.str_SetUpGUI("Ore totale corso(in ore):"));
            finestra.textSetUpGUI_3.add(oreTotCorso);
            panelOggettiGUI.add(oreTotCorso);
            totOreCorsoText = new JTextField(JTextFieldLITTLESIZE);
            finestra.oggettiGUI.add(totOreCorsoText);
            panelOggettiGUI.add(totOreCorsoText);
            
            JLabel orePerLezione = new JLabel (Translator.str_SetUpGUI("Ore per lezione:"));
            finestra.textSetUpGUI_4.add(orePerLezione);
            panelOggettiGUI.add(orePerLezione);
            orePerLezioneText = new JTextField(JTextFieldLITTLESIZE +5);
            finestra.oggettiGUI.add(orePerLezioneText);
            panelOggettiGUI.add(orePerLezioneText);
            /*
            JLabel orePerSettimanaLabel = new JLabel("Ore per Settimana:");
            panelOggettiGUI.add(orePerSettimanaLabel);
            JTextField orePerSettimanaText = new JTextField(JTextFieldLITTLESIZE);
            finestra.oggettiGUI.add(orePerSettimanaText);
            panelOggettiGUI.add(orePerSettimanaText); 
            */ // Sostituito con frequenza corso
            
            JLabel frequenzaCorsoLabel = new JLabel(Translator.str_SetUpGUI("Frequenza corso:"));
            finestra.textSetUpGUI_5.add(frequenzaCorsoLabel);
            
            panelOggettiGUI.add(frequenzaCorsoLabel);
            String[] lista2 = new String[7];
           
        
        
        lista2[0] = Translator.str_SetUpGUI("giornaliera");
        lista2[1] = Translator.str_SetUpGUI("settimanale");
        lista2[2] = Translator.str_SetUpGUI("ogni 2 giorni");
        lista2[3] = Translator.str_SetUpGUI("ogni 3 giorni");
        lista2[4] = Translator.str_SetUpGUI("ogni 4 giorni");
        lista2[5] = Translator.str_SetUpGUI("ogni 5 giorni");
        lista2[6] = Translator.str_SetUpGUI("ogni 6 giorni");
        frequenzalezioni = new JComboBox(lista2);
        
        finestra.oggettiGUI.add(frequenzalezioni);
        panelOggettiGUI.add(frequenzalezioni);
            
            JLabel oraInizioCorsoLabel = new JLabel(Translator.str_SetUpGUI("Ora inizio corso(HH:MM):"));
            finestra.textSetUpGUI_6.add(oraInizioCorsoLabel);
            
            panelOggettiGUI.add(oraInizioCorsoLabel);
            JTextField oraInizioCorsoText = new JTextField(JTextFieldLITTLESIZE);
            // Aggiunge l'orario di inizio della scuola ad ogni orario inizio Lezione
            String str = finestra.hhStartLessonsString + ":" + finestra.mmStartLessonsString;
            oraInizioCorsoText.setText(str);
            finestra.oggettiGUI.add(oraInizioCorsoText);
            panelOggettiGUI.add(oraInizioCorsoText); 
            
            
            JLabel giorniEscludere = new JLabel(Translator.str_SetUpGUI("Giorni da escludere:"));
            finestra.textSetUpGUI_7.add(giorniEscludere);
            panelOggettiGUI.add(giorniEscludere);          
                        
            domCheckBox = new JCheckBox(Translator.str_SetUpGUI("domenica"));
            finestra.textSetUpGUI_8.add(domCheckBox);
            sabCheckBox = new JCheckBox(Translator.str_SetUpGUI("sabato"));
            finestra.textSetUpGUI_9.add(sabCheckBox);
            venCheckBox = new JCheckBox(Translator.str_SetUpGUI("venerdì"));
            finestra.textSetUpGUI_10.add(venCheckBox);
            gioCheckBox = new JCheckBox(Translator.str_SetUpGUI("giovedì"));
            finestra.textSetUpGUI_11.add(gioCheckBox);
            merCheckBox = new JCheckBox(Translator.str_SetUpGUI("mercoledì"));
            finestra.textSetUpGUI_12.add(merCheckBox);
            marCheckBox = new JCheckBox(Translator.str_SetUpGUI("martedì"));
            finestra.textSetUpGUI_13.add(marCheckBox);
            lunCheckBox = new JCheckBox(Translator.str_SetUpGUI("lunedì"));
            finestra.textSetUpGUI_14.add(lunCheckBox);
            
            
            if (exist_finestraconf)
            {// Se esiste la finestra configurazione configurata
            fillGui(); //riempi la gui di ogni corso con le regole generali
            /*
            * DEFAULT . In caso di Default riempi con i seguenti dati
            */
            } else  if (finestra.isDefault)
                {
           /*
           Riempi i dati secondo la configurazione standard
           tutte le aule libere, nessuna data dal calendario esclusa, 
           sabato e domenica sono esclusi, frequenza giornaliera
           */
           domCheckBox.setSelected(true);
           sabCheckBox.setSelected(true);
           orePerLezioneText.setText("1");
           finestra.isConfigured = true;
                }
            
            panelOggettiGUI.add(lunCheckBox);            
            panelOggettiGUI.add(marCheckBox);
            panelOggettiGUI.add(merCheckBox);
            panelOggettiGUI.add(gioCheckBox);
            panelOggettiGUI.add(venCheckBox);
            panelOggettiGUI.add(sabCheckBox);
            panelOggettiGUI.add(domCheckBox);
            
            finestra.oggettiGUI.add(lunCheckBox);
            finestra.oggettiGUI.add(marCheckBox);
            finestra.oggettiGUI.add(merCheckBox);
            finestra.oggettiGUI.add(gioCheckBox);
            finestra.oggettiGUI.add(venCheckBox);
            finestra.oggettiGUI.add(sabCheckBox);
            finestra.oggettiGUI.add(domCheckBox);
            
                      
            EraseButton button_minus = new EraseButton(panelOggettiGUI, finestra, this);
            button_minus.setBackground(Color.red);
            LeftButton button_left = new LeftButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_left.setBackground(Color.CYAN);            
            RightButton button_right = new RightButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_right.setBackground(Color.CYAN);
            //ai bottoni passa i puntatori alla finestra iniziale e alla classe setupgui in modo da eliminare o aggiungere
            buttons_right.add(button_right);          
            buttons_left.add(button_left);
            
            
            
            
            panelOggettiGUI.add(button_minus);
            panelOggettiGUI.add(button_left);
            panelOggettiGUI.add(button_right);
            
            AddButton button_plus = new AddButton(finestra, this);
            button_plus.setBackground(Color.green);  
            panelOggettiGUI.add(button_plus);
            buttons_add.add(button_plus);
            
            finestra.mainContainer.add(panelOggettiGUI); // Aggiungo il mio panelOggettiGUI
            
            // Azioni del pannello
            
            
          
    }
    
    private void fillGui()
    {
        /*
        Questa funzione riempie la GUI dei dati configurati in caso la finestra iniziale non è stata configurata la riempie con i dati di default
        */
        /*
        In teoria non dovrebbe essere obbligatiorio questo controllo ma è buona prassi 
        inserire dei boolean (che sono strutture dati molto leggere )
        per verificare la logica di  business della vostra applicazione
        isConfigured, isDefault ...è un modo per dare una coerenza logica alle vostre classi.
        */
        
       if ((finestra.isConfigured) && (!finestra.isDefault))
       {
           /*
           Riempi i dati secondo la configurazione nella finestraconf
           */
           
           lunCheckBox.setSelected(finestraconf.esclLun.isSelected());
           marCheckBox.setSelected(finestraconf.esclMar.isSelected());
           merCheckBox.setSelected(finestraconf.esclMer.isSelected());
           gioCheckBox.setSelected(finestraconf.esclGio.isSelected());
           venCheckBox.setSelected(finestraconf.esclVen.isSelected());
           sabCheckBox.setSelected(finestraconf.esclSab.isSelected());
           domCheckBox.setSelected(finestraconf.esclDom.isSelected());
           totOreCorsoText.setText(finestraconf.getTotOreCorso());
           orePerLezioneText.setText(finestraconf.getOrePerLezione());
           frequenzalezioni.setSelectedIndex(finestraconf.frequenzalezioni.getSelectedIndex());
           
           
       }
       
      
       
       
       
       
       
       
    }
    
    

    /**
     *  Add courses to maincontainer in FinestraIniziale.
     * @param posIndex where add the panel
     * @param new_numoggettiGUI how many panels instanciate.
     */
    
    public void createGui_position(int posIndex, int new_numoggettiGUI) {
        
       
       Translator.init_str_SetUpGUI();
       
       for (int i = 1; i <= new_numoggettiGUI; i++)
       {   posIndex++;       
           unoCreateGUI_position(i,posIndex);        // Creo n pannelli con i dati che deve inserire l'utente            
                 
           
       }
       finestra.creaItemMnu.setBackground(Color.GREEN.darker());
           finestra.mostraItemMnu.setBackground(Color.GREEN);
           finestra.saveItemMnu.setBackground(Color.GREEN);
           finestra.cancellaItemMnu.setBackground(Color.GREEN);
      
       finestra.validate(); // validate è fondamentale se no non appare niente!!
       finestra.repaint();
       isSetUpGUI = true; 
       /* 
       Sono stati creati 1+ corsi
       */
    }
    
    private void unoCreateGUI_position(int i, int posIndex)
    {
           //posIndex++;
           int JTextFieldSIZE = 15; // Grandezza delle caselle di testo
           int JTextFieldLITTLESIZE = 10;
           
           MyPanel panelOggettiGUI=new MyPanel();
           System.out.println(Translator.str_SetUpGUI("Nome corso:"));
           JLabel corsi = new JLabel(Translator.str_SetUpGUI("Nome corso:"));           
           panelOggettiGUI.add(corsi); 
           
      /* notare che Jframe si comporta come una lista si potrebbero 
           aggiungere gli oggetti direttamenta al JFrame. Ma è buona pratica
           utilizzare sempre un contenitore, JPanel (swing) è un contenitore leggero
           da preferire al classico Container (awt).
             */
            nomeCorsoText = new JTextField(JTextFieldSIZE);
            nomeCorsoText.setText(Translator.str_SetUpGUI("CorsoAggiunto") + i);
            finestra.oggettiGUI.add(nomeCorsoText);
             
            /*
           AGGIUNGO ALLA LISTA SOLO GLI OGGETTI CHE DEVO ELABORARE ESCLUDO LE LABEL
           attenzione utilizzando add l'indice di posizione partirà da 0 e non da 1
           */
            panelOggettiGUI.add(nomeCorsoText);
            dateText = new JTextField(JTextFieldSIZE);
          
            finestra.oggettiGUI.add(dateText); 
                                    
            /*
            DATA PICKER
            */
            final JLabel label = new JLabel(Translator.str_SetUpGUI("Data di inizio corso:"));

            panelOggettiGUI.add(label);
 
            
            Date data_ora = new Date();
            datePicker = new JXDatePicker(data_ora);
            datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		//label.setText(datePicker.getDate().toString());
                
                }
                });
            /*
            FINE DATA PICKER
            */
            
            JButton datePickerButton = new JButton(Translator.str_SetUpGUI("Data"));
            finestra.oggettiGUI.add(datePicker);
            panelOggettiGUI.add(datePicker);
            
            JLabel oreTotCorso = new JLabel(Translator.str_SetUpGUI("Ore totale corso(in ore):"));
            panelOggettiGUI.add(oreTotCorso);
            totOreCorsoText = new JTextField(JTextFieldLITTLESIZE);
            finestra.oggettiGUI.add(totOreCorsoText);
            panelOggettiGUI.add(totOreCorsoText);
            
            JLabel orePerLezione = new JLabel (Translator.str_SetUpGUI("Ore per lezione:"));
            panelOggettiGUI.add(orePerLezione);
            orePerLezioneText = new JTextField(JTextFieldLITTLESIZE +5);
            finestra.oggettiGUI.add(orePerLezioneText);
            panelOggettiGUI.add(orePerLezioneText);
            /*
            JLabel orePerSettimanaLabel = new JLabel("Ore per Settimana:");
            panelOggettiGUI.add(orePerSettimanaLabel);
            JTextField orePerSettimanaText = new JTextField(JTextFieldLITTLESIZE);
            finestra.oggettiGUI.add(orePerSettimanaText);
            panelOggettiGUI.add(orePerSettimanaText); 
            */ // Sostituito con frequenza corso
            
            JLabel frequenzaCorsoLabel = new JLabel(Translator.str_SetUpGUI("Frequenza corso:"));
            panelOggettiGUI.add(frequenzaCorsoLabel);
            String[] lista2 = new String[7];
           
        
        
        lista2[0] = Translator.str_SetUpGUI("giornaliera");
        lista2[1] = Translator.str_SetUpGUI("settimanale");
        lista2[2] = Translator.str_SetUpGUI("ogni 2 giorni");
        lista2[3] = Translator.str_SetUpGUI("ogni 3 giorni");
        lista2[4] = Translator.str_SetUpGUI("ogni 4 giorni");
        lista2[5] = Translator.str_SetUpGUI("ogni 5 giorni");
        lista2[6] = Translator.str_SetUpGUI("ogni 6 giorni");
        frequenzalezioni = new JComboBox(lista2);
        
        finestra.oggettiGUI.add(frequenzalezioni);
        panelOggettiGUI.add(frequenzalezioni);
            
            JLabel oraInizioCorsoLabel = new JLabel(Translator.str_SetUpGUI("Ora inizio corso(HH:MM):"));
            panelOggettiGUI.add(oraInizioCorsoLabel);
            JTextField oraInizioCorsoText = new JTextField(JTextFieldLITTLESIZE);
            // Aggiunge l'orario di inizio della scuola ad ogni orario inizio Lezione
            String str = finestra.hhStartLessonsString + ":" + finestra.mmStartLessonsString;
            oraInizioCorsoText.setText(str);
            finestra.oggettiGUI.add(oraInizioCorsoText);
            panelOggettiGUI.add(oraInizioCorsoText); 
            
            
            JLabel giorniEscludere = new JLabel(Translator.str_SetUpGUI("Giorni da escludere:"));
            panelOggettiGUI.add(giorniEscludere);          
                        
            domCheckBox = new JCheckBox(Translator.str_SetUpGUI("domenica"));
            sabCheckBox = new JCheckBox(Translator.str_SetUpGUI("sabato"));
            venCheckBox = new JCheckBox(Translator.str_SetUpGUI("venerdì"));
            gioCheckBox = new JCheckBox(Translator.str_SetUpGUI("giovedì"));
            merCheckBox = new JCheckBox(Translator.str_SetUpGUI("mercoledì"));
            marCheckBox = new JCheckBox(Translator.str_SetUpGUI("martedì"));
            lunCheckBox = new JCheckBox(Translator.str_SetUpGUI("lunedì"));
            
            
            if (exist_finestraconf)
            {// Se esiste la finestra configurazione configurata
            fillGui(); //riempi la gui di ogni corso con le regole generali
            /*
            * DEFAULT . In caso di Default riempi con i seguenti dati
            */
            } else  if (finestra.isDefault)
                {
           /*
           Riempi i dati secondo la configurazione standard
           tutte le aule libere, nessuna data dal calendario esclusa, 
           sabato e domenica sono esclusi, frequenza giornaliera
           */
           domCheckBox.setSelected(true);
           sabCheckBox.setSelected(true);
           orePerLezioneText.setText("1");
           finestra.isConfigured = true;
                }
            
            panelOggettiGUI.add(lunCheckBox);            
            panelOggettiGUI.add(marCheckBox);
            panelOggettiGUI.add(merCheckBox);
            panelOggettiGUI.add(gioCheckBox);
            panelOggettiGUI.add(venCheckBox);
            panelOggettiGUI.add(sabCheckBox);
            panelOggettiGUI.add(domCheckBox);
            
            finestra.oggettiGUI.add(lunCheckBox);
            finestra.oggettiGUI.add(marCheckBox);
            finestra.oggettiGUI.add(merCheckBox);
            finestra.oggettiGUI.add(gioCheckBox);
            finestra.oggettiGUI.add(venCheckBox);
            finestra.oggettiGUI.add(sabCheckBox);
            finestra.oggettiGUI.add(domCheckBox);
            
                      
            EraseButton button_minus = new EraseButton(panelOggettiGUI, finestra, this);
            button_minus.setBackground(Color.red);
            LeftButton button_left = new LeftButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_left.setBackground(Color.CYAN);            
            RightButton button_right = new RightButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_right.setBackground(Color.CYAN);
            //ai bottoni passa i puntatori alla finestra iniziale e alla classe setupgui in modo da eliminare o aggiungere
            buttons_right.add(posIndex, button_right);                    
            buttons_left.add(posIndex, button_left);
            
            
            
            
            panelOggettiGUI.add(button_minus);
            panelOggettiGUI.add(button_left);
            panelOggettiGUI.add(button_right);
            
            AddButton button_plus = new AddButton(finestra, this);
            button_plus.setBackground(Color.green);  
            panelOggettiGUI.add(button_plus);
            buttons_add.add(posIndex,button_plus);  
            // si dovrebbero nascondere i comandi qui finchè gli indici e le strutture dati non sono 
            // riordinate
            finestra.mainContainer.add(panelOggettiGUI, posIndex); // Aggiungo il mio panelOggettiGUI
            finestra.mainContainer.repaint();
            
            // Azioni del pannello. Volendo essere formali prima di questa operazione bisognerebbe
            // rendere i comandi non visibili.
            ArrangeIndexes();
            
          
    }
    
    /**
     * @param Data to instantiate
     * @param numoggettiGUI_load How many panels need to instantiate.
     * @javadoc: This create the GUI from the Data from loadAll. (This method is called by FinestraIniziale)
     */
    public void createGui_loadAll(List<String> Data, int numoggettiGUI_load) {        
       
       posInLoading = 0; // Quando viene invocata la funzione 
       
       Translator.init_str_SetUpGUI();
       
       for (int i = 1; i <= numoggettiGUI_load; i++)
       {          
           unoCreateGUI_loadAll(i,Data);        // Creo n pannelli con i dati che deve inserire l'utente            
            
       }
       finestra.creaItemMnu.setBackground(Color.GREEN.darker());
       finestra.mostraItemMnu.setBackground(Color.GREEN);
       finestra.cancellaItemMnu.setBackground(Color.yellow);
       finestra.saveItemMnu.setBackground(Color.GREEN);
       finestra.saveItemMnu.setVisible(true);
       finestra.loadItemMnu.setBackground(Color.yellow);
       
      
       finestra.validate(); // validate è fondamentale se no non appare niente!!
       finestra.repaint();
       isSetUpGUI = true; 
       
       /* 
       Sono stati creati 1+ corsi
       */
      
    }
    
    private void unoCreateGUI_loadAll(int i, List<String> Data)
    {
           //Translator.init_str_SetUpGUI();
           MyPanel panelOggettiGUI;
           int JTextFieldSIZE = 15; // Grandezza delle caselle di testo
           int JTextFieldLITTLESIZE = 10;
           
           panelOggettiGUI=new MyPanel();
           
           JLabel corsi = new JLabel(Translator.str_SetUpGUI("Nome corso:")); 
           finestra.textSetUpGUI_1.add(corsi);
           panelOggettiGUI.add(corsi); 
         
      
            nomeCorsoText = new JTextField(JTextFieldSIZE);
            
            nomeCorsoText.setText(Data.get(posInLoading));
            finestra.oggettiGUI.add(nomeCorsoText);
            posInLoading++;
             
          
            panelOggettiGUI.add(nomeCorsoText);
            dateText = new JTextField(JTextFieldSIZE);
            
            finestra.oggettiGUI.add(dateText); 
                                    
            /*
            DATA PICKER
            */
            final JLabel label = new JLabel(Translator.str_SetUpGUI("Data di inizio corso:"));
            finestra.textSetUpGUI_2.add(label);
            panelOggettiGUI.add(label);
            
       
            
            //Date data_ora = new Date();
             Date data_ora = null;
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        try {
            data_ora = format.parse(Data.get(posInLoading));
            } catch (ParseException ex) {
            Logger.getLogger(SetUpGUI.class.getName()).log(Level.SEVERE, null, ex);
           }
            datePicker = new JXDatePicker(data_ora);
            datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		//label.setText(datePicker.getDate().toString());
                
                }
                });
            /*
            FINE DATA PICKER
            */
            posInLoading++;
            
            JButton datePickerButton = new JButton("Data");
            finestra.oggettiGUI.add(datePicker);
            panelOggettiGUI.add(datePicker);
            
            JLabel oreTotCorso = new JLabel(Translator.str_SetUpGUI("Ore totale corso(in ore):"));
            finestra.textSetUpGUI_3.add(oreTotCorso);
            panelOggettiGUI.add(oreTotCorso);
            totOreCorsoText = new JTextField(JTextFieldLITTLESIZE);
            totOreCorsoText.setText(Data.get(posInLoading));
            posInLoading++;
            finestra.oggettiGUI.add(totOreCorsoText);
            panelOggettiGUI.add(totOreCorsoText);
            
            JLabel orePerLezione = new JLabel (Translator.str_SetUpGUI("Ore per lezione:"));
            finestra.textSetUpGUI_4.add(orePerLezione);
            panelOggettiGUI.add(orePerLezione);
            orePerLezioneText = new JTextField(JTextFieldLITTLESIZE +5);
            orePerLezioneText.setText(Data.get(posInLoading));
            posInLoading++;
            finestra.oggettiGUI.add(orePerLezioneText);
            panelOggettiGUI.add(orePerLezioneText);
            /*
            JLabel orePerSettimanaLabel = new JLabel("Ore per Settimana:");
            panelOggettiGUI.add(orePerSettimanaLabel);
            JTextField orePerSettimanaText = new JTextField(JTextFieldLITTLESIZE);
            finestra.oggettiGUI.add(orePerSettimanaText);
            panelOggettiGUI.add(orePerSettimanaText); 
            */ // Sostituito con frequenza corso
            
            JLabel frequenzaCorsoLabel = new JLabel(Translator.str_SetUpGUI("Frequenza corso:"));
            finestra.textSetUpGUI_5.add(frequenzaCorsoLabel);
            panelOggettiGUI.add(frequenzaCorsoLabel);
            String[] lista2 = new String[7];
           
        
        
        lista2[0] = "giornaliera";
        lista2[1] = "settimanale";
        lista2[2] = "ogni 2 giorni";
        lista2[3] = "ogni 3 giorni";
        lista2[4] = "ogni 4 giorni";
        lista2[5] = "ogni 5 giorni";
        lista2[6] = "ogni 6 giorni";
        frequenzalezioni = new JComboBox(lista2);
        frequenzalezioni.setSelectedItem(Data.get(posInLoading));
        posInLoading++;
        finestra.oggettiGUI.add(frequenzalezioni);
        panelOggettiGUI.add(frequenzalezioni);
            
            JLabel oraInizioCorsoLabel = new JLabel(Translator.str_SetUpGUI("Ora inizio corso(HH:MM):"));
            finestra.textSetUpGUI_6.add(oraInizioCorsoLabel);
            panelOggettiGUI.add(oraInizioCorsoLabel);
            JTextField oraInizioCorsoText = new JTextField(JTextFieldLITTLESIZE);
           
            oraInizioCorsoText.setText(Data.get(posInLoading));
            posInLoading++;
            finestra.oggettiGUI.add(oraInizioCorsoText);
            panelOggettiGUI.add(oraInizioCorsoText); 
            
            
            JLabel giorniEscludere = new JLabel(Translator.str_SetUpGUI("Giorni da escludere:"));
            finestra.textSetUpGUI_7.add(giorniEscludere);
            panelOggettiGUI.add(giorniEscludere);          
                        
            domCheckBox = new JCheckBox(Translator.str_SetUpGUI("domenica"));
            finestra.textSetUpGUI_8.add(domCheckBox);
            sabCheckBox = new JCheckBox(Translator.str_SetUpGUI("sabato"));
            finestra.textSetUpGUI_9.add(sabCheckBox);
            venCheckBox = new JCheckBox(Translator.str_SetUpGUI("venerdì"));
            finestra.textSetUpGUI_10.add(venCheckBox);
            gioCheckBox = new JCheckBox(Translator.str_SetUpGUI("giovedì"));
            finestra.textSetUpGUI_11.add(gioCheckBox);
            merCheckBox = new JCheckBox(Translator.str_SetUpGUI("mercoledì"));
            finestra.textSetUpGUI_12.add(merCheckBox);
            marCheckBox = new JCheckBox(Translator.str_SetUpGUI("martedì"));
            finestra.textSetUpGUI_13.add(marCheckBox);
            lunCheckBox = new JCheckBox(Translator.str_SetUpGUI("lunedì"));
            finestra.textSetUpGUI_14.add(lunCheckBox);
            
            if (Data.get(posInLoading).startsWith("true"))  {
                lunCheckBox.setSelected(true);
                posInLoading++;
                
            }  else
            {
             lunCheckBox.setSelected(false);
                posInLoading++;   
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                marCheckBox.setSelected(true);
                posInLoading++;
                
                
            }  else
            {
                marCheckBox.setSelected(false);
                posInLoading++;                
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                merCheckBox.setSelected(true);
                posInLoading++;
                
                
            }  else
            {
                merCheckBox.setSelected(false);
                posInLoading++;
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                gioCheckBox.setSelected(true);
                posInLoading++;
            }  else
            {
                gioCheckBox.setSelected(false);
                posInLoading++;
                
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                venCheckBox.setSelected(true);
                posInLoading++;
            }  else
            {
                venCheckBox.setSelected(false);
                posInLoading++;
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                 sabCheckBox.setSelected(true);
                posInLoading++;
            }  else
            {
                sabCheckBox.setSelected(false);
                posInLoading++;
            }
            
            if (Data.get(posInLoading).startsWith("true"))  {
                domCheckBox.setSelected(true);
                posInLoading++;
            }  else
            {
                domCheckBox.setSelected(false);
                posInLoading++;
            }
            
            
            
            panelOggettiGUI.add(lunCheckBox);            
            panelOggettiGUI.add(marCheckBox);
            panelOggettiGUI.add(merCheckBox);
            panelOggettiGUI.add(gioCheckBox);
            panelOggettiGUI.add(venCheckBox);
            panelOggettiGUI.add(sabCheckBox);
            panelOggettiGUI.add(domCheckBox);
            
            finestra.oggettiGUI.add(lunCheckBox);
            finestra.oggettiGUI.add(marCheckBox);
            finestra.oggettiGUI.add(merCheckBox);
            finestra.oggettiGUI.add(gioCheckBox);
            finestra.oggettiGUI.add(venCheckBox);
            finestra.oggettiGUI.add(sabCheckBox);
            finestra.oggettiGUI.add(domCheckBox);
            
                      
            EraseButton button_minus = new EraseButton(panelOggettiGUI, finestra, this);
            button_minus.setBackground(Color.red);
            LeftButton button_left = new LeftButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_left.setBackground(Color.CYAN);            
            RightButton button_right = new RightButton(this, finestra, finestra.mainContainer.getComponentCount());
            button_right.setBackground(Color.CYAN);
            //ai bottoni passa i puntatori alla finestra iniziale e alla classe setupgui in modo da eliminare o aggiungere
            buttons_right.add(button_right);          
            buttons_left.add(button_left);
            
            
            
            
            panelOggettiGUI.add(button_minus);
            panelOggettiGUI.add(button_left);
            panelOggettiGUI.add(button_right);
            
            AddButton button_plus = new AddButton(finestra, this);
            button_plus.setBackground(Color.green);  
            panelOggettiGUI.add(button_plus);
            buttons_add.add(button_plus);
            
            finestra.mainContainer.add(panelOggettiGUI); // Aggiungo il mio panelOggettiGUI
            finestra.isCreated = true;
            // Azioni del pannello
           
            
          
    }

    /**
     * Rearrange all the indexes.
     */
    public void ArrangeIndexes() {
       /*
             Questa funzione riordina gli indici e le strutture dati contenenti i bottoni di comando dei pannelli
             */
       
        for (int i=0; i<this.finestra.mainContainer.getComponentCount();i++)
        {
          
          RightButton tempR = buttons_right.get(i);
          LeftButton tempL = buttons_left.get(i);
          AddButton tempA = buttons_add.get(i);
          tempR.indexButton = i;
          tempL.indexButton = i;
          tempA.indexButton = i;        
          
                  
         }
       }
    
    public void ArrangeIndexes(int a) {
       /*
             Questa funzione riordina gli indici e le strutture dati contenenti i bottoni di comando dei pannelli
             */
       
        for (int i=0; i<this.finestra.mainContainer.getComponentCount();i++)
        {
          
          RightButton tempR = buttons_right.get(i);
          LeftButton tempL = buttons_left.get(i);
          AddButton tempA = buttons_add.get(i);
          tempR.indexButton = i;
          tempL.indexButton = i;
          tempA.indexButton = i;        
          
                  
         }
       }
    
       
    }

   

    
    

            
    

