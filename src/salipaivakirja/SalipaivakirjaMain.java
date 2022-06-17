package salipaivakirja;

import javafx.application.Application;
import javafx.stage.Stage;
import sali.Sali;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author teemu
 * @version 24.1.2021
 * email:tkvolane@student.jyu.fi
 *
 * Pääohjelma salipäiväkirja-ohjelman käynnistämiseksi
 * 
 * Ohjelma toimii muuten hyvin paitsi tulostusta ei ole voitu oikeasti testata
 * ja tämän takia toteuttaa. Liikkeiden poistaminen jää myös toteuttamatta koska
 * ohjelman suunnitelman aikana en ymmärtänyt mikä on oikeasti järkevää
 *
 */
public class SalipaivakirjaMain extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SalipaivakirjaGUIView.fxml"));
            final Pane root = ldr.load();
            final SalipaivakirjaGUIController salipaivakirjaCtrl = (SalipaivakirjaGUIController) ldr.getController();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("salipaivakirja.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Salipaivakirja");
            
            Sali sali = new Sali();
            salipaivakirjaCtrl.setSali(sali);
            
            primaryStage.show();
            
            salipaivakirjaCtrl.avaa();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Käynnistetään käytöliittymä
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}