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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import org.zoz.dossier.Dossier;

public class Insert4Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;
    private Dossier dossier;

 @FXML
    private TextField field1;

    @FXML
    private TextField field2;

    @FXML
    private TextField field3;

    @FXML
    private TextField field4;

    @FXML
    private DatePicker field5;

    @FXML
    private TextArea field6;

    @FXML
    private Button nextButton;

    @FXML
    private Button terugButton;

    @FXML
    private Label titel;

    @FXML
    private BorderPane topPane;

    @FXML
    void verder(ActionEvent event) throws IOException {
        addData();
        URL url = Util.getPath("Popup1");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        PopupController pc = loader.getController();

        pc.renderInsertPopup(root);

        // Retrieve the result from the PopupController
        String result = pc.getResult();

        // Handle the result based on the button clicked
        if ("button1".equals(result)) {
            // Perform action for button1
            System.out.println("Nieuw aangifte!");
            saveData();

            url = Util.getPath("Insert1");
            loader = new FXMLLoader(url);
            root = loader.load();
            Insert1Controller ic = loader.getController();

            ic.setStage(stage);
            ic.render(root);
            ic.setDossier(dossier);
        } else if ("button2".equals(result)) {
            // Perform action for button2
            System.out.println("Nieuw dossier!");
            saveData();

            url = Util.getPath("Insert1");
            loader = new FXMLLoader(url);
            root = loader.load();
            Insert1Controller ic = loader.getController();

            ic.setStage(stage);
            ic.render(root);

            
            ic.setDossier(Util.createNewDossier());
        } else if ("cancel".equals(result)) {
            // Handle cancel action
            System.out.println("Action cancelled");
        }

        
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        URL url = Util.getPath("Insert3");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert3Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
    }

    private void addData(){
        String[] data = new String[6];

        data[0] = field1.getText();
        data[1] = field2.getText();
        data[2] = field3.getText();
        data[3] = field4.getText();
        data[4] = getDate(field5);
        data[5] = field6.getText();

        String fullData = String.join(";", data);
        dossier.setInfo2(fullData);
    }

    private void saveData() throws IOException{
        Util.saveToExcel(dossier);
    }


    @Override
    public void render(Parent root) throws IOException{
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();   
    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }
    public Dossier getDossier(){
        return this.dossier;
    }

    private String getDate(DatePicker datePicker){
        if (datePicker.getValue() == null){
            return "";
        } else {
            return datePicker.getValue().toString();
        }
    }


}
