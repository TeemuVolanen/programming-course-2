package salipaivakirja;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Luokka jota ei kayteta ollenkaan. Suorittajia ei tarvinnut lisata lainkaan
 *
 */
public class LisaaSuorittajaController implements ModalControllerInterface<String> {

    @FXML
    private TextField uusiSuorittaja;

    
    @FXML
    void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata tallentaa vielä!");
        ModalController.closeStage(uusiSuorittaja);
    }

    
    @FXML
    void handleTakaisin() {
        ModalController.closeStage(uusiSuorittaja);
    }

    
    @Override
    public String getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

    
    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
    }
}
