package com.hcyzz1company.skybattle.core.factory;

import com.hcyzz1company.skybattle.core.level.LevelParent;

import java.lang.reflect.Constructor;

/**
 * A factory class for creating instances of game levels.
 *
 * <p>This class uses the Factory Design Pattern to instantiate level objects dynamically
 * based on their class names. It leverages Java reflection to load and initialize level classes.</p>
 */
public class LevelFactory {

    /**
     * Creates a new instance of a level based on the specified class name.
     *
     * <p>This method dynamically loads the class, retrieves its default constructor,
     * and instantiates the object. The level class must extend {@link LevelParent}
     * and have a no-argument constructor.</p>
     *
     * @param className the fully qualified name of the level class (e.g., "com.example.levels.LevelOne").
     * @return an instance of the specified level class.
     * @throws ClassNotFoundException                      if the specified level class cannot be found.
     * @throws NoSuchMethodException                       if the level class does not have a no-argument constructor.
     * @throws InstantiationException                      if the level class is abstract or cannot be instantiated.
     * @throws IllegalAccessException                      if the constructor is inaccessible.
     * @throws java.lang.reflect.InvocationTargetException if the constructor throws an exception during instantiation.
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