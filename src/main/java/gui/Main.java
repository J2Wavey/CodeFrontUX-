package main.java.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start() will initialise the application by creating a window, loading the login page and displaying it.
     * Certain properties will be set too: the window will be named "Lancaster's", and cannot be resized.
     * @param mainStage - the window the menu.
     * @throws Exception - will be thrown if the .fxml cannot be loaded.
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        mainStage.setTitle("Lancaster's");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    /**
     * main method to launch the application
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}