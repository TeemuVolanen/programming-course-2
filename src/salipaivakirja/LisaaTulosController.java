package salipaivakirja;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sali.Tulos;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Luokka tulosten lisaamista varten
 *
 */
public class LisaaTulosController implements ModalControllerInterface<Tulos>, Initializable {
    
    @FXML
    private TextField uusiTulos;

    @FXML
    private DatePicker uusiPvm;

    @FXML
    private TextArea uusiLisa;
    
    @FXML
    private Label virheLabel;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }

    
    @FXML
    void handleTakaisin() {
        tulosKohdalla = null;
        ModalController.closeStage(uusiTulos);
    }

    
    @FXML
    void handleTallenna() {
        if ( tulosKohdalla != null && tulosKohdalla.getPvm().trim().equals("") || tulosKohdalla.getTulos() <= 0) {
            Dialogs.showMessageDialog("Tuloksen päivämäärä ei saa olla tyhjä ja tulos ei saa olla nolla tai negatiivinen!");
            return;
        }
        ModalController.closeStage(uusiTulos);
    }

    
    @Override
    public Tulos getResult() {
        return tulosKohdalla;
    }

    
    @Override
    public void handleShown() {
        uusiPvm.requestFocus();
    }

    
    @Override
    public void setDefault(Tulos oletus) {
        tulosKohdalla = oletus;
    }

    
    private Tulos tulosKohdalla;
    
    
    /**
     * Alustetaan
     */
    protected void alusta() {
        TextField muutettuTulos = uusiTulos;
        TextArea muutettuLisa = uusiLisa;
        DatePicker muutettuPvm = uusiPvm;
        muutettuTulos.setOnKeyReleased( e -> setUusiTulos((TextField)(e.getSource())));
        muutettuLisa.setOnKeyReleased( e -> setUusiLisatieto((TextArea)(e.getSource())));
        muutettuPvm.valueProperty().addListener((ov, oldValue, newValue) -> {
            setUusiPvm(newValue);
        });
    }

    
    /**
     * Asetetaan paivamaara
     * @param pvm paivamaara mika asetetaan
     */
    private void setUusiPvm(LocalDate pvm) {
        String uusi = pvm.toString();
        tulosKohdalla.setPvm(uusi);
    }

    
    /**
     * Asetetaan lisatiedot
     * @param teksti lisatiedot mita laitetaan tekstina
     */
    private void setUusiLisatieto(TextArea teksti) {
        String uusi = teksti.getText();
        tulosKohdalla.setLisatieto(uusi);
    }
    
    
    /**
     * Asetetaan tulos numero
     * @param teksti tulos numero tekstina
     */
    private void setUusiTulos(TextField teksti) {
        String uusi = teksti.getText();
        String virhe = null;
        virhe = tulosKohdalla.setTulos(uusi);
        if (virhe == null) {
            Dialogs.setToolTipText(teksti, "");
            teksti.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(teksti, virhe);
            teksti.getStyleClass().add(virhe);
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Naytetaan virhe labelissa virheteksti
     * @param virhe teksti mika naytetaan
     */        
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            virheLabel.setText("");
            virheLabel.getStyleClass().removeAll("virhe");
            return;
        }
        virheLabel.setText(virhe);
        virheLabel.getStyleClass().add(virhe);
    }

    
    /**
     * Luodaan tuloksen lisaamisen dialogi ja palautetaan null tai taytetty tulos
     * @param modalityStage mille ollaan modaalisia, null tarkoittaa sovellusta
     * @param oletus mika tulos naytetaan oletuksena
     * @return null jos painetaan takaisin, muuten taytetty uusi tulos
     */
    public static Tulos kysyTulos(Stage modalityStage, Tulos oletus) {
        return ModalController.showModal(
                LisaaTulosController.class.getResource("LisaaTulosView.fxml"),
                "Lisää uusi tulos", modalityStage, oletus, null);

    }
}
