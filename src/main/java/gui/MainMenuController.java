package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;    // Refers to the window
    private Scene scene;    // Refers to a menu

    @FXML
    private Label ordersInfo;   // displays info regarding the "Orders" button
    @FXML
    private Label tableInfo1;   // displays info regarding the "Table Layout" button
    @FXML
    private Label tableInfo2;   // displays more info regarding the "Table Layout" button
    @FXML
    private Label bookingsInfo; // // displays info regarding the "Bookings" button

    /**
     * logout() - loads the LoginPage fxml and displays it (i.e. returns to the login page).
     * @param event - a mouse click
     * @throws IOException - thrown if the fxml cannot be found
     */
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * ordersScene() -
     * loads the Orders page after clicking the Orders button
     * @param event - a mouse click
     * @throws IOException - thrown if the fxml cannot be found
     */
    public void ordersScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * tablesScene() -
     * loads the Tables page after clicking the Table Layout button
     * @param event - a mouse click
     * @throws IOException - thrown if the fxml cannot be found
     */
    public void tablesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tables.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * bookingsScene() -
     * loads the Bookings page after clicking the Bookings button
     * @param event - a mouse click
     * @throws IOException - thrown if the fxml cannot be found
     */
    public void bookingsScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Bookings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * displayOrdersInfo() - displays the description of the Orders page when hovering over the Orders button.
     */
    public void displayOrdersInfo() {
        ordersInfo.setText("View and retrieve current orders");
    }

    /**
     * hideOrdersInfo() - hides the description of the Orders page when the mouse is no longer hovering over the
     * Orders button (in fact, replaces it with a whitespace, which cannot be seen).
     */
    public void hideOrdersInfo() {
        ordersInfo.setText(" ");
    }

    /**
     * displayTableInfo() - displays the description of the Tables page when hovering over the Table Layout button.
     */
    public void displayTableInfo() {
        tableInfo1.setText("View and/or adjust the layout of the tables.");
        tableInfo2.setText("View reservations corresponding to each table.");
    }

    /**
     * hideTableInfo() - hides the description of the Tables page when the mouse is no longer hovering over the
     * Table Layout button (in fact, replaces it with a whitespace, which cannot be seen).
     */
    public void hideTableInfo() {
        tableInfo1.setText(" ");
        tableInfo2.setText(" ");
    }

    /**
     * displayBookingsInfo() - displays the description of the Bookings page when hovering over the Bookings button.
     */
    public void displayBookingsInfo() {
        bookingsInfo.setText("View bookings from walk-ins and reservations");
    }

    /**
     * hideBookingsInfo() - hides the description of the Bookings page when the mouse is no longer hovering over the
     * Bookings button (in fact, replaces it with a whitespace, which cannot be seen).
     */
    public void hideBookingsInfo() {
        bookingsInfo.setText(" ");
    }
}
