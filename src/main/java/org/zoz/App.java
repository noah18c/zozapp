package org.zoz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import org.zoz.controllers.MainMenuController;
import org.zoz.controllers.Util;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //init stuff
        Util.loadCountries();
        Util.loadIC();

        URL url = Util.getPath("MainMenu");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        MainMenuController mmc = loader.getController();
        mmc.setStage(primaryStage);
        mmc.render(root);

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