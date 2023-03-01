package org.scienceleadership.filmsgui2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FilmsGUI extends Application {
    FilmsController myController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FilmsGUI.class.getResource("FilmsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 631, 567);
        myController = fxmlLoader.getController();
        stage.setTitle("Top Films List");
        stage.setScene(scene);
        stage.show();
    }

    public void stop() throws Exception {
        myController.saveData();
    }

    public static void main(String[] args) {
        launch();
    }
}