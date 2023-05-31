// Copyright (C) 2020
// All rights reserved

package com.espol.edu;

import java.util.HashMap;
import java.util.Map;

/**
 * VacationPackage class represents a vacation package cost estimator.
 * @author YourName
 */
public class VacationPackage {

    /** Base cost for a vacation package. */
    private int baseCost = 1000;

    /** Group discount threshold for 10% discount. */
    private int groupDiscountThreshold1 = 4;

    /** Group discount threshold for 20% discount. */
    private int groupDiscountThreshold2 = 10;

    /** Maximum group size. */
    private int maxGroupSize = 80;

    /** Discount for groups larger than groupDiscountThreshold1. */
    private double groupDiscount1 = 0.10;

    /** Discount for groups larger than groupDiscountThreshold2. */
    private double groupDiscount2 = 0.20;

    /** Duration below which penalty applies. */
    private int penaltyDuration = 7;

    /** Duration above which promotional discount applies. */
    private int promotionalDuration = 30;

    /** Number of passengers for which promotional discount applies. */
    private int promotionalPassengerCount = 2;

    /** Penalty fee for short vacations. */
    private int penaltyFee = 200;

    /** Promotional discount for long vacations and exactly 2 passengers. */
    private int promotionDiscount = 200;

    /** Additional cost for popular tourist spots. */
    private Map<String, Integer> touristSpotsCost;

    /**
     * Constructor initializes the touristSpotsCost map.
     */
    public VacationPackage() {
        touristSpotsCost = new HashMap<String, Integer>();
        touristSpotsCost.put("Paris", 500);
        touristSpotsCost.put("New York City", 600);
    }

    /**
     * Calculates the cost of a vacation package.
     * @param destination The destination of the vacation.
     * @param travelers The number of travelers.
     * @param duration The duration of the vacation in days.
     * @return The total cost of the vacation package or -1 if the input data is invalid.
     */
    public int calculatePackageCost(String destination, int travelers, int duration) {
        if (!validateInputs(destination, travelers, duration)) {
            return -1;
        }

        int cost = baseCost;

        // add additional cost if destination is a popular tourist spot
        if (touristSpotsCost.containsKey(destination)) {
            cost += touristSpotsCost.get(destination);
        }

        // add base cost per traveler
        cost *= travelers;

        // apply group discount
        if (travelers > groupDiscountThreshold1 && travelers <= groupDiscountThreshold2) {
            cost -= cost * groupDiscount1;
        } else if (travelers > groupDiscountThreshold2) {
            cost -= cost * groupDiscount2;
        }

        // apply penalty or discount based on duration
        if (duration < penaltyDuration) {
            cost += penaltyFee;
        } else if (duration > promotionalDuration || travelers == promotionalPassengerCount) {
            cost -= promotionDiscount;
        }

        return cost;
    }

    /**
     * Validates the inputs for a vacation package.
     * @param destination The destination of the vacation.
     * @param travelers The number of travelers.
     * @param duration The duration of the vacation in days.
     * @return true if the inputs are valid, false otherwise.
     */
    private boolean validateInputs(String destination, int travelers, int duration) {
        if (destination == null || destination.isEmpty() || travelers <= 0 || travelers > maxGroupSize || duration <= 0) {
            System.err.println("Invalid input data.");
            return false;
        }
        return true;
    }
}
