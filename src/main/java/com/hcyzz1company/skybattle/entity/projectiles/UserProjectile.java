package com.hcyzz1company.skybattle.entity.projectiles;

import com.hcyzz1company.skybattle.entity.projectiles.Projectile;

/**
 * The UserProjectile class represents a projectile fired by the user's plane.
 * It extends the Projectile class and handles the movement and state updates for the projectile.
 */
public class UserProjectile extends Projectile {

    //Image Information
    private static final String IMAGE_NAME = "userfire.png";
    private static final int IMAGE_HEIGHT = 125;

    //Projectile Action Information
    private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Constructs a UserProjectile object with the specified initial position.
     * Initializes the projectile with an image, height, and position.
     *
     * @param initialXPos the initial X position of the projectile.
     * @param initialYPos the initial Y position of the projectile.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT,
                initialXPos, initialYPos,
                HORIZONTAL_VELOCITY);
    }

}
