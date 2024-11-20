/**
 * Module Management for the Sky Battle Project.
 * You can see the dependencies and encapsulation between packages.
 */
module com.hcyzz1company.skybattle {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hcyzz1company.skybattle to javafx.fxml;
    exports com.hcyzz1company.skybattle;
}