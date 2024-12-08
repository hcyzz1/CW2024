/**
 * Module Management for the Sky Battle Project.
 * You can see the dependencies and encapsulation between packages.
 */
module skyBattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports com.hcyzz1company.skybattle;
    exports com.hcyzz1company.skybattle.constants;
    exports com.hcyzz1company.skybattle.core;
    exports com.hcyzz1company.skybattle.core.factory;
    exports com.hcyzz1company.skybattle.core.handle;
    exports com.hcyzz1company.skybattle.core.level;
    exports com.hcyzz1company.skybattle.core.level.levelOne;
    exports com.hcyzz1company.skybattle.core.level.levelTwo;
    exports com.hcyzz1company.skybattle.core.level.levelThree;

    exports com.hcyzz1company.skybattle.entity.actors;
    exports com.hcyzz1company.skybattle.entity.common;
    exports com.hcyzz1company.skybattle.entity.projectiles;

    exports com.hcyzz1company.skybattle.exceptions;
    exports com.hcyzz1company.skybattle.ui;
    exports com.hcyzz1company.skybattle.ui.basicImage;
    exports com.hcyzz1company.skybattle.ui.specialElements;

    exports com.hcyzz1company.skybattle.utils;

    opens com.hcyzz1company.skybattle to javafx.fxml;
    opens com.hcyzz1company.skybattle.utils;
}