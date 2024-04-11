package main.java.interfaces;

import main.java.models.BookingDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface managementSystem {
    // Retrieving  number of dishes sold for a particular dish
    int getTheSalesOfDishes(String nameOfDish, LocalDateTime startTime, LocalDateTime endTime);

    // this Retrieves most popular dishes
    // List<Dish> getMostPopularDishes(LocalDateTime start, LocalDateTime end);

    // This should retrieve total revenue for each day
    double getTotalRevenue(LocalDate startTime);

    // This is providing data, in regard to  the party size within a given day
    Map<LocalDateTime, Integer> getBookingSizes(LocalDate t );

    // this here is designed to retrieve  a list of bookings for a day, including key aspects  such as booking type, number of customers, and date as specified in the spec and
    List<BookingDetails> getBookingsDetails(LocalDateTime start, LocalDateTime end);

    // method to retriev the total number bookings by querying the database with the given datetime in the parameters
    int getTotalNumberOfBookings(LocalDateTime start, LocalDateTime end);
}


class FohManagementDataAccessImpl implements managementSystem {

    // reutrn 0 and return null act as placeholder until further implementation

    @Override
    public int getTheSalesOfDishes(String dishName, LocalDateTime start, LocalDateTime end) {

        return 0;
    }



    @Override
    public double getTotalRevenue(LocalDate startTime) {
        return 0;
    }



    @Override
    public Map<LocalDateTime, Integer> getBookingSizes(LocalDate t) {
        return null;
    }


    @Override
    public List<BookingDetails> getBookingsDetails(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public int getTotalNumberOfBookings(LocalDateTime start, LocalDateTime end) {
        return 0;
    }


}
