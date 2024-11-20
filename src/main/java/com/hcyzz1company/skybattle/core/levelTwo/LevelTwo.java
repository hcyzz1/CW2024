package com.hcyzz1company.skybattle.core.levelTwo;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.ui.screenView.LevelView;

/**
 * The LevelTwo class represents the second level in the game, which introduces a boss enemy.
 * It handles the initialization of friendly and enemy units, checks for game over conditions,
 * and manages the transition between winning or losing the game.
 */
public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background3.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 20;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a LevelOne instance with the specified screen dimensions.
     */
    public LevelTwo() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected boolean winLevel() {
        return userHasReachedKillTarget();
    }

    /**
     * Spawns enemy units for this level. The method randomly generates enemy planes based on
     * a predefined spawn probability and ensures that the total number of enemies does not exceed
     * the limit.
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
     * Checks if the user has reached the target number of kills required to advance to the next level.
     *
     * @return true if the user has reached the kill target, false otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
