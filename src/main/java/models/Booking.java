package main.java.models;
import  java.sql.Date;
import java.sql.Time;


public class Booking {

private int BookingID ;

private Date date;

private Time time;

private String customerInfoCustomerPhoneNumber;

    public Booking(int bookingID, Date date, Time time, String customerInfoCustomerPhoneNumber) {
        BookingID = bookingID;
        this.date = date;
        this.time = time;
        this.customerInfoCustomerPhoneNumber = customerInfoCustomerPhoneNumber;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getCustomerInfoCustomerPhoneNumber() {
        return customerInfoCustomerPhoneNumber;
    }

    public void setCustomerInfoCustomerPhoneNumber(String customerInfoCustomerPhoneNumber) {
        this.customerInfoCustomerPhoneNumber = customerInfoCustomerPhoneNumber;
    }
}
