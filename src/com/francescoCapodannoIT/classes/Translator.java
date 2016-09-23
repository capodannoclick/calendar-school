/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This class translate all the text of the application with an hashmap and have also UIManager for
 * JOptionPane.
 * @author Francesco Capodanno
 */
public final class Translator {    

    /**
     * This variable indicates: 
     * value 1 the language Italian
     * value 2 the language English
     * value 3 the language Deutsch
     */
    protected static int translatorValue;
    private static FinestraIniziale finestraIniziale;
    private static FinestraConfigurazione finestraConf;
    private static ResultsJFrame resultsFrame;

    /**
     *
     * @return value 1 the language Italian
     *               value 2 the language English
     *               value 3 the language Deutsch
     */
    public static int get_translatorValue()
    {
        return translatorValue;
    }
    private static void trans_finestraIniziale() {
        System.out.print("traduzione finestra iniziale...");
        if (translatorValue==1) trans_finestraIniziale_it();
        if (translatorValue==2) trans_finestraIniziale_en();
        if (translatorValue==3) trans_finestraIniziale_de();
    }

    private static void trans_finestraConf() {
       System.out.println("traduzione finestra configurazione...");
       if (finestraConf != null){
           finestraConfigurazione_catchComponents();
       }
    }

    private static void trans_resultsFrame() {
      
      if (resultsFrame != null)
      {
          System.out.println("traduzione finestra risultati...")  ;
          resultsFrame.updateTranslatedText();          
      }
      
    }

    private static void trans_finestraIniziale_it() {
       System.out.println("it");
       /*
            Traduzione del menu
             */
       finestraIniziale.aboutItemMnu.setText("About");
       finestraIniziale.helpMnu.setText("Help ?");
       finestraIniziale.cancellaItemMnu.setText("Cancella Tutto");
       finestraIniziale.creaItemMnu.setText("Crea");
       finestraIniziale.configuraItemMnu.setText("Configura regole generali");
       finestraIniziale.mostraItemMnu.setText("Mostra risultato");
       finestraIniziale.saveItemMnu.setText("Salva progetto");
       finestraIniziale.loadItemMnu.setText("Carica progetto");
       finestraIniziale.creaMnu.setText("Crea/Salva");
       finestraIniziale.languageMnu.setText("Language/Lingua/Sprache"); 
       if (!finestraIniziale.oggettiGUI.isEmpty()) finestraIniziale_catchComponents();
    }
    
   
            

    private static void trans_finestraIniziale_en() {
       System.out.println("en"); 
        /*
            Traduzione del menu
             */
       finestraIniziale.aboutItemMnu.setText("About");
       finestraIniziale.helpMnu.setText("Help ?");
       finestraIniziale.cancellaItemMnu.setText("Erase All");
       finestraIniziale.creaItemMnu.setText("Create");
       finestraIniziale.configuraItemMnu.setText("Configure general rules");
       finestraIniziale.mostraItemMnu.setText("Show results");
       finestraIniziale.saveItemMnu.setText("Save project");
       finestraIniziale.loadItemMnu.setText("Load project");
       finestraIniziale.creaMnu.setText("Load/Save");
       finestraIniziale.languageMnu.setText("Language/Lingua/Sprache"); 
        if (!finestraIniziale.oggettiGUI.isEmpty()) finestraIniziale_catchComponents();
    }

    private static void trans_finestraIniziale_de() {
       System.out.println("de");
       
        /*
            Traduzione del menu
             */
       finestraIniziale.aboutItemMnu.setText("About");
       finestraIniziale.helpMnu.setText("Help");
       finestraIniziale.cancellaItemMnu.setText("Erase All");
       finestraIniziale.creaItemMnu.setText("Create");
       finestraIniziale.configuraItemMnu.setText("Configure general rules");
       finestraIniziale.mostraItemMnu.setText("Show results");
       finestraIniziale.saveItemMnu.setText("Save project");
       finestraIniziale.loadItemMnu.setText("Load project");
       finestraIniziale.creaMnu.setText("Load/Save");
       finestraIniziale.languageMnu.setText("Language/Lingua/Sprache"); 
        if (!finestraIniziale.oggettiGUI.isEmpty()) finestraIniziale_catchComponents();
    }
    
   /**
     *  This function captures the components need to translated. and translate
     */
    private static void finestraIniziale_catchComponents()
    {
        System.out.println("catturo elementi da tradurre dalla finestra iniziale");
        
        for (Object text : finestraIniziale.textSetUpGUI_1) {
            JLabel change = (JLabel) text;           
            change.setText(Translator.str_SetUpGUI("Nome corso:"));            
        }
        for (Object text : finestraIniziale.textSetUpGUI_2)
        {JLabel change = (JLabel) text;        
         change.setText(Translator.str_SetUpGUI("Data di inizio corso:"));
        }
        for (Object text : finestraIniziale.textSetUpGUI_3)
        {JLabel change = (JLabel) text;
         change.setText(Translator.str_SetUpGUI("Ore totale corso(in ore):"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_4)
        {JLabel change = (JLabel) text;      
         change.setText(Translator.str_SetUpGUI("Ore per lezione:"));   
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_5)
        {
         JLabel change = (JLabel) text;
         change.setText(Translator.str_SetUpGUI("Frequenza corso:"));
            
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_6)
        {JLabel change = (JLabel) text;        
           change.setText(Translator.str_SetUpGUI("Ora inizio corso(HH:MM):")); 
           
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_7)
        {JLabel change = (JLabel) text;        
            change.setText(Translator.str_SetUpGUI("Giorni da escludere:"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_8)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("domenica"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_9)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("sabato"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_10)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("venerdì"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_11)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("giovedì"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_12)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("mercoledì"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_13)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("martedì"));
        }
        
        for (Object text : finestraIniziale.textSetUpGUI_14)
        {JCheckBox change = (JCheckBox) text;
            change.setText(Translator.str_SetUpGUI("lunedì"));
        }
        
        finestraIniziale.validate();
    }

    private static void finestraConfigurazione_catchComponents()
    { 
     finestraConf.pannelloGiorniEscludere.setTitle(
      Translator.str_FinestraConfigurazione("giorni da escludere"));
     
     finestraConf.pannelloGiorniCalendarioEscludere.setTitle(
      Translator.str_FinestraConfigurazione("giorni del calendario da escludere"));
     
     finestraConf.pannelloAuleDisponibili.setTitle(
      Translator.str_FinestraConfigurazione("aule disponibili e frequenza lezioni"));
     
     finestraConf.bottoneConfigura.setText(Translator.str_FinestraConfigurazione("Configura"));
     
     finestraConf.bottoneReset.setText(Translator.str_FinestraConfigurazione("Default"));
     
     finestraConf.salvaConfigura.setText(Translator.str_FinestraConfigurazione
        ("Salva Configurazione"));
     
     finestraConf.caricaConfigura.setText(Translator.str_FinestraConfigurazione
        ("Carica Configurazione"));
     
     finestraConf.cancellaConfigura.setText(Translator.str_FinestraConfigurazione
        ("Reset"));
     
     finestraConf.cancellaSelezione.setText(Translator.str_FinestraConfigurazione
        ("Cancella data selezionata"));
     finestraConf.esclLun.setText(Translator.str_FinestraConfigurazione("Lunedì"));
     finestraConf.esclMar.setText(Translator.str_FinestraConfigurazione("Martedì"));
     finestraConf.esclMer.setText(Translator.str_FinestraConfigurazione("Mercoledì"));
     finestraConf.esclGio.setText(Translator.str_FinestraConfigurazione("Giovedì"));
     finestraConf.esclVen.setText(Translator.str_FinestraConfigurazione("Venerdì"));
     finestraConf.esclSab.setText(Translator.str_FinestraConfigurazione("Sabato"));
     finestraConf.esclDom.setText(Translator.str_FinestraConfigurazione("Domenica"));
     
     finestraConf.oraTotCorso_check.setText(Translator.str_FinestraConfigurazione
        ("configura la durata totale(in ore) di tutti i corsi"));
     
     finestraConf.orePerLezioneLabel.setText(Translator.str_FinestraConfigurazione("Ore per Lezione"));
     finestraConf.auledisponibiliLabel.setText(Translator.str_FinestraConfigurazione("Aule disponibili:"));
     finestraConf.jLabelTotDate.setText(Translator.str_FinestraConfigurazione("Nessuna"));
     finestraConf.frequenzalezioniLabel.setText(Translator.str_FinestraConfigurazione
        ("Frequenza delle lezioni:"));
     
     
     finestraConf.lista2[0] = Translator.str_FinestraConfigurazione("giornaliera");
     finestraConf.lista2[1] = Translator.str_FinestraConfigurazione("settimanale");
        finestraConf.lista2[2] = Translator.str_FinestraConfigurazione("ogni 2 giorni");
        finestraConf.lista2[3] = Translator.str_FinestraConfigurazione("ogni 3 giorni");
        finestraConf.lista2[4] = Translator.str_FinestraConfigurazione("ogni 4 giorni");
        finestraConf.lista2[5] = Translator.str_FinestraConfigurazione("ogni 5 giorni");
        finestraConf.lista2[6] = Translator.str_FinestraConfigurazione("ogni 6 giorni");
        //finestraConf.frequenzalezioni.removeAllItems();
        
        // validate all
        finestraConf.frequenzalezioni = new JComboBox(finestraConf.lista2);
        finestraConf.frequenzalezioni.updateUI();
        finestraConf.validate();
       
        
        finestraConf.container.validate();
        finestraConf.container.updateUI();
        
  
     
    }
    /**
        methods and variables returns strings translated   
      */
    static private Map<String,String> totString_SetUpGUI; 
    static private Map<String,String> totString_FinestraIniziale;
    static private Map<String,String> totString_FinestraConfigurazione;
    static private Map<String,String> totString_ResultsJFrame;
    
    public static void init_str_ResultsJFrame(){
        System.out.println("Stringhe finestra risultati iniziallizate");
        
        
        totString_ResultsJFrame = new HashMap<>();
        if (translatorValue == 1)
        {// Stringhe italiane
        totString_ResultsJFrame.put("Riassunto", "Riassunto");
        totString_ResultsJFrame.put("Riassunto per Aule", "Riassunto per Aule");
        totString_ResultsJFrame.put("Distribuzione","Distribuzione");
        totString_ResultsJFrame.put("Meno dati", "Meno dati");
        totString_ResultsJFrame.put("Più dati", "Più dati");
        totString_ResultsJFrame.put("Ore per lezione:", "Ore per lezione: ");
        totString_ResultsJFrame.put("Il corso inizia:","Il corso inizia:");
        totString_ResultsJFrame.put("Il corso finisce:", "Il corso finisce:");
        totString_ResultsJFrame.put("Aula","Aula");
        totString_ResultsJFrame.put("La durata totale della scuola è di mesi:",
                "La durata totale della scuola è di mesi:");
        totString_ResultsJFrame.put("La scuola inizia:","La scuola inizia:");
        totString_ResultsJFrame.put("finisce:","finisce:");
        totString_ResultsJFrame.put("I corsi sono dalle: ","I corsi sono dalle: ");
        totString_ResultsJFrame.put("Il corso si tiene ogni:","Il corso si tiene ogni:");
        totString_ResultsJFrame.put("La durata totale della scuola è di mesi:",
                "La durata totale della scuola è di mesi:");
        totString_ResultsJFrame.put("Lezioni","Lezioni");
        totString_ResultsJFrame.put("Lezione","Lezione ");
        totString_ResultsJFrame.put("giorni/o","giorni/o");
        totString_ResultsJFrame.put("Inizia:","Inizia:");
        totString_ResultsJFrame.put("Finisce:","Finisce:");
        totString_ResultsJFrame.put("Numero aule disponibili:","Numero aule disponibili:");
        totString_ResultsJFrame.put("corso/i:","corso/i:");
        totString_ResultsJFrame.put("Resto delle aule","Resto delle aule");
        totString_ResultsJFrame.put("Vuoto","Vuoto");
        totString_ResultsJFrame.put("Pieno","Pieno");
        totString_ResultsJFrame.put("Distribuzione uso aule", 
                "Distribuzione uso aule");
        totString_ResultsJFrame.put("Il seguente testo è copiato negli appunti",
                "Il seguente testo è copiato negli appunti");
        totString_ResultsJFrame.put("Copia negli appunti","Copia negli appunti");
        totString_ResultsJFrame.put("il:"," il: ");
        totString_ResultsJFrame.put("alle:", " alle: ");
        totString_ResultsJFrame.put("dalle:", " dalle: ");
        totString_ResultsJFrame.put("tutte", "tutte");
        totString_ResultsJFrame.put("Corso:","Course:");
        totString_ResultsJFrame.put("Aula:","Aula:");
        totString_ResultsJFrame.put("Ore per Lezione:","Ore per Lezione:");
        
        } else if (translatorValue == 2)
        {
            // Stringhe inglesi
        totString_ResultsJFrame.put("Riassunto", "Summary");
        totString_ResultsJFrame.put("Riassunto per Aule", "Summary and Classrooms");
        totString_ResultsJFrame.put("Distribuzione","Distribution");
        totString_ResultsJFrame.put("Meno dati", "- results");
        totString_ResultsJFrame.put("Più dati", "+ results");
        totString_ResultsJFrame.put("Ore per lezione:", "Hours per Lesson: ");
        totString_ResultsJFrame.put("Il corso inizia:","Course start at:");
        totString_ResultsJFrame.put("Il corso finisce:", "Course finish at:");
        totString_ResultsJFrame.put("Aula:","Room:");
        totString_ResultsJFrame.put("La durata totale della scuola è di mesi:",
                "The school is open for:");
        totString_ResultsJFrame.put("La scuola inizia:","The school start at:");
        totString_ResultsJFrame.put("finisce:","finish at: ");
        totString_ResultsJFrame.put("I corsi sono dalle: ","The courses start from: ");
        totString_ResultsJFrame.put("Il corso si tiene ogni:","The course is every:");  
        totString_ResultsJFrame.put("Lezioni","Lessons");
        totString_ResultsJFrame.put("Lezione","Lesson ");
        totString_ResultsJFrame.put("giorni/o","day/s");
        totString_ResultsJFrame.put("Inizia:","Start:");
        totString_ResultsJFrame.put("Finisce:","End:");
        totString_ResultsJFrame.put("Numero aule disponibili:","Avaiable classrooms:");
        totString_ResultsJFrame.put("corso/i:","course/s:");
        totString_ResultsJFrame.put("Resto delle aule","Others Rooms");
        totString_ResultsJFrame.put("Vuoto","Empty");
        totString_ResultsJFrame.put("Pieno","Full");
        totString_ResultsJFrame.put("Distribuzione uso aule", 
                "Classrooms distribution");
        
        totString_ResultsJFrame.put("Il seguente testo è copiato negli appunti",
                "Copied in clipboard");
        totString_ResultsJFrame.put("Copia negli appunti","Copied in clipboard");
        totString_ResultsJFrame.put("il:"," on: ");
        totString_ResultsJFrame.put("alle:"," until: ");
        totString_ResultsJFrame.put("dalle:", " from: ");
         totString_ResultsJFrame.put("tutte", "all rooms");
         totString_ResultsJFrame.put("Corso:", "Course:");
         totString_ResultsJFrame.put("Aula:","Room:");
         totString_ResultsJFrame.put("Ore per Lezione:","Hours per lesson: ");
            
        }else if (translatorValue == 3)
        {
            // Stringhe tedesche
        totString_ResultsJFrame.put("Riassunto", "Summary");
        totString_ResultsJFrame.put("Riassunto per Aule", "Summary and Classrooms");
        totString_ResultsJFrame.put("Distribuzione","Distribution");
        totString_ResultsJFrame.put("Meno dati", "- results");
        totString_ResultsJFrame.put("Più dati", "+ results");
        totString_ResultsJFrame.put("Ore per lezione:", "Hours per Lesson: ");
        totString_ResultsJFrame.put("Il corso inizia:","Course start at:");
        totString_ResultsJFrame.put("Il corso finisce:", "Course finish at:");
        totString_ResultsJFrame.put("Aula:","Room:");
        totString_ResultsJFrame.put("La durata totale della scuola è di mesi:",
                "The school is open for:");
        totString_ResultsJFrame.put("La scuola inizia:","The school start at:");
        totString_ResultsJFrame.put("finisce:","finish at:");
        totString_ResultsJFrame.put("I corsi sono dalle: ","The courses start from: ");
        totString_ResultsJFrame.put("Il corso si tiene ogni:","The course is every:");  
        totString_ResultsJFrame.put("Lezioni","Lessons");
        totString_ResultsJFrame.put("Lezione","Lesson ");
        totString_ResultsJFrame.put("giorni/o","day/s");
        totString_ResultsJFrame.put("Inizia:","Start:");
        totString_ResultsJFrame.put("Finisce:","End:");
        totString_ResultsJFrame.put("Numero aule disponibili:","Avaiable classrooms:");
        totString_ResultsJFrame.put("corso/i:","course/s:");
        totString_ResultsJFrame.put("Resto delle aule","Others Rooms");
        totString_ResultsJFrame.put("Vuoto","Empty");
        totString_ResultsJFrame.put("Pieno","Full");
        totString_ResultsJFrame.put("Distribuzione uso aule", 
                "Classrooms distribution");
        
        totString_ResultsJFrame.put("Il seguente testo è copiato negli appunti",
                "Copied in clipboard");
        totString_ResultsJFrame.put("Copia negli appunti","Copied in clipboard");
        totString_ResultsJFrame.put("il:"," am: ");
        totString_ResultsJFrame.put("alle:"," until: ");
        totString_ResultsJFrame.put("dalle:", " from: ");
        totString_ResultsJFrame.put("tutte", "all rooms");
         totString_ResultsJFrame.put("Corso:","Course:");
         totString_ResultsJFrame.put("Room:","Room:");
         totString_ResultsJFrame.put("Ore per Lezione:","Ore per Lezione: ");
        }
        
    }
    
    public static void init_str_FinestraConfigurazione(){
        
        totString_FinestraConfigurazione = new HashMap<>();
        
        if (translatorValue == 1)
        {// Italian strings
         totString_FinestraConfigurazione.put("Queste regole verranno applicate ai nuovi corsi creati",
                 "Queste regole verranno applicate ai nuovi corsi creati");
         totString_FinestraConfigurazione.put("Configurazione regole generali",
                 "Configurazione regole generali");
         totString_FinestraConfigurazione.put("aule disponibili e frequenza lezioni",                   
                    "aule disponibili e frequenza lezioni");
         totString_FinestraConfigurazione.put("giorni da escludere", 
                 "giorni da escludere");
         totString_FinestraConfigurazione.put("Lunedì", "Lunedì");
         totString_FinestraConfigurazione.put("Martedì", "Martedì");
         totString_FinestraConfigurazione.put("Mercoledì", "Mercoledì");
         totString_FinestraConfigurazione.put("Giovedì", "Giovedì");
         totString_FinestraConfigurazione.put("Venerdì", "Venerdì");
         totString_FinestraConfigurazione.put("Sabato", "Sabato");
         totString_FinestraConfigurazione.put("Domenica", "Domenica");
         totString_FinestraConfigurazione.put("giorni del calendario da escludere"
                 , "giorni del calendario da escludere");
         totString_FinestraConfigurazione.put("Nessuna", "Nessuna");
         totString_FinestraConfigurazione.put("date inserite: ", "date inserite: ");
         //error
         totString_FinestraConfigurazione.put("Errore", "Error");
         totString_FinestraConfigurazione.put("E' stato riscontrato un errore nell'inserimento delle ore",
                 "E' stato riscontrato un errore nell'inserimento delle ore");
         totString_FinestraConfigurazione.put("Sono state inserite più di 8700 ore di lezione!",
                 "Sono state inserite più di 8700 ore di lezione!");
         
         totString_FinestraConfigurazione.put("Cancella data selezionata", 
                 "Cancella data selezionata");
         totString_FinestraConfigurazione.put("Aule disponibili:", "Aule disponibili:");
         totString_FinestraConfigurazione.put("Frequenza delle lezioni:", "Frequenza delle lezioni:");
         totString_FinestraConfigurazione.put("configura la durata totale(in ore) di tutti i corsi",
                 "configura la durata totale(in ore) di tutti i corsi");
         totString_FinestraConfigurazione.put("Ore per Lezione", "Ore per Lezione");
         totString_FinestraConfigurazione.put("Configura", "Configura");
         totString_FinestraConfigurazione.put("Default", "Default");
         totString_FinestraConfigurazione.put("Salva Configurazione", "Salva Configurazione");
         totString_FinestraConfigurazione.put("Carica Configurazione", "Carica Configurazione");
         totString_FinestraConfigurazione.put("Reset", "Reset");
         totString_FinestraConfigurazione.put("giornaliera","giornaliera");            
         totString_FinestraConfigurazione.put("settimanale","settimanale");            
         totString_FinestraConfigurazione.put("ogni 2 giorni","ogni 2 giorni");            
         totString_FinestraConfigurazione.put("ogni 3 giorni","ogni 3 giorni");            
         totString_FinestraConfigurazione.put("ogni 4 giorni","ogni 4 giorni");            
         totString_FinestraConfigurazione.put("ogni 5 giorni","ogni 5 giorni");            
         totString_FinestraConfigurazione.put("ogni 6 giorni","ogni 6 giorni");            
         
         
         //Tipps 
           //ONE_ONE
         totString_FinestraConfigurazione.put("Finestra Configurazione", 
           "Questa è la finestra per la configurazione delle regole generali."
        + "<br>Il numero delle aule disponibili dovrebbe essere 1<br>"
         );
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea1",
         "<html>Nella finestra principale <br> <h3>Vai su Crea/Salva --> Crea </h3></html>");
         
          //MANY_MANY
         totString_FinestraConfigurazione.put("Finestra Configurazione2", 
          "<h3>Questa è la finestra per la configurazione delle regole generali.<br>Siete pregati di inserire il numero delle aule > 1<br>"
         + "In questo modo configurete diversi corsi per più aule.</h3>");
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea2",
         "<html>Nella finestra principale <br> <h3>Vai su Crea/Salva --> Crea </h3></html>");
         
         
         
            
        } else if (translatorValue == 2)
        {// English Strings
         totString_FinestraConfigurazione.put("Queste regole verranno applicate ai nuovi corsi creati",
                 "This rules applied to new courses");
        totString_FinestraConfigurazione.put("Configurazione regole generali",
                 "General Configuration");
            
        totString_FinestraConfigurazione.put("aule disponibili e frequenza lezioni",                   
                    "Avaiable rooms and frequency lessons");
         totString_FinestraConfigurazione.put("giorni da escludere", 
                 "day to exclude");
         totString_FinestraConfigurazione.put("Lunedì", "Monday");
         totString_FinestraConfigurazione.put("Martedì", "Tuesday");
         totString_FinestraConfigurazione.put("Mercoledì", "Wednesday");
         totString_FinestraConfigurazione.put("Giovedì", "Thursday");
         totString_FinestraConfigurazione.put("Venerdì", "Friday");
         totString_FinestraConfigurazione.put("Sabato", "Saturday");
         totString_FinestraConfigurazione.put("Domenica", "Sunday");
         totString_FinestraConfigurazione.put("giorni del calendario da escludere"
                 , "days to exlclude from the calendar");
         totString_FinestraConfigurazione.put("Nessuna", "Nothing");
         totString_FinestraConfigurazione.put("date inserite: ", "dates inserted: ");
         //error
         totString_FinestraConfigurazione.put("Errore", "Error");
         totString_FinestraConfigurazione.put("E' stato riscontrato un errore nell'inserimento delle ore",
                 "Problem with hours");
         totString_FinestraConfigurazione.put("Sono state inserite più di 8700 ore di lezione!",
                 "You insert to much hours. Hours of lessons > 8700!");
         
         totString_FinestraConfigurazione.put("Cancella data selezionata", 
                 "Erase selected date");
         totString_FinestraConfigurazione.put("Aule disponibili:", "Avaiable rooms:");
         totString_FinestraConfigurazione.put("Frequenza delle lezioni:", 
                 "Frequency lessons:");
         totString_FinestraConfigurazione.put("configura la durata totale(in ore) di tutti i corsi",
                 "set up the duration lessons( in hours) for all the courses");
         totString_FinestraConfigurazione.put("Ore per Lezione", "Hours per Lesson");
         totString_FinestraConfigurazione.put("Configura", "Configure");
         totString_FinestraConfigurazione.put("Default", "Default");
         totString_FinestraConfigurazione.put("Salva Configurazione", "Save Configuration");
         totString_FinestraConfigurazione.put("Carica Configurazione", "Load Configuration");
         totString_FinestraConfigurazione.put("Reset", "Reset");
         totString_FinestraConfigurazione.put("giornaliera","daily");            
         totString_FinestraConfigurazione.put("settimanale","weekly");            
         totString_FinestraConfigurazione.put("ogni 2 giorni","every 2 days");            
         totString_FinestraConfigurazione.put("ogni 3 giorni","every 3 days");            
         totString_FinestraConfigurazione.put("ogni 4 giorni","every 4 days");            
         totString_FinestraConfigurazione.put("ogni 5 giorni","every 5 days");            
         totString_FinestraConfigurazione.put("ogni 6 giorni","every 6 days");    
         
          //Tipps 
           //ONE_ONE
         totString_FinestraConfigurazione.put("Finestra Configurazione", 
           "This is the window for general rules<br>"
         + "The number of the avaiaiable rooms must be 1 <br>"
         );
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea1",
         "in the MAIN WINDOW <br> <h2>Go to Create/Save --> Create </h2>");
         
          //MANY_MANY
         totString_FinestraConfigurazione.put("Finestra Configurazione2", 
          "This is the window for set-up the general rules of your courses."
        + "<br>Please insert the number of the rooms > 1<br>"
         + "In this way you set-up many courses for many rooms.");
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea2",
         "In the MAIN WINDOW :<br> <h3>GO TO :<h2> Create/Save --> Create</h2>");
         
            
        } else if (translatorValue == 3)
        {// Deutsch strings
         totString_FinestraConfigurazione.put("Queste regole verranno applicate ai nuovi corsi creati",
                 "This rules applied to new courses");
         totString_FinestraConfigurazione.put("Configurazione regole generali",
                 "General Configuration");
          totString_FinestraConfigurazione.put("aule disponibili e frequenza lezioni",                   
                    "Avaiable rooms and frequency lessons");
         totString_FinestraConfigurazione.put("giorni da escludere", 
                 "day to exclude");
         totString_FinestraConfigurazione.put("Lunedì", "Monday");
         totString_FinestraConfigurazione.put("Martedì", "Tuesday");
         totString_FinestraConfigurazione.put("Mercoledì", "Wednesday");
         totString_FinestraConfigurazione.put("Giovedì", "Thursday");
         totString_FinestraConfigurazione.put("Venerdì", "Friday");
         totString_FinestraConfigurazione.put("Sabato", "Saturday");
         totString_FinestraConfigurazione.put("Domenica", "Sunday");
         totString_FinestraConfigurazione.put("giorni del calendario da escludere"
                 , "days to exlclude from the calendar");
         totString_FinestraConfigurazione.put("Nessuna", "Nothing");
         totString_FinestraConfigurazione.put("date inserite: ", "dates inserted: ");
         //error
         totString_FinestraConfigurazione.put("Errore", "Error");
         totString_FinestraConfigurazione.put("E' stato riscontrato un errore nell'inserimento delle ore",
                 "Problem with hours");
         totString_FinestraConfigurazione.put("Sono state inserite più di 8700 ore di lezione!",
                 "You insert to much hours. Hours of lessons > 8700!");
         
         totString_FinestraConfigurazione.put("Cancella data selezionata", 
                 "Erase selected date");
         totString_FinestraConfigurazione.put("Aule disponibili:", "Avaiable rooms:");
         totString_FinestraConfigurazione.put("Frequenza delle lezioni:", 
                 "Frequency lessons:");
         totString_FinestraConfigurazione.put("configura la durata totale(in ore) di tutti i corsi",
                 "set up the duration lessons( in hours) for all the courses");
         totString_FinestraConfigurazione.put("Ore per Lezione", "Hours per Lesson");
         totString_FinestraConfigurazione.put("Configura", "Configure");
         totString_FinestraConfigurazione.put("Default", "Default");
         totString_FinestraConfigurazione.put("Salva Configurazione", "Save Configuration");
         totString_FinestraConfigurazione.put("Carica Configurazione", "Load Configuration");
         totString_FinestraConfigurazione.put("Reset", "Reset");
         totString_FinestraConfigurazione.put("giornaliera","daily");            
         totString_FinestraConfigurazione.put("settimanale","weekly");            
         totString_FinestraConfigurazione.put("ogni 2 giorni","every 2 days");            
         totString_FinestraConfigurazione.put("ogni 3 giorni","every 3 days");            
         totString_FinestraConfigurazione.put("ogni 4 giorni","every 4 days");            
         totString_FinestraConfigurazione.put("ogni 5 giorni","every 5 days");            
         totString_FinestraConfigurazione.put("ogni 6 giorni","every 6 days");     
         
          //Tipps 
           //ONE_ONE
         totString_FinestraConfigurazione.put("Finestra Configurazione", 
           "<h3>Questa è la finestra per la configurazione delle regole generali.<br>Il numero delle aule disponibili dovrebbe essere 1<br>"
         + "In questo configurete diversi corsi per una aula.</h3>");
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea1",
         "<html>Nella finestra principale <br> <h3>Vai su Crea/Salva --> Crea </h3></html>");
         
          //MANY_MANY
         totString_FinestraConfigurazione.put("Finestra Configurazione2", 
          "<h3>Questa è la finestra per la configurazione delle regole generali.<br>Siete pregati di inserire il numero delle aule > 1<br>"
         + "In questo modo configurete diversi corsi per più aule.</h3>");
         
         totString_FinestraConfigurazione.put("Vai a Crea/Salva --> Crea2",
         "<html>Nella finestra principale <br> <h3>Vai su Crea/Salva --> Crea </h3></html>");
        }
    }
    
    /**
     *This method initialize the string maps for the translation for the class FinestraIniziale
     */
    public static void init_str_FinestraIniziale(){ 
        totString_FinestraIniziale = new HashMap<>();
        System.out.println("Inizializzazione stringhe Finestra iniziale");
       
        if (translatorValue == 1)
        {// First Start
         totString_FinestraIniziale.put(
         "Questo software è creato per dare assistenza all'amministrazione di una scuola..."  
        ,"Questo software è creato per dare assistenza all'amministrazione di una scuola\n"
       + "Con questo tool informatico potrete costruire un calendario, o meglio uno \" uno scenario \"\n"
       + "e vedere quante date e quante aule i vostri corsi occupano.\n"
       + "Questa versione ha solo l'obiettivo di mostrare alcune tecniche di design del software\n"
       + "Questo software è offerto senza nessuna garanzia ed è vietata la sua commerciabilità\n"
         );
            
         
         // TIPPS 
         
          //TippTunnellingFrame
         totString_FinestraIniziale.put("Benvenuto in One Year Schedule Scenario","Benvenuto in One Year Schedule Scenario");
         
         totString_FinestraIniziale.put("Vuoi essere aiutato all'utilizzo di questa applicazione?",
                 "Vuoi essere aiutato all'utilizzo di questo software?");
         totString_FinestraIniziale.put("Cosa desideri fare?","Cosa desideri fare?");
         totString_FinestraIniziale.put("Voglio inserire più corsi per un'aula",
                 "Voglio inserire più corsi per un'aula");
         totString_FinestraIniziale.put("Inserire più corsi per più aule", 
                 "Inserire più corsi per più aule");
         totString_FinestraIniziale.put("Non voglio suggerimenti","Non voglio suggerimenti");
         
         //Tipps for finestra iniziale
           // tipp ONE_MANY
        
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi",
         "<br>Questa che vedi è la finestra principale.<br>Inizia creando una configurazione generale dei corsi"
       + "<br>Questo configurerà i tuoi corsi per UN AULA"
        +"<br>Dal menù soprastante scegli:<br><h2><i><b> Crea/Salva --> Configurazione regole generali</b></i></h2>");
         
         
         
         
           // tipp MANY_MANY
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi2",
         "<br>Questa è la finestra principale.<br>Inizia creando una configurazione generale dei corsi"
       + "<br>Questo configurerà i tuoi corsi per PIU' AULE"
        +"<br>Dal menù soprastante scegli:<br><h2><i><b> Crea/Salva --> Configurazione regole generali</b></i></h2>");
         
         //menu
         totString_FinestraIniziale.put("About","About");
         totString_FinestraIniziale.put("Help ?", "Aiuto ?");
         totString_FinestraIniziale.put("Cancella Tutto", "Cancella Tutto");
          totString_FinestraIniziale.put("Crea", "Crea");
         totString_FinestraIniziale.put("Configura regole generali", "Configura regole generali");
         totString_FinestraIniziale.put("Mostra risultato", "Mostra risultato");
         totString_FinestraIniziale.put("Salva progetto", "Salva progetto");
         totString_FinestraIniziale.put("Carica progetto", "Carica progetto");
          totString_FinestraIniziale.put("Crea/Salva", "Crea/Salva");
          totString_FinestraIniziale.put("Language/Lingua/Sprache", "Language/Lingua/Sprache"); 
         
         // about
         totString_FinestraIniziale.put("One year School Schedule Scenario v.0.1 è la versione base\ndel software School Schedule Scenario Enterprise 2.8.\n", 
         "One year School Schedule Scenario v.0.1 è la versione base\ndel software School Schedule Scenario Enterprise 2.8.\n");
         totString_FinestraIniziale.put("\nquesto software serve per dare assistenza all'amministrazione\ndi un ente formativo.\n","\nquesto software serve per dare assistenza all'amministrazione\ndi un ente formativo.\n");
         totString_FinestraIniziale.put("\nQuesta versione è offerta secondo la licenza d'uso:",
         "\nQuesta versione è offerta secondo la licenza d'uso:");   
         totString_FinestraIniziale.put("\nGPLv3. \nPer info contattare l'autore\nFrancesco Capodanno: francesco@panglosses.com",
         "\nGPLv3. \nPer info contattare l'autore\nFrancesco Capodanno: francesco@panglosses.com");
         totString_FinestraIniziale.put("About","About");
         
         //error not configured
         totString_FinestraIniziale.put("Non hai creato una configurazione generale dei corsi,",
           "Non hai creato una configurazione generale dei corsi,");
          totString_FinestraIniziale.put("\nutilizzerò una configurazione di default standard",
                 "\nutilizzerò una configurazione di default standard" );
           
           totString_FinestraIniziale.put("\n1)sabato e domenica non si fanno corsi",
                  "\n1)sabato e domenica non si fanno corsi");
          totString_FinestraIniziale.put("\n2)tutte le aule sono disponibili(50).",
                  "\n2)tutte le aule sono disponibili(50).");
           totString_FinestraIniziale.put("\n3)nessuna data specifica è esclusa dal calendario",
                   "\n3)nessuna data specifica è esclusa dal calendario");
                   
             totString_FinestraIniziale.put("Errore", "Errore");
             
            //ListenerMenuCrea 
            totString_FinestraIniziale.put("Quanti corsi devo programmare?","Quanti corsi devo programmare?");
            totString_FinestraIniziale.put("Crea programmazione corsi","Crea programmazione corsi");
            totString_FinestraIniziale.put("Orario di apertura della scuola", "Orario di apertura della scuola");
            totString_FinestraIniziale.put("Orario di chiusura della scuola","Orario di chiusura della scuola");
            totString_FinestraIniziale.put("Ore","Ore");
            totString_FinestraIniziale.put("Minuti","Minuti");
        } else if (translatorValue == 2)
        {/*
               Stringhe inglesi
            */
            // First Start
         totString_FinestraIniziale.put(
         "Questo software è creato per dare assistenza all'amministrazione di una scuola..."  
        ,"This software is created for give support to school administrators\n"
       + "With this tool you can build a calendar, or better, an \" scenario\"\n"
       + "you can see, how many dates and classrooms your courses involve.\n"
       + "This version shows you just some design techniques for software design\n"
       + "This software have no warranty and it's not allowed the merchantability\n"
         );
            
          //TippTunnellingFrame
         totString_FinestraIniziale.put("Benvenuto in One Year Schedule Scenario",
                 "Welcome in One Year Schedule Scenario");
         
         totString_FinestraIniziale.put("Vuoi essere aiutato all'utilizzo di questa applicazione?",
                 "Do you want help for using this software?");
         totString_FinestraIniziale.put("Cosa desideri fare?","What do you want to do?");
         totString_FinestraIniziale.put("Voglio inserire più corsi per un'aula",
                 "I want insert MANY courses per ONE classroom");
         totString_FinestraIniziale.put("Inserire più corsi per più aule", 
                 "I want insert MANY courses per MANY classrooms");
         totString_FinestraIniziale.put("Non voglio suggerimenti","I don't want suggestions");
         
         
          //Tipps for finestra iniziale
           // tipp ONE_MANY
        
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi",
         "This is the main window. <br>First step: create the general rules for your courses in one classroom<br>"
       + "<br>"
        +"click on: Create/Save --> Configure general rules");
         
         
           // tipp MANY_MANY
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi2",
         "<br>This is the main window.<br> First step: create the general rules for your courses in more classrooms<br>"
       + "<br>"
        +"<br>From the menù go to:<br><h2><i><b> Create/Save --> Configure general rules</b></i></h2>");
         
          //menu
         totString_FinestraIniziale.put("About","About");
         totString_FinestraIniziale.put("Help ?", "Help ?");
         totString_FinestraIniziale.put("Cancella Tutto", "Erase All");
          totString_FinestraIniziale.put("Crea", "Create");
         totString_FinestraIniziale.put("Configura regole generali", "Configure general rules");
         totString_FinestraIniziale.put("Mostra risultato", "Show results");
         totString_FinestraIniziale.put("Salva progetto", "Save project");
         totString_FinestraIniziale.put("Carica progetto", "Load project");
          totString_FinestraIniziale.put("Crea/Salva", "Load/Save");
          totString_FinestraIniziale.put("Language/Lingua/Sprache", "Language/Lingua/Sprache");
         
            // about
            totString_FinestraIniziale.put("One year School Schedule Scenario v.0.1 è la versione base\ndel software School Schedule Scenario Enterprise 2.8.\n", 
         "One year School Schedule Scenario v.0.1 is the demo version \n for software School Schedule Scenario Enterprise 2.8.\n");
         totString_FinestraIniziale.put("\nquesto software serve per dare assistenza all'amministrazione\ndi un ente formativo.\n",
                 "\nThis software give help to a school administrator\n");
         totString_FinestraIniziale.put("\nQuesta versione è offerta secondo la licenza d'uso:",
         "\nThis software is under the following license:");   
         totString_FinestraIniziale.put("\nGPLv3. \nPer info contattare l'autore\nFrancesco Capodanno: francesco@panglosses.com",
         "\nGPLv3 \nFor info contact me: \nFrancesco Capodanno: francesco@panglosses.com");
         totString_FinestraIniziale.put("About","About");
         
         //error not configured
         totString_FinestraIniziale.put("Non hai creato una configurazione generale dei corsi,",
           "You don't have create a general course configuration,");
          totString_FinestraIniziale.put("\nutilizzerò una configurazione di default standard",
                 "\nThe software use a standard configuration:" );
           
           totString_FinestraIniziale.put("\n1)sabato e domenica non si fanno corsi",
                  "\n1)No courses on saturday and sunday");
          totString_FinestraIniziale.put("\n2)tutte le aule sono disponibili(50).",
                  "\n2)All the rooms are avaiable(50).");
           totString_FinestraIniziale.put("\n3)nessuna data specifica è esclusa dal calendario",
                   "\n3)No dates are excluded");
                   
             totString_FinestraIniziale.put("Errore", "Error");
             
             //ListenrMenuCrea
             totString_FinestraIniziale.put("Quanti corsi devo programmare?",
                     "How many courses do you want?");
            totString_FinestraIniziale.put("Crea programmazione corsi",
                    "Courses creator");             
            totString_FinestraIniziale.put("Orario di apertura della scuola", 
                    "School open at");
            totString_FinestraIniziale.put("Orario di chiusura della scuola",
                    "School close at");
            totString_FinestraIniziale.put("Ore","Hours");
            totString_FinestraIniziale.put("Minuti","Minutes");
            
        } else if (translatorValue == 3)
        {/*
               Stringhe tedesche
            */totString_FinestraIniziale.put(
         "Questo software è creato per dare assistenza all'amministrazione di una scuola..."  
        ,"This software is created for give support to school administrators\n"
       + "With this tool you can build a calendar, or better, an \" scenario\"\n"
       + "you can see, how many dates and classrooms your courses involve.\n"
       + "This version shows you just some design techniques for software design\n"
       + "This software have no warranty and it's not allowed the merchantability\n"
         );
            
          //TippTunnellingFrame
         totString_FinestraIniziale.put("Benvenuto in One Year Schedule Scenario",
                 "Welcome in One Year Schedule Scenario");
         
         totString_FinestraIniziale.put("Vuoi essere aiutato all'utilizzo di questa applicazione?",
                 "Do you want help for using this software?");
         totString_FinestraIniziale.put("Cosa desideri fare?","What do you want to do?");
         totString_FinestraIniziale.put("Voglio inserire più corsi per un'aula",
                 "I want insert MANY courses per ONE classroom");
         totString_FinestraIniziale.put("Inserire più corsi per più aule", 
                 "I want insert MANY courses per MANY classrooms");
         totString_FinestraIniziale.put("Non voglio suggerimenti","I don't want suggestions");
         
          //Tipps for finestra iniziale
           // tipp ONE_MANY
        
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi",
         "This is the main window. First step: create the general rules for your courses i\n"
       + "\n"
        +"click on: Create/Save --> Configure general rules");
         
         
           // tipp MANY_MANY
         totString_FinestraIniziale.put(
         "Inizia creando una configurazione generale dei corsi2",
         "<br>This is the main window.<br> First step: create the general rules for your courses in more classrooms<br>"
       + "<br>"
        +"<br>From the menù go to:<br><h2><i><b> Create/Save --> Configure general rules</b></i></h2>");
         
         
        
         
         //menu
         totString_FinestraIniziale.put("About","About");
         totString_FinestraIniziale.put("Help ?", "Aiuto ?");
         totString_FinestraIniziale.put("Cancella Tutto", "Erase All");
          totString_FinestraIniziale.put("Crea", "Create");
         totString_FinestraIniziale.put("Configura regole generali", "Configure general rules");
         totString_FinestraIniziale.put("Mostra risultato", "Show results");
         totString_FinestraIniziale.put("Salva progetto", "Save project");
         totString_FinestraIniziale.put("Carica progetto", "Load project");
          totString_FinestraIniziale.put("Crea/Salva", "Load/Save");
          totString_FinestraIniziale.put("Language/Lingua/Sprache", "Language/Lingua/Sprache");
         
            // about
         totString_FinestraIniziale.put("One year School Schedule Scenario v.0.1 è la versione base\ndel software School Schedule Scenario Enterprise 2.8.\n", 
         "One year School Schedule Scenario v.0.1 is the demo version \n for software School Schedule Scenario Enterprise 2.8.\n");
         totString_FinestraIniziale.put("\nquesto software serve per dare assistenza all'amministrazione\ndi un ente formativo.\n",
                 "\nThis software give help to a school administrator\n");
         totString_FinestraIniziale.put("\nQuesta versione è offerta secondo la licenza d'uso:",
         "\nThis software is under the following license:");   
         totString_FinestraIniziale.put("\nGPLv3. \nPer info contattare l'autore\nFrancesco Capodanno: francesco@panglosses.com",
         "\nGPLv3 \nFor info contact me: \nFrancesco Capodanno: francesco@panglosses.com");
         totString_FinestraIniziale.put("About","About");
         
         //error not configured
         totString_FinestraIniziale.put("Non hai creato una configurazione generale dei corsi,",
           "Non hai creato una configurazione generale dei corsi,");
          totString_FinestraIniziale.put("\nutilizzerò una configurazione di default standard",
                 "\nutilizzerò una configurazione di default standard" );
           
           totString_FinestraIniziale.put("\n1)sabato e domenica non si fanno corsi",
                  "\n1)sabato e domenica non si fanno corsi");
          totString_FinestraIniziale.put("\n2)tutte le aule sono disponibili(50).",
                  "\n2)tutte le aule sono disponibili(50).");
           totString_FinestraIniziale.put("\n3)nessuna data specifica è esclusa dal calendario",
                   "\n3)nessuna data specifica è esclusa dal calendario");
                   
             totString_FinestraIniziale.put("Errore", "Error");
             
             //ListenerMenuCrea
             totString_FinestraIniziale.put("Quanti corsi devo programmare?","Quanti corsi devo programmare?");
            totString_FinestraIniziale.put("Crea programmazione corsi","Crea programmazione corsi");
             totString_FinestraIniziale.put("Orario di apertura della scuola", "Orario di apertura della scuola");
            totString_FinestraIniziale.put("Orario di chiusura della scuola","Orario di chiusura della scuola");
            totString_FinestraIniziale.put("Ore","Hours");
            totString_FinestraIniziale.put("Minuti","Minutes");
        }
    }
          
    /**
     *This method initialize the string maps for the translation for the class SetUpGUI
     */
    public static void init_str_SetUpGUI(){
        totString_SetUpGUI = new HashMap<>();
        System.out.println("Inizializzazione Stringhe SetUpGUI:" + translatorValue);
        if (translatorValue == 1)
        {
            /*
              Stringhe italiane
            */
            System.out.println("Caricamento Stringhe Italiane per SetUpGUI");
            
            totString_SetUpGUI.put("Corso", "Corso");
            totString_SetUpGUI.put("Nome corso:", "Nome corso");
            totString_SetUpGUI.put("CorsoAggiunto", "CorsoAggiunto");
            totString_SetUpGUI.put("Data", "Data");
            
            totString_SetUpGUI.put("Nome corso:", "Nome corso:");
            totString_SetUpGUI.put("Data di inizio corso:", "Data di inizio corso:");
            totString_SetUpGUI.put("Ore totale corso(in ore):", "Ore totale corso(in ore):");
            totString_SetUpGUI.put("Ore per lezione:", "Ore per lezione:");
            totString_SetUpGUI.put("Frequenza corso:", "Frequenza corso:");
            totString_SetUpGUI.put("Ora inizio corso(HH:MM):", "Ora inizio corso(HH:MM):");
            totString_SetUpGUI.put("Giorni da escludere:", "Giorni da escludere:");
            totString_SetUpGUI.put("lunedì", "lunedì");
            totString_SetUpGUI.put("martedì", "martedì");
            totString_SetUpGUI.put("mercoledì","mercoledì");
            totString_SetUpGUI.put("giovedì", "giovedì");
            totString_SetUpGUI.put("venerdì", "venerdì");
            totString_SetUpGUI.put("sabato", "sabato");
            totString_SetUpGUI.put("domenica", "domenica");
            // Frequenza:
            totString_SetUpGUI.put("giornaliera","giornaliera");            
            totString_SetUpGUI.put("settimanale","settimanale");            
            totString_SetUpGUI.put("ogni 2 giorni","ogni 2 giorni");            
            totString_SetUpGUI.put("ogni 3 giorni","ogni 3 giorni");            
            totString_SetUpGUI.put("ogni 4 giorni","ogni 4 giorni");            
            totString_SetUpGUI.put("ogni 5 giorni","ogni 5 giorni");            
            totString_SetUpGUI.put("ogni 6 giorni","ogni 6 giorni");            
            
          
            
        } else if (translatorValue == 2)
        {
            /*
             Stringhe inglesi
            */
            totString_SetUpGUI.put("Corso", "Course");           
            totString_SetUpGUI.put("Nome corso:", "Course Name:");
            totString_SetUpGUI.put("CorsoAggiunto", "AddedCourse");
            totString_SetUpGUI.put("Data", "Date");
            
            totString_SetUpGUI.put("Data di inizio corso:", "         Start date:");
            totString_SetUpGUI.put("Ore totale corso(in ore):", "         Total hours");
            totString_SetUpGUI.put("Ore per lezione:", "        Hours for lesson:");
            totString_SetUpGUI.put("Frequenza corso:", "        Attendance:");
            totString_SetUpGUI.put("Ora inizio corso(HH:MM):", "Start Time(HH:MM)");
            totString_SetUpGUI.put("Giorni da escludere:", "Days be excluded:");
            totString_SetUpGUI.put("lunedì", "monday");
            totString_SetUpGUI.put("martedì", "thuesday");
            totString_SetUpGUI.put("mercoledì","wednesday");
            totString_SetUpGUI.put("giovedì", "thrusday");
            totString_SetUpGUI.put("venerdì", "friday");
            totString_SetUpGUI.put("sabato", "saturday");
            totString_SetUpGUI.put("domenica", "sunday");
            
            totString_SetUpGUI.put("giornaliera","daily");            
            totString_SetUpGUI.put("settimanale","weekly");            
            totString_SetUpGUI.put("ogni 2 giorni","every 2 days");            
            totString_SetUpGUI.put("ogni 3 giorni","every 3 days");            
            totString_SetUpGUI.put("ogni 4 giorni","every 4 days");            
            totString_SetUpGUI.put("ogni 5 giorni","every 5 days");            
            totString_SetUpGUI.put("ogni 6 giorni","every 6 days"); 
            
        } else if (translatorValue == 3)
        {
            /*
               Stringhe tedesche
            */
            totString_SetUpGUI.put("Corso", "Kurs");
            totString_SetUpGUI.put("Nome corso", "Kurs Name");
            totString_SetUpGUI.put("Nome corso:", "Course Name:");
            totString_SetUpGUI.put("CorsoAggiunto", "AddedCourse");
            totString_SetUpGUI.put("Data", "Date");
            
            totString_SetUpGUI.put("Data di inizio corso:", "         Start date:");
            totString_SetUpGUI.put("Ore totale corso(in ore):", "         Total hours");
            totString_SetUpGUI.put("Ore per lezione:", "        Hours for lesson:");
            totString_SetUpGUI.put("Frequenza corso:", "        Attendance:");
            totString_SetUpGUI.put("Ora inizio corso(HH:MM):", "Start Time(HH:MM)");
            totString_SetUpGUI.put("Giorni da escludere:", "Days be excluded:");
            totString_SetUpGUI.put("lunedì", "monday");
            totString_SetUpGUI.put("martedì", "thuesday");
            totString_SetUpGUI.put("mercoledì","wednesday");
            totString_SetUpGUI.put("giovedì", "thrusday");
            totString_SetUpGUI.put("venerdì", "friday");
            totString_SetUpGUI.put("sabato", "saturday");
            totString_SetUpGUI.put("domenica", "sunday");
            
            totString_SetUpGUI.put("giornaliera","daily");            
            totString_SetUpGUI.put("settimanale","weekly");            
            totString_SetUpGUI.put("ogni 2 giorni","every 2 days");            
            totString_SetUpGUI.put("ogni 3 giorni","every 3 days");            
            totString_SetUpGUI.put("ogni 4 giorni","every 4 days");            
            totString_SetUpGUI.put("ogni 5 giorni","every 5 days");            
            totString_SetUpGUI.put("ogni 6 giorni","every 6 days");             
            
        }
            
     }
    
    
    /**
     * Methods returns string translated
     */
    
    
    /**
      *  Methods returns string for SetUpGUI class
     * @param str the string to be translated
     * @return the string translated
      */
    public static String str_SetUpGUI(String str){
    String ret_str = "";
    
       if ((str != null) && (totString_SetUpGUI != null))
     { 
        ret_str = totString_SetUpGUI.get(str);
        
     }
    else 
    {
        System.out.println("Error in translation: Strings for SetUpGUI is not loaded or not Intialized");
    }
     
     return ret_str;     
    }
    
    /**
      *  Methods returns string for FinestraIniziale class
     * @param str the string to be translated
     * @return the string translated
      */
    public static String str_FinestraIniziale(String str){
    String ret_str = "";
    
       if ((str != null) && (totString_FinestraIniziale != null))
     { 
        ret_str = totString_FinestraIniziale.get(str);
     }
    else 
    {
        System.out.println("Error in translation: Strings for FinestraIniziale class is not loaded or not Intialized");
    }
     
     return ret_str;     
    }
    
    
    
    /**
      *  Methods returns string for FinestraConfigurazione class
     * @param str the string to be translated
     * @return the string translated
      */
    public static String str_FinestraConfigurazione(String str){
    String ret_str = "";
    
       if ((str != null) && (totString_FinestraConfigurazione != null))
     { 
        ret_str = totString_FinestraConfigurazione.get(str);
     }
    else 
    {
        System.out.println("Error in translation: Strings for FinestraConfigurazione class is not loaded or not Intialized");
    }
     
     return ret_str;     
    }
    
    /**
      *  Methods returns string for ResultsJFrame class
     * @param str the string to be translated
     * @return the string translated
      */
    public static String str_ResultsJFrame(String str){
    String ret_str = "";
    
       if ((str != null) && (totString_ResultsJFrame != null))
     { 
        ret_str = totString_ResultsJFrame.get(str);
     }
    else 
    {
        System.out.println("Error in translation: Strings for ResultsJFrame class is not loaded or not Intialized");
    }
     
     return ret_str;     
    }
    
    /**
     * Avvio del Translator.
     */
    public Translator()
    {
       
       System.out.println("Caricamento di translator effettuato...");
       setValue("IT");
    }
    
    /**
     * This method set the language but not translate you need to use setFramesToTranslate method.
     * This method initialize strings and use methods init_str_[class] 
     * If you want to call a string translated use the Translator.str_[class] this return a String value translated     * 
     * @param flag
     */
    static public void setValue(String flag)
    {
        System.out.println("Language: " + flag);
        if (flag.equals("IT")) translatorValue = 1;
        if (flag.equals("EN")) translatorValue = 2;
        if (flag.equals("DE")) translatorValue = 3;  
        
        /*Initialize main  strings*/
       
        init_str_FinestraIniziale();
        init_str_SetUpGUI();    
        init_str_FinestraConfigurazione();
        init_str_ResultsJFrame();
        
        
    }
    
    /**
     * This function translate the relative frame. The params that are null are not translated.
     * @param fi
     * @param fg
     * @param fr
     */
    static public void setFramesToTranslate(FinestraIniziale fi, FinestraConfigurazione fg, ResultsJFrame fr)
    {
          if (fi != null) 
          {
              finestraIniziale = fi;
              trans_finestraIniziale();
          } else { System.out.println("finestra iniziale è null"); }
          
          if (fg != null) 
          {
              finestraConf = fg;
              trans_finestraConf();
          } else { System.out.println("finestra conf è null"); }
          
          if (fg != null) 
          {   
              resultsFrame = fr;
              System.out.println("Traduzione finestra risultati...");
              trans_resultsFrame();
          } else { System.out.println("finestra frame è null"); }
          
    }
    
}
