package com.hcyzz1company.skybattle.core.level.levelOne;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * Represents the first level of the Sky Battle game.
 */
public class LevelOne extends LevelParent {

    // Path to the background image for this level
    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background1.jpg";
    // Maximum number of enemies allowed on the screen
    private static final int TOTAL_ENEMIES = 5;
    // Number of kills required to win the level
    private static final int KILLS_TO_ADVANCE = 10;
    // Probability of spawning a new enemy in each spawn cycle
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    // Initial health of the player's plane
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs the LevelOne instance.
     *
     * <p>Sets up the background image and initializes the player's health to the level's starting value.</p>
     */
    public LevelOne() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the player has won the level.
     *
     * @return {@code true} if the player has reached the required number of kills, {@code false} otherwise.
     */
    @Override
    protected boolean winLevel() {
        return userHasReachedKillTarget();
    }

    /**
     * Spawns enemy planes based on the spawn probability and the current number of enemies on the screen.
     *
     * <p>This method ensures that the total number of enemies does not exceed the limit defined by {TOTAL_ENEMIES}.
     * Each new enemy's vertical position is randomized.</p>
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(AppConstants.SCREEN_WIDTH, newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Checks if the player has achieved the required number of kills to advance to the next level.
     *
     * @return {true} if the player's kill count is equal to or greater than {KILLS_TO_ADVANCE}, {@code false} otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
