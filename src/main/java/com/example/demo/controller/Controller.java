package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

/**
 * Controller class that manages game flow and changes between levels.
 * Implements Observer to handle level changes.
 */
public class Controller implements Observer {

	// Constant for the first level class name
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;

	/**
	 * Constructor for Controller.
	 * @param stage The main stage of the game (game window).
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launch the game by showing the stage and going to the first level.
	 * @throws ClassNotFoundException If the level class is not found.
	 * @throws NoSuchMethodException If the constructor is not found.
	 * @throws SecurityException If a security manager denies access.
	 * @throws InstantiationException If the class cannot be instantiated.
	 * @throws IllegalAccessException If the constructor is not accessible.
	 * @throws IllegalArgumentException If the constructor arguments are invalid.
	 * @throws InvocationTargetException If the constructor throws an exception.
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			// Show the game window
			stage.show();
			// Start the game by going to the first level
			goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transition to a specific game level.
	 * @param className The name of the class representing the level.
	 * @throws ClassNotFoundException If the level class is not found.
	 * @throws NoSuchMethodException If the constructor is not found.
	 * @throws SecurityException If a security manager denies access.
	 * @throws InstantiationException If the class cannot be instantiated.
	 * @throws IllegalAccessException If the constructor is not accessible.
	 * @throws IllegalArgumentException If the constructor arguments are invalid.
	 * @throws InvocationTargetException If the constructor throws an exception.
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();

	}

	/**
	 * Updates the controller when the observed level changes.
	 * @param arg0 The observable object (level).
	 * @param arg1 The new level class name to switch to.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}
