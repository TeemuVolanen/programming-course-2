package salipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sali.Liike;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Luokka liikkeiden lisaamista varten
 *
 */
public class LisaaLiikeController implements ModalControllerInterface<Liike>, Initializable {
    
    @FXML
    private TextField uusiLiike;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }

    
    @FXML
    void handleTakaisin() {
        liikeKohdalla = null;
        ModalController.closeStage(uusiLiike);
    }

    
    @FXML
    void handleTallenna() {
        if ( liikeKohdalla != null && liikeKohdalla.getNimi().trim().equals("") ) {
            Dialogs.showMessageDialog("Liikeen nimi ei saa olla tyhjä!");
            return;
        }
        ModalController.closeStage(uusiLiike);
    }

    
    @Override
    public Liike getResult() {
        return liikeKohdalla;
    }

    
    @Override
    public void setDefault(Liike oletus) {
        liikeKohdalla = oletus;        
    }

    
    @Override
    public void handleShown() {
        uusiLiike.requestFocus();
    }

    
    private Liike liikeKohdalla;
    
    
    /**
     * Alustetaan
     */
    protected void alusta() {
        TextField muutettuLiike = uusiLiike;
        muutettuLiike.setOnKeyReleased( e -> setUusiLiike((TextField)(e.getSource())));
    }

    
    /**
     * Asetetaan liikkeen nimi
     * @param teksti nimi tekstina
     */
    private void setUusiLiike(TextField teksti) {
        String uusi = teksti.getText();
        liikeKohdalla.setLiikeNimi(uusi);
    }

    
    /**
     * Luodaan liikkeen lisaamisen dialogi ja palautetaan null tai uusi liike
     * @param modalityStage mille ollaan modaalisia, null tarkoittaa sovellusta
     * @param oletus mika liike naytetaan oletuksena
     * @return null jos painetaan takaisin, muuten uusi liike
     */
    public static Liike kysyLiike(Stage modalityStage, Liike oletus) {
        return ModalController.showModal(
                LisaaLiikeController.class.getResource("LisaaLiikeView.fxml"),
                "Lisää uusi liike", modalityStage, oletus, null);
    }
}

