package org.zoz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
       
        this.stage = primaryStage;

        MainMenuController mmc = new MainMenuController();

        System.out.println("Lit");
        mmc.setStage(stage);
        mmc.render();

    }


    public static Parent loadFXML(String fxml) {

        try {
            String fxmlpath = "/org/zoz/fxml/" + fxml + ".fxml";
            System.out.println("Trying to load from:"+fxmlpath);
            Parent root = FXMLLoader.load(App.class.getResource(fxmlpath));
            return root;
        } catch (IOException e) {
            System.out.println("Cannot load FXML file!");
            return null;
        }
            
 
    }

    public static void main(String[] args) {
        launch();
    }

}