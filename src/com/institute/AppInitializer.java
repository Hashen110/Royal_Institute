package com.institute;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/MainForm.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Royal Institute");
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
