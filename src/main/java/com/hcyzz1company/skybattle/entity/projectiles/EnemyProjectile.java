package com.hcyzz1company.skybattle.entity.projectiles;

/**
 * The EnemyProjectile class represents a projectile fired by an enemy plane.
 * It extends the Projectile class and defines behavior specific to the enemy's projectiles, such as movement.
 */
public class EnemyProjectile extends Projectile {

    //Image Information
    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 50;
    
    //Projectile Action Information
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs an EnemyProjectile object with the specified initial position.
     * The projectile is initialized with an image and position.
     *
     * @param initialXPos the initial horizontal position of the projectile.
     * @param initialYPos the initial vertical position of the projectile.
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT,
                initialXPos, initialYPos,
                HORIZONTAL_VELOCITY);
    }

}
