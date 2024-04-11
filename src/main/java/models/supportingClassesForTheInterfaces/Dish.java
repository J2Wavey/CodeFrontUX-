package main.java.models.supportingClassesForTheInterfaces;

import java.time.LocalDateTime;

public class Dish {
    private String name;
    private int numberOfSales;
    private LocalDateTime dateTime;

    // Constructor is here for the clas
    public Dish(String name, int numberOfSales, LocalDateTime dateTime) {
        this.name = name;
        this.numberOfSales = numberOfSales;
        this.dateTime = dateTime;
    }

    // Getters are located here
    public String getName() {
        return name;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // finally Setters are here
    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
