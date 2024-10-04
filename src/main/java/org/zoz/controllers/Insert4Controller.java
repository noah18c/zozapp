package org.zoz.controllers;

import java.io.IOException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDate;

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
    void verder(ActionEvent event) throws IOException{
        String result = "";
        try{
            if(validateFields()){
                addData();
                URL url = Util.getPath("Popup1");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                PopupController pc = loader.getController();
        
                pc.renderInsertPopup(root);
        
                // Retrieve the result from the PopupController
                result = pc.getResult();
                Insert1Controller ic;
        
                // Handle the result based on the button clicked
                switch (result) {
                    case "button1":
                        System.out.println("Nieuw aangifte!");
                        saveData();
                        url = Util.getPath("Insert1");
                        loader = new FXMLLoader(url);
                        root = loader.load();
                        ic = loader.getController();
        
                        ic.setStage(stage);
                        ic.render(root);
                        ic.setDossier(dossier);
                        ic.newAangifte(true);
                        break;
                    case "button2":
                        // Perform action for button2
                        System.out.println("Nieuw dossier!");
                        saveData();
        
                        url = Util.getPath("Insert1");
                        loader = new FXMLLoader(url);
                        root = loader.load();
                        ic = loader.getController();
        
                        ic.setStage(stage);
                        ic.render(root);
                        ic.setDossier(Util.createNewDossier());
                        ic.newAangifte(true);
                        break;
                    case "home":
                        System.out.println("Hoofdmenu");
                        saveData();
                        url = Util.getPath("MainMenu");
                        loader = new FXMLLoader(url);
                        root = loader.load();
                        MainMenuController mmc = loader.getController();
                        mmc.setStage(stage);
                        mmc.render(root);
                        
                        break;
                    default:
                        System.out.println("Action cancelled");
                        break;
                }
            }
        } catch(IOException e){
            System.out.println("er is een error met overschrijven, bestand is geopend!");
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Sluit het gekozen Excel-bestand!");
            alert.setContentText("Kan niet overschrijven!");
    
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.OK);
        
            //Deactivate Defaultbehavior for yes-Button:
            Button okButton = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
            okButton.setText("Ok");
            okButton.setDefaultButton( true );
            alert.showAndWait();
            /*
            URL url;
            Insert1Controller ic;
            FXMLLoader loader;
            Parent root;
            
            
            switch (result) {
                case "button1":
                    url = Util.getPath("Insert1");
                    loader = new FXMLLoader(url);
                    root = loader.load();
                    ic = loader.getController();

                    ic.setStage(stage);
                    ic.render(root);
                    ic.setDossier(dossier);
                    ic.newAangifte(true);
                    break;
                case "button2":
                    url = Util.getPath("Insert1");
                    loader = new FXMLLoader(url);
                    root = loader.load();
                    ic = loader.getController();
    
                    ic.setStage(stage);
                    ic.render(root);
                    ic.setDossier(Util.createNewDossier());
                    ic.newAangifte(true);
                    break;
                case "home":
                    url = Util.getPath("MainMenu");
                    loader = new FXMLLoader(url);
                    root = loader.load();
                    MainMenuController mmc = loader.getController();
                    mmc.setStage(stage);
                    mmc.render(root);
                    break;
                default:
                    System.out.println("nothing happened");
                    break;
            }
                    */
                    
            
            
        }
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        addData();
        URL url = Util.getPath("Insert3");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert3Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(this.dossier);
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
        this.loadData();
    }
    private void loadData() {

        if (this.dossier.getInfo2() != "") {
            String[] data = this.dossier.getInfo2().split(";");
    
            try {
                field1.setText(data[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field1 data is missing in getInfo2");
            }
    
            try {
                field2.setText(data[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field2 data is missing in getInfo2");
            }
    
            try {
                field3.setText(data[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field3 data is missing in getInfo2");
            }
    
            try {
                field4.setText(data[3]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field4 data is missing in getInfo2");
            }
    
            try {
                field5.setValue(LocalDate.parse(data[4]));
            } catch (Exception e) {
                System.out.println("Failed to parse date for field5 in getInfo2");
            }
    
            try {
                field6.setText(data[5]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Field6 data is missing in getInfo2");
            }
        }
    }

    private boolean validateFields() {
        boolean isValid = true;
    
        // Validate field4 (TextField)
        if (field4.getText().trim().isEmpty()) {
            field4.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field4.setStyle(null); // Reset the style if valid
        }
    
        // Validate field5 (DatePicker)
        if (field5.getValue() == null) {
            field5.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field5.setStyle(null); // Reset the style if valid
        }
    
        if (!isValid) {
            System.out.println("Field4 and Field5 are required. Please fill them in.");
        }
    
        return isValid;
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
