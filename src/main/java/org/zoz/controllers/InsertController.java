package org.zoz.controllers;

import java.io.IOException;
import java.net.URL;

import org.zoz.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InsertController implements Controller{

    private Stage stage;
    private Scene scene;

    public void render() throws IOException{
        Parent root = Util.loadFMXL("Insert1");
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();   
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    
}
