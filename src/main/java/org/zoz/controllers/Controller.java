package org.zoz.controllers;

import java.io.IOException;

import javafx.stage.Stage;

public interface Controller {

    public void render() throws IOException;
    public void setStage(Stage stage);
    
}
