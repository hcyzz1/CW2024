package com.hcyzz1company.skybattle.core.levelTwo;

import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.core.LevelView;
import com.hcyzz1company.skybattle.entity.actors.Boss;

/**
 * The LevelTwo class represents the second level in the game, which introduces a boss enemy.
 * It handles the initialization of friendly and enemy units, checks for game over conditions,
 * and manages the transition between winning or losing the game.
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/hcyzz1company/skybattle/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	/**
	 * Constructor to initialize LevelTwo with the given screen dimensions.
	 *
	 * @param screenHeight the height of the screen for the level
	 * @param screenWidth the width of the screen for the level
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	/**
	 * Initializes the friendly units for this level, which is just the user plane.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over. The game is over if the user plane is destroyed or the boss is destroyed.
	 * If the user is destroyed, the game ends with a loss. If the boss is destroyed, the game ends with a win.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units for the level. In LevelTwo, the boss is the only enemy unit.
	 * The boss is only spawned once there are no remaining enemies in the level.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the LevelView for LevelTwo, which manages the display of the player's health and other level-specific information.
	 *
	 * @return a new instance of LevelViewLevelTwo for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
