package com.hcyzz1company.skybattle.core.level.levelTwo;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * Represents the second level of the game, which introduces a boss enemy.
 * <p>Handles the initialization of friendly and enemy units, checks for game over conditions,
 * and manages level progression based on the player's kills.</p>
 */
public class LevelTwo extends LevelParent {
    // Path to the background image for this level
    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background3.jpg";
    // Total number of enemies to spawn in the level
    private static final int TOTAL_ENEMIES = 5;
    // Number of kills required to advance to the next level
    private static final int KILLS_TO_ADVANCE = 20;
    // Probability of spawning an enemy
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    // Initial health of the player's plane
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs the LevelTwo instance.
     * <p>Initializes the level with the specified background image and player health.</p>
     */
    public LevelTwo() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the player has won the level by reaching the kill target.
     *
     * @return {@code true} if the player has reached the kill target, {@code false} otherwise
     */
    @Override
    protected boolean winLevel() {
        return userHasReachedKillTarget();
    }

    /**
     * Spawns enemy units based on a predefined spawn probability.
     * <p>The method ensures that the total number of enemies does not exceed the limit.</p>
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                if (newEnemyInitialYPosition < getEnemyMinimumYPosition()) {
                    newEnemyInitialYPosition = getEnemyMinimumYPosition();
                }
                ActiveActorDestructible newEnemy = new EnemyPlane(AppConstants.SCREEN_WIDTH, newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }


    /**
     * Checks if the player has reached the required number of kills to advance to the next level.
     *
     * @return {@code true} if the player's kill count is greater than or equal to the target, {@code false} otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
