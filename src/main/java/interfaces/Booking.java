package main.java.interfaces;

import java.time.LocalDateTime;

public class Booking {
    private String bookingType;
    private int numberOfCustomers;
    private LocalDateTime dateTime;

    // Here is the construcotr for this class
    public Booking(String bookingType, int numberOfCustomers, LocalDateTime dateTime) {
        this.bookingType = bookingType;
        this.numberOfCustomers = numberOfCustomers;
        this.dateTime = dateTime;
    }

    //These are the getters below
    public String getBookingType() {
        return bookingType;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // here are the setters below
    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

