package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    void terug(ActionEvent event) throws IOException {
        Insert3Controller ic3 = new Insert3Controller();
        ic3.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic3.render();
    }

    @FXML
    void verder(ActionEvent event) {
        
    }


    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert4");

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

    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }
    public Dossier getDossier(){
        return this.dossier;
    }


}
