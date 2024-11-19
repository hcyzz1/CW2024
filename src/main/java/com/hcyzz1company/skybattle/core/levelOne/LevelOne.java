package com.hcyzz1company.skybattle.core.levelOne;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.ui.screenView.LevelView;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * The LevelOne class represents the first level of the game. It inherits from LevelParent and
 * handles the initialization of the game environment, the spawning of enemies, and the conditions
 * for advancing to the next level or losing the game. It also manages the user's progress in terms
 * of kills and health.
 */
public class LevelOne extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a LevelOne instance with the specified screen dimensions.
     */
    public LevelOne() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected boolean winLevel() {
        return userHasReachedKillTarget();
    }

    /**
     * Initializes the friendly units for this level. Specifically, this method adds the user's
     * unit (the player) to the game root.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
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
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
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
