package com.hcyzz1company.skybattle.logic;

import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

/**
 * Factory Design for creating level instances.
 */
public class LevelFactory {

    /**
     * Creates a new level instance based on the class name.
     *
     * @param className The name of the level class.
     * @param stage     The stage for the JavaFX application.
     * @return The level object of the specified class name.
     * @throws LevelLoadingException if there is an error while loading the level.
     */
    public static LevelParent createLevel(String className, Stage stage) throws LevelLoadingException {
        try {
            Class<?> newLevelClass = Class.forName(className);
            Constructor<?> constructor = newLevelClass.getConstructor(double.class, double.class);
            return (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
            throw new LevelLoadingException("Failed to load level: " + className, e);
        }
    }
}