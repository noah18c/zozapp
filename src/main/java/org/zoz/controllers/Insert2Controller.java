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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.zoz.dossier.Dossier;
import org.zoz.dossier.Verdachte;
public class Insert2Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;
    private Dossier dossier;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> field1;

    @FXML
    private DatePicker field10;

    @FXML
    private TextField field2;

    @FXML
    private TextField field3;

    @FXML
    private ComboBox<String> field4;

    @FXML
    private DatePicker field5;

    @FXML
    private ComboBox<String> field6;

    @FXML
    private TextField field7;

    @FXML
    private DatePicker field8;

    @FXML
    private DatePicker field9;

    @FXML
    private ComboBox<String> field11;

    @FXML
    private ListView<String> lijst;

    @FXML
    private Button minButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button opnieuwButton;

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
    void removeAll(ActionEvent event) {
        lijst.getItems().clear();
        dossier.getAangifte().getVerdachtes().clear();
    }

    @FXML
    void removeItem(ActionEvent event) {
        int verdachteInd = lijst.getSelectionModel().getSelectedIndex();
        lijst.getItems().remove(verdachteInd);
        dossier.getAangifte().removeVerdachte(verdachteInd);
    }

    
    @FXML
    void toList(ActionEvent event) {
        dossier.getAangifte().addVerdachte();

        System.out.println("Hoeveel verdachtes :"+dossier.getAangifte().getVerdachtes().size());

        ArrayList<String> data = new ArrayList<>();
        

        data.add(field1.getSelectionModel().getSelectedItem());
        data.add(field11.getSelectionModel().getSelectedItem());
        data.add(field2.getText());
        data.add(field3.getText());
        data.add(field4.getSelectionModel().getSelectedItem());
        data.add(getDate(field5));
        data.add(field6.getSelectionModel().getSelectedItem());
        data.add(field7.getText());
        data.add(getDate(field8));
        data.add(getDate(field9));
        data.add(getDate(field10));

        String fullData = String.join(";", data);

        dossier.getAangifte().getVerdachte().setInfo(fullData);
        lijst.getItems().add(dossier.getAangifte().getVerdachte().getInfo());
    }

    private String getDate(DatePicker datePicker){
        if (datePicker.getValue() == null){
            return "";
        } else {
            return datePicker.getValue().toString();
        }
    }

    @FXML
    void verder(ActionEvent event) throws IOException {
        if(validateFields()){
            URL url = Util.getPath("Insert3Voortgang");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Insert3Controller ic = loader.getController();
    
            ic.setStage(stage);
            ic.render(root);
            ic.setDossier(dossier);
        }

    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        URL url = Util.getPath("Insert1Aangifte");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert1Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
        ic.newAangifte(false);
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
        field1.getItems().addAll(Util.getIC());
        field1.setValue(Util.getIC().get(0));
        field1.setEditable(false);

        String[] feitfases = {"Voltooid feit", "Poging", "Voorbereiding",""};

        field11.getItems().addAll(feitfases);
        field11.setValue(feitfases[3]);
        field11.setEditable(false);



        field4.getItems().addAll(Util.getCountries());
        field4.setValue(Util.getCountries().get(8));
        field5.setPromptText("dd-mm-jjjj");
        field8.setPromptText("dd-mm-jjjj");
        field9.setPromptText("dd-mm-jjjj");
        field10.setPromptText("dd-mm-jjjj");

        String[] tabh = {"ja", "nee", "idem", "nvt", ""};

        field6.getItems().addAll(tabh);
        field6.setValue(tabh[4]);
        field6.setEditable(false);
        
    }

    private boolean validateFields() {
        boolean isValid = true;
    
        // Validate field1 (ComboBox)
        if (field1.getSelectionModel().isEmpty()) {
            field1.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field1.setStyle(null);
        }

        // Validate field1 (ComboBox)
        if (field11.getSelectionModel().isEmpty()) {
            field11.setStyle("-fx-border-color: red;"); // Highlight the field if empty
            isValid = false;
        } else {
            field11.setStyle(null);
        }
    
    
        // Validate lijst (ListView)
        if (lijst.getItems().isEmpty()) {
            lijst.setStyle("-fx-border-color: red;"); // Highlight the list if empty
            isValid = false;
        } else {
            lijst.setStyle(null);
        }
    
        if (!isValid) {
            System.out.println("Please select a value for field1 and ensure the list is not empty.");
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

    private void loadData(){
        if(this.dossier.getAangifte().getVerdachtes().size() > 0){
            for(Verdachte verdachte: this.dossier.getAangifte().getVerdachtes()){
                lijst.getItems().add(verdachte.getInfo());
            }

            String[] data = this.dossier.getAangifte().getVerdachte().getInfo().split(";");
            try {
                if (data[0] == "" && lijst.getItems().isEmpty()){
                    field1.setValue(data[0]);
                } else {
                    field1.setValue(Util.getIC().get(0));
                }
                
                field2.setText(data[1]);
                field3.setText(data[2]);
                field4.setValue(data[3]);
                field6.setValue(data[5]);
                field7.setText(data[6]);
                try {
                    field5.setValue(LocalDate.parse(data[4]));
                } catch (Exception e) {
                    System.out.println("Failed to parse date for field5");
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
                
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not all was filled while loading fields"); 
            }
        }

        


    }
        


}
