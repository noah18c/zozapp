package org.zoz.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.zoz.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import java.time.LocalDate;

public class Insert2Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button addButton;

    @FXML
    private TextField field1;

    @FXML
    private DatePicker field10;

    @FXML
    private TextField field2;

    @FXML
    private TextField field3;

    @FXML
    private ComboBox<?> field4;

    @FXML
    private DatePicker field5;

    @FXML
    private ComboBox<?> field6;

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
    private Button plusButton;

    @FXML
    private Button terugButton;

    @FXML
    private Label titel;

    @FXML
    private BorderPane topPane;

    @FXML
    void toInsert1(ActionEvent event) throws IOException {
        Insert1Controller ic1 = new Insert1Controller();
        ic1.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic1.render();
    }

    @FXML
    void toInsert2(ActionEvent event) {

    }

    @FXML
    void toMainMenu(ActionEvent event) throws IOException {
        MainMenuController mmc = new MainMenuController();
        mmc.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        mmc.render();
    }

    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert2");

        this.scene = new Scene(root);

        //String css = Util.getStyle("insertstyles");
        //scene.getStylesheets().add(css);

        init();

        this.stage.setScene(scene);
        stage.centerOnScreen();
        this.stage.show();   
    }


    private void init(){
        

    
    }

    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

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


}
