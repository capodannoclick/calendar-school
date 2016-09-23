/*
 * (c) Francesco Capodanno 2015
 * Tutti i diritti sono riservati agli autori * 
 */
package com.francescoCapodannoIT.classes;

/**
 *
 * @author Francesco Capodanno
 */
public final class HarburTipps {
    private static FinestraIniziale finestra;
    
    /**
     * Three routes of tipps is possibile.
     * ONE_ONE
     * MANY_MANY
     * and NONE
   */    
    public static enum Route {ONE_ONE, MANY_MANY, NONE}
    
    /**
     * Three routes of tipps is possibile.
     * ONE_ONE the user want to create courses for 1 classroom. Tipically in a school.
     * MANY_MANY the user want to create many courses for many classroom. Tipically for a company
     * and NONE the user want not have suggestion.
     */
    public static Route route;
    
    public static void startApplication()
    {
        finestra = new FinestraIniziale();
    }
    
}
