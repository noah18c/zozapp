package org.zoz.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenuController implements Controller{
    @FXML
    private Button mmButton1;

    private Stage stage;
    private Scene scene;

    @Override
    public void render() throws IOException {
        Parent root = Util.loadFMXL("MainMenu");
        this.scene = new Scene(root);

        Image icon = new Image(getClass().getResourceAsStream("/org/zoz/images/Aruba_Police_Force_logo.png"));

        //String css = Util.getStyle("homestyles");
        //scene.getStylesheets().add(css);
        
        // Set the icon for the stage
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("ZOZ - free trial");
        stage.show();
    }

    
    @FXML
    public void switchScene(ActionEvent event) throws IOException {
        if(event.getSource().equals(mmButton1)){
            Insert0Controller ic = new Insert0Controller();
            ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            ic.render();
        }
    }

    @Override
    public void setStage(Stage stage){
        this.stage = stage;
    }


}