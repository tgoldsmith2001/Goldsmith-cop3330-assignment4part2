package ucf.assignments;/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url= getClass().getResource("/hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}