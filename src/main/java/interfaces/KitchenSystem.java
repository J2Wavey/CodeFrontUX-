package main.java.interfaces;

import main.java.models.supportingClassesForTheInterfaces.DetailsOnOrderPreparation;
import main.java.models.supportingClassesForTheInterfaces.OrderDetails;

import java.time.LocalDate;
import java.util.List;

public interface KitchenSystem {

    /* this retieves the order which are ready to be prepared, to access
    the data the team should query the data that the FoH team provides from thier database
    This would include details like allergens, items and special instructions.

    These are the orders are in the 'ready' state for the kitchen to start preparing, hence named getReadyOrders.
    The date parameter is the date in which the ready orders are requested
    The mealServicePeriod is for a specific period like "Lunch", "Dinner"
     */
    List<OrderDetails> getReadyOrders(LocalDate date, String mealServicePeriod);

    /* this would update the status of an order once a course is completed

     */
    void updateOrderStatus(int orderId, String status);


    /*
    This method here is designed to get a list of orders with details, including the sequences
    of dished to prepare and timing of when to start preparing.
     */

    List<DetailsOnOrderPreparation> getTimingAndPreparationSequence(LocalDate date, String mealServicePeriod);



    class KitchenOrderDataAccessImpl implements KitchenSystem {



        @Override
        public List<OrderDetails> getReadyOrders(LocalDate date, String mealServicePeriod) {
            return null;
        }

        @Override
        public void updateOrderStatus(int orderId, String status) {

        }

        @Override
        public List<DetailsOnOrderPreparation> getTimingAndPreparationSequence(LocalDate date, String mealServicePeriod) {
            return null;
        }


    }

}





