package salipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
 * Luokka tulosten muokkaamista varten
 *
 */
public class MuokkaaTulosController implements ModalControllerInterface<Tulos>, Initializable {
    
    @FXML
    private TextField muokkaaTulos;
    
    @FXML
    private TextArea muokkaaLisa;
    
    @FXML
    private Label virheLabel;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }


    @FXML
    private void handleTakaisin() {
        tulosKohdalla = null;
        ModalController.closeStage(muokkaaTulos);
    }

    
    @FXML
    private void handleTallenna() {
        if ( tulosKohdalla != null && tulosKohdalla.getTulos() <= 0) {
            Dialogs.showMessageDialog("Tulos ei saa olla nolla tai negatiivinen!");
            return;
        }
        ModalController.closeStage(muokkaaTulos);
    }

    
    @Override
    public void handleShown() {
        muokkaaTulos.requestFocus();
    }

    
    @Override
    public Tulos getResult() {
        return tulosKohdalla;
    }

    
    @Override
    public void setDefault(Tulos oletus) {
        tulosKohdalla = oletus;
        naytaTulos(tulosKohdalla);
    }

    
    private Tulos tulosKohdalla;
    
    
    /**
     * Alustetaan
     */
    protected void alusta() {
        TextField muutettuTulos = muokkaaTulos;
        TextArea muutettuLisa = muokkaaLisa;
        muutettuTulos.setOnKeyReleased( e -> kasitteleMuutosTulokseen((TextField)(e.getSource())));
        muutettuLisa.setOnKeyReleased( e -> kasitteleMuutosLisatietoon((TextArea)(e.getSource())));
    }
    
    
    /**
     * Kasitellaan tuloksen lisatietoihin tehdyt muutokset
     * @param teksti muuttunut tekstialue
     */    
    private void kasitteleMuutosLisatietoon(TextArea teksti) {
        if (tulosKohdalla == null) return;
        String uusi = teksti.getText();
        String virhe = null;
        virhe = tulosKohdalla.setLisatieto(uusi);
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
     * Kasitellaan tuloksen numeroarvoon tehdyt muutokset
     * @param teksti muuttunut tekstikentta
     */    
    private void kasitteleMuutosTulokseen(TextField teksti) {
        if (tulosKohdalla == null) return;
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
     * Naytetaan tuloksen tiedot tekstikentassa ja alueessa
     * @param tulos naytettava tulos
     */        
    private void naytaTulos(Tulos tulos) {
        if (tulos == null) return;
        muokkaaTulos.setText("" + tulos.getTulos());
        muokkaaLisa.setText(tulos.getLisatiedot());
    }

    
    /**
     * Luodaan tuloksen muokkausdialogi ja palautetaan sama tulos muutettuna.
     * @param modalityStage Mille ollaan modaalisia. Null = sovellukselle eli vähän niinku kaikille
     * @param oletus tulos oletuksena
     * @return null jos painetaan takaisin, muuten muokattu tulos
     */
    public static Tulos kysyTulos(Stage modalityStage, Tulos oletus) {
        return ModalController.showModal(
                MuokkaaTulosController.class.getResource("MuokkaaTulosView.fxml"),
                "Muokkaa tulosta", modalityStage, oletus, null);
    }
}
