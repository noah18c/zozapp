package org.zoz.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.ArrayList;

import java.time.LocalDate;

public class Insert1Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;

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
        Insert2Controller ic2 = new Insert2Controller();
        ic2.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic2.render();
    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        MainMenuController mmc = new MainMenuController();
        mmc.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        mmc.render();
    }

    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert1");

        this.scene = new Scene(root);

        this.stage.setScene(scene);
        stage.centerOnScreen();
        this.stage.show();   
    }


    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        field2.setValue(LocalDate.now());
        field7.setPromptText("dd-mm-jjjj");
        

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
    }

}
