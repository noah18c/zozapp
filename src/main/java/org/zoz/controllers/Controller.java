package org.zoz.controllers;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface Controller {

    public void render(Parent root) throws IOException;
    public void setStage(Stage stage);
}
