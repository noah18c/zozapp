package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    private DatePicker field12;

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
    private AnchorPane topPane;

    @FXML
    private Button homeButton;
    
    @FXML
    void goHome(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Hoofdmenu");
        alert.setHeaderText("U staat op het punt om naar het menu te gaan!");
        alert.setContentText("U zult ingevoerde gegevens verliezen!");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
    
        //Deactivate Defaultbehavior for yes-Button:
        Button yesButton = (Button) alert.getDialogPane().lookupButton( ButtonType.YES );
        yesButton.setText("Hoofdmenu");
        yesButton.setDefaultButton( false );
    
        //Activate Defaultbehavior for no-Button:
        Button noButton = (Button) alert.getDialogPane().lookupButton( ButtonType.NO );
        noButton.setText("Annuleren");
        noButton.setDefaultButton( true );

        if(alert.showAndWait().get() == ButtonType.YES){
            URL url = Util.getPath("MainMenu");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            MainMenuController mmc = loader.getController();
            mmc.setStage(stage);
            mmc.render(root);
        }
    }

    @FXML
    void verder(ActionEvent event) throws IOException {
        if(validateFields()){
            addData();
            URL url = Util.getPath("Insert4");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Insert4Controller ic = loader.getController();
    
            ic.setStage(stage);
            ic.render(root);
            ic.setDossier(dossier);
        }        
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        addData();
        URL url = Util.getPath("Insert2");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert2Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
    }

    private boolean validateFields() {
        boolean isValid = true;
    
        // Validate field2 (DatePicker)
        if (field2.getValue() == null) {
            field2.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field2.setStyle(null); // Reset the style if valid
        }
    
        if (!isValid) {
            System.out.println("Field2 is required. Please select a date.");
        }
    
        return isValid;
    }

    @Override
    public void render(Parent root) throws IOException{
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();   
    }

    private void addData(){
        String[] data = new String[12];

        data[0] = field1.getText();
        data[1] = getDate(field2);
        data[2] = getDate(field3);
        data[3] = getDate(field4);
        data[4] = getDate(field5);
        data[5] = getDate(field6);
        data[6] = getDate(field7);
        data[7] = getDate(field8);
        data[8] = getDate(field9);
        data[9] = getDate(field10);
        data[10] = getDate(field11);
        data[11] = getDate(field12);

        String fullData = String.join(";", data);
        dossier.setInfo1(fullData);
    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        
        

        field1.setPromptText("Naam");
        field2.setPromptText("dd-mm-jjjj");
        field3.setPromptText("dd-mm-jjjj");
        field4.setPromptText("dd-mm-jjjj");
        field5.setPromptText("dd-mm-jjjj");
        field6.setPromptText("dd-mm-jjjj");
        field7.setPromptText("dd-mm-jjjj");
        field8.setPromptText("dd-mm-jjjj");
        field9.setPromptText("dd-mm-jjjj");
        field10.setPromptText("dd-mm-jjjj");
        field11.setPromptText("dd-mm-jjjj");
        field12.setPromptText("dd-mm-jjjj");
    }


    private String getDate(DatePicker datePicker){
        if (datePicker.getValue() == null){
            return "";
        } else {
            return datePicker.getValue().toString();
        }
    }

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
        this.loadData();
    }
    
    private void loadData() {

        if (this.dossier.getInfo1() != "") {
            String[] data = this.dossier.getInfo1().split(";");
            
            try {
                field1.setText(data[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field1 data is missing");
            }
    
            try {
                field2.setValue(LocalDate.parse(data[1]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field2");
            }
    
            try {
                field3.setValue(LocalDate.parse(data[2]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field3");
            }
    
            try {
                field4.setValue(LocalDate.parse(data[3]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field4");
            }
    
            try {
                field5.setValue(LocalDate.parse(data[4]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field5");
            }
    
            try {
                field6.setValue(LocalDate.parse(data[5]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field6");
            }
    
            try {
                field7.setValue(LocalDate.parse(data[6]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field7");
            }
    
            try {
                field8.setValue(LocalDate.parse(data[7]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field8");
            }
    
            try {
                field9.setValue(LocalDate.parse(data[8]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field9");
            }
    
            try {
                field10.setValue(LocalDate.parse(data[9]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field10");
            }
    
            try {
                field11.setValue(LocalDate.parse(data[10]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field11");
            }

            try {
                field12.setValue(LocalDate.parse(data[11]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field12");
            }
        }
    }
    

    public Dossier getDossier(){
        return this.dossier;
    }


}
