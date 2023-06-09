package com.espol.edu;

import java.util.HashMap;
import java.util.Map;

/**
 * The main class to run the vacation package cost calculation.
 */
public class Main {
	
    /**
     * The main method to start the program.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        VacationPackage vacationPackage = new VacationPackage();

        // Select add-ons
        Map<String, Boolean> selectedAddOns = new HashMap<String, Boolean>();
        selectedAddOns.put("All-Inclusive Package", true);
        selectedAddOns.put("Adventure Activities Package", false);
        selectedAddOns.put("Spa and Wellness Package", true);

        // Calculate cost
        int cost = vacationPackage.calculatePackageCost("Paris", 5, 10, selectedAddOns);

        // Print the total cost of the vacation package
        System.out.println(cost);
    }
}
