package bandeiras;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnClose;

    @FXML
    private AnchorPane closeAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    

    @FXML
    public void closeAction(ActionEvent event) {
        Stage stage= (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
