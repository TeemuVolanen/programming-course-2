package salipaivakirja;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sali.Liike;
import sali.SailoException;
import sali.Sali;
import sali.Tulos;

/**
 * @author teemu
 * @version 24.1.2021
 * email:tkvolane@student.jyu.fi
 *
 *Luokka käyttöliittymän tapahtumia varten. Hoitaa kaiken näyttöön tulevan tekstin.
 *Hoitaa kaiken tiedon pyytämisen käyttäjältä. Avustajat: Sali, Liike ja Tulos -luokat.
 *
 *Liikkeiden poistaminen ei toimi koska sitä ei ole järkevää mahdollistaa
 *
 */
public class SalipaivakirjaGUIController implements Initializable {

    @FXML
    private DatePicker pickerPaivamaara;

    @FXML
    private ListChooser<Liike> chooserLiike;

    @FXML
    private TextField textTulos;

    @FXML
    private TextArea areaLisatietoja;
    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    /**
     * Käsittelijä kun liikettä painetaan käyttöliittymässä
     */
    @FXML
    void handleLiike() {
        if (liikeKohdalla != null) {
            naytaTulos();
        }
    }
    
    
    /**
     * Käsittelijä kun painetaan lisää uusi liike käyttöliittymässä
     */
    @FXML
    void handleLisaaLiike() {
        uusiLiike();
    }

    
    /**
     * Käsittelijä kun painetaan lisää uusi tulos käyttöliittymässä
     */
    @FXML
    void handleLisaaTulos() {
        uusiTulos();
    }

    
    /**
     * Käsittelijä kun painetaan lopeta käyttöliittymässä
     */
    @FXML
    void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    /**
     * Käsittelijä kun painetaan muokkaa tulosta käyttöliittymässä
     */
    @FXML
    void handleMuokkaaTulos() {
        muokkaaTulos();
    }

    
    /**
     * Käsittelijä kun painetaan näytä tietoja käyttöliittymässä
     */
    @FXML
    void handleNaytaTietoja() {
        ModalController.showModal(SalipaivakirjaGUIController.class.getResource("TietojaView.fxml"), "Tietoja", null, "");
    }
    
    
    /**
     * Käsittelijä kun valitaan päivämäärä käyttöliittymässä
     */
    @FXML
    void handlePaivamaara() {
        naytaTulos();
    }

    
    /**
     * Käsittelijä kun painetaan poista liike käyttöliittymässä
     */
    @FXML
    void handlePoistaLiike() {
        Dialogs.showMessageDialog("Liikkeitä ei voi poistaa lainkaan!");
    }

    
    /**
     * Käsittelijä kun liikettä painetaan käyttöliittymässä
     */
    @FXML
    void handlePoistaSuorittaja() {
        poistaSuorittaja();
    }

    
    /**
     * Käsittelijä kun painetaan poista tulos käyttöliittymässä
     */
    @FXML
    void handlePoistaTulos() {
        poistaTulos();
    }

    
    /**
     * Käsittelijä kun painetaan tallenna käyttöliittymässä
     */
    @FXML
    void handleTallenna() {
        tallenna();
    }

    
    /**
     * Käsittelijä kun halutaan tulostaa käyttöliittymässä
     */
    @FXML
    void handleTulosta() {
        TulostaController tCtrl = TulostaController.tulosta(null);
        tulostaTrendi(tCtrl.getTextArea());
    }


    private Sali sali;
    private Liike liikeKohdalla;
    private LocalDate pvmKohdalla;
    private Tulos tulosKohdalla;
    private String suorittajanNimi = "Suorittaja";
    
    
    /**
     * Alustetaan ohjelman kuuntelijat ja asetetaan paivamaara
     */
    protected void alusta() {
        LocalDate localDate = LocalDate.now();
        pickerPaivamaara.setValue(localDate);
        chooserLiike.clear();
        chooserLiike.addSelectionListener(e -> naytaTulos());
    }
    
    
    /**
     * Näytetään valittu tulos
     */
    private void naytaTulos() {
        liikeKohdalla = chooserLiike.getSelectedObject();
        pvmKohdalla = pickerPaivamaara.getValue();
        if (pvmKohdalla == null) return;
        String pvm = pvmKohdalla.toString();
        tulosKohdalla = sali.annaTulos(liikeKohdalla, pvm);
        
        if (liikeKohdalla == null) return;
        if (tulosKohdalla == null) {
            textTulos.clear();
            areaLisatietoja.clear();
            return;
        }
        
        String tulos = sali.annaTulosNro(tulosKohdalla);
        String lisa = sali.annaLisatiedot(tulosKohdalla);
        textTulos.clear();
        areaLisatietoja.clear();
        textTulos.appendText(tulos);
        areaLisatietoja.appendText(lisa);
    }

    
    /**
     * muokataan tulosta
     */
    private void muokkaaTulos() {
        if (tulosKohdalla == null) return;
        Tulos tulos;
        try {
            tulos = tulosKohdalla.clone();
            tulos = MuokkaaTulosController.kysyTulos(null, tulos);
            if (tulos == null) return;
            sali.korvaaTaiLisaa(tulos);
            hae(liikeKohdalla.getLiikeId());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Tulostaa tuloksen tiedot
     * @param os tietovirta johon tulostetaan
     * @param liike minkä liikkeen tulostettava tulos
     */
    /*
    public void tulosta(PrintStream os, final Liike liike) {
        //os.println("----------------------------------------------");
        //liike.tulosta(os);
        //os.println("----------------------------------------------");
        //List<Tulos> tulokset = sali.annaTulos(liike);
        String tulos = sali.annaTulosNro(liike);
        String lisa = sali.annaLisatiedot(liike);
        //for (Tulos t : tulokset) t.tulosta(os);
        //textTulos.appendText(tulos);
        textTulos.clear();
        areaLisatietoja.clear();
        textTulos.appendText(tulos);
        areaLisatietoja.appendText(lisa);
    }*/

    
    /**
     * Tallennetaan
     */
    private void tallenna() {
        try {
            sali.tallenna();
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Tallennus ei onnistu! " + e.getMessage());
        }
    }

    
    /**
     * Haetaan liikkeitä
     * @param jarjestysNro
     */
    private void hae(int jarjestysNro) {
        chooserLiike.clear();
        
        int index = 0;
        for (int i = 0; i < sali.getLiikkeet(); i++) {
            Liike liike = sali.annaLiike(i);
            if (liike.getLiikeId() == jarjestysNro) index = i;
            chooserLiike.add(liike.getNimi(), liike);            
        }
        chooserLiike.setSelectedIndex(index);
    }
    
    
    /**
     * Lisätään saliin uusi liike
     */
    private void uusiLiike() {
        try {
            Liike uusiL = new Liike();
            uusiL = LisaaLiikeController.kysyLiike(null, uusiL);
            if (uusiL == null || uusiL.getNimi().trim().equals("")) return;
            uusiL.rekisteroi();
            sali.lisaa(uusiL);
            hae(uusiL.getLiikeId());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia liikkeen luomisessa " + e.getMessage());
            return;
        }
    }
    
    
    /**
     * Lisätään saliin uusi tulos
     */
    private void uusiTulos() {
        Tulos uusiT = new Tulos();
        uusiT = LisaaTulosController.kysyTulos(null, uusiT);
        if (uusiT == null || uusiT.getPvm().trim().equals("") || uusiT.getTulos() <= 0) return;
        uusiT.rekisteroi();
        uusiT.taytaTuloksella(1, liikeKohdalla.getLiikeId());
        sali.lisaa(uusiT);
        hae(liikeKohdalla.getLiikeId());
    }
    
    
    /**
     * Poistetaan valittu tulos
     */
    private void poistaTulos() {
        Tulos poistettava = tulosKohdalla;
        sali.poistaTulos(poistettava);
        hae(liikeKohdalla.getLiikeId());
    }

    
    /**
     * Alustetaan sali käytäjä/suorittaja lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta käyttäjän/suorittajan tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
       suorittajanNimi = nimi;
       setTitle("Salipäiväkirja - " + suorittajanNimi);
       try {
           sali.lueTiedostosta(nimi);
           hae(0);
           return null;
       } catch (SailoException e) {
           hae(0);
           String virhe = e.getMessage(); 
           if ( virhe != null ) Dialogs.showMessageDialog(virhe);
           return virhe;
       }
    }
    
    
    /**
     * Asetetaan käyttöliittymän otsikko
     * @param title otsikko merkkijonona
     */
    private void setTitle(String title) {
        ModalController.getStage(pickerPaivamaara).setTitle(title); //miksi olen laittanut paivamaaran tohon?
    }

    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusinimi = KaynnistysController.kysyNimi(null, suorittajanNimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    
    /**
     * Asetetaan käytettävä sali/suorittaja?
     * @param sali jota käytetään
     */
    public void setSali(Sali sali) {
        this.sali = sali;
    }

    
    /**
     * Poistetaan valitun suorittajan/kayttajan kaikki tulokset
     */    
    private void poistaSuorittaja() {
        if ( !Dialogs.showQuestionDialog("HUOMIO!", "Haluatko varmasti POISTAA henkilön " 
                +  suorittajanNimi
                + " tiedot ja kaikki hänen tuloksensa?", "Kyllä", "Ei") ) return;
        Dialogs.showMessageDialog(suorittajanNimi + " poistetaan ohjelman sammuessa.");
        String poisto = suorittajanNimi + "/tulokset.dat";
        File poistettava = new File(poisto);
        poistettava.deleteOnExit();
    }    

    
    /**
     * Tulostetaan valitun liikkeen kaikki tulokset tekstialueeseen
     * @param teksti alue johon tulostetaan
     */    
    private void tulostaTrendi(TextArea teksti) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(teksti)) {
            List<Tulos> alkiot = sali.annaListaTuloksista(liikeKohdalla);
            os.println("Tulostetaan liikkeen " + liikeKohdalla.getNimi() + " trendi:");
            for (Tulos t : alkiot) {
                os.print(t.getPvm());
                for (int i = 0; i < (t.getTulos() / 2); i++) {
                    os.print('-');
                }
                os.println(t.getTulos());
            }
        }
    }
}
