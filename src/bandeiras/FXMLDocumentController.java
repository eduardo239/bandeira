package bandeiras;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.BandeiraDAO;
import model.bean.Bandeira;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

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
    private JFXTextField txtId;
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
    @FXML
    private TableColumn<Bandeira, String> colID;
    @FXML
    private Label lblImagePath;
    @FXML
    private ImageView imgView;

    BandeiraDAO dao = new BandeiraDAO();
    List<String> lstFiles;

    // - - - - - - - - - - - - - - INIT - - - - - - - - - - - - - - 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // - - - - - tentativa de criar tabela se não existir - - -
        dao.createTable();
        try {
            // - - - - - - - - load table - - - - - - - - -
            colID.setCellValueFactory(new PropertyValueFactory<>("idPais"));
            colCapital.setCellValueFactory(new PropertyValueFactory<>("capital"));
            colISO.setCellValueFactory(new PropertyValueFactory<>("codISO"));
            colNome.setCellValueFactory(new PropertyValueFactory<>("nomePais"));
            // - - - - - - - - select * from - - - - - - - - -
            ObservableList<Bandeira> ol = FXCollections.observableList(dao.read());
            // - - - - - - - - insert data - - - - - - - - -
            tblPaises.setItems(ol);
            // - - - - - - - - order by - - - - - - - - -
            colID.setSortType(TableColumn.SortType.ASCENDING);
            // - - - - - - - - image type - - - - - - - - -
            lstFiles = new ArrayList<>();
            lstFiles.add("*.jpg");
            lstFiles.add("*.png");
            lstFiles.add("*.bmp");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    @FXML
    public void onUpdateList() {
        // - - - - - - - - load data - - - - - - - - -
        ObservableList<Bandeira> ol = FXCollections.observableList(dao.read());
        tblPaises.setItems(ol);
    }

    @FXML
    private void onUpdate(ActionEvent event) {

        Bandeira b = new Bandeira();
        b = tblPaises.getSelectionModel().getSelectedItem();

        if ("".equals(txtNome.getText())
                || "".equals(txtOficialNome.getText())
                || "".equals(txtCapital.getText())
                || "".equals(txtISO.getText())
                || "".equals(txtContinente.getText())
                || "".equals(lblImagePath.getText())) {
            lab.setTextFill(Color.ORANGE);
            lab.setText("Selecione um item da lista para atualizar e adicione uma imagem.");

        } else if (txtISO.getText().length() > 3) {
            lab.setTextFill(Color.ORANGE);
            lab.setText("O código ISO deve ter no máximo 3 caracteres.");

        } else {
            try {

                lab.setTextFill(Color.GREEN);
                b.setNomePais(txtNome.getText());
                b.setNomeOficial(txtOficialNome.getText());
                b.setCapital(txtCapital.getText());
                b.setCodISO(txtISO.getText());
                b.setContinente(txtContinente.getText());
                b.setBandeira(new SerialBlob(lblImagePath.getText().getBytes()));

                // - - - - - - - - update table  - - - - - - - - -
                dao.update(b);

            } catch (SQLException ex) {
                System.out.println("Erro ao selecionar " + ex);
            } finally {
                // - - - - - - - - update list - - - - - - - - -
                lab.setTextFill(Color.GREEN);
                lab.setText("Cadastro atualizado com sucesso.");
                this.onUpdateList();
                this.clear();

            }
        }
    }

    // - - - - - - - - - - - - - - INSERT - - - - - - - - - - - - - - 
    @FXML
    private void onRegister(ActionEvent event) {

        if ("".equals(txtNome.getText())
                || "".equals(txtOficialNome.getText())
                || "".equals(txtCapital.getText())
                || "".equals(txtISO.getText())
                || "".equals(txtContinente.getText())
                || "".equals(lblImagePath.getText())
                || "".equals(imgView.getImage())) {
            
            lab.setTextFill(Color.ORANGE);
            lab.setText("É necessário preencher todos os campos e carregar a imagem.");

        } else if (txtISO.getText().length() > 3) {
            lab.setTextFill(Color.ORANGE);
            lab.setText("O código ISO deve ter no máximo 3 caracteres.");

        } else {
            try {

                lab.setTextFill(Color.GREEN);
                Bandeira b = new Bandeira();
                b.setNomePais(txtNome.getText());
                b.setNomeOficial(txtOficialNome.getText());
                b.setCapital(txtCapital.getText());
                b.setCodISO(txtISO.getText());
                b.setContinente(txtContinente.getText());
                b.setBandeira(new SerialBlob(lblImagePath.getText().getBytes()));

                // - - - - - - - - insert into - - - - - - - - -
                dao.create(b);

            } catch (SQLException ex) {
                System.out.println("Erro ao selecionar " + ex);
            } finally {
                lab.setText("Cadastro realizado com sucesso.");
                this.onUpdateList();
                this.clear();

            }
        }
    }

    // - - - - - - - - - - - - - - CLICK ROW - - - - - - - - - - - - - - 
    @FXML
    private void onRowClick(MouseEvent event) {

        Bandeira b = new Bandeira();
        b = tblPaises.getSelectionModel().getSelectedItem();

        // - - - - - - - - load data - - - - - - - - -
        dao.readById(b);
        try {
            if (b.getIdPais() != -1) {
                txtCapital.setText(b.getCapital());
                txtNome.setText(b.getNomePais());
                txtOficialNome.setText(b.getNomeOficial());
                txtISO.setText(b.getCodISO());
                txtContinente.setText(b.getContinente());
                txtId.setText(String.valueOf(b.getIdPais()));
                imgView.setImage(b.getImage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao selecionar " + e);
        }

    }

    // - - - - - - - - - - - - - -LIMPAR CAMPOS - - - - - - - - - - - - - - 
    @FXML
    private void onClear(ActionEvent event) {
        this.clear();
    }

    // - - - - - - - - - - - - - - IMG PICKER - - - - - - - - - - - - - - 
    @FXML
    private void onImagePicker(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image files", lstFiles));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            // - - - - - - - - image path - - - - - - - - -
            String path = f.getAbsolutePath();
            lblImagePath.setText(f.getAbsolutePath());

            // - - - - - - - - set image - - - - - - - - -
            File file = new File(path);
            Image image = new Image(file.toURI().toString());
            imgView.setImage(image);
        }

    }

    // - - - - - - - - - - - - - - DELETE - - - - - - - - - - - - - - 
    @FXML
    private void onDelete(ActionEvent event) {

        if ("".equals(txtNome.getText())
                || "".equals(txtId.getText())) {
            lab.setTextFill(Color.ORANGE);
            lab.setText("Selecione um item da lista para apagar e adicione uma imagem.");
        } else {
            try {
                // - - - - - - - - tela de confirmação - - - - - - - - -
                int a = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar?");
                if (a == JOptionPane.YES_OPTION) {
                    Bandeira b = new Bandeira();
                    b.setIdPais(Integer.parseInt(txtId.getText()));
                    
                    // - - - - - - - - delete - - - - - - - - -
                    dao.delete(b);
                    
                    lab.setTextFill(Color.GREEN);
                    lab.setText("Cadastro apagado com sucesso.");
                    this.onUpdateList();
                    this.clear();
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao deletar : " + e);
            } finally {
            }
        }

    }

    // - - - - - - - - - - - - - - HELP - - - - - - - - - - - - - - 
    @FXML
    void windowHelp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            // - - - - - - - - open help window - - - - - - - - -
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void clear() {
        txtNome.setText("");
        txtOficialNome.setText("");
        txtCapital.setText("");
        txtISO.setText("");
        txtContinente.setText("");
        txtBandeira.setText("");
        txtId.setText("");
        lblImagePath.setText("");
    }
}
