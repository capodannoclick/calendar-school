/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

/**
 *
 * @author Francesco Capodanno
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


import javax.swing.JPanel;


public class PanelRoom extends JPanel{

Color yellowColor;
Color mainColor;
Color redColor;
Rectangle header;
int busyRoom;


    public PanelRoom() {
        mainColor = Color.decode("#389900");  
        yellowColor = Color.decode("#f7a22e");
        redColor = Color.decode("#C61236");  
        header = new Rectangle(0,0,50,50);
        busyRoom = 0;
       
    }


@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        draw(g);
      
    } 
public void draw(Graphics g) {
        if (busyRoom > 0)
        {
         g.setColor(mainColor);
        // g.fillRect(header.x + addx, header.y +addy, header.width, header.height);
         g.draw3DRect(header.x, header.y, header.width, header.height, true);           
         if (busyRoom == 0)
         {
           g.fillRect(header.x, header.y, header.width, header.height);       
         }
         
         if ((busyRoom >0) && (busyRoom <=30))
         {
           g.setColor(this.mainColor);
           int valueToSottract = 50 - (busyRoom / 2) ;
           g.fillRect(header.x, header.y, header.width - valueToSottract, header.height); 
         }
         if ((busyRoom >30) && (busyRoom <=80))
         {
           g.setColor(this.yellowColor);
           int valueToSottract = 50 - (busyRoom / 2) ;
           g.fillRect(header.x, header.y, header.width - valueToSottract, header.height); 
         }
         if ((busyRoom >80) && (busyRoom <95))
         {
           g.setColor(this.yellowColor);
           int valueToSottract = 50 - (busyRoom / 2) ;
           g.fillRect(header.x, header.y, header.width - valueToSottract, header.height);
         }
         if ((busyRoom >=95) && (busyRoom <=100))
         {
           g.setColor(this.redColor);
           g.fillRect(header.x, header.y, header.width, header.height);   
         }     
        }
}

public void setHeader(int a, int b, int c, int d)
{
    header = new Rectangle(a,b,c,d);
}


    /**
     * This is a number from 0 to 100. 0 is not busy, 100 is busy.
     * @param i
     */
    public void setValue(int i)
{
   if (busyRoom > 100) busyRoom = 100;
   busyRoom = i; 
}
       
}

