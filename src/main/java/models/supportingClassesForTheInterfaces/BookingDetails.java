package main.java.models.supportingClassesForTheInterfaces;

import java.time.LocalDateTime;

public class BookingDetails {
    private String bookingType;
    private int numberOfCustomers;
    private LocalDateTime dateTime;

    // Constructor for the class is found here
    public BookingDetails(String bookingType, int numberOfCustomers, LocalDateTime dateTime) {
        this.bookingType = bookingType;
        this.numberOfCustomers = numberOfCustomers;
        this.dateTime = dateTime;
    }

    // Getters for this class given here
    public String getBookingType() {
        return bookingType;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // setters for the booking deatils class
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

