/**
 * Module Management for the Sky Battle Project.
 * You can see the dependencies and encapsulation between packages.
 */
module com.hcyzz1company.skybattle {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.hcyzz1company.skybattle.boot;
    exports com.hcyzz1company.skybattle.logic;
    exports com.hcyzz1company.skybattle.entity.actors;
    opens com.hcyzz1company.skybattle.entity.actors to javafx.fxml;
    exports com.hcyzz1company.skybattle.entity.projectiles;
    opens com.hcyzz1company.skybattle.entity.projectiles to javafx.fxml;
    exports com.hcyzz1company.skybattle.ui;
    opens com.hcyzz1company.skybattle.ui to javafx.fxml;
    exports com.hcyzz1company.skybattle.action;
    opens com.hcyzz1company.skybattle.action to javafx.fxml;
    exports com.hcyzz1company.skybattle.core.levelOne;
    opens com.hcyzz1company.skybattle.core.levelOne to javafx.fxml;
    opens com.hcyzz1company.skybattle.core.levelTwo to javafx.fxml;
    exports com.hcyzz1company.skybattle.core.levelTwo;
    exports com.hcyzz1company.skybattle.core;
    opens com.hcyzz1company.skybattle.core to javafx.fxml;
}