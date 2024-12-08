package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.projectiles.BossProjectile;
import com.hcyzz1company.skybattle.entity.projectiles.BossProjectile1;
import com.hcyzz1company.skybattle.entity.projectiles.BossProjectile2;
import com.hcyzz1company.skybattle.ui.basicImage.ShieldImage;
import com.hcyzz1company.skybattle.ui.specialElements.HealthBar;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The BossPlane class represents a powerful boss fighter plane.
 * It introduces movement patterns, projectile firing, and shield mechanics.
 */
public class BossPlane1 extends Plane {
    // Image Information
    private static final String IMAGE_NAME = "boss1.png";
    private static final int IMAGE_HEIGHT = 200;
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;

    //Projectile Information
    private static final double PROJECTILE_Y_POSITION_OFFSET = 55.0;
    private static final double BOSS_FIRE_RATE = .04;

    // Health Information
    private static final int HEALTH = 100;

    // Shield Information
    private static final double BOSS_SHIELD_PROBABILITY = .01;
    private static final int MAX_FRAMES_WITH_SHIELD = 100;
    private static final int SHIELD_POSITION_X_OFFSET = -30;
    private static final int SHIELD_POSITION_Y_OFFSET = -50;
    // Move Information
    private static final double Y_POSITION_UPPER_BOUND = AppConstants.SCREEN_HEIGHT_UPPER_ADJUSTED;
    private static final double Y_POSITION_LOWER_BOUND = AppConstants.SCREEN_HEIGHT_LOWER_ADJUSTED;
    private static final int VERTICAL_VELOCITY = 4;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;


    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private ShieldImage shieldImage;

    private HealthBar healthBar;  // 使用 HealthBar 类管理血条

    /**
     * Constructs a BossPlane object with predefined image, position, health, and movement pattern.
     */
    public BossPlane1() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
        shieldImage = new ShieldImage();
        healthBar = new HealthBar(HEALTH);  // 初始化血条
    }

    /**
     * Updates the position of the boss based on its movement pattern and vertical bounds.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
        updateShieldPosition();
        updateHealthBarPosition();
    }


    // 更新血条位置
    private void updateHealthBarPosition() {
        double x = this.getLayoutX() + this.getTranslateX();  // 获取Boss的当前X位置
        double y = this.getLayoutY() + this.getTranslateY()+ SHIELD_POSITION_Y_OFFSET;  // 血条放置在Boss图片下方
        healthBar.updatePosition(x, y);  // 更新血条的位置
    }


    /**
     * Updates the shield's position relative to the boss's current position.
     */
    public void updateShieldPosition() {
        shieldImage.setLayoutX(this.getLayoutX() + this.getTranslateX() + SHIELD_POSITION_X_OFFSET);
        shieldImage.setLayoutY(this.getLayoutY() + this.getTranslateY() + SHIELD_POSITION_Y_OFFSET);
    }

    /**
     * Fires a projectile if the boss decides to fire.
     *
     * @return A new BossProjectile if the boss fires, otherwise null.
     */
    @Override
    public List<ActiveActorDestructible> fireProjectile() {
        List<ActiveActorDestructible> list = new ArrayList<>();
        if (bossFiresInCurrentFrame()) {
            list.add(new BossProjectile1(getProjectileInitialPosition()));
            list.add(new BossProjectile(getProjectileInitialPosition()));
            list.add(new BossProjectile2(getProjectileInitialPosition()));
        }
        return list;
    }

    /**
     * Takes damage unless the boss is shielded.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
            updateHealthBar();  // 更新血条
        }
    }

    // 更新血条
    private void updateHealthBar() {
        healthBar.updateHealth(getHealth());  // 使用 HealthBar 更新血条
    }

    // 获取血条
    public HealthBar getHealthBar() {
        return healthBar;
    }


    /**
     * Initializes the boss's movement pattern.
     * The pattern alternates between vertical movements up, down, and stationary.
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
     * Updates the shield state: activates or deactivates based on certain conditions.
     */
    public void updateShieldView() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) {
            activateShield();
        }
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Gets the next vertical move for the boss from its movement pattern.
     * Reshuffles the pattern after moving in the same direction too long.
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
     * Determines if the boss fires a projectile in the current frame based on its fire rate.
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
     * Determines if the boss should activate its shield in the current frame.
     *
     * @return true if the shield should be activated, false otherwise.
     */
    private boolean shieldShouldBeActivated() {
        double num = Math.random();
        return num < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the boss's shield has been active for the maximum allowed frames.
     *
     * @return true if the shield has been active for too long, false otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the boss's shield, making it invulnerable to damage.
     */
    private void activateShield() {
        isShielded = true;
        shieldImage.showShield();
    }

    /**
     * Deactivates the boss's shield and resets the shield frame count.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        shieldImage.hideShield();
    }

    /**
     * Returns the shield ImageView for the boss.
     *
     * @return the shield ImageView
     */
    public ImageView getSheildImageView() {
        return this.shieldImage;
    }

}
