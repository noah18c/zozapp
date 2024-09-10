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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
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
    private BorderPane topPane;

    private ArrayList<String> countries;

    @FXML
    void verder(ActionEvent event) throws IOException {
        saveData();

        Insert2Controller ic2 = new Insert2Controller();
        ic2.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic2.render();

    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        Insert0Controller ic0 = new Insert0Controller();
        ic0.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic0.render();
    }

    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert1");
        this.scene = new Scene(root);

        this.stage.setScene(scene);
        stage.centerOnScreen();
        this.stage.show();   
    }

    public void render(Parent root) throws IOException{
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public void saveData() throws IOException{
        dossier.addAangifte();
        //dossier.addInfo(Util.getDossier().




        File file = new File("src/main/resources/org/zoz/data/aangifte.csv");
        boolean fileExists = file.exists();
        FileWriter writer = new FileWriter(file, true);
        
        if(!fileExists || file.length() == 0){
            writer.write("Dossiernr;Mutatienr;Aangifte d.d.;District van PD;Straatnaam_plaatsdelict;Aangever-Voornamen/ACHTERNAAM;Aangever-Geboorteland;Aangever-Geboortedatum;Aangifte opgenomen door (verb)\n");
        }


        if (field7.getValue() == null){
            field7.setValue(LocalDate.now());
        }


        writer.write(395+";"+
                    field1.getText()+";"+
                    field2.getValue().toString()+";"+
                    field3.getText()+";"+
                    field4.getText()+";"+ 
                    field5.getText()+";"+
                    field6.getSelectionModel().getSelectedItem()+";"+
                    field7.getValue().toString()+";"+
                    field8.getText()+"\n");
        System.out.println("Data Saved succesfully!");
        
        writer.close();
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

        field6.getItems().addAll(countries);
        field6.setValue(countries.get(8));

        field7.setPromptText("dd-mm-jjjj");

        field8.setText("");
    }

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }
    public Dossier getDossier(){
        return this.dossier;
    }

    public String test(){
        return "this is a test for insert1controller";
    }

}
