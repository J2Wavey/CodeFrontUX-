package main.java.models;

public class CustomerInfo {

    private String phoneNumberOfCustomer;

    private String ForeNameOfCustomer;

    private String SurnameOfCustomer;

    public CustomerInfo(String phoneNumberOfCustomer, String foreNameOfCustomer, String surnameOfCustomer) {
        this.phoneNumberOfCustomer = phoneNumberOfCustomer;
        ForeNameOfCustomer = foreNameOfCustomer;
        SurnameOfCustomer = surnameOfCustomer;
    }

    public String getPhoneNumberOfCustomer() {
        return phoneNumberOfCustomer;
    }

    public void setPhoneNumberOfCustomer(String phoneNumberOfCustomer) {
        this.phoneNumberOfCustomer = phoneNumberOfCustomer;
    }

    public String getForeNameOfCustomer() {
        return ForeNameOfCustomer;
    }

    public void setForeNameOfCustomer(String foreNameOfCustomer) {
        ForeNameOfCustomer = foreNameOfCustomer;
    }

    public String getSurnameOfCustomer() {
        return SurnameOfCustomer;
    }

    public void setSurnameOfCustomer(String surnameOfCustomer) {
        SurnameOfCustomer = surnameOfCustomer;
    }
}
