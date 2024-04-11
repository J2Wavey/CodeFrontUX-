package main.java.fhgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TablesController {

    private Stage stage;        // Refers to the window
    private Scene scene;        // Refers to a menu

    /**
     * setTableLayout() - TODO
     */
    public void setTableLayout() {
        //TODO
        System.out.println("setTableLayout");
    }

    /**
     * menuReturn() - reloads the main menu scene, and sets the window to display it.
     * @param event - a mouse click event
     * @exception - thrown if the fxml cannot be loaded.
     */
    public void menuReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/fhgui/MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
