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
    exports com.hcyzz1company.skybattle.entity.projectiles;
    exports com.hcyzz1company.skybattle.core;
    exports com.hcyzz1company.skybattle.core.levelOne;
    exports com.hcyzz1company.skybattle.core.levelTwo;
    exports com.hcyzz1company.skybattle.utils;
    exports com.hcyzz1company.skybattle.utils.ui;

    opens com.hcyzz1company.skybattle.core to javafx.fxml;
    opens com.hcyzz1company.skybattle.core.levelOne to javafx.fxml;
    opens com.hcyzz1company.skybattle.core.levelTwo to javafx.fxml;
    opens com.hcyzz1company.skybattle.entity.actors to javafx.fxml;
    opens com.hcyzz1company.skybattle.entity.projectiles to javafx.fxml;


    exports com.hcyzz1company.skybattle.ui.basicImage;
    opens com.hcyzz1company.skybattle.ui.basicImage to javafx.fxml;
    exports com.hcyzz1company.skybattle.ui.specialElements;
    opens com.hcyzz1company.skybattle.ui.specialElements to javafx.fxml;
    exports com.hcyzz1company.skybattle.ui.screenView;
    opens com.hcyzz1company.skybattle.ui.screenView to javafx.fxml;

    // For JUnit Test
    opens com.hcyzz1company.skybattle.utils.ui;
    exports com.hcyzz1company.skybattle.entity.common;
    opens com.hcyzz1company.skybattle.entity.common to javafx.fxml;
}