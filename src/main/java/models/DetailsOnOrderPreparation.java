package main.java.models;/*
This class provides the kitchen with information on the sequence in which to prepare
the dish.

 */


import java.time.LocalDateTime;

public class DetailsOnOrderPreparation {

    private  String nameOfDish ;

    private int orderID;
    private int sequence;

    private LocalDateTime suggestedStartTime;

// here is the construcotor for this class
    public DetailsOnOrderPreparation(String nameOfDish, int orderID, int sequence, LocalDateTime suggestedStartTime) {
        this.nameOfDish = nameOfDish;
        this.orderID = orderID;
        this.sequence = sequence;
        this.suggestedStartTime = suggestedStartTime;
    }


    // the getters for this class are found here
    public String getNameOfDish() {
        return nameOfDish;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getSequence() {
        return sequence;
    }

    public LocalDateTime getSuggestedStartTime() {
        return suggestedStartTime;
    }

    // setter are here below


    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }


    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setSuggestedStartTime(LocalDateTime suggestedStartTime) {
        this.suggestedStartTime = suggestedStartTime;
    }
}
