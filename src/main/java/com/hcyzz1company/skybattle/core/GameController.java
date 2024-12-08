package com.hcyzz1company.skybattle.core;

import java.util.Observable;
import java.util.Observer;

import com.hcyzz1company.skybattle.core.factory.LevelFactory;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import com.hcyzz1company.skybattle.utils.AlertUtil;
import com.hcyzz1company.skybattle.utils.LevelUtil;
import com.hcyzz1company.skybattle.utils.MusicUtil;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Manages game flow and handles transitions between levels.
 * Implements Observer to react to level changes.
 */
public class GameController implements Observer {

	private final Stage stage;

	/**
	 * Constructor for GameController.
	 *
	 * @param stage the primary window for JavaFX Application
	 */
	public GameController(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game, shows the stage, and starts the first level.
	 * Displays an error alert if there is a failure.
	 */
	public void launchGame() {
		try {
			// Show the game start screen
			showStartScreen();
			// Show the game window
			stage.show();
			// defalut start music background
			MusicUtil.playBackGroundSound();

		} catch (Exception e) {
			e.printStackTrace();
			AlertUtil.showError("ERROR",
					"Failed to launch the Game.",
					"An error occurred while starting the game. <br>" +
							"Please check the stack information");
			Platform.exit();
		}
	}

	private void showStartScreen() {
		GameStartScreen startScreen = new GameStartScreen(
				stage,
				this::startFirstLevel
		);
		startScreen.show();
	}

	private void startFirstLevel() {
		try {
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
	 * Switches to a specified level.
	 *
	 * @param className The class name representing the level.
	 * @throws LevelLoadingException if loading the level fails.
	 */
	private void goToLevel(String className) throws LevelLoadingException {
		try {
			// Use LevelFactory to get the instance of new Level
			LevelParent newLevel = LevelFactory.createLevel(className);

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
	 * Reacts to level changes and loads the new level.
	 *
	 * @param levelObj     The level that was observed.
	 * @param newLevelName The name of the next level to switch to.
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
