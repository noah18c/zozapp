package org.zoz;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController{


    private Stage stage;

    
    public void render(){
        Parent root = App.loadFXML("MainMenu");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


}