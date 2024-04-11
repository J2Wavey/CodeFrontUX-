package main.java.models;/*
This is the central class when it comes to managing information about each individual
order. This class includes details such as, which dishes are ordered, their allergies.
This is a vital class, for coordinating the kitchen preparation and ensuring that all customer
requirements are met.
 */



import java.sql.Timestamp;
import java.util.List;

public class OrderDetails {

    private int indentityOfOrder;
    private int tableNumber;
    private List<Allergen> allergens;
    private List<Item> orderItems;
    private Timestamp timeOfOrder;
    private String statusOfOrder;


    // getters for the class
    public int getIndentityOfOrder() {
        return indentityOfOrder;
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public List<Allergen> getAllergens() {
        return allergens;
    }
    public List<Item> getOrderItems() {
        return orderItems;
    }
    public Timestamp getTimeOfOrder() {
        return timeOfOrder;
    }
    public String getStatusOfOrder() {
        return statusOfOrder;
    }

    // setters for the class
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }
    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }
    public void setTimeOfOrder(Timestamp timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }
    public void setStatusOfOrder(String statusOfOrder) {
        this.statusOfOrder = statusOfOrder;
    }



    // Constructor is found here for this orderdeatails class
    public OrderDetails(int orderIdentifier, int tableNumber, List<Allergen> allergens, List<Item> orderItems, Timestamp orderTime, String orderStatus) {
        this.indentityOfOrder = orderIdentifier;
        this.tableNumber = tableNumber;
        this.allergens = allergens;
        this.orderItems = orderItems;
        this.timeOfOrder = orderTime;
        this.statusOfOrder = orderStatus;
    }
}
