package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;

import org.zoz.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        this.stage.setScene(scene);
        this.stage.show();
    }

    
    @FXML
    public void switchScene(ActionEvent event) throws IOException {
        if(event.getSource().equals(mmButton1)){
            System.out.println("awesome");
            InsertController ic = new InsertController();
            ic.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
            ic.render();

            //stage.setScene(scene);
            //stage.show();
            
            //ic.setStage(stage);

            

            /*
            if(stage.equals(null)){

                System.out.println("alrighty");
            } else{

                System.out.println("shit");
            }
            
            ic.render();
            */
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


}