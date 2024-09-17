package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

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
    private Dossier dossier;

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

            URL url = Util.getPath("Insert1");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Insert1Controller ic = loader.getController();

            ic.setDossier(dossier);
            ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            ic.render(root);

            Util.saveToExcel();
        } catch (IOException e){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("ERROR!");
            a.setContentText("Ongeldig bestand!");
            a.showAndWait();
        }
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        URL url = Util.getPath("MainMenu");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        MainMenuController mmc = loader.getController();
        mmc.setStage(stage);
        mmc.render(root);
    }

    @Override
    public void render(Parent root) throws IOException{
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();   
    }


    public void saveData() throws IOException {
        dossier = new Dossier();
        Util.setExcel(field1.getText());

        double currentDossier = Util.getBottomMostCellInFirstColumn() + 1;

        dossier.setId(LocalDate.now().getYear()+"-"+((int) currentDossier));
    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        field1.setText("C:\\Users\\noahc\\Documents\\Werk\\Papa\\ZOZ V24.12 (huidig versie in gebruik).xlsm");
    }

}
