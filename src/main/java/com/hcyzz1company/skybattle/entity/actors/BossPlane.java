package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.projectiles.BossProjectile;

import java.util.*;

/**
 * The BossPlane class represents a powerful boss fighter plane in the game. It extends
 * the Plane class and introduces additional behavior like movement patterns,
 * projectile firing, and shield management.
 */
public class BossPlane extends Plane {

    private static final String IMAGE_NAME = "bossplane.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = .002;
    private static final int IMAGE_HEIGHT = 300;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 100;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 500;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    /**
     * Constructs a BossPlane object with predefined image, position, health, and movement pattern.
     */
    public BossPlane() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
    }

    /**
     * Updates the position of the boss based on its movement pattern.
     * Ensures the boss stays within vertical bounds.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }

        updateShield();
    }

    /**
     * Fires a projectile if the boss decides to fire in the current frame.
     *
     * @return A new BossProjectile if the boss fires, otherwise null.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
    }

    /**
     * Takes damage unless the boss is shielded.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    /**
     * Initializes the boss's movement pattern, alternating between vertical movement up, down, and stationary.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Updates the shield state, activating or deactivating it based on certain conditions.
     */
    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Gets the next vertical move for the boss from the movement pattern.
     * If the boss moves the same direction for too long, the pattern is reshuffled.
     *
     * @return The next vertical move value.
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Determines whether the boss fires a projectile based on its fire rate.
     *
     * @return true if the boss fires, false otherwise.
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Gets the initial Y position for the projectile fired by the boss.
     *
     * @return The Y position where the projectile will be fired.
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Determines whether the boss should activate its shield in the current frame.
     *
     * @return true if the shield should be activated, false otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the boss's shield has been active for too long and should be deactivated.
     *
     * @return true if the shield has been active for the maximum allowed frames, false otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the boss's shield, making it invulnerable to damage.
     */
    private void activateShield() {
        isShielded = true;
    }

    /**
     * Deactivates the boss's shield and resets the shield frame count.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
    }

}
