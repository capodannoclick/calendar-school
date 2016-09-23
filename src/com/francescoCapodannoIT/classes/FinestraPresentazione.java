/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;


import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 *
 * @author Francesco Capodanno
 */
public class FinestraPresentazione extends JFrame {
    public FinestraPresentazione()
            {
                super("Presentazione Software");
                
                
                  //JPanel background = new JPanel();
                  //this.add(background);
                  this.setBounds(0, 0, 540, 380); 
                  Dimension a = new Dimension(542,382); 
                  //background.setVisible(true);
                  //background.setBounds(new Rectangle(540,380));
                      
                    
                    this.setMinimumSize(a);
                    
                    //this.setVisible(true); // mostriamo la finestra 
                    //this.pack();                    
                    
                    this.setUndecorated(true);
                    
                     
                    this.pack();
                    this.setLocationRelativeTo(null);
                    this.setVisible(true);
                    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                    
                BufferedImage myImage;
                ImagePanel myBackground;
                ClassLoader classLoader = getClass().getClassLoader();
                
                try
                {
                myImage = ImageIO.read(new File(classLoader.getResource("images/OneYearScheduleScenario.png").getFile()));
                myBackground = new ImagePanel(myImage);
                this.setContentPane(myBackground);
                myBackground.setVisible(true);
                
                validate();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
                  
            }
}
