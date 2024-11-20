package com.hcyzz1company.skybattle.core;

import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

/**
 * Factory Design Pattern for creating level instances.
 */
public class LevelFactory {

    /**
     * Creates a new level instance based on the class name.
     *
     * @param className The name of the level class.
     * @return The level object of the specified class name.
     * @throws ClassNotFoundException                      if the level class cannot be found.
     * @throws NoSuchMethodException                       if the constructor for the level class is not found.
     * @throws InstantiationException                      if the level class cannot be instantiated.
     * @throws IllegalAccessException                      if the constructor of the level class is inaccessible.
     * @throws java.lang.reflect.InvocationTargetException if the constructor throws an exception.
     */
    public static LevelParent createLevel(String className)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        try {
            // Use reflection to load the class and create an instance
            Class<?> newLevelClass = Class.forName(className);
            Constructor<?> constructor = newLevelClass.getConstructor();
            return (LevelParent) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            // Print the stack trace and rethrow the exception
            e.printStackTrace();
            throw e;
        }
    }
}