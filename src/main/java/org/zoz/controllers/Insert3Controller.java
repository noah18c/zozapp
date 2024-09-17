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
        addData();
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

    private void addData(){
        String[] data = new String[11];

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

        String fullData = String.join(";", data);
        dossier.setInfo1(fullData);
    }

    private void saveData(){

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


        /*
        field2.setValue(LocalDate.now());

        countries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/org/zoz/data/countries.csv"), "UTF-8"))) {
            String line;
            // Skip the header
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                countries.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ObservableList<String> observableCountries = FXCollections.observableArrayList(countries);
        field6.getItems().addAll(countries);
        field6.setValue(countries.get(8));
        */
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
    }
    public Dossier getDossier(){
        return this.dossier;
    }


}
