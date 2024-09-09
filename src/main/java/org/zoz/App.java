package org.zoz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import org.zoz.controllers.MainMenuController;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainMenuController mmc = new MainMenuController();
        mmc.setStage(primaryStage);
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