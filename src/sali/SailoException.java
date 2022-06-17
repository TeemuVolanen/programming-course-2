package sali;

/**
 * @author teemu
 * @version Mar 2, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Poikkeusluokka
 */
public class SailoException extends Exception {
    
    private static final long serialVersionUID = 1L;
   
    
    /**
    * Muodostaja jolle tuodaan poikkeuksessa käytettävä viesti
    * @param viesti Poikkeuksen viesti
    */
    public SailoException(String viesti) {
        super(viesti);
    }
}
