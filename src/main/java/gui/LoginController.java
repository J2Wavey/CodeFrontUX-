package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public Button loginButton;  // used to confirm the username and password entered to log in.
    public String username = "Lancaster";   // username used for logging in
    public String password = "123456";      // password used for logging in
    private Stage stage;    // A Stage and Scene field will be present in all the menus
    private Scene scene;    // These fields are used to refer to the stage and change its scene (the current menu)
    @FXML
    private TextField userField;    // text box for typing the username
    @FXML
    private TextField passwordField;    // text box for typing the password
    @FXML
    private Label loginStatus;  // if the login credentials are incorrect, this label will be displayed to let the user know

    /**
     * onLoginEnter() - checks if the entered username AND password match the username and password fields. If they do,
     * load the next scene, which is the main menu in this case. The scene of the stage will be set to the main menu
     * and then displayed. If the credentials do not match the aforementioned fields, a label will be displayed with
     * text: "Invalid credentials".
     * @param event - this method responds to an event - a click in this case.
     * @throws IOException - thrown if the fxml file cannot be found.
     */
    public void onLoginEnter(ActionEvent event) throws IOException {
        if (userField.getText().equals(username) && passwordField.getText().equals(password)) {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            loginStatus.setText("Invalid credentials");
        }
    }
}
