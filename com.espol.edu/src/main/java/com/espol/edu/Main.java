// Copyright (C) 2020
// All rights reserved
package com.espol.edu;

/**
 * Main class to run the VacationPackage application.
 * @author YourName
 */
public class Main {

    /**
     * The main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        VacationPackage vacationPackage = new VacationPackage();
        System.out.println(vacationPackage.calculatePackageCost("Paris", 5, 10));
    }
}
