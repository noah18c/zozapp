package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import java.io.FileOutputStream;

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
import javafx.scene.control.ButtonType;
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
    private Dossier dossier = null;

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

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Waarschuwing!");
        alert.setHeaderText("Sluit het gekozen Excel-bestand!");
        alert.setContentText("Er kunnen fouten met het overschrijven ontstaan indien het bestand geopend is!");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.OK);
    
        //Deactivate Defaultbehavior for yes-Button:
        Button okButton = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
        okButton.setText("Ok");
        okButton.setDefaultButton( true );
        alert.showAndWait();

        try{
            saveData();

            URL url = Util.getPath("Insert1");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Insert1Controller ic = loader.getController();

            ic.setDossier(dossier);
            ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            ic.render(root);
            ic.newAangifte(true);
        } catch (IOException e){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("ERROR!");
            a.setContentText("Ongeldig bestand!");
            a.showAndWait();
            e.printStackTrace();
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
        if (Util.getExcel() != null) {
            Util.getExcel().close();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();   
        stage.setOnCloseRequest(e->{
            
        });
    }


    public void saveData() throws IOException {
        System.out.println("current path:"+field1.getText());
        Util.setExcel(field1.getText());
        if(dossier == null)
            dossier = Util.createNewDossier();
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
