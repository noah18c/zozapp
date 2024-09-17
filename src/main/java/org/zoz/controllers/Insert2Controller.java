package org.zoz.controllers;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private BorderPane topPane;


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
        
        String[] data = new String[10];

        data[0] = field1.getSelectionModel().getSelectedItem();
        data[1] = field2.getText();
        data[2] = field3.getText();
        data[3] = field4.getSelectionModel().getSelectedItem();
        data[4] = getDate(field5);
        data[5] = field6.getSelectionModel().getSelectedItem();
        data[6] = field7.getText();
        data[7] = getDate(field8);
        data[8] = getDate(field9);
        data[9] = getDate(field10);

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
        saveData();
        URL url = Util.getPath("Insert3");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert3Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        saveData();
        URL url = Util.getPath("Insert1");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Insert1Controller ic = loader.getController();

        ic.setStage(stage);
        ic.render(root);
        ic.setDossier(dossier);
    }

    private void saveData(){
        Util.setTempLijst(lijst.getItems());
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

        field4.getItems().addAll(Util.getCountries());
        field4.setValue(Util.getCountries().get(8));
        field5.setPromptText("dd-mm-jjjj");
        field8.setPromptText("dd-mm-jjjj");
        field9.setPromptText("dd-mm-jjjj");
        field10.setPromptText("dd-mm-jjjj");

        String[] tabh = {"ja", "nee", "idem", "nvt", ""};

        field6.getItems().addAll(tabh);
        field6.setValue(tabh[4]);
        
        if (Util.getTempLijst() != null)
            lijst.getItems().setAll(Util.getTempLijst());
        
    }

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }
    public Dossier getDossier(){
        return this.dossier;
    }


}
