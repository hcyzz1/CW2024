package com.hcyzz1company.skybattle.logic;

import java.util.Observable;
import java.util.Observer;

import com.hcyzz1company.skybattle.core.LevelFactory;
import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import com.hcyzz1company.skybattle.utils.AlertUtil;
import com.hcyzz1company.skybattle.utils.LevelUtil;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.hcyzz1company.skybattle.core.LevelParent;

/**
 * Controller class that manages game flow and changes between levels.
 * Implements Observer to handle level changes.
 */
public class Controller implements Observer {

	private final Stage stage;

	/**
	 * Constructor for Controller.
	 *
	 * @param stage the primary window for JavaFX Application.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launch the game by showing the stage and going to the first level.
	 * If any exception occurs, we will show an [Alert] to tell users.
	 * And you can check the error Message in the stack.(Where run this JavaFX Application)
	 */
	public void launchGame() {
		try {
			// Show the game window
			stage.show();
			// Start the game by going to the first level
			goToLevel(LevelUtil.getFirstLevel());
		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.showError("ERROR",
					"Failed to launch the Game.",
					"An error occurred while starting the game. <br>" +
							"Please check the stack information");
			Platform.exit();
		}
	}

	/**
	 * Move to a specific game level.
	 *
	 * @param className The name of the class representing the level.
	 * @throws LevelLoadingException : A custom exception that will be thrown if any error occurs while loading the level.
	 */
	private void goToLevel(String className) throws LevelLoadingException {
		try {
			// Use LevelFactory to get the instance of new Level
			LevelParent newLevel = LevelFactory.createLevel(className, stage);

			//add observer for newLevel
			newLevel.addObserver(this);

			//show newLevel in the stage
			Scene scene = newLevel.initializeScene();
			stage.setScene(scene);

			//newLevel start
			newLevel.startGame();
		} catch (Exception e) {
			throw new LevelLoadingException("Failed to load level: " + className, e);
		}
	}

	/**
	 * Updates the controller when the observed level changes.
	 *
	 * @param levelObj     The observable object (level).
	 * @param newLevelName The new level class name to switch to.
	 */
	@Override
	public void update(Observable levelObj, Object newLevelName) {
		try {
			goToLevel((String) newLevelName);
		} catch (LevelLoadingException e) {
			e.printStackTrace();
			AlertUtil.showError("Level Load Error",
					"Unable to load level",
					e.getMessage());
		}
	}

}
