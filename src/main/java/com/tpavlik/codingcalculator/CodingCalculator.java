package com.tpavlik.codingcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CodingCalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CodingCalculator.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setResizable(false);
        stage.setTitle("Coding calculator");
        stage.setScene(scene);
        stage.getIcons().add(new Image("calculator.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}