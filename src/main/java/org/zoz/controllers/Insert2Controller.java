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

        if (field5.getValue() == null){
            field5.setValue(LocalDate.now());
        }
        if (field8.getValue() == null){
            field8.setValue(LocalDate.now());
        }
        if (field9.getValue() == null){
            field9.setValue(LocalDate.now());
        }
        if (field10.getValue() == null){
            field10.setValue(LocalDate.now());
        }


        dossier.getAangifte().getVerdachte().setInfo(field1.getSelectionModel().getSelectedItem()+";"+
                                                    field2.getText()+";"+
                                                    field3.getText()+";"+
                                                    field4.getSelectionModel().getSelectedItem()+";"+ 
                                                    field5.getValue().toString()+";"+
                                                    field6.getSelectionModel().getSelectedItem()+";"+
                                                    field7.getText()+";"+
                                                    field8.getValue().toString()+";"+
                                                    field9.getValue().toString()+";"+
                                                    field10.getValue().toString());

        lijst.getItems().add(dossier.getAangifte().getVerdachte().getInfo());
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
