/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author guru
 */
public class Elaboration {
    FinestraIniziale finestra;
    FinestraConfigurazione finestraconf;
    ResultsJFrame resultsJFrame;
    boolean exist_finestraconf;
    
    /**
     *@javadoc: this return a CONSTANT! Max numbers of courses changes this value for improving the elaboration
     */
    public  final int NUM_CORSI = 16; /* Numeri di corsi gestibili dall'applicazione*/
    private final static int MAX_ROOMS = 50; /*Numeri massimo di corsi gestibili dall'applicazione */
    private final static int MAX_ERRORS = 208; // 13 componenti * 16 pannelli
    private final static int MAX_DATES = 8700;
    
    private int num_corsi_inseriti;
    private List<String> nomiCorsi;
    private List<String> dateInizioCorso_ui;
    private List<Date>   dateInizioCorso_raw;
    
        //String[] oreInizioCorso_StringArray;
    
    private int[] hHInizioCorso_intArray;
    private int[] mMInizioCorso_intArray;
    private int[] orePerLezione_intArray;
    private int[] oreTotCorso_intArray;        
    private boolean[] lun_array;
    private boolean[] mar_array;
    private boolean[] mer_array;
    private boolean[] gio_array;
    private boolean[] ven_array;
    private boolean[] sab_array;
    private boolean[] dom_array;
        
        Locale thisLocale;
        int[] distanceDays_array;
        List<Date> datesToExclude;
        List<Date> totDates;
        boolean isEmptyListToExclude;
        
        
        boolean err_elaborazione;
        String[] errors;
        int num_errors;
        
        private JPanel raccPanel;
        private JXDatePicker raccPicker;
        private JTextField raccNomeCorso,raccOreTotCorso, raccOrePerLezione, raccOraInizioCorso;
        private JComboBox raccFrequenza;
        private JCheckBox raccLun, raccMar, raccMer, raccGio, raccVen, raccSab, raccDom;
        
        
     
        
        private Map<Integer,List<Date>> lessonsDates;
        
        private Map<Integer,List<Date>> end_lessonsDates; 
        
        
        private Map<Integer,Integer> course_rooms;
        
        
    private int num_rooms;
    private int num_rooms_ins;
    private int num_rooms_tot;
    private int num_lessonsDates;
    private int lastAssignment;
    
    private Date[] datesToExclude_load;
    private boolean isAvaiableRoom;   
    boolean closeAssignment;
    private boolean[] course_assignment;

    Elaboration(FinestraIniziale aThis, FinestraConfigurazione fc) {
        thisLocale = Locale.ITALIAN;
        this.course_assignment = new boolean[NUM_CORSI];
       
        this.lastAssignment = 0;
        
        this.closeAssignment = false;
        this.isAvaiableRoom = true;
        this.course_rooms = new HashMap<>();
        this.totDates = new ArrayList<>();
        
        this.num_rooms = 1;
        
        this.end_lessonsDates = new HashMap<>();
        this.lessonsDates = new HashMap<>();
        finestra = aThis;
        finestraconf = fc;
        exist_finestraconf = true;
        err_elaborazione = false;
        err_elaborazione = false;
        errors = new String[MAX_ERRORS]; // Stringa contenente tutti gli errori dell'utente
         
        nomiCorsi= new ArrayList<>(); // num max di corsi gestibili
        
        dateInizioCorso_ui = new ArrayList<>();
        dateInizioCorso_raw = new ArrayList<>();
        distanceDays_array = new int[NUM_CORSI];
        oreTotCorso_intArray = new int[NUM_CORSI];
        orePerLezione_intArray = new int[NUM_CORSI];
        //oreInizioCorso_StringArray = new String[NUM_CORSI];
        hHInizioCorso_intArray = new int[NUM_CORSI];
        mMInizioCorso_intArray = new int[NUM_CORSI];
        
        lun_array = new boolean[NUM_CORSI];
        mar_array = new boolean[NUM_CORSI];
        mer_array = new boolean[NUM_CORSI];
        gio_array = new boolean[NUM_CORSI];
        ven_array = new boolean[NUM_CORSI];
        sab_array = new boolean[NUM_CORSI];
        dom_array = new boolean[NUM_CORSI];
        num_corsi_inseriti = 0;
        errors = new String[5];
        num_errors = 0;
        num_lessonsDates = 0;
        datesToExclude = new ArrayList<>();        
        datesToExclude_load = finestraconf.getConfigDays();
        /*
        * Carica la lista se è presente o se no isEmptyListExclude = true;
        * codice ridodante
        */
        int value = 0;
        
        for (Date datesToExclude_load1 : datesToExclude_load) {
            if (datesToExclude_load1 != null) {
                datesToExclude.add(datesToExclude_load1);
                value++;
            }
        }
        
        isEmptyListToExclude = value == 0; // è stato trovato almeno un valore non nullo
        
        num_rooms = finestraconf.getRoomsAvaiable(); // ottieni il numero massimo di aule dalla finestra configurazione
        num_rooms_tot = num_rooms;
        System.out.println("Numero di aule disponibili! " + num_rooms);
        num_rooms_ins = 0;
        if (catchComponents()) makeResults();
        
    }

    Elaboration(FinestraIniziale aThis) {
        this.thisLocale = Locale.ITALIAN;
        course_assignment = new boolean[NUM_CORSI];
        
        lastAssignment = 0;
        
        closeAssignment = false;
        isAvaiableRoom = true;
        course_rooms = new HashMap<>();
        totDates = new ArrayList<>();
        
        num_rooms = 1;
        
        end_lessonsDates = new HashMap<>();
        lessonsDates = new HashMap<>();
        finestra = aThis;        
        exist_finestraconf = false;
        err_elaborazione = false;
        
        err_elaborazione = false;
        errors = new String[MAX_ERRORS]; // Stringa contenente tutti gli errori dell'utente
         
        nomiCorsi= new ArrayList<>(); // num max di corsi gestibili
        
        dateInizioCorso_ui = new ArrayList<>();
        dateInizioCorso_raw = new ArrayList<>();
        distanceDays_array = new int[NUM_CORSI];
        oreTotCorso_intArray = new int[NUM_CORSI];
        orePerLezione_intArray = new int[NUM_CORSI];
        //oreInizioCorso_StringArray = new String[NUM_CORSI];
        hHInizioCorso_intArray = new int[NUM_CORSI];
        mMInizioCorso_intArray = new int[NUM_CORSI];
        
        lun_array = new boolean[NUM_CORSI];
        mar_array = new boolean[NUM_CORSI];
        mer_array = new boolean[NUM_CORSI];
        gio_array = new boolean[NUM_CORSI];
        ven_array = new boolean[NUM_CORSI];
        sab_array = new boolean[NUM_CORSI];
        dom_array = new boolean[NUM_CORSI];
        num_corsi_inseriti = 0;
        errors = new String[5];
        num_errors = 0;
        num_rooms_tot = MAX_ROOMS;
        num_lessonsDates = 0;
        num_rooms =MAX_ROOMS; // il numero di aule senza finestra configurazione.
        num_rooms_ins = 0;
        if (catchComponents()) makeResults();
        
       
    }
    
    public void setDatesToExclude()
    {
        try
        {
        datesToExclude_load = finestraconf.getConfigDays();
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    
    public void resetElaborationData()
    {
        
        if (finestraconf == null) 
        {System.out.println("finestra conf è null");
            this.num_rooms_tot = 50;
        
        }
        course_assignment = new boolean[NUM_CORSI];
        lastAssignment = 0;
        
        closeAssignment = false;
        isAvaiableRoom = true;
        course_rooms = new HashMap<>();
        totDates = new ArrayList<>();
        
        this.num_rooms = 1;
        
        end_lessonsDates = new HashMap<>();
        lessonsDates = new HashMap<>();        
       
        err_elaborazione = false;
        err_elaborazione = false;
        errors = new String[MAX_ERRORS]; // Stringa contenente tutti gli errori dell'utente
         
        nomiCorsi= new ArrayList<>(); // num max di corsi gestibili
        
        dateInizioCorso_ui = new ArrayList<>();
        dateInizioCorso_raw = new ArrayList<>();
        distanceDays_array = new int[NUM_CORSI];
        oreTotCorso_intArray = new int[NUM_CORSI];
        orePerLezione_intArray = new int[NUM_CORSI];
        //oreInizioCorso_StringArray = new String[NUM_CORSI];
        hHInizioCorso_intArray = new int[NUM_CORSI];
        mMInizioCorso_intArray = new int[NUM_CORSI];
        
        lun_array = new boolean[NUM_CORSI];
        mar_array = new boolean[NUM_CORSI];
        mer_array = new boolean[NUM_CORSI];
        gio_array = new boolean[NUM_CORSI];
        ven_array = new boolean[NUM_CORSI];
        sab_array = new boolean[NUM_CORSI];
        dom_array = new boolean[NUM_CORSI];
        num_corsi_inseriti = 0;
        errors = new String[5];
        num_errors = 0;
        num_lessonsDates = 0;
        datesToExclude = new ArrayList<>();
        int value = 0;
        if (finestraconf!= null)
        {
        datesToExclude_load = finestraconf.getConfigDays();
        
        /*
        * Carica la lista se è presente o se no isEmptyListExclude = true;
        * codice ridodante
        */
        
        
        for (int i=0; i<datesToExclude_load.length; i++)
        {
            if (datesToExclude_load[i] != null) 
            {
             datesToExclude.add(datesToExclude_load[i]);
             value++;
            }
           
        }
        }
        
        
        
        isEmptyListToExclude = value == 0; // è stato trovato almeno un valore non nullo
        if (finestraconf != null)
        {
        System.out.println("Prendi le aule disponibili dalla configurazione");
        num_rooms = finestraconf.getRoomsAvaiable(); // ottieni il numero massimo di aule dalla finestra configurazione
        System.out.println("Aule disponibili :" + num_rooms);
        num_rooms_tot = num_rooms;        
        }
        else
        {
            num_rooms = MAX_ROOMS;
            num_rooms_tot = MAX_ROOMS;
        }
        num_rooms_ins = 0; 
    }
    private boolean catchComponents() {
        
      
    String str;
    boolean no_error;
       
        
        Component raccoglitore;
        num_corsi_inseriti = finestra.mainContainer.getComponentCount();
        for (int i = 0; i < finestra.mainContainer.getComponentCount(); i++)
        { // Numeri di pannelli istanziati
           
           raccoglitore = finestra.mainContainer.getComponent(i);
          
           raccPanel = (JPanel) raccoglitore;
           /*
           * Questo codice mostra come è strutturato un pannello.
           */
           /*
           for (int j=0; j < raccPanel.getComponentCount(); j++)
           {
            System.out.println("Componenti raccolti:\n Componente n° "+ j +"-->" + raccPanel.getComponent(j).toString()+"<--\n");
           }
           */
           raccNomeCorso = (JTextField) raccPanel.getComponent(1);
           raccPicker = (JXDatePicker) raccPanel.getComponent(3); // Data inizio Corso
           raccOreTotCorso = (JTextField) raccPanel.getComponent(5);  // Ore Tot Corso
           raccOrePerLezione = (JTextField) raccPanel.getComponent(7); // Ore Per Lezione
           raccFrequenza = (JComboBox) raccPanel.getComponent(9); //  Frequenza
           raccOraInizioCorso = (JTextField) raccPanel.getComponent(11); // ora Inizio Corso
           raccLun = (JCheckBox) raccPanel.getComponent(13);
           raccMar = (JCheckBox) raccPanel.getComponent(14);
           raccMer = (JCheckBox) raccPanel.getComponent(15);
           raccGio = (JCheckBox) raccPanel.getComponent(16);
           raccVen = (JCheckBox) raccPanel.getComponent(17);
           raccSab = (JCheckBox) raccPanel.getComponent(18);
           raccDom = (JCheckBox) raccPanel.getComponent(19);  
           
           lun_array[i] =raccLun.isSelected();
           mar_array[i] =raccMar.isSelected();
           mer_array[i] =raccMer.isSelected();
           gio_array[i] =raccGio.isSelected();
           ven_array[i] =raccVen.isSelected();
           sab_array[i] =raccSab.isSelected();
           dom_array[i] =raccDom.isSelected(); 
           
           
           dateInizioCorso_raw.add(raccPicker.getDate()); // Date inizio corso
           dateInizioCorso_ui.add(uiSimpleConvertDate(raccPicker.getDate().toString())); // Date inizio corso
           
           /*
           * RACCOLTA DEI NOMI DEI CORSI
           */
           
           if (!((raccNomeCorso.getText().trim().equals(""))))
           {
               int conv;
               
               
               /*
               * IL try qui non è necessario lo utilizzo solo per coerenza in seguito verrà rimosso
               */
               try
               { 
                nomiCorsi.add(raccNomeCorso.getText());   
                raccNomeCorso.setBackground(Color.white);
               }
               catch (Exception e)
               {
                   errors[i] = e.getLocalizedMessage();
                   raccNomeCorso.setBackground(Color.red);
                   num_errors++; 
               }
               
               
           } else
           {
               raccNomeCorso.setBackground(Color.red);
               num_errors++;
           }
           
           /*
           * RACCOLTA DELLE ORE TOTALI DEI CORSI
           */
           
           if (!(raccOreTotCorso.getText().trim().equals("")))
           {
               int conv;
               
               
               try
               { 
                conv = Integer.parseInt(raccOreTotCorso.getText());
                oreTotCorso_intArray[i] = conv;
                raccOreTotCorso.setBackground(Color.white);
                
               }
               catch (Exception e)
               {
                   errors[i] = e.getLocalizedMessage();
                   raccOreTotCorso.setBackground(Color.red);
                   num_errors++; 
               }
               
               
           } else
           {
               raccOreTotCorso.setBackground(Color.red);
               num_errors++;
           }
           /*
           * RACCOLTA ORE PER LEZIONE
           */
           
           if (!(raccOrePerLezione.getText().trim().equals("")))
           {
               int conv;
               
               
               try
               { 
                conv = Integer.parseInt(raccOrePerLezione.getText());
                orePerLezione_intArray[i] = conv;
                raccOrePerLezione.setBackground(Color.white);
                
               }
               catch (Exception e)
               {
                   errors[i] = e.getLocalizedMessage();
                   raccOrePerLezione.setBackground(Color.red);
                   num_errors++; 
               }
               
               
           } else
           {
               raccOrePerLezione.setBackground(Color.red);
               num_errors++;
           }
           
           /*
           * RACCOLTA FREQUENZA
           */
           
           String strFreq = raccFrequenza.getSelectedItem().toString();
           if ((strFreq.equals("giornaliera")) || (strFreq.equals("daily")) 
                            || (strFreq.equals("täglich")))
                            {
                                distanceDays_array[i] = 1;
                            }
                    else if((strFreq.equals("settimanale"))|| (strFreq.equals("weekly"))
                            || (strFreq.equals("wöchentlich")))
                    {
                            distanceDays_array[i] = 7;
                    }
                    else if((strFreq.equals("ogni 2 giorni"))|| (strFreq.equals("every 2 days"))
                            || (strFreq.equals("wöchentlich")))
                     {
                           distanceDays_array[i] = 2;
                     }
                    else if ((strFreq.equals("ogni 3 giorni")) || (strFreq.equals("every 3 days")) || 
                            (strFreq.equals("alle 3 Tage")))
                    {
                       distanceDays_array[i] = 3;
                    } 
                    else if ((strFreq.equals("ogni 4 giorni")) || (strFreq.equals("every 4 days")) || 
                            (strFreq.equals("alle 4 Tage")))
                    {
                        distanceDays_array[i] = 4;
                    }
                    else if ((strFreq.equals("ogni 5 giorni")) || (strFreq.equals("every 5 days")) || 
                            (strFreq.equals("alle 5 Tage")))
                    {
                        distanceDays_array[i] = 5;
                    } 
                    else if ((strFreq.equals("ogni 6 giorni")) || (strFreq.equals("every 6 days")) || 
                            (strFreq.equals("alle 6 Tage")))
                    {
                        distanceDays_array[i] = 6;
                    } 
           /* old switch without translator
           switch (raccFrequenza.getSelectedItem().toString())
           {
            case "giornaliera"  : distanceDays_array[i] = 1;break;
            case "settimanale"  : distanceDays_array[i] = 7;break;
            case "ogni 2 giorni": distanceDays_array[i] = 2;break;
            case "ogni 3 giorni": distanceDays_array[i] = 3;break;
            case "ogni 4 giorni": distanceDays_array[i] = 4;break;
            case "ogni 5 giorni": distanceDays_array[i] = 5;break;
            case "ogni 6 giorni": distanceDays_array[i] = 6;break;    
            default : break;                     
           }
           */
           
           
           /*
           * RACCOLTA DELLE ORE INIZIO CORSO
           */
           
           String str_ore;
           String s1,s2,s3,hH;
           
           str_ore = raccOraInizioCorso.getText();
           
           if (!((str_ore.trim().equals(""))))
           {
               
               int ipos_twopoints;
               boolean err = false;
               
               if (str_ore.length() > 5) {err = true; num_errors++; 
               raccOraInizioCorso.setBackground(Color.red);
               errors[i] = "Errore ore inizio corso al pannello n°" + Integer.toString(i);} 
               else {
                       ipos_twopoints = str_ore.indexOf(":");
                       if ((ipos_twopoints == 1) || (ipos_twopoints == 2))
                       {
                           boolean notnumber = false;
                           boolean hhfind = false;
                           boolean mmfind = false;
                           int hindex; // l'indice dove finiscono le ore e iniziano i minuti incluso :
                           hindex = 0;
                           int c1,c2,c3,c4,c5;
                           
                           
                           s1 = Character.toString(str_ore.charAt(0));
                           s2 = Character.toString(str_ore.charAt(1));
                          
                           
                           try
                           {
                            c1 = Integer.parseInt(s1); 
                            raccOraInizioCorso.setBackground(Color.white);
                           }
                           catch(Exception e)
                           {
                               num_errors++; raccOraInizioCorso.setBackground(Color.red);
                               errors[i] = "Errore ore inizio corso al pannello n°" + Integer.toString(i) + 
                                           "del Tipo: " + e.getLocalizedMessage(); notnumber = true;                              
                           }
                           /*
                           * TROVA LE ORE E TROVE I MINUTI
                           */
                           if (notnumber == false) 
                           {
                               /*
                               * TROVA LE ORE
                               */
                               if (s2.equals(":"))
                               {
                                   hH = s1; // se è 0 è poco probabile che ci sia un corso ma quest'errore non è gestito                                 
                                   hHInizioCorso_intArray[i] = Integer.parseInt(hH);                                   
                                   hhfind = true;
                                   hindex = 2; // Ore trovate prima della posizione 2 (0 = numero, 1 = :) 
                                   
                               }
                               else 
                               {
                                   try
                                   {
                                    c2 = Integer.parseInt(s2);
                                    raccOraInizioCorso.setBackground(Color.white);
                                   }
                                   catch (Exception e)
                                   {
                                    num_errors++;
                                    errors[i] = "Errore ore inizio corso al pannello n°" + Integer.toString(i) + 
                                    "del Tipo: " + e.getLocalizedMessage(); notnumber = true;  
                                    raccOraInizioCorso.setBackground(Color.red);
                                   }
                                   if (notnumber == false)
                                   { int conv2 = 0;
                                     
                                       if(s1.equals("0")) {hH = s2;hhfind = true;hindex=3; /* le ore finiscono qui */}
                                               else {hH = s1 + s2;}
                                       try
                                       {
                                        conv2 = Integer.parseInt(hH);
                                        if (conv2<=23)
                                        {
                                        hHInizioCorso_intArray[i] = Integer.parseInt(hH);
                                        hhfind = true;
                                        hindex=3; // quindi da questa posizione + 1 ci sono i minuti                                        
                                        raccOraInizioCorso.setBackground(Color.white);
                                        }
                                        else
                                        {
                                            num_errors++;
                                            errors[i] = "Errore ad ore inizio corso al pannello n°" + Integer.toString(i);
                                            raccOraInizioCorso.setBackground(Color.red);hhfind = false;
                                        }    
                                        
                                       }
                                       catch (Exception e) // Parse Exception
                                       {
                                           num_errors++; notnumber = true;
                                           errors[i] = "Errore inaspettato ad ore inizio corso al pannello n°" + Integer.toString(i) + 
                                                        "del Tipo: " + e.getLocalizedMessage(); 
                                           raccOraInizioCorso.setBackground(Color.red);
                                       }
                                          
                                   }
                               }
                            
                              /*
                               * TROVA I MINUTI (Se le ore sono state trovate)
                               */ 
                              if (hhfind)
                              {
                                 /*
                                  Se le ore sono state trovate i test principali sono stati superati
                                  basta sapere quindi da che parte della stringa iniziare a cercare
                                  hindex è l'indice della posizione HH:<-- 
                                  Creo quindi una sottostringa contenente la posizione il numero dei minuti
                                  */ 
                                  s3 = str_ore.substring(hindex);
                                  try
                                  {int conv2 = 0;
                                  conv2 = Integer.parseInt(s3);
                                  mMInizioCorso_intArray[i] = conv2;
                                  raccOraInizioCorso.setBackground(Color.white);
                                  mmfind = true;
                                  if (conv2 > 60) {num_errors++; 
                                  raccOraInizioCorso.setBackground(Color.red);
                                  errors[i] = "Errore ad ore inizio corso al pannello n°" + Integer.toString(i); 
                                  mmfind = false;}
                                  }
                                  catch (Exception e)
                                  {num_errors++; notnumber = true; mmfind = false;
                                           errors[i] = "Errore ad ore inizio corso al pannello n°" + Integer.toString(i) + 
                                                        "del Tipo: " + e.getLocalizedMessage(); 
                                           raccOraInizioCorso.setBackground(Color.red);                                      
                                  }
                                 
                              }
                             }
                           
                           
                         /*
                         * WARNING FINALI Corsi che iniziano prima delle 8.00 e dopo le 19.00 di sera sono sospetti
                         */  
                           if ((mmfind) && (hhfind))
                                   {
                                      if ((hHInizioCorso_intArray[i]>19) || (hHInizioCorso_intArray[i]<8))
                                      {
                                          raccOraInizioCorso.setBackground(Color.yellow);
                                      }                                    
                               }
                           
                       }
                       else {err = true; raccOraInizioCorso.setBackground(Color.red);
                       num_errors++; 
                       errors[i] = "Errore ore inizio corso al pannello n° " + Integer.toString(i);}
                       
                       
               }
               
               
           } else
           {
               raccOraInizioCorso.setBackground(Color.red);
               num_errors++;
           }
           
        }
      
        no_error = num_errors == 0;
     
     
     return no_error;
    }
    
    private void makeResults() {
        boolean okLessons;
        
        okLessons = true;        
        
        for (int i=0; i < num_corsi_inseriti; i++)
        {
            boolean isMakeLessons;
            isMakeLessons = makeLessons(i);
            if (isMakeLessons == false) okLessons = false;          
            
        }
        
       
        if (okLessons) 
        {
            optimizeAlgorithm();
            showResults();
        }
        else showErrors();
        
    }
    
    public void makeNewResults(FinestraConfigurazione fc)
    {   finestraconf = fc;
        if (finestraconf == null) System.out.println("Finestra Conf è null");
        resetElaborationData();
        if (catchComponents()) makeResults();
    }
    
    private boolean makeLessons(int forCourse) {
        boolean isMade, routineDayToExclude, listDayToExclude;
        int n_course = forCourse;
        int n_lessons;
        float n_lessonsR;
        float c;
        
        
        List<Date> lessons_show;
        List<Date> lessons_end;
        MutableDateTime lesson_calc;
        MutableDateTime lesson_calc_end;
        
        lesson_calc = new MutableDateTime();
        lesson_calc_end = new MutableDateTime();
        
        lessons_show = new ArrayList<>();
        lessons_end = new ArrayList<>();
        
        
        
        
         isMade = true;
        
        
        n_lessons = oreTotCorso_intArray[n_course] / orePerLezione_intArray[n_course];
        n_lessonsR =(float) oreTotCorso_intArray[n_course] / orePerLezione_intArray[n_course];
        c = (float) n_lessonsR - n_lessons;
        if (c >= 0.5f) n_lessons++; 
        //approx per excess avoid overlap
        //System.out.println(n_lessonsR + " <-->" + n_lessons);
        if (n_course == 0)
        { 
            lesson_calc.setDate(dateInizioCorso_raw.get(n_course).getTime());
            lesson_calc.setHourOfDay(hHInizioCorso_intArray[n_course]);
            lesson_calc.setMinuteOfHour(mMInizioCorso_intArray[n_course]);
            lesson_calc.setSecondOfMinute(0);
            
           
            
            for (int i=1; i<=n_lessons; i++)
            {
                 
                if (i == 1)
                {
                    // inserimento prima lezione del primo corso
                    boolean overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc = moveOverClousureTime(lesson_calc.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     }
                    
                routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course); 
                
                    while (routineDayToExclude)
                    {
                     lesson_calc.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course);           
                    }
            
                  listDayToExclude = isListDayToExclude(lesson_calc.toDate(), n_course);
                    while (listDayToExclude)
                    {
                      lesson_calc.addDays(1);
                      listDayToExclude = isListDayToExclude(lesson_calc.toDate(),n_course);                      
                    }
                    if (assignRoom(lesson_calc, n_course))
                    {
                        lesson_calc = postAssignment(lesson_calc.toDate(), n_course);
                    }
                    
                    lessons_show.add(lesson_calc.toDate()); // carica il vettore delle lezioni
                    
                    lesson_calc_end.setDate(lesson_calc);
                    lesson_calc_end.setTime(lesson_calc);
                    lesson_calc_end.setSecondOfMinute(0);
                    
                    lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                    // testa che la data finale non vada fuori orario
                    overClousureTime = this.isOverClousureTime(lesson_calc_end.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc_end = moveOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         
                         lesson_calc.setDate(lesson_calc_end);
                         lesson_calc.setHourOfDay(lesson_calc_end.getHourOfDay());
                         lesson_calc.setMinuteOfHour(lesson_calc_end.getMinuteOfHour());
                         lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                         lessons_show.set(lessons_show.size()-1, 
                                 lesson_calc.toDate()); // sostituisci l'ultima data inserita
                     }
                    lessons_end.add(lesson_calc_end.toDate());
                    
                    
                  
                } else if (i > 1)
                {
                    lesson_calc.addDays(distanceDays_array[n_course]);
                    boolean overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc = moveOverClousureTime(lesson_calc.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     }
                    routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course); 
                    while (routineDayToExclude)
                    {
                     lesson_calc.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course);           
                    }
            
                  listDayToExclude = isListDayToExclude(lesson_calc.toDate(), n_course);
                    while (listDayToExclude)
                    {
                      lesson_calc.addDays(1);
                      listDayToExclude = isListDayToExclude(lesson_calc.toDate(),n_course);                      
                    }
                    
                    if (assignRoom(lesson_calc, n_course))
                    {
                        lesson_calc = postAssignment(lesson_calc.toDate(),n_course);
                    }
                    lessons_show.add(lesson_calc.toDate()); // carica il vettore delle lezioni
                    
                    lesson_calc_end.setDate(lesson_calc);
                    lesson_calc_end.setTime(lesson_calc);
                    lesson_calc_end.setSecondOfMinute(0);
                    
                    lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                    // testa che la data finale non vada fuori orario
                    overClousureTime = this.isOverClousureTime(lesson_calc_end.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc_end = moveOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         
                         lesson_calc.setDate(lesson_calc_end);
                         lesson_calc.setHourOfDay(lesson_calc_end.getHourOfDay());
                         lesson_calc.setMinuteOfHour(lesson_calc_end.getMinuteOfHour());
                         lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                         lessons_show.set(lessons_show.size()-1, 
                                 lesson_calc.toDate()); // sostituisci l'ultima data inserita
                     }
                    lessons_end.add(lesson_calc_end.toDate());
                   
                    
                }
                
                    
            }
           lessonsDates.put(n_course, lessons_show);
           end_lessonsDates.put(n_course, lessons_end);
           
           
                   
        }
        
        if (n_course > 0)
        {
            // Bisogna verificare l'overlap
           
            
            lesson_calc.setDate(dateInizioCorso_raw.get(n_course).getTime());
            lesson_calc.setHourOfDay(hHInizioCorso_intArray[n_course]);
            lesson_calc.setMinuteOfHour(mMInizioCorso_intArray[n_course]);
            lesson_calc.setSecondOfMinute(0);
            
            for (int i=1; i<=n_lessons; i++)
            { // 
                 if (i == 1)
                {
                     // inserimento prima lezione del primo corso
                     boolean overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc = moveOverClousureTime(lesson_calc.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     }
                     
                routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course); 
                
                    while (routineDayToExclude)
                    {
                     lesson_calc.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course);           
                    }
            
                  listDayToExclude = isListDayToExclude(lesson_calc.toDate(), n_course);
                    while (listDayToExclude)
                    {
                      lesson_calc.addDays(1);
                      listDayToExclude = isListDayToExclude(lesson_calc.toDate(),n_course);                      
                    }
                    
                    if (assignRoom(lesson_calc, n_course))
                    {
                       lesson_calc = postAssignment(lesson_calc.toDate(),n_course);
                    }
                    
                   
                     
                    lessons_show.add(lesson_calc.toDate()); // carica il vettore delle lezioni
                    
                    lesson_calc_end.setDate(lesson_calc);
                    lesson_calc_end.setTime(lesson_calc);
                    lesson_calc_end.setSecondOfMinute(0);
                    
                    lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                    
                    // testa che la data finale non vada fuori orario
                    overClousureTime = this.isOverClousureTime(lesson_calc_end.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc_end = moveOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         
                         lesson_calc.setDate(lesson_calc_end);
                         lesson_calc.setHourOfDay(lesson_calc_end.getHourOfDay());
                         lesson_calc.setMinuteOfHour(lesson_calc_end.getMinuteOfHour());
                         lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                         lessons_show.set(lessons_show.size()-1, 
                                 lesson_calc.toDate()); // sostituisci l'ultima data inserita
                     }
                    
                    lessons_end.add(lesson_calc_end.toDate());
                   
                    
                  
                } else if (i > 1)
                {
                    lesson_calc.addDays(distanceDays_array[n_course]);
                    
                    boolean overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc = moveOverClousureTime(lesson_calc.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(lesson_calc.toDate(), n_course);
                     }
                    routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course); 
                    while (routineDayToExclude)
                    {
                     lesson_calc.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(lesson_calc.toDate(), n_course);           
                    }
            
                  listDayToExclude = isListDayToExclude(lesson_calc.toDate(), n_course);
                    while (listDayToExclude)
                    {
                      lesson_calc.addDays(1);
                      listDayToExclude = isListDayToExclude(lesson_calc.toDate(),n_course);                      
                    }
                    
                    if (assignRoom(lesson_calc, n_course))
                    {
                       lesson_calc = postAssignment(lesson_calc.toDate(),n_course);
                    }
                    
                    
                    
                    lessons_show.add(lesson_calc.toDate()); // carica il vettore delle lezioni
                    
                    lesson_calc_end.setDate(lesson_calc);
                    lesson_calc_end.setTime(lesson_calc);
                    lesson_calc_end.setSecondOfMinute(0);
                    
                    lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                    // testa che la data finale non vada fuori orario
                    overClousureTime = this.isOverClousureTime(lesson_calc_end.toDate(), n_course);
                     
                     while (overClousureTime)
                     {
                         lesson_calc_end = moveOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         overClousureTime = this.isOverClousureTime(
                                 lesson_calc_end.toDate(), n_course);
                         
                         lesson_calc.setDate(lesson_calc_end);
                         lesson_calc.setHourOfDay(lesson_calc_end.getHourOfDay());
                         lesson_calc.setMinuteOfHour(lesson_calc_end.getMinuteOfHour());
                         lesson_calc_end.addHours(orePerLezione_intArray[n_course]);
                         System.out.println(lesson_calc.toDate().toString());
                         lessons_show.set(lessons_show.size()-1, 
                         lesson_calc.toDate()); // sostituisci l'ultima data inserita
                     }
                     
                    lessons_end.add(lesson_calc_end.toDate());
                    
                    
                }
            
            }
           lessonsDates.put(n_course, lessons_show);
           end_lessonsDates.put(n_course, lessons_end);
        }
        
        return isMade;
    }
    
     

    private void showErrors() {
      System.out.println("Errors: " + num_errors);          
    
    }
    
    /*
    * METODI PER IL CONTROLLO E L'ORDINAMENTO DELLE CLASSI
    */
    
    
    
     private boolean isRoutineDayToExclude(Date ex, int index)
    {
        /*
        * Il seguente metodo restituisce un booleano vero se la data inserita
        * è da escludere
        */
        Date pr; // La data deve essere convertita in stringa
       
        String substr;
        pr = ex;
        int i = index;
        boolean isok;
        isok = false;
        substr = pr.toString().substring(0,3);
        
         // Mon Tue Wed Thu Fri Sat Sun
        
        switch (substr)
        {
            case "Mon" : 
                if (lun_array[i]) isok = true; break;
            case "Tue" : 
                if (mar_array[i]) isok = true; break;
            case "Wed" : 
                if (mer_array[i]) isok = true; break;
            case "Thu" : 
                if (gio_array[i]) isok = true; break;
            case "Fri" : 
                if (ven_array[i]) isok = true; break;
            case "Sat" :     
                if (sab_array[i]) isok = true; break;
            case "Sun" : 
                if (dom_array[i]) isok = true; break;
            default : break;    
            
        }
        
        return isok;
        
    }
    
     /*
     * isListDayToExclude ritorna vero se è nella lista
     */
     private boolean isListDayToExclude(Date toDate, int i) {
       boolean isOnList = false;
       MutableDateTime dateToTest = new MutableDateTime(toDate);
       dateToTest.setHourOfDay(0);
       dateToTest.setMinuteOfHour(0);
       
       
       if ((this.exist_finestraconf) && (!isEmptyListToExclude))
       {
          for (int k=0; k<datesToExclude.size(); k++)
          { String str1;
            String str2;
            str1 = dateToTest.toDate().toString();
            str2 = datesToExclude.get(k).toString();
            str1 = str1.trim();
            str2 = str2.trim();          
           
           if (str1.equals(str2))
           {               
               isOnList = true;
           }
         
          }
          
           
       }
       
       
       return isOnList;
    }

  
    
    private boolean isOverClousureTime(Date toDate, int forCourse)
    {
       boolean isOver;
       
       
       isOver = false;
       MutableDateTime move = new MutableDateTime(toDate);
       MutableDateTime myTest = new MutableDateTime(toDate);
       finestra.hhEndLessons_algorithm = finestra.hhEndLessons + 3;
       if (finestra.hhEndLessons_algorithm > 23) finestra.hhEndLessons_algorithm = 23;
       myTest.setHourOfDay(finestra.hhEndLessons_algorithm);
       myTest.setMinuteOfHour(finestra.mmEndLessons);   
       
       int moveIsBig = move.getMinuteOfDay();
       
       int testIsBig = myTest.getMinuteOfDay();
       
       if (moveIsBig >= testIsBig) isOver = true;    
       
       return isOver;
       
    }
    
    private MutableDateTime moveOverClousureTime(Date toDate, int forCourse)
    {
        MutableDateTime move = new MutableDateTime(toDate);
        move.setHourOfDay(finestra.hhStartLessons);
        move.setMinuteOfHour(finestra.mmStartLessons);        
        move.addWeeks(1);     
        boolean routineDayToExclude = isRoutineDayToExclude(move.toDate(), forCourse); 
                    while (routineDayToExclude)
                    {
                     move.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(move.toDate(), forCourse);           
                    }
            
       boolean  listDayToExclude = isListDayToExclude(move.toDate(), forCourse);
                    while (listDayToExclude)
                    {
                      move.addDays(1);
                      listDayToExclude = isListDayToExclude(move.toDate(),forCourse);                      
                    }
                    
           
        
        return move;
    }
     
     
    private boolean isOverlapLessonsActual(Date toDate, int forCourse) {
        boolean isOverlap = false;        
        
        /*
        * Questo test viene avviato solo se non è vuoto l'insieme delle associazioni tra corsi e aule
        */
        
        if (!this.course_rooms.isEmpty())
        {
           //int  aula = course_rooms.get(forCourse - 1 ); // ottieni l'aula per il corso precedente
           
           MutableDateTime dateToTest = new MutableDateTime(toDate);           
           List<Date> listDateToTest;
           List<Date> listDateToTest_end;
           listDateToTest = lessonsDates.get(forCourse);
           listDateToTest_end = end_lessonsDates.get(forCourse);
           
           int minutesDateToTest = dateToTest.getMinuteOfDay();
           
           for (int i=0; i<listDateToTest.size();i++)
           {
               MutableDateTime datePrec = new MutableDateTime(listDateToTest.get(i));
               MutableDateTime datePrec_end = new MutableDateTime(listDateToTest_end.get(i));
               
               if (
                  (datePrec.getDayOfMonth() == dateToTest.getDayOfMonth()) &&
                  (datePrec.getYear() == dateToTest.getYear()) &&
                  (datePrec.getMonthOfYear() == dateToTest.getMonthOfYear())
                  )     
                       {
                           
                           int lim_inf = datePrec.getMinuteOfDay();
                           int lim_sup = datePrec_end.getMinuteOfDay();
                           boolean half_test = false;
                           boolean one_test = false;
                          /*
                           if (minutesDateToTest >= lim_inf){
                               half_test = true;
                                   if  (minutesDateToTest <= lim_sup)
                                     {                                      
                                     one_test = true; 
                                     }
                                   }
                           
                           isOverlap = (one_test) && (half_test); */
                           
                           if ((minutesDateToTest >= lim_inf) && (minutesDateToTest <= lim_sup)) isOverlap = true;
                           else if ((minutesDateToTest <= lim_inf) || (minutesDateToTest >= lim_sup)) isOverlap = false;
                           if ((minutesDateToTest <= lim_inf) && (minutesDateToTest <= lim_sup)) isOverlap = true;
                       }
           }
        }
        
        return isOverlap;
    } 

    private MutableDateTime moveOverlapLessons(Date toDate, int forCourse) {
        MutableDateTime new_lesson;        
        new_lesson = new MutableDateTime(toDate);
        new_lesson.addMinutes(5);
        while (this.isOverClousureTime(new_lesson.toDate(), forCourse))
        {
            new_lesson = moveOverClousureTime(new_lesson.toDate(), forCourse);
        }
        
        boolean routineDayToExclude = isRoutineDayToExclude(new_lesson.toDate(), forCourse); 
                    while (routineDayToExclude)
                    {
                     new_lesson.addDays(1);   
                     routineDayToExclude = isRoutineDayToExclude(new_lesson.toDate(), forCourse);           
                    }
            
       boolean  listDayToExclude = isListDayToExclude(new_lesson.toDate(), forCourse);
                    while (listDayToExclude)
                    {
                      new_lesson.addDays(1);
                      listDayToExclude = isListDayToExclude(new_lesson.toDate(),forCourse);                      
                    }
        
        return new_lesson;
        
    }
    private boolean assignRoom(MutableDateTime lesson_calc, int n_course) {
     boolean assigned;
     assigned = false;
     isAvaiableRoom = true;
     
    
     MutableDateTime lesson_calc_end_room;
     List<Date> lessonsToTest = new ArrayList<>();
     List<Date> lessonsToTest_end = new ArrayList<>();
     
     
     /*
         * Fai l'assegnamento e metti i corsi in tutte le aule disponibili chiudi quando 
         * tutte le aule disponibili sono state occupate
         */
     if (!closeAssignment)
     {
         if ((n_course == 0) && (this.num_corsi_inseriti == 1))
         {
       /*
             * primo corso l'assegnamento è semplicemente
             */
            num_rooms--;
            num_rooms_ins++;
            course_rooms.put(n_course,num_rooms_ins); 
            assigned = true;
            course_assignment[n_course] = true;
            closeAssignment = true;
         }
         
         if ((n_course >= 0) && (this.num_corsi_inseriti > 1))
         {
           if ((num_rooms > 0) && (course_rooms.get(n_course) == null))
           {
               num_rooms--;
               num_rooms_ins++;
               course_rooms.put(n_course,num_rooms_ins);    
               assigned = true;
               course_assignment[n_course] = true;
               
           } else if (num_rooms <= 0)
                   {
                       if (course_assignment[n_course]) course_assignment[n_course] = true;
                       else
                       {
                       course_assignment[n_course] = false;
                       }
                       closeAssignment = true;
                   }
           
         }
         
     }
       
     // Qui abbiamo il vettore che conosce sempre se un corso è stato assegnato oppure no
     
     return closeAssignment;
    }
    
   
    /*
    * UTILITIES PER LE DATE
    */
    private String uiSimpleConvertDate(String str)
    {
        /**
         * Semplice funzione che converte le date
         * in listaDate abbiamo la string non convertita nello standard locale.US
         * in listaDateconv abbiamo le date convertite in modo da farle apparire leggibili
         */
        Date dateconv;
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US);
        
        try{
        dateconv = (Date)formatter.parse(str);
                        //System.out.println(dateconv);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateconv);
                        String formatedDate = cal.get(Calendar.DATE) + "/" +
                                (cal.get(Calendar.MONTH) + 1) + "/" + 
                                cal.get(Calendar.YEAR);
                        
                        str = formatedDate;
                        
                        
                        } catch (ParseException e) {
                         
                         Component fr = null;
                         JOptionPane.showMessageDialog(fr,
                         e.getLocalizedMessage(),
                         "Configurazione",
                         JOptionPane.ERROR_MESSAGE);
		
                        }
        return str;
    }
    
    
 

    private MutableDateTime postAssignment(Date toDate, int n_course) {
      MutableDateTime lesson_calc = new MutableDateTime (toDate);
      if ((course_assignment[n_course]== false) && (num_rooms_ins != 0))
      {
         num_rooms++;
         num_rooms_ins--;         
         course_assignment[n_course] = true;
         course_rooms.put(n_course, num_rooms);
         
         /*
         * Cerca in course_rooms l'aula assegnata e ricava il num_di corso da testare
         * Devo sostituire int n_courseToTest con un array o una lista in modo 
         * che si possano controllare in sequenza anche più di un corso per aula
         */
         int n_courseToTest = 0; 
        
         
         for (int i=0; i<n_course;i++)
         {
             if (course_rooms.get(i) == num_rooms)
             {
                 n_courseToTest = i; 
                 while (isOverlapLessonsActual(lesson_calc.toDate(), n_courseToTest)) {
                  lesson_calc = moveOverlapLessons(lesson_calc.toDate(), n_course);
              }
               
             }
         } 
         
       }
      if ((course_assignment[n_course]== false) && (num_rooms != 0))
      {
         num_rooms--;
         num_rooms_ins++;         
         course_assignment[n_course] = true;
         course_rooms.put(n_course, num_rooms_ins);
         
         /*
         * Cerca in course_rooms l'aula assegnata e ricava il num_di corso da testare
         */
         int n_courseToTest = 0;
         
         for (int i=0; i<n_course;i++)
         {
             if (course_rooms.get(i) == num_rooms_ins)
             {
                 n_courseToTest = i;  
                 while (isOverlapLessonsActual(lesson_calc.toDate(), n_courseToTest)) {
                  lesson_calc = moveOverlapLessons(lesson_calc.toDate(), n_course);
              }
                 
             }
         }
         
         
      }
      
      return lesson_calc;
    }

  private void showResults() {
       
       DateTimeFormatter fmt = DateTimeFormat.forPattern("E");
     
       System.out.println("Show Results:");
       StringBuilder resultsForRooms, resultsStringBuilder_medium, 
       resultsStringBuilder_expanse,
               resultsSummaryStringBuilder;
       
       resultsStringBuilder_medium = new StringBuilder();
       resultsStringBuilder_expanse = new StringBuilder();
       resultsSummaryStringBuilder = new StringBuilder();
       resultsForRooms = new StringBuilder();
       List <Date> dateToShow;
       List <Date> dateToShow_end;
       List <Integer> aule_ore = new ArrayList<>(); // Ricordare che l'indice 0 dell'aula corrisponde all'aula 1
       Translator.init_str_ResultsJFrame(); // Anche qui
       //int num_rooms_topass;
       
       if (resultsJFrame == null) 
       {
           resultsJFrame = new ResultsJFrame();
           // passa la referenza alla finestra iniziale
           finestra.resultsJFrame = resultsJFrame;
           resultsJFrame.setVisible(true);
       } else
       {
           resultsJFrame.setVisible(true);
       }
       StringBuilder mmSchool = new StringBuilder();
           StringBuilder mmSchool_end = new StringBuilder();
           if (finestra.mmStartLessons< 9) mmSchool.append("0")
                                            .append(finestra.mmStartLessons);
           else mmSchool.append(finestra.mmStartLessons);
           if (finestra.mmEndLessons< 9) mmSchool_end.append("0")
                                         .append(finestra.mmEndLessons);
           
           resultsStringBuilder_medium.append("\n")
                   .append(Translator.str_ResultsJFrame("I corsi sono dalle: "))
                   .append(finestra.hhStartLessons)
                   .append(":")
                   .append(mmSchool).append(" ")
                   .append(Translator.str_ResultsJFrame("alle:")).append(" ")                                      
                   .append(finestra.hhEndLessons)
                   .append(":")
                   .append(mmSchool_end);
       
       for (int i=0; i< num_corsi_inseriti; i++)
       {   
           
                                      
           resultsStringBuilder_medium.append("\n ---------------------------");
           resultsSummaryStringBuilder.append("\n ---------------------------");
           resultsStringBuilder_expanse.append("\n ---------------------------");
          
           resultsStringBuilder_medium.append("\n").
                   append(Translator.str_ResultsJFrame("Corso:")).
                   append(nomiCorsi.get(i))
                               .append("\n").
                   append(Translator.str_ResultsJFrame("Aula:")).
                   append(course_rooms.get(i))
                               .append("\n").
                   append(Translator.str_ResultsJFrame("Ore per lezione:"))
                               .append(this.orePerLezione_intArray[i])
                               .append("\n").
                   append(Translator.str_ResultsJFrame("Il corso si tiene ogni:")).
                   append(" ")
                               .append(this.distanceDays_array[i]).append(" ")
                               .append(Translator.str_ResultsJFrame("giorni/o"));
           resultsStringBuilder_expanse.append("\n")
                   .append(Translator.str_ResultsJFrame("Corso:"))
                   .append(nomiCorsi.get(i))
                   .append("\n")
                   .append(Translator.str_ResultsJFrame("Aula:"))
                   .append(course_rooms.get(i))
                   .append("\n")
                   .append(Translator.str_ResultsJFrame("Ore per lezione:"))
                   .append(this.orePerLezione_intArray[i])
                   .append("\n")
                   .append(Translator.str_ResultsJFrame("Il corso si tiene ogni:"))
                   .append(" ")
                   .append(this.distanceDays_array[i])
                   .append(Translator.str_ResultsJFrame("giorni/o"));
           
           
           resultsSummaryStringBuilder.append("\n")
                   .append(Translator.str_ResultsJFrame("Corso:"))
                   .append(nomiCorsi.get(i))
                   .append("\n")
                   .append(Translator.str_ResultsJFrame("Aula:"))
                   .append(course_rooms.get(i))
                   .append("\n")
                   .append(Translator.str_ResultsJFrame("Ore per Lezione:"))
                   .append(this.orePerLezione_intArray[i]);
           
           
                               
           resultsStringBuilder_expanse.append("\n")
                   .append(Translator.str_ResultsJFrame("Lezioni"));
           dateToShow = lessonsDates.get(i); 
           dateToShow_end = end_lessonsDates.get(i);
           for (int k=0; k< dateToShow.size(); k++)
           {   String convDate;
               Date dateToConv,dateToConv_end;
               StringBuilder mmConv = new StringBuilder();
               StringBuilder mmConv_end = new StringBuilder();
               
               dateToConv = dateToShow.get(k);
               dateToConv_end = dateToShow_end.get(k);
               MutableDateTime dateforTime = new MutableDateTime(dateToConv);
               MutableDateTime dateforTime_end = new MutableDateTime(dateToConv_end);
               
               DateTime dt = new DateTime(dateToConv);
               
               String dateStr = fmt.withLocale(thisLocale).print(dt);
               
               if (dateforTime.getMinuteOfHour() < 9)
               {
                mmConv.append("0");
               }
               mmConv.append(Integer.toString(dateforTime.getMinuteOfHour()));
               if (dateforTime_end.getMinuteOfHour() < 9)
               {
                mmConv_end.append("0");
               }
               mmConv_end.append(Integer.toString(dateforTime_end.getMinuteOfHour()));
               
               convDate = this.uiSimpleConvertDate(dateToConv.toString());
               if (k==0) 
               {
                 resultsStringBuilder_medium.append("\n").append(Translator.str_ResultsJFrame("Il corso inizia:")).
                       append(dateStr).append(" ").append(convDate).append(Translator.str_ResultsJFrame("alle:")).
                       append(dateforTime.getHourOfDay()).append(":").
                       append(mmConv);
                 resultsSummaryStringBuilder.append("\n").
                       append(Translator.str_ResultsJFrame("Il corso inizia:"))
                      .append(" ").
                       append(dateStr).append(" ").append(convDate).append(Translator.str_ResultsJFrame("alle:")).
                       append(dateforTime.getHourOfDay()).append(":").
                       append(mmConv);    
               }
               if ( k==(dateToShow.size() -1) )
               {
                   resultsStringBuilder_medium.append("\n").
                       append(Translator.str_ResultsJFrame("Il corso finisce:")).
                       append(dateStr).append(" ").append(convDate).
                       append(Translator.str_ResultsJFrame("alle:")).
                       append(dateforTime_end.getHourOfDay()).append(":").
                       append(mmConv_end); 
                   resultsSummaryStringBuilder.append("\n").
                       append(Translator.str_ResultsJFrame("Il corso finisce:")).
                       append(dateStr).append(" ").append(convDate).
                       append(Translator.str_ResultsJFrame("alle:")).
                       append(dateforTime_end.getHourOfDay()).append(":").
                       append(mmConv_end);    
               }
               resultsStringBuilder_expanse.append("\n").
                       append(Translator.str_ResultsJFrame("Lezione")).append(" n° ").
                       append(k + 1).
                       append(Translator.str_ResultsJFrame("il:")).append(convDate).
                       append(Translator.str_ResultsJFrame("dalle:")).
                       append(dateforTime.getHourOfDay()).append(":").
                       append(mmConv).
                       append(Translator.str_ResultsJFrame("alle:")).
                       append(dateforTime_end.getHourOfDay()).
                       append(":").
                       append(mmConv_end);               
           }
           
       }
       
       resultsForRooms.
               append(Translator.str_ResultsJFrame("Numero aule disponibili:"));
       
       if (num_rooms_tot == MAX_ROOMS) resultsForRooms.append("tutte").append("\n");
       else resultsForRooms.append(num_rooms_tot);
         int q= 0;
       
       /*
                  *  Calcolo sulle aule.
                  */
          Map<Integer, ArrayList<DateTime>> aule_oretoSort;
          aule_oretoSort = new HashMap<>();
       for (int i=1; i <=num_rooms_tot; i++)
       {
           if ((finestra.isDefault) && (this.num_corsi_inseriti >= i))
           {
           resultsForRooms.append("\n----------------------------------\n");
           resultsForRooms.append("In aula:").append(i).append(" \ncorso/i:");
           } else if (finestra.isDefault == false)
           {
               resultsForRooms.append("\n----------------------------------\n");
               resultsForRooms.append("In aula:").append(i).append(" \ncorso/i:");
           }
           
         
         
          ArrayList<DateTime> roomsDate = new ArrayList<>();
           
           
           for (int k=0; k<num_corsi_inseriti; k++)
           {
               if (course_rooms.get(k) == i)
               {   List<Date> lessonsToShow;
                   List<Date> lessonsToStart;
                   lessonsToShow = end_lessonsDates.get(k); 
                   lessonsToStart = lessonsDates.get(k);               
                   
                   DateTime dt, dt_end;
                   
                   dt_end = new DateTime(lessonsToShow.get(lessonsToShow.size() -1));
                   dt = new DateTime(lessonsToStart.get(0));
                   roomsDate.add(dt);
                   roomsDate.add(dt_end);
                   aule_oretoSort.put(i, roomsDate);
                   
                 
                   
                      
                    //int addhour = (dt_end.getHourOfDay() - dt.getHourOfDay()) + aule_ore.get(i-1);
                    //aule_ore.set(i-1, addhour);   
                    
                    
                    
                    
                   
                   
                   StringBuilder dt_endString = new StringBuilder();
                   StringBuilder dt_String = new StringBuilder();                   
                   if (dt_end.getMinuteOfHour() < 9)
                   {
                       dt_endString.append("0");
                   }
                   if (dt.getMinuteOfHour() < 9)
                   {
                       dt_String.append("0");
                   }
                   dt_endString.append(dt_end.getMinuteOfHour());
                   dt_String.append(dt.getMinuteOfHour());
                   resultsForRooms.append("\n").append(nomiCorsi.get(k)).append(" Inizia: ")
                           .append(dateInizioCorso_ui.get(k))
                           .append(" Finisce: ")
                           .append(uiSimpleConvertDate(dt_end.toDate().toString()))
                           .append(" Orario: ")
                           .append(dt.getHourOfDay())
                           .append(":")
                           .append(dt_String)
                           .append("-")
                           .append(dt_end.getHourOfDay())
                           .append(":")
                           .append(dt_endString);
                                  
               }
           }
          
           
       }
        
                   
      /*
         Provo a convertire in aule_ore
          */
       for (int i=1; i <=num_rooms_tot;i++)
       {
           ArrayList<DateTime> dateToConv;
           int ore = 0;
           try
           {    
           dateToConv = aule_oretoSort.get(i);
           DateTime dt = dateToConv.get(0);
           DateTime dt_max = dateToConv.get(dateToConv.size()-1);
           ore = dt_max.getHourOfDay() - dt.getHourOfDay();    
           Collections.sort(dateToConv);
           }
           catch
           (Exception e)
           {
               
           }
           
           aule_ore.add(ore);
       }
       
     // System.out.println("\n Date che devono essere escluse: " + datesToExclude.toString()); 
       List<String> infoRoomData = new ArrayList<>();
       sendAvaiableRoomData(infoRoomData, aule_ore);
       resultsStringBuilder_medium.insert(0, infoRoomData.get(0));
       resultsStringBuilder_medium.insert(0, infoRoomData.get(1));
       resultsStringBuilder_expanse.insert(0, infoRoomData.get(0));
       resultsStringBuilder_expanse.insert(0, infoRoomData.get(1));
      
       this.resultsJFrame.setMediumResults(resultsStringBuilder_medium.toString());
       this.resultsJFrame.setExpanseResults(resultsStringBuilder_expanse.toString());
       this.resultsJFrame.setLittleResults(resultsSummaryStringBuilder.toString());
       this.resultsJFrame.setSlidertoStart();
       this.resultsJFrame.setResults(resultsSummaryStringBuilder.toString());
       this.resultsJFrame.setResultsForRooms(resultsForRooms.toString());
       
    }

    private void optimizeAlgorithm() {
        /*
               Ottimizza i risultati in base alle aule disponibili        
              */
       
    }

    private void sendAvaiableRoomData(List<String> infoRoomData, List<Integer> aule_ore) {
        List<Integer> roomsData;
        List<Long> maxDates_inNumber;
        List<Long> minDates_inNumber;
        List<Long> tempDatesStart_inN;
        List<Long> tempDatesEnd_inN;
        List<Integer> tempDatesEnd_inMinutes;
        Long dateLittle_start;
        Long dateBig_end;
        Long diffInMills;
        MutableDateTime endData = new MutableDateTime(); // la data più grande dei corsi
        MutableDateTime firstData = new MutableDateTime(); // la data più piccola dei corsi
        roomsData = new ArrayList<>();
        tempDatesStart_inN = new ArrayList<>();
        tempDatesEnd_inN = new ArrayList<>();
        minDates_inNumber = new ArrayList<>();
        maxDates_inNumber = new ArrayList<>();
        
        /* prendi tutte le lezioni per aula dall' inizio alla fine */
        /* valuta i minuti liberi e quelli non liberi */
        /* Calcolo dell'anno scolastico */
        
        for (int k=0; k<num_corsi_inseriti;k++)
        {
            //* Trovami la data iniziale più alta e tra quella più alta calcola la finale questo rappresenterà l'anno
            List<Date> lessonsToFind_start = this.lessonsDates.get(k);
            List<Date> lessonsToFind_end = this.end_lessonsDates.get(k);
            
            for (Date lessonsToFind_start1 : lessonsToFind_start) {
                MutableDateTime dateToTransform = new MutableDateTime(lessonsToFind_start1);
                tempDatesStart_inN.add(dateToTransform.getMillis());               
            }
            for (Date lessonsToFind_end1 : lessonsToFind_end) {
                MutableDateTime dateToTransform = new MutableDateTime(lessonsToFind_end1);
                tempDatesEnd_inN.add(dateToTransform.getMillis());               
            }
            
            Collections.sort(tempDatesStart_inN);
            Collections.sort(tempDatesEnd_inN);
            minDates_inNumber.add(tempDatesStart_inN.get(0)); 
            maxDates_inNumber.add(tempDatesEnd_inN.get(tempDatesEnd_inN.size() - 1));            
        }
        Collections.sort(minDates_inNumber);
        Collections.sort(maxDates_inNumber);
        // Qui abbiamo la data più piccola e la data più grande
        dateLittle_start = minDates_inNumber.get(0); // data iniziale
        dateBig_end = maxDates_inNumber.get(minDates_inNumber.size() - 1); // data finale 
        endData.setMillis(dateBig_end);
        firstData.setMillis(dateLittle_start);
        diffInMills = dateBig_end - dateLittle_start;        
        

        // calc will equal end
        //DateTime calc = firstData.plus(period);

       // able to calculate whole days between two dates easily
        
       // Days days = Days.daysBetween(firstData, endData);

        // able to calculate whole months between two dates easily
        Months months = Months.monthsBetween(firstData, endData); // calcola i mesi della scuola
        
        //Hours hours = Hours.hoursBetween(firstData, endData);
        
        //Minutes minutes = Minutes.minutesBetween(firstData, endData);
        int m = monthsToInt(months);
        //int h = hoursToInt(hours); // ore totali 
        //int ms = minutesToInt(minutes); // minuti totali
        
        infoRoomData.add("La scuola inizia: "+ uiSimpleConvertDate(firstData.toDate().toString()) + 
                " finisce:" + uiSimpleConvertDate(endData.toDate().toString()) +"\n");
        
        infoRoomData.add("La durata totale della scuola è di mesi: " + m +"\n");
        
        List<Integer> HoursTotinRooms = new ArrayList<>();
        
        MutableDateTime startLessons = new MutableDateTime();
        MutableDateTime endLessons = new MutableDateTime();
        startLessons.setHourOfDay(finestra.hhStartLessons);
        startLessons.setMinuteOfHour(finestra.mmStartLessons);
        endLessons.setHourOfDay(finestra.hhEndLessons);
        endLessons.setMinuteOfHour(finestra.mmEndLessons);            
        Hours hoursTotal = Hours.hoursBetween(startLessons, endLessons);
       
        int hoursTot_int = hoursToInt(hoursTotal);
        
        System.out.println("Numeri aule totali: " + num_rooms_tot);
        for (int i = 0; i< num_rooms_tot;i++)
        {  
            HoursTotinRooms.add(hoursTot_int);
        }        
        
        System.out.println(aule_ore.size());
        for (int i=0; i<aule_ore.size();i++)
        {
            
                
            int percent = (100*aule_ore.get(i)) / hoursTot_int;
            
            roomsData.add(percent);
        }
        
        
        
        //System.out.println("Percentuali utilizzo aule: " + roomsData );
            
           
        
        
        
        
        this.resultsJFrame.setValuesRooms(roomsData);
    }

    private int monthsToInt(Months months) {
        int r_months = 0;
        StringBuilder monthsToConv = new StringBuilder();
        monthsToConv.append(months.toString());
        for (int i=0; i < monthsToConv.length(); i++)
        {
            if ((monthsToConv.charAt(i) == 'P') || (monthsToConv.charAt(i) == 'M'))
            {
                monthsToConv.deleteCharAt(i);
            }
        }
        
        try
        {
           r_months = Integer.parseInt(monthsToConv.toString());          
        }
        catch(Exception e)
        {
            
        }
        
        
        
        return r_months;
        
    }

    private int hoursToInt(Hours hours) {
        int r_hours = 0;        
        StringBuilder hoursToConv = new StringBuilder();
        hoursToConv.append(hours.toString());
        for (int i=0; i <= hoursToConv.length(); i++)
        {
            if ((hoursToConv.charAt(i) == 'P') || (hoursToConv.charAt(i) == 'H')  )
            {                
                hoursToConv.deleteCharAt(i);                
            }
        }
        hoursToConv.deleteCharAt(0); // elminate T       
         
        try
        {
           r_hours = Integer.parseInt(hoursToConv.toString());   
      
        }
        catch(Exception e)
        {           
           
        }
        return r_hours;
    }

    private int minutesToInt(Minutes minutes) {
       int m_minutes = 0;      
        StringBuilder minutesToConv = new StringBuilder();
        minutesToConv.append(minutes.toString());
        for (int i=0; i < minutesToConv.length(); i++)
        {
            if ((minutesToConv.charAt(i) == 'P') || (minutesToConv.charAt(i) == 'T') 
                    || (minutesToConv.charAt(i) == 'M')  )
            {
                minutesToConv.deleteCharAt(i);
            }
        }
        
        try
        {
           m_minutes = Integer.parseInt(minutesToConv.toString());          
        }
        catch(Exception e)
        {
          
        }
        
       return m_minutes;
    }

   

    

    

    
}
