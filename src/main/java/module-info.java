/**
 * Module Management for the Sky Battle Project.
 * You can see the dependencies and encapsulation between packages.
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo to javafx.fxml;

    exports com.example.demo.controller;
    exports com.example.demo;
}