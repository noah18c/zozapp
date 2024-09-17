package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;

import org.zoz.dossier.Dossier;

public class Insert3Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;
    private Dossier dossier;


    
    @FXML
    private TextField field1;

    @FXML
    private DatePicker field10;

    @FXML
    private DatePicker field11;

    @FXML
    private DatePicker field2;

    @FXML
    private DatePicker field3;

    @FXML
    private DatePicker field4;

    @FXML
    private DatePicker field5;

    @FXML
    private DatePicker field6;

    @FXML
    private DatePicker field7;

    @FXML
    private DatePicker field8;

    @FXML
    private DatePicker field9;

    @FXML
    private Button nextButton;

    @FXML
    private Label status;

    @FXML
    private Button terugButton;

    @FXML
    private Label titel;

    @FXML
    private BorderPane topPane;

    @FXML
    void verder(ActionEvent event) throws IOException {
        saveData();
        URL url = Util.getPath("Insert4");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert4Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        saveData();
        URL url = Util.getPath("Insert2");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert2Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
    }

    @Override
    public void render(Parent root) throws IOException{
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();   
    }

    private void saveData(){

    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }
    public Dossier getDossier(){
        return this.dossier;
    }


}
