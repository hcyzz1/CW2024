package com.hcyzz1company.skybattle.core.levelThree;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.BossPlane;
import com.hcyzz1company.skybattle.ui.screenView.LevelView;

/**
 * The LevelOne class represents the first level of the game. It inherits from LevelParent and
 * handles the initialization of the game environment, the spawning of enemies, and the conditions
 * for advancing to the next level or losing the game. It also manages the user's progress in terms
 * of kills and health.
 */
public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final BossPlane boss;
    private LevelView levelView;

    /**
     * Constructor to initialize LevelTwo with the given screen dimensions.
     *
     * @param screenHeight the height of the screen for the level
     * @param screenWidth  the width of the screen for the level
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new BossPlane();
    }

    /**
     * Initializes the friendly units for this level, which is just the user plane.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Check win the level or not
     */
    @Override
    protected boolean winLevel() {
        return boss.isDestroyed();
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
     * @return a new instance of LevelView for this level
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }


}
