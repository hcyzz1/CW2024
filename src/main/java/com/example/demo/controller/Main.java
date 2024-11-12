package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class launches the "Sky Battle" game by initializing the primary stage
 * and controlling the game flow through the Controller.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

	/**
	 * The entry point for starting the JavaFX application. It sets up the main game window
	 * and initializes the game controller.
	 *
	 * @param stage The primary stage for this application, onto which scenes will be set.
	 * @throws ClassNotFoundException If a class required for the game is not found.
	 * @throws NoSuchMethodException If a required method (constructor) is missing.
	 * @throws SecurityException If a security manager denies access to resources.
	 * @throws InstantiationException If an object cannot be instantiated.
	 * @throws IllegalAccessException If a class or constructor is inaccessible.
	 * @throws IllegalArgumentException If an invalid argument is provided to a constructor.
	 * @throws InvocationTargetException If the constructor throws an exception.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * The main method to launch the JavaFX application.
	 *
	 * @param args Command line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		launch();
	}
}