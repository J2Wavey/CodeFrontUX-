package main.java.fhgui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.database.DBUtil;
import main.java.models.Booking;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingsController {

    private Stage stage;    // Refers to the window
    private Scene scene;    // Refers to a menu
    @FXML
    private TableView<Booking> bookingsTableView;
    @FXML
    private TableColumn<Booking, Number> BookingIDColumn;
    @FXML
    private TableColumn<Booking, Date> DateColumn;
    @FXML
    private TableColumn<Booking, Time> TimeColumn;
    @FXML
    private TableColumn<Booking, String> CustomerPhoneNumberColumn;


    @FXML private Button removeButton;
@FXML private Button editButton;
@FXML private Button AddButton;

@FXML private DatePicker datePickerBookings;
@FXML private TextField timeInputTextField;
@FXML private TextField textFieldForForeName;

@FXML private TextField FieldOftextForSureName;
@FXML private TextField PhoneNumberTextField;
@FXML private Pane addBookingsPane;
@FXML private Pane EditBookingsPane;
@FXML private TextField bookingIdTextFieldEdit;
@FXML private DatePicker newDatePickerEdit;
@FXML private TextField newTimeTextFieldEdit;

    /**
     * setBookings() - TODO
     */
    public void setBookings(){
        //TODO
        System.out.println("setBookings");
    }
    @FXML
    public void initialize() {

    }

    /**
     * menuReturn() - reloads the main menu scene, and sets the window to display it.
     * @param event - a mouse click event
     * @exception IOException - thrown if the fxml cannot be loaded.
     */
    public void menuReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/fhgui/MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

@FXML protected void whenUpdateButtonIsClickec(){

        removeButton.setVisible(true);
        editButton.setVisible(true);
        AddButton.setVisible(true);
}

@FXML private void showAddBookingPane(){
    System.out.println("Show Add Booking Pane invoked.");
        addBookingsPane.setVisible(true);
}
@FXML private void closeAddBookingPane(){
timeInputTextField.clear();
textFieldForForeName.clear();
FieldOftextForSureName.clear();
PhoneNumberTextField.clear();
addBookingsPane.setVisible(false);
}
    @FXML
    private void checkAvailability() {
        System.out.println("Check Availability button clicked.");

    LocalDate date = datePickerBookings.getValue(); // Get the date from the DatePicker
        LocalTime time = LocalTime.parse(timeInputTextField.getText(), DateTimeFormatter.ofPattern("HH:mm")); // Convert input text to LocalTime

        if (isBookingSlotAvailable(date, time)) { // where the actual checking takes place

            showAlert("Available", "The selected date and time are available.", Alert.AlertType.INFORMATION); // alert if available
        } else {

            showAlert("Not Available", "The selected date and time are not available.", Alert.AlertType.ERROR); // error alert, if time and date not available
        }
    }
    @FXML
    private void confirmBooking() {
        System.out.println("Confirm Booking button clicked.");
     LocalDate date = datePickerBookings.getValue();
        LocalTime time = LocalTime.parse(timeInputTextField.getText(), DateTimeFormatter.ofPattern("HH:mm"));

        if (isBookingSlotAvailable(date, time)) {// once again checking if the booking slot is available to avoid race conditions
       String forename = textFieldForForeName.getText();
            String surname = FieldOftextForSureName.getText();
        String phoneNumber = PhoneNumberTextField.getText();

            // Insert booking into the database
            if (insertBooking(date, time, forename, surname, phoneNumber)) {
                showAlert("Success", "Booking confirmed successfully.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to confirm booking.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "The selected date and time are no longer available.", Alert.AlertType.ERROR);
        }
    }
 private boolean isBookingSlotAvailable(LocalDate date, LocalTime time) { //checking if a time slot is available on a date
     System.out.println("Checking if booking slot is available for Date: " + date + " Time: " + time);
 boolean isAvailable = false; // iniitially the booking slot is being set as false
        String checkQuery = "SELECT COUNT(*) AS slot_count FROM Booking WHERE Date = ? AND Time = ?";// sql query to check availability in the appropriate database

   try (Connection conn = DBUtil.getConnection();//// to ensure that resources are sucesssfully closd once this operation is done
   PreparedStatement preparedStatement = conn.prepareStatement(checkQuery)) {
    preparedStatement.setDate(1, java.sql.Date.valueOf(date));//  conversions happening here
            preparedStatement.setTime(2, java.sql.Time.valueOf(time));

  ResultSet resultSet = preparedStatement.executeQuery();//// executing the query, while storing the set of results

         if (resultSet.next()) {
        int slotCount = resultSet.getInt("slot_count");// retriving the number slots from the set of results
                isAvailable = (slotCount == 0);// if this counter indicator is 0 than it means there is slots available
            }
     }

 catch (SQLException e) {
       e.printStackTrace();
        }
        return isAvailable; // returning the availibilty
    }

    private boolean insertBooking(LocalDate date, LocalTime time, String forename, String surname, String phoneNumber) { // a method which will insert data into 2 different tables
        System.out.println("Inserting booking for " + forename + " " + surname + " at " + time + " on " + date);
 boolean isInsertSuccessful = false;
    String sqlForInsertingCustomers = "INSERT INTO CustomerInfo (CustomerPhoneNumber, CustomerForename, CustomerSurname) VALUES (?, ?, ?)"; // this query is to insert into the customer info table
   String sqlQueryForAddingBookingDatas = "INSERT INTO Booking (Date, Time, CustomerPhoneNumber) VALUES (?, ?, ?)";//  query to insert  into the Booking table


  try (Connection conn = DBUtil.getConnection()) {// using this block to ensure resources are closed after use

            conn.setAutoCommit(false);// this for just in case the customerinfo gets stored but due to some reason the the booking data is not stored, then that would mean we have info of the customer without their booking, causing problems


   try (PreparedStatement StatementtoInsertIntoCustomerInfo = conn.prepareStatement(sqlForInsertingCustomers)) { // storing the values of the customer dat in our databse
           StatementtoInsertIntoCustomerInfo.setString(1, phoneNumber);

           StatementtoInsertIntoCustomerInfo.setString(2, forename);

              StatementtoInsertIntoCustomerInfo.setString(3, surname);
                StatementtoInsertIntoCustomerInfo.executeUpdate();
            } catch (SQLException e) {

                e.printStackTrace(); // this operation fails
                conn.rollback();
                return false;
            }


  try (PreparedStatement bookingStatementInsert = conn.prepareStatement(sqlQueryForAddingBookingDatas)) { //  // inserting the new booking
           bookingStatementInsert.setDate(1, java.sql.Date.valueOf(date));
       bookingStatementInsert.setTime(2, java.sql.Time.valueOf(time));
                bookingStatementInsert.setString(3, phoneNumber);
     bookingStatementInsert.executeUpdate();
            } catch (SQLException e) {

                e.printStackTrace();// booking insert fails, print stack trace, rollback, and return making sure no redundant data is stored
                conn.rollback();
                return false;
            }

    conn.commit(); // if its all good we can successfully commit into both the tables

            isInsertSuccessful = true;// both the inserts were successful
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isInsertSuccessful;
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        System.out.println("Showing alert: " + title + " - " + message);
     Alert alert = new Alert(type);
      alert.setTitle(title);
        alert.setHeaderText(null);

      alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML private void showHiddedEditPane(){
    EditBookingsPane.setVisible(true);
    }
    @FXML private void closeEditPane(){EditBookingsPane.setVisible(false);}
@FXML
  private void checkEditAvailability() {

      int bookingId = Integer.parseInt( bookingIdTextFieldEdit.getText());
    LocalDate DatePickerEdit = newDatePickerEdit.getValue();
        LocalTime newTime = LocalTime.parse(newTimeTextFieldEdit.getText(), DateTimeFormatter.ofPattern("HH:mm"));// get the values from the form

        System.out.println("Checking availability for Booking ID: " + bookingId + " on " + newDatePickerEdit + " at " + newTime);

 if (isEditSlotAvailable(bookingId, DatePickerEdit, newTime)) {// here checking if the newly selected time slots are available

       showAlert("Available", "The new date and time are available. You can proceed to confirm the changes.", Alert.AlertType.INFORMATION);
        } else {

            showAlert("Not Available", "The new date and time are not available for the selected booking.", Alert.AlertType.ERROR); // alerting the user
        }
    }

    private boolean isEditSlotAvailable(int bookingId, LocalDate newDate, LocalTime newTime) {

  String checkQuery = "SELECT COUNT(*) AS slot_count FROM Booking WHERE Date = ? AND Time = ? AND BookingID != ?";// query to get booked times excep the currently booked oen
     try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {// once again setting up the connection

    pstmt.setDate(1, java.sql.Date.valueOf(newDate));
       pstmt.setTime(2, java.sql.Time.valueOf(newTime));
    pstmt.setInt(3, bookingId); // Exclude the current booking from the check

        ResultSet resultSet = pstmt.executeQuery();

       if (resultSet.next()) {
         int slotCount = resultSet.getInt("slot_count");

           return slotCount == 0;// if this is 0 then no other slots were found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

private boolean updateBooking(int bookingId, LocalDate newDate, LocalTime newTime) {
   String updateQuery = "UPDATE Booking SET Date = ?, Time = ? WHERE BookingID = ?";// setting the sql query

        try (Connection conn = DBUtil.getConnection(); // try with resources block with connection and prepared statements being initilased;
             PreparedStatement prepareStatement = conn.prepareStatement(updateQuery)) {

     prepareStatement.setDate(1, java.sql.Date.valueOf(newDate));
    prepareStatement.setTime(2, java.sql.Time.valueOf(newTime));
     prepareStatement.setInt(3, bookingId);

            int affectedRows = prepareStatement.executeUpdate();// update execution

       return affectedRows > 0;//seeing if update was successful
        } catch (SQLException e) {

     e.printStackTrace();
        }

    return false;
    }
    @FXML
    private void confirmEditBooking() {

   int bookingId = Integer.parseInt(bookingIdTextFieldEdit.getText()); // conversion to suitable format
        LocalDate newDate = newDatePickerEdit.getValue(); // grabbing new info of data from the date picker
    LocalTime newTime = LocalTime.parse(newTimeTextFieldEdit.getText(), DateTimeFormatter.ofPattern("HH:mm")); // going through and parsing the new line

    System.out.println("Attempting to update booking with ID: " + bookingId + " to new date: " + newDate + " and time: " + newTime);

   boolean updateSuccess = updateBooking(bookingId, newDate, newTime);// try to update here
 if (updateSuccess) {
            showAlert("Success", "The booking has been updated successfully.", Alert.AlertType.INFORMATION);// alerting if it was a sucess
        } else {
            // If update fails, show an error alert
            showAlert("Error", "There was a problem updating the booking. Please try again.", Alert.AlertType.ERROR);
        }
    }


}

