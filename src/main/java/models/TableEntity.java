package main.java.models;

public class TableEntity {

    private String status;
    private int tableNumber;

    private int bookingID;
    private int capacity;

    private int staffMemberId;

    private int billpaymentNumber;

    public TableEntity(String status, int tableNumber, int bookingID, int capacity, int staffMemberId, int billpaymentNumber) {
        this.status = status;
        this.tableNumber = tableNumber;
        this.bookingID = bookingID;
        this.capacity = capacity;
        this.staffMemberId = staffMemberId;
        this.billpaymentNumber = billpaymentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(int staffMemberId) {
        this.staffMemberId = staffMemberId;
    }

    public int getBillpaymentNumber() {
        return billpaymentNumber;
    }

    public void setBillpaymentNumber(int billpaymentNumber) {
        this.billpaymentNumber = billpaymentNumber;
    }
}
