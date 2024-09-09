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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;
public class Insert2Controller implements Controller, Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<?> field1;

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
    private ListView<?> lijst;

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

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    
    @FXML
    void toList(ActionEvent event) {

    }

    @FXML
    void terug(ActionEvent event) throws IOException {
        Insert1Controller ic1 = new Insert1Controller();
        ic1.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic1.render();
    }


    @FXML
    void verder(ActionEvent event) throws IOException {
        Insert3Controller ic3 = new Insert3Controller();
        ic3.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        ic3.render();
    }



    @Override
    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert2");

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
        field5.setPromptText("dd-mm-jjjj");
        field8.setPromptText("dd-mm-jjjj");
        field9.setPromptText("dd-mm-jjjj");
        field10.setPromptText("dd-mm-jjjj");

        String[] tabh = {"ja", "nee", "idem", "nvt", ""};

        field6.getItems().addAll(tabh);
        field6.setValue(tabh[4]);

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
