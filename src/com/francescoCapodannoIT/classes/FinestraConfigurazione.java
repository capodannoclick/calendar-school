/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

import com.francescoCapodannoIT.classes.HarburTipps.Route;
import com.francescoCapodannoIT.classes.tippspanels.TippPanel;
import com.francescoCapodannoIT.classes.tippspanels.Tippable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Francesco Capodanno (c) 2014
 */
public class FinestraConfigurazione extends JFrame {
    
    /*
    VARIABILI PUBBLICHE
    */

    /**
     *@javadoc: if esclLun.isSeleted.true is true that monday is not included
     */
    
    public final JCheckBox esclLun;

    /**
     *@javadoc: if esclMar.isSelected.true is true that thuesday is not included
     */
    public final JCheckBox esclMar;

    /**
     *@javadoc: if esclMar.isSelected.true is true that wedneday is not included
     */
    public final JCheckBox esclMer;

    /**
     *@javadoc: if esclMar.isSelected.true is true that thursday is not included
     */
    public final JCheckBox esclGio;

    /**
     *@javadoc: if esclMar.isSelected.true is true that friday is not included
     */
    public final JCheckBox esclVen;

    /**
     *@javadoc: if esclMar.isSelected.true is true that saturday is not included
     */
    public final JCheckBox esclSab;

    /**
     *@javadoc: if esclMar.isSelected.true is true that sunday is not included
     */
    public final JCheckBox esclDom;
    
    public ConfigPanel container;
    public PannelloTitolo pannelloGiorniEscludere;
    public PannelloTitolo pannelloGiorniCalendarioEscludere;
    public PannelloTitolo pannelloAuleDisponibili;
    public JButton bottoneConfigura;
    public JButton bottoneReset;
    public JButton salvaConfigura;
    public JButton caricaConfigura;
    public JButton cancellaConfigura;
    public JButton cancellaSelezione;
    public JPanel pannelloBottoni;
    public FlowLayout layoutBottoni;
    
    private JComboBox date;
    public JComboBox auledisponibili;

    /**
     *oggetto JCOMBOX che segnal la frequenza delle lezioni
     */
    public JComboBox frequenzalezioni;
    public JComboBox orePerLezione;
    /**
    *@javadoc: Lista delle Date non convertite per l'UI
    */
    private final String[] listaDate;
    private final String[] listaorePerLezione;
    /**
    *@javadoc: Lista per le date convertite per l'UI
    */
    private final String[] listaDateconv;
    public final String lista2[];
    private int numDate;
    public JLabel jLabelTotDate;
    private final FinestraIniziale finestra;
    private final JXDatePicker datePicker;
    private final Date data_ora;
    private final String lista[];
    public JCheckBox oraTotCorso_check;
    public JTextField oraTotCorso_field;
    public JLabel auledisponibiliLabel;
    public JLabel orePerLezioneLabel;
    public JLabel frequenzalezioniLabel;
    private final JFileChooser jFileSave;
    private final JFileChooser jFileOpen;
    
    
    private Date perDates;
    
    /**
     *Is configured button pressed ?
     */
    public boolean isConfigured;
    private final Date[] datesToSend;
    
   // private final Object[] objectstrings;
    
    /**
     * This parameter is true if the configuration need to save
     */        
    public boolean isToSave;
    public String configurationFile;
    
    /**
     *
     * @param frame FinestraIniziale
     */        
    public FinestraConfigurazione(FinestraIniziale frame)
    {
        super(Translator.str_FinestraConfigurazione("Configurazione regole generali"));
        
              
        
        
        // Inizializzato nel Listener Translator.init_str_FinestraConfigurazione();
        this.numevent = 1;
        isToSave = true;
        
        lista = new String[50];
        data_ora = new Date();
        lista2 = new String[7];
         for(int i=1;i<= lista.length;i++) lista[i-1]=i +"";
        listaorePerLezione = new String[12];
         for(int i=1;i<= listaorePerLezione.length;i++) listaorePerLezione[i-1]=i +""; 
        
        listaDateconv = new String[365];
        
        
        lista2[0] = Translator.str_FinestraConfigurazione("giornaliera");
        lista2[1] = Translator.str_FinestraConfigurazione("settimanale");
        lista2[2] = Translator.str_FinestraConfigurazione("ogni 2 giorni");
        lista2[3] = Translator.str_FinestraConfigurazione("ogni 3 giorni");
        lista2[4] = Translator.str_FinestraConfigurazione("ogni 4 giorni");
        lista2[5] = Translator.str_FinestraConfigurazione("ogni 5 giorni");
        lista2[6] = Translator.str_FinestraConfigurazione("ogni 6 giorni");
        frequenzalezioni = new JComboBox(lista2);
        orePerLezione = new JComboBox(listaorePerLezione);
        /*
        Impostazioni della finestra
        */
        finestra = frame;
        isConfigured = false;
        finestra.setVisible(false); // per ridurre il carico cognitivo dell'utente inutile quando si configura si nasconde
        
        this.setBounds(100, 80, 800, 350); 
        Dimension a = new Dimension(800,350); // Fisso la dimensione minima
                    
                    this.setMinimumSize(a);
                    this.setVisible(true); // mostriamo la finestra
                    
       
                  
                    this.pack();
                    /* Settiamo che per defalut quando si preme su la x  si chiude l'applicazione*/
                    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    WindowListener exitListener;
        exitListener = new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                finestra.setVisible(true);
            }
        };
        addWindowListener(exitListener);
                    this.setResizable(false); 
        
        /*
        Instanziamento degli oggetti principali            
        */            
        container = new ConfigPanel();
        pannelloGiorniEscludere = new PannelloTitolo(Translator.str_FinestraConfigurazione("giorni da escludere"),780, 100);
        pannelloGiorniCalendarioEscludere = new PannelloTitolo(
        Translator.str_FinestraConfigurazione("giorni del calendario da escludere"),
                780, 50);
        pannelloAuleDisponibili = new PannelloTitolo(
        Translator.str_FinestraConfigurazione("aule disponibili e frequenza lezioni"), 
                780, 100);
        
        
        pannelloBottoni = new JPanel();
        /*
        BOTTONI
        */
        bottoneConfigura = new JButton(
                Translator.str_FinestraConfigurazione("Configura"));
        bottoneReset = new JButton(Translator.str_FinestraConfigurazione("Default"));
        salvaConfigura = new JButton(Translator.str_FinestraConfigurazione
        ("Salva Configurazione"));
        caricaConfigura = new JButton(Translator.str_FinestraConfigurazione
        ("Carica Configurazione"));
        cancellaConfigura = new JButton(Translator.str_FinestraConfigurazione
        ("Reset"));
        cancellaSelezione = new JButton(Translator.str_FinestraConfigurazione
        ("Cancella data selezionata"));
        
        layoutBottoni = new FlowLayout(LEFT); 
        esclLun = new JCheckBox(Translator.str_FinestraConfigurazione("Lunedì"));
        esclMar = new JCheckBox(Translator.str_FinestraConfigurazione("Martedì"));
        esclMer = new JCheckBox(Translator.str_FinestraConfigurazione("Mercoledì"));
        esclGio = new JCheckBox(Translator.str_FinestraConfigurazione("Giovedì"));
        esclVen = new JCheckBox(Translator.str_FinestraConfigurazione("Venerdì"));
        esclSab = new JCheckBox(Translator.str_FinestraConfigurazione("Sabato"));
        esclDom = new JCheckBox(Translator.str_FinestraConfigurazione("Domenica"));
        listaDate = new String[365]; 
        
        /* ci possono essere al massimo 365 date 
         da escludere in un anno*/
        auledisponibili = new JComboBox(lista);
        datePicker = new JXDatePicker(data_ora);
        datesToSend = new Date[365]; // carico una lista da mandare
        date = new JComboBox();
        
        oraTotCorso_check = new JCheckBox(Translator.str_FinestraConfigurazione
        ("configura la durata totale(in ore) di tutti i corsi"));
        oraTotCorso_field = new JTextField();
        
        jLabelTotDate = new JLabel(Translator.str_FinestraConfigurazione("Nessuna"));
        numDate = 0;  
        /*
        FILE CHOOSER per il salvataggio e per l'apertura del file
        */
        jFileSave = new JFileChooser(); 
        jFileOpen = new JFileChooser();
        
        myComponents();
        myActions(); // carica le azioni dell'interfaccia
        
        container.validate();
        container.updateUI();
        
        /*
        Aggiunta degli oggetti al container
        */
        add(container); 
             container.add(pannelloGiorniEscludere);
             container.add(pannelloGiorniCalendarioEscludere);
             container.add(pannelloAuleDisponibili);
             container.add(pannelloBottoni);
        suggestUse(); // Suggest the use of UI
        ifModifiedSomething_isToSave(); // Control the UI activity.
        
        // tipps
        Tippable finestraTipp = new TippPanel(this,
                Translator.str_FinestraConfigurazione("Finestra Configurazione")
                ,Route.ONE_ONE);
        
        Tippable finestraTipp2 = new TippPanel(this,
                Translator.str_FinestraConfigurazione("Finestra Configurazione2")
                ,Route.MANY_MANY);
       if (HarburTipps.route == Route.MANY_MANY) 
       {
           pannelloAuleDisponibili.setBackground(Color.red);
           
       
       }
    }
    
    private void init_Tipps()
    {
        // tipps
        Tippable finestraTipp = new TippPanel(this,
                Translator.str_FinestraConfigurazione("Finestra Configurazione")
                ,Route.ONE_ONE);
        
        Tippable finestraTipp2 = new TippPanel(this,
                Translator.str_FinestraConfigurazione("Finestra Configurazione2")
                ,Route.MANY_MANY);
       if (HarburTipps.route == Route.MANY_MANY)
       {
           
           pannelloAuleDisponibili.setBackground(Color.red);
           
       
       }
    }
    
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
                        System.out.println(dateconv);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateconv);
                        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                        
                        str = formatedDate;
                        
                        
                        } catch (ParseException e) {
                         
                      
                         JOptionPane.showMessageDialog(null,
                         e.getLocalizedMessage(),
                         "Configurazione",
                         JOptionPane.ERROR_MESSAGE);
		
                        }
        return str;
    }
    
    private void myComponents()
    {
        /*
        Layouts e dimensioni
        */
        
        pannelloBottoni.setPreferredSize(new Dimension(700,200));
        
        pannelloBottoni.setLayout(layoutBottoni);
        
        pannelloGiorniEscludere.setLayout(new GridLayout(2,5));
        
        pannelloGiorniCalendarioEscludere.setLayout(new GridLayout(1,3));
        
        pannelloAuleDisponibili.setLayout(new FlowLayout());
        
       
        /*
            DATA PICKER
        */
         
            perDates = new Date();
            
            datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //label.setText(datePicker.getDate().toString());
                
                String data = datePicker.getDate().toString();
                perDates = datePicker.getDate();
                boolean isEquals;
                boolean isFind = false;
               
                if (numDate > 0)
                { 
                    for (int i = 0; i < numDate ; i++ )
                    {   
                        isEquals = data.equals(listaDate[i]);                       
                        if (isEquals) isFind = true;
                    }
                    
                    if (!isFind) 
                        {
                            listaDate[numDate] = data;                            
                            listaDateconv[numDate] = uiSimpleConvertDate(data);
                            
                            
                            datesToSend[numDate] = perDates;
                            numDate++;
                            date.addItem(uiSimpleConvertDate(data)); // va aggiunto la data convertita
                            date.updateUI();  
                            jLabelTotDate.setText("date inserite: "+ numDate);
                                                     
                        }
                } else
                {
                    listaDate[numDate] = data;
                    listaDateconv[numDate] = uiSimpleConvertDate(data);
                    
                    datesToSend[numDate] = perDates;
                    numDate++;
                    date.addItem(uiSimpleConvertDate(data)); // va aggiunta la data convertita
                    date.updateUI();  
                    jLabelTotDate.setText("date inserite: "+ numDate);
                  }
                
                }
                });
            pannelloGiorniCalendarioEscludere.add(datePicker);
            pannelloGiorniCalendarioEscludere.add(date);
            pannelloGiorniCalendarioEscludere.add(jLabelTotDate);
            pannelloGiorniCalendarioEscludere.add(cancellaSelezione);
            
            /*
            FINE DATA PICKER
            */
        
        
        /*
        costruzione pannello Giorni da Escludere
        */
        pannelloGiorniEscludere.add(esclLun);
        pannelloGiorniEscludere.add(esclMar);
        pannelloGiorniEscludere.add(esclMer);
        pannelloGiorniEscludere.add(esclGio);
        pannelloGiorniEscludere.add(esclVen);
        pannelloGiorniEscludere.add(esclSab);
        pannelloGiorniEscludere.add(esclDom);
        
        /*
        Aggiunta della combobox 
        */
        
        frequenzalezioniLabel = new JLabel(Translator.str_FinestraConfigurazione
        ("Frequenza delle lezioni:"));
        auledisponibiliLabel = new JLabel(Translator.str_FinestraConfigurazione("Aule disponibili:"));
        oraTotCorso_field.setColumns(5);
        oraTotCorso_field.setEnabled(false);
        orePerLezioneLabel = new JLabel(Translator.str_FinestraConfigurazione("Ore per Lezione"));
        
        /* 
        Si possono configurare le ore totali del corso
        */
        oraTotCorso_check.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (oraTotCorso_check.isSelected()) 
                {
                    oraTotCorso_field.setEnabled(true);
                }
                else
                {
                    oraTotCorso_field.setEnabled(false);
                }
                
            }
            
        });
     if (auledisponibili.getBackground() != Color.lightGray)
     {
     auledisponibili.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent e) {
                   System.out.println("Azione?");
                   pannelloAuleDisponibili.setBackground(Color.lightGray);
                   pannelloAuleDisponibili.repaint();
               }
               
           });
     
     }
         pannelloAuleDisponibili.add(auledisponibiliLabel);
         pannelloAuleDisponibili.add(auledisponibili);
         pannelloAuleDisponibili.add(frequenzalezioniLabel);
         pannelloAuleDisponibili.add(frequenzalezioni);
         pannelloAuleDisponibili.add(oraTotCorso_check);
         pannelloAuleDisponibili.add(oraTotCorso_field);
         pannelloAuleDisponibili.add(orePerLezioneLabel);
         pannelloAuleDisponibili.add(orePerLezione);
         
         
        /*
        costruzione pannello Bottoni
        */
       
        pannelloBottoni.add(bottoneConfigura);
        
        pannelloBottoni.add(bottoneReset);
        
        pannelloBottoni.add(salvaConfigura);
        
        pannelloBottoni.add(caricaConfigura);
        
        pannelloBottoni.add(cancellaConfigura);
        
    
    }
    
    public void setDefaults()
    {
        /*
        Metodo che setta la configurazione di base
        */
        System.out.println("Default Values");
        esclLun.setSelected(false);
        esclMar.setSelected(false);
        esclMer.setSelected(false);
        esclGio.setSelected(false);
        esclVen.setSelected(false);
        esclSab.setSelected(true);
        esclDom.setSelected(true);
        auledisponibili.setSelectedIndex(49);
        frequenzalezioni.setSelectedIndex(0);
        date.removeAllItems();
        for (int i=0; i <listaDate.length; i++)
        {
            // svuota le date dalla listaDate
            listaDate[i] = null;
            datesToSend[i] = null;
        }
        for (int i=0; i <listaDateconv.length; i++)
        {
            // svuota le date dalla listaDate
            listaDateconv[i] = null;
            datesToSend[i] = null;
        }
        numDate = 0;
        
        // finestra.isConfigured = true;
        jLabelTotDate.setText(Translator.str_FinestraConfigurazione("Nessuna")); 
        oraTotCorso_field.setText("");
        oraTotCorso_field.setEnabled(false);
        oraTotCorso_check.setSelected(false);        
        datePicker.updateUI();
        bottoneConfigura.setBackground(Color.green);
        isToSave = false;
        
    }
    
    public void setReset()
    {
        System.out.println("Reset");
        esclLun.setSelected(false);
        esclMar.setSelected(false);
        esclMer.setSelected(false);
        esclGio.setSelected(false);
        esclVen.setSelected(false);
        esclSab.setSelected(false);
        esclDom.setSelected(false);
        auledisponibili.setSelectedIndex(0);
        frequenzalezioni.setSelectedIndex(0);
        date.removeAllItems();
        for (int i=0; i <listaDate.length; i++)
        {
            // svuota le date dalla listaDate
            listaDate[i] = null;
            datesToSend[i] = null;
            
        }
        for (int i=0; i<listaDateconv.length; i++)
        {
            listaDateconv[i] = null;
            datesToSend[i] = null;
        }
        numDate = 0;
        // finestra.isConfigured = true;
        jLabelTotDate.setText(Translator.str_FinestraConfigurazione("Nessuna")); 
        oraTotCorso_field.setText("");
        oraTotCorso_field.setEnabled(false);
        oraTotCorso_check.setSelected(false);        
        datePicker.updateUI();
        isToSave = false;
        
    }
    
    private boolean check_ore()
    {
     boolean all_ok = false;
     if (oraTotCorso_check.isSelected())
          { 
            int a = 0;
            boolean parserr;
            String s = oraTotCorso_field.getText();
            try 
                {
            a = Integer.parseInt(s); 
            parserr = false;
            all_ok = true;
            System.out.println(parserr);
            
                }
            catch (Exception e)
                {   Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    "E' stato riscontrato un errore nell'inserimento delle ore",
                    "Errore",
                     JOptionPane.ERROR_MESSAGE);
                    parserr = true;
                    all_ok = false;
                  
                }
            if ((a > 8700) && (!parserr))
                    {
                     Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    Translator.str_FinestraConfigurazione("Sono state inserite più di 8700 ore di lezione!"),
                    Translator.str_FinestraConfigurazione("Errore"),
                     JOptionPane.ERROR_MESSAGE);
                    
                     all_ok = false;
                    }    
            }
     
     if (!oraTotCorso_check.isSelected()) all_ok = true;
     return all_ok;
    }
    
    
    /**
        Questa funzione configura 
        */
    private void configure()
    {
        /* verifica prima le ore inserite */
        boolean all_ok = check_ore();
        if (all_ok)
        {
            Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    Translator.str_FinestraConfigurazione("Queste regole verranno applicate ai nuovi corsi creati"),
                    Translator.str_FinestraConfigurazione("Configurazione regole generali"),
                     JOptionPane.INFORMATION_MESSAGE);
                    if (finestra.isConfigured)
                    {
                     finestra.updateGUI();   
                     finestra.repaint();
                    }else 
                    {
                    finestra.isConfigured = true;
                    Tippable tippAfterConfigure1;
                    tippAfterConfigure1 = new TippPanel(finestra,
                    Translator.str_FinestraConfigurazione("Vai a Crea/Salva --> Crea1"),
                    Route.ONE_ONE);
                    
                    Tippable tippAfterConfigure2;
                    tippAfterConfigure2 = new TippPanel(finestra,
                    Translator.str_FinestraConfigurazione("Vai a Crea/Salva --> Crea2"),
                    Route.MANY_MANY);
                    
                    }
                    finestra.creaItemMnu.setBackground(Color.GREEN);
                    finestra.configuraItemMnu.setBackground(Color.GREEN.darker());
                    finestra.loadItemMnu.setBackground(Color.yellow);
                    this.isConfigured = true;
                    this.setVisible(false);
                    finestra.setVisible(true);
                    finestra.repaint();
                    try
                    {
                    finestra.getMininumHours = Integer.parseInt(this.getOrePerLezione());
                    }
                    catch (Exception e)
                    {
                       System.out.println(Arrays.toString(e.getStackTrace()));     
                    }
        }
     
    }
    
    /**
     * Set the configuration from a data file.
     * @param lines
     */
    public void setLoadedConfiguration(List<String> lines)
    {
        String str;
        boolean wde,dde,hours,freq,rooms,hourslessons,corruptionlogic;
        wde = false;
        dde = false;
        hours = false;
        freq = false;
        rooms = false;
        hourslessons = false;
        corruptionlogic = false;
        numDate = 0; // prima di caricare svuota il numero di te inserite.
       
        /*
        Questo metodo privato setta la configurazione letta dal file
         */
        for (int nlines = 0; nlines<lines.size(); nlines++) {
            str = lines.get(nlines);
            
            if (str.startsWith("#"))
            {
                /* Questa è una linea di commento
                non fare nulla
                */
            }
            else if((str.equals("<start: wde>")) && !wde){
                    for (int i = 1; i<=7; i++){ 
                        str = lines.get(nlines+i);
                        if ((!str.equals("<end: wde>"))) 
                        {
                            
                            if ((str.equals("true")) && (i == 1))
                            {
                                esclLun.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 1))
                                {
                                esclLun.setSelected(false);
                                }                            
                            
                            
                            if ((str.equals("true")) && (i == 2))
                            {
                                esclMar.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 2))
                                {
                                esclMar.setSelected(false);
                                }
                            
                            
                            if ((str.equals("true")) && (i == 3))
                            {
                                esclMer.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 3))
                                {
                                esclMer.setSelected(false);
                                }
                            
                            
                            if ((str.equals("true")) && (i == 4))
                            {
                                esclGio.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 4))
                                {
                                esclGio.setSelected(false);
                                }
                            
                            
                            if ((str.equals("true")) && (i == 5))
                            {
                                esclVen.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 5))
                                {
                                esclVen.setSelected(false);
                                }
                            
                            
                            if ((str.equals("true")) && (i == 6))
                            {
                                esclSab.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 6))
                                {
                                esclSab.setSelected(false);
                                }
                            
                            
                            if ((str.equals("true")) && (i == 7))
                            {
                                esclDom.setSelected(true);
                            }
                            else if ((str.equals("false")) && (i == 7))
                                {
                                esclDom.setSelected(false);
                                }
                            
                            if ((((str.equals("true"))) ||(str.equals("false"))) && 
                               ((i == 1) || (i == 2)  || (i == 3) || (i == 4) || 
                                (i == 5) || (i == 6) || (i ==7 )))
                                    {
                                        /*
                                        Test di coerenza logica: in questo caso è passato
                                        Questo test è ridondante. Il test logico è in fase Beta non è utilizzato
                                        potrebbe essere sostituito con le assertions
                                        */    
                                    }
                            else 
                            {
                                System.out.println("E' stato rilevato un errore logico nel file durante il caricamento");
                                corruptionlogic = true;
                            }
                            
                        }
                        
                    }
                           
                   if (!corruptionlogic) wde = true;
                        
                
                }
            
            if ((str.equals("<start: dde>")) && (!dde)){
               DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US);
               Date dateconv;
               date.removeAllItems();
               /*
               Svuotiamo le liste delle date
               */
               
               for (int k=0; k<listaDate.length; k++)
               {
                   listaDate[k] = null;
                   datesToSend[k] = null;
               }
               for (int k=0; k<listaDateconv.length; k++)
               {
                   listaDateconv[k] = null;
                   
               }
               
                 for (int i=1; i < 8700; i++){
                     str = lines.get(nlines+i);                    
                     if (str.equals("<end: dde>")) {dde = true; break;}
                     
                     try {
                         /*
                         * Conversione date per un aspetto semplice nell' UI
                         */
 
                        dateconv = (Date)formatter.parse(str);
                        
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateconv);
                        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                        
                        listaDateconv[i-1] = formatedDate;
                        listaDate[i-1] = str; // qui rimane la lista con quelle non convertite.
                        datesToSend[i-1] = dateconv;
                        date.addItem(formatedDate);                        
                        numDate++;
                        jLabelTotDate.setText("date inserite: "+ numDate);
                        } catch (Exception e) {
                         corruptionlogic = true;
                         Component fr = null;
                         JOptionPane.showMessageDialog(fr,
                         e.getLocalizedMessage(),
                         "Configurazione",
                         JOptionPane.ERROR_MESSAGE);
		
                        }
                    
               }
                
            }
            if ((str.equals("<start: hours>")) && (!hours)) {
            
                str = lines.get(nlines+1);
                if (!str.equals("<end: hours>")) {
                    
                    oraTotCorso_check.setSelected(true);
                    oraTotCorso_field.setEnabled(true);
                    oraTotCorso_field.setText(str);
                    hours = true;} // niente ore
                
                /* test logico Se non è presente end hours nella stringa attuale o in quella successiva */
                String str2 = lines.get(nlines+2);               
                if (str.equals("<end: hours>") || str2.equals("<end: hours>")) {/* test superato*/}
                else corruptionlogic = true;
               
             }
            if ((str.equals("<start: freq>")) && (!freq)){
                str = lines.get(nlines+1);
                if (!str.equals("<end: freq>")) {
                    
                    if ((str.equals("giornaliera")) || (str.equals("daily")) 
                            || (str.equals("täglich")))
                            {
                                frequenzalezioni.setSelectedIndex(0);
                            }
                    else if((str.equals("settimanale"))|| (str.equals("weekly"))
                            || (str.equals("wöchentlich")))
                    {
                            frequenzalezioni.setSelectedIndex(1);
                    }
                    else if((str.equals("ogni 2 giorni"))|| (str.equals("every 2 days"))
                            || (str.equals("wöchentlich")))
                     {
                           frequenzalezioni.setSelectedIndex(2);
                     }
                    else if ((str.equals("ogni 3 giorni")) || (str.equals("every 3 days")) || 
                            (str.equals("alle 3 Tage")))
                    {
                        frequenzalezioni.setSelectedIndex(3);
                    } 
                    else if ((str.equals("ogni 4 giorni")) || (str.equals("every 4 days")) || 
                            (str.equals("alle 4 Tage")))
                    {
                        frequenzalezioni.setSelectedIndex(4);
                    }
                    else if ((str.equals("ogni 5 giorni")) || (str.equals("every 5 days")) || 
                            (str.equals("alle 5 Tage")))
                    {
                        frequenzalezioni.setSelectedIndex(5);
                    } 
                    else if ((str.equals("ogni 6 giorni")) || (str.equals("every 6 days")) || 
                            (str.equals("alle 6 Tage")))
                    {
                        frequenzalezioni.setSelectedIndex(6);
                    } else { frequenzalezioni.setSelectedIndex(0); }
                       
                   
                    freq = true;}
                
                 /* test logico Se non è presente end hours nella stringa attuale o in quella successiva */
                String str2 = lines.get(nlines+2);               
                if (str.equals("<end: freq>") || str2.equals("<end: freq>")) {/* test superato*/}
                else corruptionlogic = true;
            
            }
            
            if ((str.equals("<start: rooms>")) && (!rooms)){
                str = lines.get(nlines+1);
                if (!str.equals("<end: rooms>")) {int convindex = 0;
                     try{
                     convindex = Integer.parseInt(str);
                     }
                     catch (Exception e)
                     {
                         corruptionlogic = true;
                         Component fr = null;
                         JOptionPane.showMessageDialog(fr,
                         e.getLocalizedMessage(),
                         "Configurazione",
                         JOptionPane.ERROR_MESSAGE);
                     }
                     
                     auledisponibili.setSelectedIndex(convindex - 1); freq = true;}
                
                
                
                 /* test logico Se non è presente end hours nella stringa attuale o in quella successiva */
                String str2 = lines.get(nlines+2);               
                if ((str.equals("<end: rooms>") || str2.equals("<end: rooms>")) && !corruptionlogic) {/* test superato*/}
                else corruptionlogic = true;
            }
            
            if ((str.equals("<start: hourslessons>") && (!hourslessons))){
                str = lines.get(nlines+1);
                
                if (!str.equals("<end: hourslessons>")){
                    int convindex = 0;
                    try{
                        convindex = Integer.parseInt(str);
                    }
                    catch (Exception e)
                    {
                        corruptionlogic = true;
                        Component fr = null;
                        JOptionPane.showMessageDialog(fr, e.getLocalizedMessage(),
                                "Configurazione", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    orePerLezione.setSelectedIndex(convindex - 1); 
                    hourslessons = true;
                }
            }
          
        }
        
    if (corruptionlogic){
        System.out.println("E' corrotta la logica di caricamento?: " + corruptionlogic);
        Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    "Il file che stai caricando sembra corrotto se ne sconsiglia l'uso\n"
                   +"creare una nuova configurazione. Si sconsiglia di modificare manualmente\n"
                   +"i file creati dal software",
                    "Configurazione",
                     JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadConfiguration()
    {
        /*
        metodo che carica da un determinato file una Configurazione
        */
        System.out.println("metodo per il caricamento avviato...");
        jFileOpen.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileOpen.setDialogTitle("Carica configurazione");
        FileFilter filter = new FileNameExtensionFilter("OSS One Year School Schedule Files (*.oss)","oss");
        jFileOpen.addChoosableFileFilter(filter);
        jFileOpen.setFileFilter(filter);
        int returnVal = jFileOpen.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            // String filePath = jFileOpen.getSelectedFile().getAbsoluteFile() + ".oss";
            File file = jFileOpen.getSelectedFile();
            if (file.canRead())
            {
                List<String> lines = new ArrayList<>();
                try
                {
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                configurationFile = file.toPath().toString();
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
                        
            /*
            qui si implementa l'apertura del file
            */
             
                if (!lines.isEmpty())
                {
                    setLoadedConfiguration(lines);
                    bottoneConfigura.setBackground(Color.GREEN);
                    caricaConfigura.setBackground(Color.GREEN.darker());
                    isToSave = false;
                }
                else 
                {
                    /*
                    se non sono state caricate linee vuol dire che il file era vuoto
                    ritorno un errore
                    */
                    Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    "Errore il file era vuoto",
                    "Configurazione",
                     JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
            else {
                System.out.println("Caricamento annullato dall'utente.");
            }
    }
    
    /**
     * Save the configuration
     */
    public void saveConfiguration()
    {
    /*
        metodo che salva la configurazione attuale in un file usa jFileSave
        */
        File file;
        FileFilter filter = new FileNameExtensionFilter("OSS One Year School Schedule Files (*.oss)","oss");
        jFileSave.addChoosableFileFilter(filter);
        jFileSave.setFileFilter(filter);
        
        jFileSave.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        System.out.println("metodo per il salvataggio avviato...");
        
        jFileSave.setDialogTitle("Salva configurazione");
        int returnVal = jFileSave.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {   
            String trovaext;
            trovaext = jFileSave.getSelectedFile().toString();
            System.out.println(trovaext);
            /* Se troviamo l'estensione lo salviamo così com'è se invece non troviamo l'estensione l'aggiungiamo
            * Notare che usiamo il metodo endsWith utile per comprendere come utilizzare le stringhe
            */
            if (trovaext.endsWith(".oss"))
            {
                file = jFileSave.getSelectedFile(); 
            }
            else
            {
               file = new File(jFileSave.getSelectedFile()+".oss"); 
            }
                    
            
            List<String> lines = new ArrayList<>();
            lines.add("# E' consigliato non cambiare manualmente questo file ma adoperare il software");
            lines.add("# One Year School Schedule. Il seguente software è stato realizzato da\n"
                    + "# Francesco Capodanno(c) 2014");
            /*
            SCRITTURA DEI DATI DA SALVARE
            */
            lines.add("<start: wde>"); // giorni della settimana da eslcudere
            
            lines.add(String.valueOf(esclLun.isSelected())); 
            lines.add(String.valueOf(esclMar.isSelected()));
            lines.add(String.valueOf(esclMer.isSelected())); 
            lines.add(String.valueOf(esclGio.isSelected()));
            lines.add(String.valueOf(esclVen.isSelected())); 
            lines.add(String.valueOf(esclSab.isSelected()));
            lines.add(String.valueOf(esclDom.isSelected()));             
            lines.add("<end: wde>");
            
            lines.add("<start: dde>"); // giorni dell'anno da escludere
            for (int i= 0; i<listaDate.length; i++)
             {
                 if (listaDate[i] == null) break;
                 lines.add(listaDate[i]);
                 
             }
            lines.add("<end: dde>");
            
            lines.add("<start: hours>"); // ore totali 
            lines.add(getTotOreCorso());
            lines.add("<end: hours>"); 
            lines.add("<start: freq>"); // frequenza lezioni
            lines.add(frequenzalezioni.getSelectedItem().toString());
            lines.add("<end: freq>");
            lines.add("<start: rooms>"); 
            lines.add(auledisponibili.getSelectedItem().toString());
            lines.add("<end: rooms>");
            lines.add("<start: hourslessons>");
            lines.add(orePerLezione.getSelectedItem().toString());
            lines.add("<end: hourslessons>");
            
            try
                {Path lastSave;
                lastSave = file.toPath();
                configurationFile = lastSave.toString();
                Files.write(file.toPath(), lines, StandardCharsets.UTF_8); 
                Component fr = null;
                    JOptionPane.showMessageDialog(fr,
                    "File salvato",
                    "Configurazione",
                     JOptionPane.INFORMATION_MESSAGE);
                salvaConfigura.setBackground(Color.green.darker());
                bottoneConfigura.setBackground(Color.GREEN);
                isToSave = false;
                        
                }
                    catch (IOException | HeadlessException e)
                     {
                         
                    JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage(),
                    "Configurazione",
                     JOptionPane.ERROR_MESSAGE);
                           
                     }
               
            
        }
        else {
                System.out.println("Salvataggio annullato dall'utente.");
            }
    }
        
    
    
    private void myActions()
    {/* 
        
        bottoneConfigura = new JButton("Configura");
        bottoneReset = new JButton("Default");
        salvaConfigura = new JButton("Salva Configurazione");
        caricaConfigura = new JButton("Carica Configurazione");
        cancellaConfigura = new JButton("Reset");
        modificaSelezione = new JButton("Modifica");
        */
        
      bottoneReset.addActionListener(new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent e) {
             setDefaults();
          }
      });
      
      cancellaConfigura.addActionListener(new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent e) {
             setReset();
          }
      });
      
      bottoneConfigura.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             
              configure();
              
          }
      });
      
       cancellaSelezione.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             
             
             eraseSelectedDate();
             
         
          }
      });
       
       salvaConfigura.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             
             
             
             saveConfiguration();
             
         
          }
      });
       
       caricaConfigura.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             
             
             
             loadConfiguration();
             
         
          }
      });
              
        
    }
    
    
    
    public void eraseSelectedDate()
    {
                        if (numDate > 0)   
                        {
                          listaDate[date.getSelectedIndex()] = null;
                            //Dates[numDate] = perDates;                            
                            date.removeItemAt(date.getSelectedIndex());                              ;
                            date.updateUI();  
                            numDate--;
                            jLabelTotDate.setText(Translator.str_FinestraConfigurazione("date inserite: ")+ numDate);
                        }
        
    }
    /**
     *Questo metodo restituisce i giorni della settimana da escludere
     * @return Date[] 
     */
    public Date[] getConfigDays()
    {       
        
        return datesToSend;
        
    }
    
    /**
     *Questo metodo restituisce tutti i giorni del calendario da escludere
     */
    public void getConfigCalendar()
    {
        
    }
    
    /**
     *
     * @return Questo metodo restituisce le aule disponibili
     */
    public int getRoomsAvaiable()
    {
        int aule;
        aule = this.auledisponibili.getSelectedIndex() + 1;
        System.out.println("Aule disponibili dalla configurazione: " + aule);
        return aule;
    }
    
    public String getTotOreCorso()
    {
        
        String s = oraTotCorso_field.getText();
        
        return s;
    }
    /*
    Questo metodo setta la configurazione nella FinestraIniziale
    */
    private void setConfigOnGUI()
    {
        
    }

    /**
     *Numeri di eventi scatenati dal suggestUse
     */
    public int numevent;
    int delay = 300;
    Timer timer;
    private void suggestUse()
    {
        
        numevent = 1;
        
        ActionListener taskPerformer;
        taskPerformer = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (numevent == 1) { pannelloGiorniEscludere.setBackground(Color.GREEN); numevent++;}
                else if (numevent == 2) {pannelloGiorniEscludere.setBackground(null); numevent++;}
                else if (numevent == 3) {pannelloGiorniCalendarioEscludere.setBackground(Color.GREEN); numevent++; }
                else if (numevent == 4) {pannelloGiorniCalendarioEscludere.setBackground(null); numevent++; }
                else if (numevent == 5) {pannelloAuleDisponibili.setBackground(Color.GREEN); numevent++;}
                else if (numevent == 6) {pannelloAuleDisponibili.setBackground(null);
                bottoneConfigura.setBackground(Color.YELLOW);
                bottoneReset.setBackground(Color.yellow);
                cancellaConfigura.setBackground(Color.yellow);
                salvaConfigura.setBackground(Color.GREEN);
                caricaConfigura.setBackground(Color.GREEN);numevent++;}
                else if (numevent == 7) {timer.stop();}
            
                
            }
        };        
        
      timer = new Timer(delay, taskPerformer);
      timer.start();
  
  
    }

    /**
     *
     * @return Ore di lezioni in String
     */
    public String getOrePerLezione() {
        String s = orePerLezione.getSelectedItem().toString();
        
        return s;
    }
    
    /*
       This method put isToSave = true if some data change in the configuration panel UI    
      */
    private void ifModifiedSomething_isToSave(){
        date.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
            
            isToSave = true;
            if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        orePerLezione.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              
            isToSave = true;
            if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        auledisponibili.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
           
            isToSave = true;
            if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
       oraTotCorso_field.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
              
                isToSave = true;
                if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
           
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
              
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclLun.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclMar.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclMer.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclGio.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclVen.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclSab.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
        esclDom.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              
               isToSave = true;
               if (isToSave) 
            {
            bottoneConfigura.setBackground(Color.yellow);
            salvaConfigura.setBackground(Color.green);
            caricaConfigura.setBackground(Color.yellow);
            }
            }
        });
        
      
    }
    
    /*class datePickerChangeListener implements ChangeListener{
           
            @Override
            public void stateChanged(ChangeEvent e) {
                 JXDatePicker source = (JXDatePicker) e.getSource();
            }
        }*/
   
            
    
    
}
