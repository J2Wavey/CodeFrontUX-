package main.java.models.supportingClassesForTheInterfaces;/*
This class is to represent individual menu items within an order, this class
includes details such as, name of the dish, quantity and any special instructions
from the customer. This is very important for the kitchen to understand what exactly
needs to be prepared for each order.
 */



public class Item {

   private String course;

   private String nameOfItem;
   private int itemID;

   private String specialInstructions;
   private  int quantity;
   private String feedback;

// this is the constructor of the class
   public Item(String course, String nameOfItem, int itemID, String specialInstructions, int quantity,String feedback){
       this.course=course;
       this.nameOfItem=nameOfItem;
       this.itemID=itemID;
       this.specialInstructions=specialInstructions;
       this.quantity=quantity;
       this.feedback=feedback;


   }
// getters and setters for the class are found here
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public int getItemID() {
        return itemID;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
