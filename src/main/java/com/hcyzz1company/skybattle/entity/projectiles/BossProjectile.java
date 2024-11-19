package com.hcyzz1company.skybattle.entity.projectiles;

/**
 * The BossProjectile class represents a projectile fired by the Boss in the game.
 * It extends the Projectile class and handles its movement and update logic.
 */
public class BossProjectile extends Projectile {
    //Image Information
    private static final String IMAGE_NAME = "fireball.png";
    private static final int IMAGE_HEIGHT = 75;
    
    //Projectile Action Information
    private static final int HORIZONTAL_VELOCITY = -15;
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a BossProjectile with a given initial Y position.
     *
     * @param initialYPos The initial vertical position of the projectile.
     */
    public BossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT,
                INITIAL_X_POSITION, initialYPos,
                HORIZONTAL_VELOCITY);
    }

}
