package main.java.models.supportingClassesForTheInterfaces;/*
This supporting class is essential to communicate dietary restrictions or any allegeries
with specific dishes. This aids  the kitchen team to avoid any cross contamination, ensuring
the safety of the customer.

 */

public class Allergen {

    private int allergenId;
    private String nameOfAllergen;
    private String descriptionOfAllergen;

    // Constructor is here for this class
    public Allergen(int allergenId, String allergenName, String allergenDescription) {
        this.allergenId = allergenId;
        this.nameOfAllergen = allergenName;
        this.descriptionOfAllergen = allergenDescription;
    }

    // the getters are specified and found here
    public int getAllergenId() {
        return allergenId;
    }

    public String getNameOfAllergen() {
        return nameOfAllergen;
    }

    public String getDescriptionOfAllergen() {
        return descriptionOfAllergen;
    }

    // Setters are situated here
    public void setNameOfAllergen(String nameOfAllergen) {
        this.nameOfAllergen = nameOfAllergen;
    }
    public void setDescriptionOfAllergen(String descriptionOfAllergen) {
        this.descriptionOfAllergen = descriptionOfAllergen;
    }
}
