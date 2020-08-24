package bandeiras;

import javafx.scene.image.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.BandeiraDAO;

public class Bandeiras extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/resources/a.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        BandeiraDAO dao = new BandeiraDAO();
            dao.createTable();
        
        
    }
    
    
}
