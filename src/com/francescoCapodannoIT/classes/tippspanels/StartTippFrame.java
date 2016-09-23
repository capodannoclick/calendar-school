/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes.tippspanels;


import com.francescoCapodannoIT.classes.HarburTipps;
import com.francescoCapodannoIT.classes.HarburTipps.Route;
import com.francescoCapodannoIT.classes.Translator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * This Frame ask to the user if want help. He take care about chronology
 * @author Francesco Capodanno
 */
public class StartTippFrame extends JFrame{
    static int FONT_GROSS = 20;
    JPanel container;
   
    JButton btn_english;
    JButton btn_italian;
    JButton btn_deutsch;
    
    JButton scelta;
    JButton scelta2;
    JLabel zero;
    JLabel uno;
    JLabel due;
    JLabel tre;
    
    
    
    public StartTippFrame ()
    {
        Border compound;
        Border redline = BorderFactory.createLineBorder(Color.red);
        compound = BorderFactory.createMatteBorder(40, 60, 60, 60, Color.yellow);
                 container = new JPanel();
                 
                 container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
                 container.setBackground(Color.lightGray);
                 
                 container.setBorder(compound);
                 
                 
                 btn_english = new JButton();
                 btn_italian = new JButton();
                 btn_deutsch = new JButton();
                 btn_english.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uk.png")));
                 btn_italian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/it.png")));
                 btn_deutsch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/de.png")));
                 btn_english.setAlignmentX(CENTER_ALIGNMENT);
                 btn_italian.setAlignmentX(CENTER_ALIGNMENT);
                 btn_deutsch.setAlignmentX(CENTER_ALIGNMENT);
                 
                 this.setBounds(0, 0, 640, 480); 
                  Dimension a = new Dimension(542,382);                
                  this.setMinimumSize(a);            
                                 
                  this.setUndecorated(true);                    
                     
                  this.pack();
                  this.setLocationRelativeTo(null);
                  this.setVisible(true);
                  this.add(container);
                   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                  
                  zero = new JLabel("<html>"
                          + "<h1 align =\"center\">One Year Schedule Scenario (Open)</h1>"
                          + "<br><hr><h2 align=\"center\">"
                          + "Lingua/Language/Sprache ?</h2><html>");
                  
                  zero.setAlignmentX(CENTER_ALIGNMENT);
                 
                 
                  
                  
                  
                  container.add(zero);
                  container.add(btn_italian);
                  container.add(btn_english);
                  container.add(btn_deutsch);
                  addListeners();
                  
                  //container.add(uno);
                  //container.add(scelta);
                  //container.add(scelta2);
                 
                  container.updateUI();
                  container.validate();
                   validate();
                  
                  
    }
    
    public void step2()
    {
        container.remove(btn_italian);
        container.remove(btn_english);
        container.remove(btn_deutsch);
        container.remove(zero);
        
        
         uno = new JLabel("<html><h1 align =\" center\">"
                          +Translator.str_FinestraIniziale(
                          "Vuoi essere aiutato all'utilizzo di questa applicazione?")
                          +"</h1><html>");
         
         
        uno.setAlignmentX(CENTER_ALIGNMENT);
        container.add(uno);
        String str = "";
        String str2 = "";
        if (Translator.get_translatorValue()==1) {str = "si"; str2 = "no";}
        if (Translator.get_translatorValue()==2) {str = "yes"; str2 = "no";}
        if (Translator.get_translatorValue()==3) {str = "ja"; str2 = "nein";}
        scelta = new JButton("<html><h1>"+ str +"</h1></html>");
        scelta2 = new JButton ("<html><h1>" + str2 +"</h1></html>");
   
        container.add(scelta);
        // nein
        container.add(scelta2);
        
        scelta2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               HarburTipps.startApplication();
               HarburTipps.route = Route.NONE; // Nessun percorso di suggerimenti viene eseguito
               dispose();
            }
            
        });
        
        scelta.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               step3();
            }
            
        });
        
        
        container.updateUI();
        container.validate();
        validate();
        
        
        
    }
    
    public void step3()
    {
        JButton route1_btn, route2_btn;
        route1_btn = new JButton ("<html><h2>" +
         Translator.str_FinestraIniziale("Voglio inserire più corsi per un'aula")
        +"</h2></html>");
        route2_btn = new JButton (
                "<html><h2>"+
         Translator.str_FinestraIniziale("Inserire più corsi per più aule")+
                "</h2></html>"
        );
        container.remove(scelta);
        container.remove(scelta2);
        
        
        
        uno.setText("<html>" + 
                "<h1>" +Translator.str_FinestraIniziale("Cosa desideri fare?")+
                "</h1>"+"</html>");
        container.add(route1_btn);
        container.add(route2_btn);
        
        
        route1_btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               HarburTipps.route = Route.ONE_ONE;               
               HarburTipps.startApplication();               
               dispose();
            }
            
        });
        
        route2_btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               HarburTipps.route = Route.MANY_MANY;
               HarburTipps.startApplication();
               dispose();
            }
            
        });
        
        container.updateUI();
        container.validate();
        validate();
        
    }
    public void addListeners()
    {
     btn_italian.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
            Translator.setValue("IT");                  
            step2();
         }
         
         
     }
     );
     
     btn_english.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
              Translator.setValue("EN");              
              step2();
         }
         
     });
     
     btn_deutsch.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
              Translator.setValue("DE");              
              step2();
         }
         
     }
     );
    }
    
}
