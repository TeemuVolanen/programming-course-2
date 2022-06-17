package salipaivakirja;

import java.awt.print.PrinterException;
//import javax.swing.JTextArea;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Tulostuksen hoitava luokka
 * 
 * Tulostaminen ei toimi oikeasti
 *
 */
public class TulostaController implements ModalControllerInterface<String> {

    @FXML
    private TextArea tulostusTeksti;

    
    @FXML
    private void handleTakaisin() {
        ModalController.closeStage(tulostusTeksti);
    }

    
    @FXML
    private void handleTulosta() {
        //Dialogs.showMessageDialog("Ei osata tulostaa vielä!");
        
        //Tämä on se mitä malliharjoitustyössä on (JavaFX)
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusTeksti.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
        /*
        //Tämä on se mitä löytyy kurssin luentomonisteesta (Swing -kirjasto)
        JTextArea jtext = new JTextArea(tulostusTeksti.getText());
        try {
            jtext.print();
        } catch (PrinterException e) {
            System.err.println("Tulostaminen ei onnistunut");
        }*/
    }

    
    @Override
    public String getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        //
    }

    
    @Override
    public void setDefault(String oletus) {
        tulostusTeksti.setText(oletus);
    }
    
    
    /**
     * @return Alue johon tulostetaan
     */
    public TextArea getTextArea() {
        return tulostusTeksti;
    }
    
    
    /**
    * Näyttää tulostusalueessa tekstin
    * @param tulostus tulostettava teskti
     * @return kontrolleri jolta voidaan pyytää lisää tietoja
    */
    public static TulostaController tulosta(String tulostus) {
        TulostaController tCtrl = 
        ModalController.showModeless(TulostaController.class.getResource("TulostaView.fxml"),
                "Tulostus", tulostus);
        return tCtrl;
    }
}
