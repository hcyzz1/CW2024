/**
 * This module represents the main application for Sky Battle.
 * It requires JavaFX modules for UI components and FXML support.
 */
module com.example.demo {
    // Declaring dependency on JavaFX controls for UI components like buttons and labels.
    requires javafx.controls;
    // Declaring dependency on JavaFX FXML for UI layout management.
    requires javafx.fxml;


    // Opening the com.example.demo package to javafx.fxml to allow reflection for FXML file loading.
    opens com.example.demo to javafx.fxml;
    // Exporting the controller package so other modules can access the classes inside.
    exports com.example.demo.controller;
    exports com.example.demo;
}