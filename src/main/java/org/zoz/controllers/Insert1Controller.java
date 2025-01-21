package org.zoz.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;

import org.zoz.dossier.Dossier;

import java.io.FileWriter;
import java.io.File;

import java.util.ArrayList;

import java.time.LocalDate;

public class Insert1Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;
    private Dossier dossier;

    @FXML
    private TextField field1;

    @FXML
    private DatePicker field2;

    @FXML
    private TextField field3;

    @FXML
    private TextField field4;

    @FXML
    private TextField field5;

    @FXML
    private ComboBox<String> field6;

    @FXML
    private DatePicker field7;

    @FXML
    private TextField field8;

    @FXML
    private Button nextButton;

    @FXML
    private Button terugButton;

    @FXML
    private Label titel;

    @FXML
    private AnchorPane topPane;

    private boolean newAangifte;

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
            URL url = Util.getPath("Insert2Verdachte");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Insert2Controller ic = loader.getController();
    
            ic.setStage(stage);
            ic.render(root);
            ic.setDossier(dossier);

        }
        
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        URL url = Util.getPath("Insert0");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert0Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
    }

    public void render(Parent root) throws IOException{


        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

            
        stage.setOnCloseRequest(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Afsluiten");
            alert.setHeaderText("U staat op het punt om af te sluiten!");
            alert.setContentText("U zult ingevoerde gegevens verliezen!");

            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
        
            //Deactivate Defaultbehavior for yes-Button:
            Button yesButton = (Button) alert.getDialogPane().lookupButton( ButtonType.YES );
            yesButton.setText("Afsluiten");
            yesButton.setDefaultButton( false );
        
            //Activate Defaultbehavior for no-Button:
            Button noButton = (Button) alert.getDialogPane().lookupButton( ButtonType.NO );
            noButton.setText("Annuleren");
            noButton.setDefaultButton( true );

            if(alert.showAndWait().get() != ButtonType.YES){
                e.consume();
            }
        });

    }


    public void addData() throws IOException{
        if(this.newAangifte)
            dossier.addAangifte();

        ArrayList<String> data = new ArrayList<>();
        data.add(field1.getText());
        data.add(getDate(field2));
        data.add(field3.getText());
        data.add(field4.getText());
        data.add(field5.getText());
        data.add(field6.getSelectionModel().getSelectedItem());
        data.add(getDate(field7));
        data.add(field8.getText());

        String fullData = String.join(";", data);
        dossier.getAangifte().setInfo(fullData);

        System.out.println(dossier.getAangifte().getInfo());

    }

    private String getDate(DatePicker datePicker){
        if (datePicker.getValue() == null){
            return "";
        } else {
            System.out.println(datePicker.getValue().toString());
            return datePicker.getValue().toString();
        }
    }



    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        field1.setText("");
        field2.setValue(LocalDate.now());
        field3.setText("");
        field4.setText("");
        field5.setText("");
        

        field6.getItems().addAll(Util.getCountries());
        field6.setValue(Util.getCountries().get(8));

        field7.setPromptText("dd-mm-jjjj");

        field8.setText("");
    }

    private void loadData(){
        if (this.dossier.getAangiftes().size() > 0){
            String[] data = dossier.getAangifte().getInfo().split(";");
            try {
                field1.setText(data[0]);
                field3.setText(data[2]);
                field4.setText(data[3]);
                field5.setText(data[4]);
                field6.setValue(data[5]);
                try {
                    field2.setValue(LocalDate.parse(data[1]));
                } catch (Exception e) {
                    System.out.println("Not all was filled while loading dates");
                }

                try {
                    field7.setValue(LocalDate.parse(data[6]));
                } catch (Exception e) {
                    System.out.println("Not all was filled while loading dates");
                }
                
                
                field8.setText(data[7]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not all was filled while loading fields"); 
            }
        }
    }



    private boolean validateFields() {
        boolean isValid = true;
    
        // Validate field1 (TextField)
        if (field1.getText().trim().isEmpty()) {
            field1.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field1.setStyle(null);
        }
    
        // Validate field2 (DatePicker)
        if (field2.getValue() == null) {
            field2.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            field2.setStyle(null);
        }
    
        // Validate field3 (TextField)
        if (field3.getText().trim().isEmpty()) {
            field3.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            field3.setStyle(null);
        }
    
        // Validate field4 (TextField)
        if (field4.getText().trim().isEmpty()) {
            field4.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            field4.setStyle(null);
        }
    
        // Validate field5 (TextField)
        if (field5.getText().trim().isEmpty()) {
            field5.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            field5.setStyle(null);
        }
    
        if (!isValid) {
            System.out.println("Please fill in all required fields.");
        } 
    
        return isValid;
    }


    public void setDossier(Dossier dossier){
        this.dossier = dossier;
        this.loadData();
    }
    public Dossier getDossier(){
        return this.dossier;
    }

    public void newAangifte(boolean newAangifte){
        this.newAangifte = newAangifte;
    }

    public String test(){
        return "this is a test for insert1controller";
    }

}
