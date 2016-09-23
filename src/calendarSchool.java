/*
 * Copyright Francesco Capodanno per il corso di Elearning in programmazione Java
 */
/**
 *
 * @author Francesco Capodanno
 */
import com.francescoCapodannoIT.classes.FinestraIniziale; //  importo tutte le classi necessarie alla compilazione
import com.francescoCapodannoIT.classes.FinestraPresentazione;
import com.francescoCapodannoIT.classes.tippspanels.StartTippFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class calendarSchool {

    /**
     * Number of events for timer at the start.
     */
    public static int numevent;

    /**
     * The timer for FinestraPresentazione
     */
    public static Timer timer;
    
   /**
     * This is the main class 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final FinestraPresentazione finestra; 
        finestra = new FinestraPresentazione();
        numevent = 0;
   
        // deployment 1500
        int delay = 500;
        
        ActionListener taskPerformer; // Azione temporale 
       
        taskPerformer = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if (numevent == 0) {numevent++;}
                  else if (numevent == 1) { 
                  startApplication(); 
                  finestra.dispose();
                  timer.stop();
                  numevent++;}
               }
                
        };
        
        timer = new Timer(delay, taskPerformer);
        
        timer.start();
        
       
    }
    
    /**
     * start application
     */
    
    public static void startApplication()
    {
        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(lookAndFeel);
            } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(calendarSchool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                //FinestraIniziale finestraStart = new FinestraIniziale(); // Instanziamo la finestra principale
                StartTippFrame finestraTipp = new StartTippFrame();
            }
        });
        
        
        
        System.out.println("Avvio dell'applicazione School Calendar v. 0.1");
    }
    
    public static final void startApplication2step()
    {
        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(lookAndFeel);
            } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(calendarSchool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                FinestraIniziale finestraStart = new FinestraIniziale(); // Instanziamo la finestra principale
            
            }
        });
    }
    
}
