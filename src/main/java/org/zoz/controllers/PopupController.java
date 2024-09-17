package org.zoz.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PopupController{

    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Label promptText;

    private String result;

    @FXML
    void buttonClicked(ActionEvent event) {
        if (event.getSource() == button1) {
            result = "button1";
        } else if (event.getSource() == button2) {
            result = "button2";
        } else if (event.getSource() == button3) {
            result = "cancel";
        }
        stage.close();
    }

    public String getResult(){
        return this.result;
    }



    public void renderInsertPopup(Parent root) throws IOException{
        stage = new Stage();
        scene = new Scene(root);

        Image icon = new Image(getClass().getResourceAsStream("/org/zoz/images/Aruba_Police_Force_logo.png"));

        button1.setText("Nieuwe Aangifte");
        button2.setText("Nieuw Dossier");
        button3.setText("Annuleren");

        promptText.setText("Hoe wilt u verdergaan?");

        // Set the icon for the stage
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.showAndWait();
    }




}