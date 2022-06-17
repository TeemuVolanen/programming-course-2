package salipaivakirja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Luokka suorittajan/kayttajan nimen kysymista varten
 *
 */
public class KaynnistysController implements ModalControllerInterface<String> {
    
    @FXML
    private TextField suorittaja;
    
    
    @FXML
    void handleAloita() {
        kayttaja = suorittaja.getText();
        ModalController.closeStage(suorittaja);
    }

    
    @Override
    public String getResult() {
        return kayttaja;
    }

    
    @Override
    public void handleShown() {
        suorittaja.requestFocus();
    }

    
    @Override
    public void setDefault(String oletus) {
        //
    }

    
    private String kayttaja = "OletusKäyttäjä";

    
    /**
     * Palautetaan valittu nimi
     * @param ModalityStage mille ollaan modaalisia?
     * @param suorittajanNimi nimi mitä käytetään oletuksena
     * @return valittu suorittajan nimi
     */
    public static String kysyNimi(Stage ModalityStage, String suorittajanNimi) {
        return ModalController.showModal(
                KaynnistysController.class.getResource("KaynnistysView.fxml"),
                "Sali",
                ModalityStage, suorittajanNimi);
    }
}
