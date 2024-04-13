package main.java.fhgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.database.DBUtil;
import javafx.scene.control.Button;
import javax.swing.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersController {

    private Stage stage;        // Refers to the window
    private Scene scene;
    @FXML
    private ComboBox<String > comboBoxForDishName;

@FXML
   private ComboBox<String > comboBoxForStatusOfOrder;
@FXML
private Button buttonForPlacingOrder;

@FXML
    private JLabel totalOrders;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;// Refers to a menu
@FXML private Pane addingNewOrderFormPane;
@FXML private Pane removeOrderPane;

@FXML private TextField orderIDTextFieldForRemove;
@FXML private Spinner<Integer> quantitySpinner;
@FXML private TextField tableNumberField;

@FXML private Pane manageOrderPane;
@FXML private TextField orderIdTextFieldForManage;
@FXML private ComboBox<String> orderStatusComboBoxForManage;
@FXML private  Button confirmManageButton;
@FXML private Button closeManageButton;

@FXML private Pane editOrderPane;
@FXML private TextField editOrderIdTextField;
@FXML private ComboBox<String> editDishComboBox;
@FXML private TextField editTableNumberField;


@FXML
public void initialize(){
    populatingOFtheDishComboBox();
    populatingOFtheStatusComboBox();
    populatingOFtheStatusManageComboBox();
    populatingOFtheEditDishComboBox();
    SpinnerValueFactory<Integer> rangeOfValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);
    this.quantitySpinner.setValueFactory(rangeOfValues);
}

    /**
     * manageOrders() - TODO
     */


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

    public void manageOrders() {
        String sql = "SELECT COUNT(OrderID) FROM Orders";

        connect = DBUtil.getConnection();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(OrderID)");
            }

            totalOrders.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlingOfAddOrderPaneForm(){ // makes a hidden pane visible to act as a form for adding / taking orders

        addingNewOrderFormPane.setVisible(true);
    }
    @FXML
    private void handlingOfClosingAddOrderForm(){// makes a hidden pane which was visible now back to invisible

        addingNewOrderFormPane.setVisible(false);
    }
    private void populatingOFtheDishComboBox(){
String queryTODatabase = "SELECT DishName FROM Dish"; // an sql query to which selects all dish names from the table Dish
        try( // using try with resources maiking sre that resources are closed after the method is done with them
          Connection connection = DBUtil.getConnection();
PreparedStatement preparedStatement =connection.prepareStatement(queryTODatabase);
ResultSet setofResult = preparedStatement.executeQuery()
                ){
while (setofResult.next()){ // here iterating through the set of results returned from the sql query
    String nameOfDish = setofResult.getString("DishName");

    comboBoxForDishName.getItems().add(nameOfDish); // adding the retireved Dishname to the comboBox list of items


}

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
    private void populatingOFtheEditDishComboBox(){
        String queryTODatabase = "SELECT DishName FROM Dish"; // an sql query to which selects all dish names from the table Dish
        try( // using try with resources maiking sre that resources are closed after the method is done with them
             Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement(queryTODatabase);
             ResultSet setofResult = preparedStatement.executeQuery()
        ){
            while (setofResult.next()){ // here iterating through the set of results returned from the sql query
                String nameOfDish = setofResult.getString("DishName");

                editDishComboBox.getItems().add(nameOfDish); // adding the retireved Dishname to the comboBox list of items


            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    private void populatingOFtheStatusComboBox(){ // this method is purely for retrieving and displaying the options available for status of ordrs
        String [ ] fourStatusesForAnOrder = new String[ ]{ // an array containing the possible status for the orders
                "Pending","In Progress","Completed", "Cancelled"
        };

        for ( String StatusOFOrder : fourStatusesForAnOrder){ // a for each loop going thorugh the array containing the necessary statuses
            comboBoxForStatusOfOrder.getItems().add(StatusOFOrder);
        }
    }
    private void populatingOFtheStatusManageComboBox(){ // this method is purely for retrieving and displaying the options available for status of ordrs
        String [ ] fourStatusesForAnOrder = new String[ ]{ // an array containing the possible status for the orders
                "Pending","In Progress","Completed", "Cancelled"
        };

        for ( String StatusOFOrder : fourStatusesForAnOrder){ // a for each loop going thorugh the array containing the necessary statuses
            orderStatusComboBoxForManage.getItems().add(StatusOFOrder);
        }
    }
    @FXML
    private void handilingofThePlacedOrder(ActionEvent Ae){ // method for getting the values in the given combo boxes and fields, comes into action when the place order button is pressed

        String dishThatIsSelected = comboBoxForDishName.getSelectionModel().getSelectedItem();
        String statusOfOrder = comboBoxForStatusOfOrder.getSelectionModel().getSelectedItem();

        int quantity =  quantitySpinner.getValue();
int tableNumber =  Integer.parseInt(tableNumberField.getText());

savingOrderToDataBase(dishThatIsSelected,statusOfOrder,quantity,tableNumber);


    }
private void savingOrderToDataBase(String nameOfDish, String StatusOfORder, int quanityOfDish, int tableNumber ) {// this is a method for  saving the order to the database

    String sqlForInsertion = "INSERT INTO Orders (Status, TableNumber, DishName, Quantity) VALUES (?, ?, ?, ?)"; // the necessary sql query

    try (Connection connection = DBUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsertion)
    ) {
preparedStatement.setString(1, StatusOfORder); // these are setting the parameters for the prepared statements , based on the details that this method will recieve from it's parameters
preparedStatement.setInt(2, tableNumber);
preparedStatement.setString(3, nameOfDish);
preparedStatement.setInt(4, quanityOfDish);

int rowsAffected = preparedStatement.executeUpdate();

if ( rowsAffected > 0){ // this is if the insertion was successfull
    System.out.println("Order inserted successfully.");
clearTheForm();
showingAlertForCOnfirmation("Order Saved", "This order has been successfuly stored in the Database");
} else {

    alertForError("Error", "There is an Error in the data");
}


    }
catch (SQLException e){
        e.printStackTrace();
    }
}

private void clearTheForm(){ // this is for clearing the hidden pane form once the order has been sent to the database
        tableNumberField.clear();
        comboBoxForDishName.getSelectionModel().clearSelection();
        comboBoxForStatusOfOrder.getSelectionModel().clearSelection();
        quantitySpinner.getValueFactory().setValue(1);
}

@FXML
private void showingRemoveOrderForm(){

        removeOrderPane.setVisible(true);
}
@FXML
private void handlingConfirmForRemove(){
  try{
      int OrderID = Integer.parseInt(orderIDTextFieldForRemove.getText().trim()); // retireving the order ID which would have been entered by the user

      removeOrderFromDatabase(OrderID);
  }catch (NumberFormatException numberFormatException){

      alertForError("Not Valid OrderID", "Please enter valid Order ID ");
  }

}
private void removeOrderFromDatabase ( int OrderID){ // the logic method for removing an order

        String sqlForDeletion ="DELETE FROM Orders WHERE OrderID = ?";

        try(
                Connection connection = DBUtil.getConnection();// setting connection with try with blocks
                PreparedStatement preparedStatement = connection.prepareStatement(sqlForDeletion)

        ){
            preparedStatement.setInt(1, OrderID);

            int detailOnAffecteddRow = preparedStatement.executeUpdate();// gets the numbr of affected rows when update is executed

            if(detailOnAffecteddRow>0){

                showingAlertForCOnfirmation("Order Removed", "The Order has been successfully removed.");
            }else {
                alertForError("Not Found For Order","The orderfor the provided OrderID was not found");
            }
        }catch (SQLException sqlException){

            alertForError("Error in Database", " An error ocurred when trying to access the database.");
        }
}



private void showingAlertForCOnfirmation ( String header, String content){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}

private void alertForError(String headerOfAlert, String contentNeededForAlert){

        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerOfAlert);
        alert.setContentText(contentNeededForAlert);
        alert.showAndWait();
}
@FXML
private void handlingOfClosingRemoveOrderForm(){

        removeOrderPane.setVisible(false);
}
@FXML private void showingOFManageForm(){

        manageOrderPane.setVisible(true);
}

@FXML private void closingOfMangeForm(){
        orderIdTextFieldForManage.clear();
        orderStatusComboBoxForManage.getSelectionModel().clearSelection();
        manageOrderPane.setVisible(false);
}

@FXML private void confirmButtonFunctionality(){

        try{
            int idOfOrder =Integer.parseInt(orderIdTextFieldForManage.getText().trim());
      String updatedStatus = orderStatusComboBoxForManage.getSelectionModel().getSelectedItem();

            updatingTheStatusOfOrder(idOfOrder,updatedStatus);
     manageOrderPane.setVisible(false);
        }catch (NumberFormatException e){
            e.printStackTrace();
            alertForError("Error in the Format", "There was an error in the way in which a field was passed ");
        }
}
private void updatingTheStatusOfOrder(int orderID, String updatedStatus){
 String sqlForUpdating ="UPDATE Orders SET Status = ? WHERE OrderID = ?";

 try ( Connection connection = DBUtil.getConnection();// setting connection with try with blocks
       PreparedStatement preparedStatement = connection.prepareStatement(sqlForUpdating) ){

     preparedStatement.setString(1,updatedStatus);
     preparedStatement.setInt(2, orderID);

     int rowsGettingAffected = preparedStatement.executeUpdate();

     if(rowsGettingAffected>0 ){
         showingAlertForCOnfirmation("Status Of Order Updated ", "The Order has been successfully updated.");

     }else {
         alertForError("Not Found For OrderID","The order for the provided OrderID was not found");
     }
 }catch (SQLException sqlException){
     sqlException.printStackTrace();
     alertForError("Error in Database", " An error ocurred when trying to access the database.");
 }



}

@FXML private void showingEditPaneForm(){

        editOrderPane.setVisible(true);
}


@FXML private void handlinfConfirmForEdit(){
        String orderIdStr = editOrderIdTextField.getText().trim();
    String newDish = editDishComboBox.getValue();
    String newTableNumberStr = editTableNumberField.getText().trim();
    if (!orderIdStr.isEmpty())   {

        int idOfOrder = Integer.parseInt(orderIdStr);
        Integer newTableNumber = null; // setting default as null due to the this functionlaity of OR


        if (!newTableNumberStr.isEmpty()) { // but here if it is not null parsing it as integer
            newTableNumber = Integer.parseInt(newTableNumberStr);
        }
editOrder(idOfOrder,newDish,newTableNumber);
    }
}
private void editOrder(int orderId, String updatedDish, Integer newUpdatedTableNumber ){// for tablenumber using Integer rather than int because Integer allows for null values, and in this scenario we need null values

StringBuilder builderOfSQLQuery= new StringBuilder("UPDATE Orders SET ");
    List<Object> trackingParameters = new ArrayList<>();
    boolean commaNeeded = false;

    if (updatedDish!=null&& !updatedDish.isEmpty()){ // this is checking if new updated dish has been provided in the dedicated area then proceeding to build the sql query from what is provided

        builderOfSQLQuery.append("DishName = ?");
        trackingParameters.add(updatedDish);
        commaNeeded=true;
    }

    if (newUpdatedTableNumber !=null){
        if (commaNeeded){
            builderOfSQLQuery.append(", ");
        }
builderOfSQLQuery.append("TableNumber = ?");

        trackingParameters.add(newUpdatedTableNumber);
    }

builderOfSQLQuery.append(" WHERE OrderID = ?");// finishing the sql statement

    trackingParameters.add(orderId);

    String finalSqlQuery = builderOfSQLQuery.toString();

    try (Connection connection = DBUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(finalSqlQuery)){
        for(int y =0; y< trackingParameters.size();y++){
            preparedStatement.setObject(y+1, trackingParameters.get(y));
        }

        int rowsAffectedByThisQuery = preparedStatement.executeUpdate();

        if (rowsAffectedByThisQuery > 0) {
            System.out.println("Order updated successfully.");
            showingAlertForCOnfirmation("Status Of Order Updated ", "The Order has been successfully updated.");

        } else {
            alertForError("Not Found For OrderID","The order for the provided OrderID was not found");
            System.out.println("No order with ID " + orderId + " was found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        alertForError("Error in Database", " An error ocurred when trying to access the database.");
    }
    }

    @FXML private void closingOfEditForm(){

        editOrderIdTextField.clear();
        editDishComboBox.getSelectionModel().clearSelection();
        editTableNumberField.clear();
        editOrderPane.setVisible(false);
    }
}




