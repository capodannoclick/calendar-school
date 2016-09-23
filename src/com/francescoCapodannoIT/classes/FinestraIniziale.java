/*
 * Francesco Capodanno (C)
 * Questa classe fa apparire la GUI della nostra applicazione
 */
package com.francescoCapodannoIT.classes;


import com.francescoCapodannoIT.classes.HarburTipps.Route;
import com.francescoCapodannoIT.classes.tippspanels.TippPanel;
import com.francescoCapodannoIT.classes.tippspanels.Tippable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.swingx.JXDatePicker;


/**
 *
 * @author Francesco Capodanno
 */

public final class FinestraIniziale extends JFrame {
     
/*
 * Costanti
 */
   private static final int MAX_ROOMS  = 50;

    /**
     * MAX Courses for FinestraIniziale
     */
    public static int MAX_COURSES = 16;
   
   public Locale thisLocale;
// componenti iniziali della GUI 
    MainPanel mainContainer;
    JScrollPane scrollFrame;
    boolean isCreated;
    
    Elaboration elaboratoreDati; // classe che elabora tutti i dati per la programmazione
    FinestraConfigurazione finestraconf;
    SetUpGUI theGUI;
    
    
    public Tippable Tipp;
    

    /**
     *Finestra Configurazione  passa il valore true dopo che è stata configurata
     */
    public boolean isConfigured;
    public boolean isDefault;
    public boolean isSetUpGui;
    
    ArrayList oggettiGUI;
    
    /**
     * LABEL PER La traduzione dinamica delle interfacce
     * 
     */
    
    /**
     * Translator.str_SetUpGUI("Nome corso:")
     */
    ArrayList textSetUpGUI_1;
    
   /**
    *Translator.str_SetUpGUI("Data di inizio corso:")
    */
    ArrayList textSetUpGUI_2;
    /**
     * Translator.str_SetUpGUI("Ore totale corso(in ore):")
     */
    ArrayList textSetUpGUI_3;
    
    /**
     * Translator.str_SetUpGUI("Ore per lezione:")
     */
    ArrayList textSetUpGUI_4;
    
    /**
     * Translator.str_SetUpGUI("Frequenza corso:")
     */
    ArrayList textSetUpGUI_5;
    
    /**
     * Translator.str_SetUpGUI("Ora inizio corso(HH:MM):")
     */
    ArrayList textSetUpGUI_6;
    
    /**
     * Translator.str_SetUpGUI("Giorni da escludere:")
     */
    ArrayList textSetUpGUI_7;

    /**
     * Translator.str_SetUpGUI("domenica")
     */
    ArrayList textSetUpGUI_8;

    /**
     * Translator.str_SetUpGUI("sabato")
     */
    ArrayList textSetUpGUI_9;

    /**
     * Translator.str_SetUpGUI("venerdì")
     */
    ArrayList textSetUpGUI_10;

    /**
     * Translator.str_SetUpGUI("giovedì")
     */
    ArrayList textSetUpGUI_11;

    /**
     * Translator.str_SetUpGUI("mercoledì")
     */
    ArrayList textSetUpGUI_12;

    /**
     * Translator.str_SetUpGUI("martedì")
     */
    ArrayList textSetUpGUI_13;

    /**
     * Translator.str_SetUpGUI("lunedì")
     */
    ArrayList textSetUpGUI_14;
    
   

      JMenuBar bar;
      JMenu creaMnu;
      JMenuItem mostraItemMnu;
      JMenuItem configuraItemMnu;
      JMenuItem creaItemMnu;
      JMenuItem cancellaItemMnu;
      JMenuItem saveItemMnu;
      JMenuItem loadItemMnu;
      
      JMenu helpMnu;
      JMenuItem aboutItemMnu;
      JMenu languageMnu;
      JMenuItem language_itItemMnu;   
      JMenuItem language_enItemMnu;
      JMenuItem language_deItemMnu;
      
      
    /**
     *Ora di chiusura della scuola
     */
    public int hhEndLessons; 
    public int hhEndLessons_algorithm; // ora di chiusura + margine protettivo.

    /**
     * Ora di chiusura della scuola in cui non si praticano lezioni
     */
    public int mmEndLessons;
    
    /*
    *Ora di inizio della scuola
    */
    public int hhStartLessons;
    
    /*
    *Ora di inizio della scuola
    */
    public int mmStartLessons;
    
    public String hhStartLessonsString;
    public String mmStartLessonsString;
    public int getMininumHours;
    public int oreLezMaxPerDay_oneRoom;
    private int oreLezMaxPerDay;
    private int hoursOveread;
 
  public ResultsJFrame resultsJFrame;
  public FinestraIniziale finestra_this;

    /**
     *This class save the project
     */
    public SaveAll saveAll;
    public LoadAll loadAll;

    /**
     *the class SetUpGUI set isToSave to true if there is any modifications to the panels instantiated. 
     */
    public boolean isToSave;
  public FinestraIniziale() throws HeadlessException {                    
            super("One Year School Schedule Scenario v. 0.1");
        
            
                    /*Inizializzazione degli elementi fondamentali della finestra*/
                    bodyCostructor_init();
                    
                    /*Suggerimento nella finestra Iniziale frame*/
                    tipps();
                    
                    
                    /*Carica gli ascoltatori delle azioni*/
                    CaricaAzioni();
                    
        }
  
    /*
        Body Costructor for init 
        */

    /**
     * add tipps to this frame
     */   
   public void tipps(){
        
        Tippable thisTipp1 = new TippPanel(this,
                    Translator.str_FinestraIniziale
        ("Inizia creando una configurazione generale dei corsi"),
                    Route.ONE_ONE);
        
        Tippable thisTipp2 = new TippPanel(this, 
                Translator.str_FinestraIniziale("Inizia creando una configurazione generale dei corsi2"),
        Route.MANY_MANY);
        
    }
    public void bodyCostructor_init(){
    this.textSetUpGUI_14 = new ArrayList<>();
        this.textSetUpGUI_13 = new ArrayList<>();
        this.textSetUpGUI_12 = new ArrayList<>();
        this.textSetUpGUI_11 = new ArrayList<>();
        this.textSetUpGUI_10 = new ArrayList<>();
        this.textSetUpGUI_9 = new ArrayList<>();
        this.textSetUpGUI_8 = new ArrayList<>();
        this.textSetUpGUI_8 = new ArrayList<>();
        this.textSetUpGUI_7 = new ArrayList<>();
        this.textSetUpGUI_6 = new ArrayList<>();
        this.textSetUpGUI_5 = new ArrayList<>();
        this.textSetUpGUI_4 = new ArrayList<>();
        this.textSetUpGUI_3 = new ArrayList<>();
        this.textSetUpGUI_2 = new ArrayList<>();
        this.textSetUpGUI_1 = new ArrayList<>();
        this.finestra_this = this;        
  
        Translator.init_str_FinestraIniziale();
        
        this.hoursOveread = 0;
        this.oreLezMaxPerDay_oneRoom = 0;
        this.oreLezMaxPerDay = 0;
                    getMininumHours = 8;
                    
                    aboutItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("About"));
                    helpMnu = new JMenu(Translator.str_FinestraIniziale("Help ?"));
                    cancellaItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Cancella Tutto"));
                    creaItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Crea"));
                    configuraItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Configura regole generali"));
                    mostraItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Mostra risultato"));
                    saveItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Salva progetto"));
                    loadItemMnu = new JMenuItem(
                            Translator.str_FinestraIniziale("Carica progetto"));
                    creaMnu = new JMenu(
                            Translator.str_FinestraIniziale("Crea/Salva"));
                    languageMnu = new JMenu("Language/Lingua/Sprache");                  
                    language_itItemMnu = new JMenuItem("IT");
                    language_itItemMnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/it.png")));                                        
                    language_enItemMnu = new JMenuItem("EN");
                    language_enItemMnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uk.png")));
                    language_deItemMnu = new JMenuItem("DE");
                    language_deItemMnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/de.png")));
                   
                   
                    bar = new JMenuBar();
                    isDefault = false; // inizializzata a false finchè non viene instanziato il corso
                    isCreated = false; // inizializzato a false  se non è stata creata la programmazione
                    isSetUpGui = false; // inizializzato a false se non è stata ancora creata la gui
                    // container = new MainPanel();
                    //jv1 = new JViewport();
                    oggettiGUI = new ArrayList(); // gli oggetti utili all'elaborazione è una soluzione poco performante ma rende il codice leggibile
                    
                    
                    
                    mainContainer = new MainPanel();
                    // jsp = new JScrollPane(mainContainer);
                    mainContainer.setLayout(new FlowLayout());
                    
                    // add(jsp);
                    //add(mainContainer);
                    //mainContainer.setAutoscrolls(true);
                    scrollFrame = new JScrollPane(mainContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollFrame.setAutoscrolls(true);
                    getContentPane().add(scrollFrame);                    
                    configuraItemMnu.setBorder(null);
                    configuraItemMnu.setBackground(Color.green);
                    mostraItemMnu.setVisible(false);
                    creaItemMnu.setBackground(Color.yellow);
                    cancellaItemMnu.setVisible(false);
                    saveItemMnu.setVisible(false);
                    saveItemMnu.setBackground(Color.green);
                    loadItemMnu.setBackground(Color.green);
                    
                    /*verifica che la prima finestra sia instanziata è utile
                    per avere un log della situazione. */
                 
                   // this.setExtendedState(WIDTH);
                    this.setBounds(0, 0, 800, 600); 
                    Dimension a = new Dimension(800,600); // Fisso la dimensione minima
                    
                    this.setMinimumSize(a);
                    
                    this.setVisible(true); // mostriamo la finestra 
                    this.pack();
                    
                    /* Settiamo che per defalut quando si preme su la x  si chiude l'applicazione
                    * Questa è la finestra principale alla sua chiusura chiude anche la VM Java
                    */
                    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

                   
                    /*Instanzio il Menu*/
                    
                    setJMenuBar(bar);
                    bar.add(creaMnu);
                    creaMnu.add(configuraItemMnu);
                    creaMnu.add(creaItemMnu);
                    creaMnu.add(cancellaItemMnu);
                    creaMnu.add(mostraItemMnu); 
                    creaMnu.add(saveItemMnu);
                    creaMnu.add(loadItemMnu);                    
                    
                    bar.add(helpMnu);
                    helpMnu.add(aboutItemMnu);
                    bar.add(languageMnu);
                    languageMnu.add(language_itItemMnu);
                    languageMnu.add(language_enItemMnu);
                    languageMnu.add(language_deItemMnu);
    }
  
  
    /*
     LISTA DELLE AZIONI CHE POSSIAMO COMPIERE CON LA NOSTRA INTERFACCIA
    */
        public final void CaricaAzioni(){
         /*Lista delle azioni che può fare il nostro menu*/
         
         
            creaItemMnu.addActionListener(new ListenerMenuCrea(this));
            mostraItemMnu.addActionListener(new ListenerMostraRisultato(this));
            
            cancellaItemMnu.addActionListener(new ListenerCancellaCrea(this));
            
            /*
            La finestra configurazione viene inizializzata qui in modo che possa essere utilizzata
            da questa classe. 
            Questa finestra viene creata nel momento in cui si creano i corsi, e viene nascosta quando si configura.
            Quando si configura non si possono aggiungere corsi
            */
            
            configuraItemMnu.addActionListener(new ListenerConfiguraCrea(this));
            saveItemMnu.addActionListener(new ListenerSaveAllCrea(this));
            loadItemMnu.addActionListener(new ListenerLoadAllCrea(this));
            aboutItemMnu.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
              
                    Component fr = null;
                    Translator.init_str_FinestraIniziale();
                 JOptionPane.showMessageDialog(fr,
            Translator.str_FinestraIniziale("One year School Schedule Scenario v.0.1 è la versione base\ndel software School Schedule Scenario Enterprise 2.8.\n")
          + Translator.str_FinestraIniziale("\nquesto software serve per dare assistenza all'amministrazione\ndi un ente formativo.\n")
           +Translator.str_FinestraIniziale("\nQuesta versione è offerta secondo la licenza d'uso:")
           +Translator.str_FinestraIniziale("\nGPLv3. \nPer info contattare l'autore\nFrancesco Capodanno: francesco@panglosses.com"),
            "About",
           JOptionPane.INFORMATION_MESSAGE);
                }
                
            } );
            
         language_itItemMnu.addActionListener(new ActionListener(){
                
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    Translator.setValue("IT");
                    Translator.setFramesToTranslate(finestra_this, finestraconf, resultsJFrame);
                }
            });
         
         language_enItemMnu.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Translator.setValue("EN");
                    Translator.setFramesToTranslate(finestra_this, finestraconf, resultsJFrame);
                }
            });
         
         language_deItemMnu.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Translator.setValue("DE");
                    Translator.setFramesToTranslate(finestra_this, finestraconf, resultsJFrame);
                }
            });
     
     }
        /*
        crea la GUI personalizzata per l'utente
        */
        public void addPanel(int pospanel)
        {
            
        }
         public boolean creaProgrammazione(boolean a, int b)
    {  
       
       
       if (a == true) {            
           
         if (isConfigured)  
         {
           if (theGUI == null) theGUI = new SetUpGUI(a, b,this,finestraconf); 
           else theGUI.setGUI(a, b,this,finestraconf);
           isSetUpGui = theGUI.isSetUpGUI;
           oreLezMaxPerDay = this.oreLezMaxPerDay_oneRoom * finestraconf.getRoomsAvaiable();
           this.mostraItemMnu.setVisible(true);
           this.cancellaItemMnu.setVisible(true);           
           isToSave();
         } 
         else
         {
             if (theGUI == null) theGUI = new SetUpGUI(a, b,this);
             else theGUI.setGUI(a, b, this);
             
             isSetUpGui = theGUI.isSetUpGUI;
             oreLezMaxPerDay = this.oreLezMaxPerDay_oneRoom * MAX_ROOMS;
             this.mostraItemMnu.setVisible(true);
             this.cancellaItemMnu.setVisible(true);
             isToSave();
         }
          
          // myContainer.add(GUIFinestraIniziale);
          a = false;        
          
      
       }
       
       return a; 
       
    }
         
    /**
     * instantiate new FinestraConfigurazione for the class ListenerConfiguraCrea
     */
    public void initConfigurazione()
         {
             if (finestraconf == null) finestraconf = new FinestraConfigurazione(this);            
             
         }
         
    /**
     * Create a FinestraConfig for LoadAll Class
     * @return
     */
    public FinestraConfigurazione initConfigurazione_forLoad()
         {
             finestraconf = new FinestraConfigurazione(this);
             return finestraconf;
         }
         
    public boolean isPossibleElaboration()
    {
     boolean isPossible;
     int hoursToCalc = 0;
   
     
     for (int i = 0; i < mainContainer.getComponentCount(); i++)
        { // Numeri di pannelli istanziati
           
           Component raccoglitore = mainContainer.getComponent(i);
          
           JPanel raccPanel = (JPanel) raccoglitore;
           
           JTextField raccOrePerLezione = (JTextField) raccPanel.getComponent(7); // Ore Per Lezione
           try
           {
            hoursToCalc = hoursToCalc + Integer.parseInt(raccOrePerLezione.getText());
           }
           catch (Exception e)
           {
             System.out.println(Arrays.toString(e.getStackTrace()));  
           }
        }
     
     //hoursToCalc++;
     //isPossible = oreLezMaxPerDay >= hoursToCalc;
     this.hoursOveread = hoursToCalc;
     isPossible = true; // Beta version
     return isPossible;
        
    }
    public void elaboraDati() {
       
        if ((isConfigured) && (isSetUpGui) && (!isDefault))
        {
         if (isPossibleElaboration())
          {     
        if (elaboratoreDati == null)
        {
        elaboratoreDati = new Elaboration(this,finestraconf); // prende i dati delle due finestre e restituisce una GUI nella FinestraIniziale del risultato
        } else elaboratoreDati.makeNewResults(finestraconf);
        
          }
         else
          {
           Component fr = null;
                 JOptionPane.showMessageDialog(fr,
            "Con una configurazione del genere è impossibile evitare la sovrapposizione degli orari!\n "+
            "Con questa configurazione ci vorrebbero " + hoursOveread + "h di lezioni al giorno!\n"
            +"Il numero di aule non soddisfa questo monte ore",
            "Error",
           JOptionPane.ERROR_MESSAGE);   
          }
        } else if ((isConfigured) && (isSetUpGui) && (isDefault))
        {
            if (elaboratoreDati == null)
            {
            elaboratoreDati = new Elaboration(this); // Non ha bisogno della finestra configurazione utilizza i dati standard                 
            }
            else elaboratoreDati.makeNewResults(finestraconf);
            
           
           
          
        } else if ((!isConfigured) && (!isSetUpGui))
        {
            Component fr = null;
                 JOptionPane.showMessageDialog(fr,
            "Devi creare prima di tutto i corsi da programmare. Inoltre è consigliabile creare una configurazione generale",
            "Error",
           JOptionPane.ERROR_MESSAGE);
            
        } else if (!isSetUpGui)
        {
            Component fr = null;
                 JOptionPane.showMessageDialog(fr,
            "Devi creare prima di tutto i corsi da programmare.",
            "Error",
           JOptionPane.ERROR_MESSAGE);
            
        }
       
    }

    void removeResults() {
        if (resultsJFrame != null)
        {
         if (resultsJFrame.isShowing() == true)
         {
            resultsJFrame.dispose();
         }
        }
    }

    void updateGUI() {
     
        /*
        * This code update GUI with new configuration
        * Search all components and update with new configuration
        * Until the upgrade GUI is not finisched the menu is disabled.
        */
        
        String str;
    boolean no_error;
       
       
        
        Component raccoglitore;
        JTextField raccNomeCorso;
        JXDatePicker raccPicker;
        JTextField raccOrePerLezione;
        JComboBox raccFrequenza;
        JTextField raccOraInizioCorso;
        JTextField raccOreTotCorso;
        JCheckBox raccLun;
        JCheckBox raccMar;
        JCheckBox raccMer;
        JCheckBox raccGio;
        JCheckBox raccVen;
        JCheckBox raccSab;
        JCheckBox raccDom;
        for (int i = 0; i < mainContainer.getComponentCount(); i++)
        { // Numeri di pannelli istanziati
           
           raccoglitore = mainContainer.getComponent(i);
          
           JPanel raccPanel = (JPanel) raccoglitore;
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
           
           
           raccLun.setSelected(finestraconf.esclLun.isSelected());
           raccMar.setSelected(finestraconf.esclMar.isSelected());
           raccMer.setSelected(finestraconf.esclMer.isSelected());
           raccGio.setSelected(finestraconf.esclGio.isSelected());
           raccVen.setSelected(finestraconf.esclVen.isSelected());
           raccSab.setSelected(finestraconf.esclSab.isSelected());
           raccDom.setSelected(finestraconf.esclDom.isSelected());
           
           raccOreTotCorso.setText(finestraconf.getTotOreCorso());
           raccOrePerLezione.setText(finestraconf.getOrePerLezione());
           raccFrequenza.setSelectedIndex(finestraconf.frequenzalezioni.getSelectedIndex());
           
           
           
           
    }
    if (elaboratoreDati != null)
     {
      elaboratoreDati.setDatesToExclude();     
     }
    this.setVisible(true);
    finestraconf.setVisible(false);
       
          
}

    void saveAll() {
        if (saveAll == null) saveAll = new SaveAll(this, finestraconf);
        else saveAll.setSave(this, finestraconf);
       
    }
    void loadAll() {
       if(loadAll == null) loadAll = new LoadAll(this, finestraconf);
       else loadAll.setLoad(this,finestraconf);
       
    }
    
    void eraseAll(){
        mainContainer.removeAll();
        mainContainer.updateUI(); 
        oggettiGUI.clear(); 
        isConfigured = false;
        isCreated = false;
        //finestra.isConfigured = false;
        //isDefault = false;
        isSetUpGui = false;
        creaItemMnu.setText("Crea");
       
        
        removeResults();
        /*
        SETTAGGIO COLORI
        */
        cancellaItemMnu.setBackground(Color.red);
        configuraItemMnu.setBackground(Color.green);
        creaItemMnu.setBackground(Color.YELLOW);
        mostraItemMnu.setBackground(Color.red);
        creaItemMnu.setVisible(true);
    }
   public void isToSave() 
   {
       
       /* Aggiunge a tutti gli elementi del main container un listener per verificare se è da salvare o no*/
       for (int i=0; i<mainContainer.getComponentCount();i++)
       {
           MyPanel raccPanel = (MyPanel) mainContainer.getComponent(i);
           final JTextField raccNomeCorso = (JTextField) raccPanel.getComponent(1);
           final JXDatePicker raccPicker = (JXDatePicker) raccPanel.getComponent(3); // Data inizio Corso
           final JTextField raccOreTotCorso = (JTextField) raccPanel.getComponent(5);  // Ore Tot Corso
           final JTextField raccOrePerLezione = (JTextField) raccPanel.getComponent(7); // Ore Per Lezione
           final JComboBox raccFrequenza = (JComboBox) raccPanel.getComponent(9); //  Frequenza
           final JTextField raccOraInizioCorso = (JTextField) raccPanel.getComponent(11); // ora Inizio Corso
           final JCheckBox raccLun = (JCheckBox) raccPanel.getComponent(13);
           final JCheckBox raccMar = (JCheckBox) raccPanel.getComponent(14);
           final JCheckBox raccMer = (JCheckBox) raccPanel.getComponent(15);
           final JCheckBox raccGio = (JCheckBox) raccPanel.getComponent(16);
           final JCheckBox raccVen = (JCheckBox) raccPanel.getComponent(17);
           final JCheckBox raccSab = (JCheckBox) raccPanel.getComponent(18);
           JCheckBox raccDom = (JCheckBox) raccPanel.getComponent(19);
           
           raccNomeCorso.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                
                isToSave = true;
                raccNomeCorso.setBackground(Color.white);
                
                raccNomeCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                
                if (isToSave) 
                {
            
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {               
               isToSave = true;
               raccNomeCorso.setBackground(Color.white);
               raccNomeCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
               
               if (isToSave) 
                {
            
                }
            }

            @Override
         public void changedUpdate(DocumentEvent e) {
                  isToSave = true;
                  raccNomeCorso.setBackground(Color.white);
                  
               if (isToSave) 
                {
            
                }
             }
              });
           
          raccOreTotCorso.getDocument().addDocumentListener(new DocumentListener() {

               @Override
               public void insertUpdate(DocumentEvent e) {
                 isToSave = true;
                 raccOreTotCorso.setBackground(Color.white);
                 raccOreTotCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                if (isToSave) 
                {
            
                }  
               }

               @Override
               public void removeUpdate(DocumentEvent e) {
                isToSave = true;
                raccOreTotCorso.setBackground(Color.white);
                raccOreTotCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void changedUpdate(DocumentEvent e) {
                isToSave = true;
                raccOreTotCorso.setBackground(Color.white);
                if (isToSave) 
                {
            
                }
               }
           });
         
          raccOrePerLezione.getDocument().addDocumentListener(new DocumentListener() {

               @Override
               public void insertUpdate(DocumentEvent e) {
                 isToSave = true;
                 raccOrePerLezione.setBackground(Color.white);
                 raccOrePerLezione.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void removeUpdate(DocumentEvent e) {
                 isToSave = true;
                 raccOrePerLezione.setBackground(Color.white);
                 raccOrePerLezione.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                if (isToSave) 
                {
            
                }
               }

               @Override
               public void changedUpdate(DocumentEvent e) {
                 isToSave = true;
                 raccOrePerLezione.setBackground(Color.white);
                if (isToSave) 
                {
            
                } 
               }
           });
          
          raccFrequenza.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                 raccFrequenza.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                 isToSave = true;
                if (isToSave) 
                {
            
                }   
               }
           });
          
          raccOraInizioCorso.getDocument().addDocumentListener(new DocumentListener() {

               @Override
               public void insertUpdate(DocumentEvent e) {
                   raccOraInizioCorso.setBackground(Color.white);
                   raccOraInizioCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                    isToSave = true;
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void removeUpdate(DocumentEvent e) {
                   isToSave = true;
                   raccOraInizioCorso.setBackground(Color.white);
                   raccOraInizioCorso.setBorder(BorderFactory.createLineBorder(Color.decode("#00d2d2")));
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void changedUpdate(DocumentEvent e) {
                   isToSave = true;
                   raccOraInizioCorso.setBackground(Color.white);
                if (isToSave) 
                {
            
                } 
               }
           });
         raccLun.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccLun.isSelected()){
                    raccLun.setOpaque(true);
                    raccLun.setBackground(Color.decode("#00d2d2"));}
                    else raccLun.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccMar.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccMar.isSelected()){
                    raccMar.setOpaque(true);
                    raccMar.setBackground(Color.decode("#00d2d2"));}
                    else raccMar.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccMer.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccMer.isSelected()){
                    raccMer.setOpaque(true);
                    raccMer.setBackground(Color.decode("#00d2d2"));}
                    else raccMer.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccGio.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccGio.isSelected()){
                    raccGio.setOpaque(true);
                    raccGio.setBackground(Color.decode("#00d2d2"));}
                    else raccGio.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccVen.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccVen.isSelected()){
                    raccVen.setOpaque(true);
                    raccVen.setBackground(Color.decode("#00d2d2"));}
                    else raccVen.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccSab.addItemListener(new ItemListener() {

               @Override
               public void itemStateChanged(ItemEvent e) {
                    isToSave = true;
                    if (raccSab.isSelected()){
                    raccSab.setOpaque(true);
                    raccSab.setBackground(Color.decode("#00d2d2"));}
                    else raccSab.setOpaque(false);
                if (isToSave) 
                {
            
                } 
               }
           });
         
         raccPicker.addMouseListener(new MouseListener() {

               @Override
               public void mouseClicked(MouseEvent e) {
                     isToSave = true;
                     
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void mousePressed(MouseEvent e) {
                     isToSave = true;
                    
                if (isToSave) 
                {
            
                } 
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                 
               }

               @Override
               public void mouseEntered(MouseEvent e) {
                  
               }

               @Override
               public void mouseExited(MouseEvent e) {
                  
               }
           });
       }
       
       
       
    
   
       }

    /**
     * This method create the panels . this method is called by loadAll
     * @param dataPanels
     * @param numPanels
     */
    public void loadingPanels(List<String> dataPanels, int numPanels) {
      /*Questa funzione deve utilizzando la classe SetUpGui instanziare i pannelli voluti dalla
        classe loadAll    
        
        */
        if (theGUI == null) 
        {
           
            theGUI = new SetUpGUI();
            theGUI.setFinestraIniziale(this);
            theGUI.createGui_loadAll(dataPanels, numPanels);            
          
        } else {
            theGUI.createGui_loadAll(dataPanels, numPanels);
          
        }
    
    }

    
}
