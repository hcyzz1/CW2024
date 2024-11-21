package com.hcyzz1company.skybattle.core.level.levelThree;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.BossPlane;

/**
 * Represents the third level in the Sky Battle game.
 * This level involves defeating a boss enemy with a shield. The level ends when the boss is destroyed.
 */
public class LevelThree extends LevelParent {

    // Path to the background image for this level
    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background2.jpg";
    // Initial health of the player's plane
    private static final int PLAYER_INITIAL_HEALTH = 5;
    // The boss enemy for this level
    private final BossPlane boss;

    /**
     * Constructs the LevelThree instance.
     * Initializes the level with the background image, player health, and creates the boss enemy.
     */
    public LevelThree() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
        boss = new BossPlane();
    }

    /**
     * Checks if the player has won the level.
     *
     * @return {@code true} if the boss is destroyed, {@code false} otherwise.
     */
    @Override
    protected boolean winLevel() {
        return boss.isDestroyed();
    }

    /**
     * Spawns the boss enemy when no enemies are currently on the screen.
     * Adds the boss to the screen and displays its shield.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            addShieldView();
        }
    }

    /**
     * Adds the boss's shield view to the screen.
     */
    private void addShieldView() {
        super.getLevelView().addElement(this.boss.getSheildImageView());
    }

    /**
     * Updates the boss's shield view on the screen.
     */
    @Override
    protected void updateExtraLevelView() {
        this.boss.updateShieldView();
    }


}
