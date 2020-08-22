package bandeiras;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.BandeiraDAO;
import model.bean.Bandeira;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author eucri
 */

public class FXMLDocumentController implements Initializable {

    int counter = 0;
    
    @FXML
    private Label label;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtOficialNome;

    @FXML
    private JFXTextField txtCapital;

    @FXML
    private JFXTextField txtISO;

    @FXML
    private JFXTextField txtContinente;

    @FXML
    private JFXTextField txtBandeira;
    
    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private JFXButton btnListar;

    @FXML
    private Label lab;
    
    @FXML
    private TableView<Bandeira> tblPaises;
    
    @FXML
    private TableColumn<Bandeira, String> colISO;
    
    @FXML
    private TableColumn<Bandeira, String> colNome;
    
    @FXML
    private TableColumn<Bandeira, String> colCapital;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCapital.setCellValueFactory(new PropertyValueFactory<>("capital"));
        colISO.setCellValueFactory(new PropertyValueFactory<>("codISO"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomePais"));
    }

    @FXML
    private void cadastro(javafx.event.ActionEvent event) {
        counter++;
        lab.setText("Btn clicked "+counter);
        
        
        if("".equals(txtNome.getText())
                || "".equals(txtOficialNome.getText())
                || "".equals(txtCapital.getText())
                || "".equals(txtISO.getText())
                || "".equals(txtContinente.getText())
                || "".equals(txtBandeira.getText())) {
            lab.setText("Você precisa preencher todos os campos ");
        } else {
            lab.setText("Cadastro de Países");
            Bandeira b = new Bandeira();
            BandeiraDAO dao = new BandeiraDAO();
            b.setNomePais(txtNome.getText());
            b.setNomeOficial(txtOficialNome.getText());
            b.setCapital(txtCapital.getText());
            b.setCodISO(txtISO.getText());
            b.setContinente(txtContinente.getText());
            b.setBandeira(txtBandeira.getText());
            
            dao.create(b);
            
            txtNome.setText("");
            txtOficialNome.setText("");
            txtCapital.setText("");
            txtISO.setText("");
            txtContinente.setText("");
            txtBandeira.setText("");
    //      readJTable();
        }
    }
    
    
    @FXML
    private void listar(javafx.event.ActionEvent event) {
        counter++;
        System.out.println("Counter "+counter);
        Bandeira b = new Bandeira();
        BandeiraDAO dao = new BandeiraDAO();
        
        ObservableList<Bandeira> ol = FXCollections.observableList(dao.read());
        
        tblPaises.setItems(ol);
    }
}
