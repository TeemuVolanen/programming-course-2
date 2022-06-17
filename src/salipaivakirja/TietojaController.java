package salipaivakirja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author teemu
 * @version Feb 15, 2021
 * email:tkvolane@student.jyu.fi
 *
 * Tietojen näyttämisen hoitava luokka
 *
 */
public class TietojaController implements ModalControllerInterface<String> {
    
    @FXML
    private Button painike;

    
    @FXML
    void handleTakaisin() { //ActionEvent event
        ModalController.closeStage(painike);
    }
    
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
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
