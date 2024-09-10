package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.ResourceBundle;

import org.zoz.dossier.Dossier;

import java.io.File;

public class Insert0Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField field1;

    @FXML
    private Button fileChooser;

    @FXML
    private Button nextButton;

    @FXML
    private Button terugButton;

    @FXML
    private Label titel;

    @FXML
    private BorderPane topPane;

    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Kies ZOZ-excell bestand");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        //.Show the file chooser dialog
        Stage stage = (Stage) fileChooser.getScene().getWindow(); // Get the current stage
        File selectedFile = fc.showOpenDialog(stage);

        // If a file was selected, set its path to the text field
        if (selectedFile != null) {
            field1.setText(selectedFile.getAbsolutePath());
        } else {
            field1.setText("No file selected");
        }
    }


    @FXML
    void verder(ActionEvent event) {
        try{
            saveData();

            //URL url = Util.getPath("Insert1");
            String fxml = "Insert1";

            Dossier dossier = new Dossier();
            dossier.addInfo("status", "single");
            

            //System.out.println(url);

            /*
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/zoz/fxml/"+fxml+".fxml"));

            Parent root = loader.load();
            Insert1Controller ic = loader.getController();

            
            System.out.println(ic.getDossier().getInfo().get("status"));

            //ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            //ic.render(root);
            */

            Insert1Controller ic = new Insert1Controller();
            ic.setDossier(dossier);

            //System.out.println(ic.getDossier().getInfo().get("status"));
            ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            ic.render();
        } catch (IOException e){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("ERROR!");
            a.setContentText("Ongeldig bestand!");
            a.showAndWait();
        }
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        MainMenuController mmc = new MainMenuController();
        mmc.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        mmc.render();
    }

    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert0");
        this.scene = new Scene(root);

        this.stage.setScene(scene);
        stage.centerOnScreen();
        this.stage.show();   
    }


    public void saveData() throws IOException {
        Util.setExcel(field1.getText());

        double currentDossier = Util.getBottomMostCellInFirstColumn() + 1;

        Util.setDossier((int) currentDossier);

        System.out.println(Util.getDossier());
    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        field1.setText("D:\\Users\\noahc\\Documenten\\Werk\\Papa\\ZOZ V24.12 (huidig versie in gebruik).xlsm");
    }

}
