package com.espol.edu;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a vacation package with configurable options and costs.
 */
public class VacationPackage {

    // Base cost of the vacation package
    private final int baseCost = 1000;

    // Thresholds and discounts for group sizes
    private final int groupDiscountThreshold1 = 4;
    private final int groupDiscountThreshold2 = 10;
    private final double groupDiscount1 = 0.10;
    private final double groupDiscount2 = 0.20;

    // Maximum group size allowed
    private final int maxGroupSize = 80;

    // Duration and cost-related variables
    private final int penaltyDuration = 7;
    private final int promotionalDuration = 30;
    private final int promotionalPassengerCount = 2;
    private final int penaltyFee = 200;
    private final int promotionDiscount = 200;

    // Cost mappings for tourist spots and add-ons
    private Map<String, Integer> touristSpotsCost; //NOPMD Map cant be final
    private Map<String, Integer> addOnsCost; //NOPMD Map cant be final

    /**
     * Constructs a new VacationPackage instance and initializes the cost mappings.
     */
    public VacationPackage() {
        touristSpotsCost = new HashMap<String, Integer>();
        touristSpotsCost.put("Paris", 500);
        touristSpotsCost.put("New York City", 600);

        addOnsCost = new HashMap<String, Integer>();
        addOnsCost.put("All-Inclusive Package", 200);
        addOnsCost.put("Adventure Activities Package", 150);
        addOnsCost.put("Spa and Wellness Package", 100);
    }

    /**
     * Calculates the total cost of the vacation package based on the specified inputs.
     *
     * @param destination     the destination of the vacation
     * @param travelers       the number of travelers
     * @param duration        the duration of the vacation
     * @param selectedAddOns  a map indicating which add-ons are selected
     * @return the total cost of the vacation package, or -1 if the inputs are invalid
     */
    public int calculatePackageCost(String destination, int travelers, int duration, Map<String, Boolean> selectedAddOns) {
        if (!validateInputs(destination, travelers, duration)) {
            return -1;
        }

        int cost = baseCost;

        if (touristSpotsCost.containsKey(destination)) {
            cost += touristSpotsCost.get(destination);
        }

        cost *= travelers;

        if (travelers > groupDiscountThreshold1 && travelers <= groupDiscountThreshold2) {
            cost -= cost * groupDiscount1;
        } else if (travelers > groupDiscountThreshold2) {
            cost -= cost * groupDiscount2;
        }

        if (duration < penaltyDuration) {
            cost += penaltyFee;
        } else if (duration > promotionalDuration || travelers == promotionalPassengerCount) {
            cost -= promotionDiscount;
        }

        for (Map.Entry<String, Boolean> entry : selectedAddOns.entrySet()) {
            if (entry.getValue() && addOnsCost.containsKey(entry.getKey())) {
                cost += addOnsCost.get(entry.getKey()) * travelers;
            }
        }

        return cost;
    }

    /**
     * Validates the inputs for the vacation package.
     *
     * @param destination  the destination of the vacation
     * @param travelers    the number of travelers
     * @param duration     the duration of the vacation
     * @return true if the inputs are valid, false otherwise
     */
    private boolean validateInputs(String destination, int travelers, int duration) {
        if (destination == null || destination.isEmpty() || travelers <= 0 || travelers > maxGroupSize || duration <= 0) {
            System.err.println("Invalid input data.");
            return false;
        }
        return true;
    }
}
