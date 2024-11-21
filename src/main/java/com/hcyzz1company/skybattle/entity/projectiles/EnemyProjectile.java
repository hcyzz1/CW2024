package com.hcyzz1company.skybattle.entity.projectiles;

/**
 * Represents a projectile fired by an enemy plane.
 * Extends Projectile for movement behavior.
 */
public class EnemyProjectile extends Projectile {

    //Image Information
    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 20;
    
    //Projectile Action Information
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs an EnemyProjectile with the given initial position.
     *
     * @param initialXPos Initial horizontal position.
     * @param initialYPos Initial vertical position.
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT,
                initialXPos, initialYPos,
                HORIZONTAL_VELOCITY);
    }

}
