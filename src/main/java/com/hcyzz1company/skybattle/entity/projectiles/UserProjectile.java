package com.hcyzz1company.skybattle.entity.projectiles;

import com.hcyzz1company.skybattle.entity.projectiles.Projectile;

/**
 * Represents a projectile fired by the user's plane.
 */
public class UserProjectile extends Projectile {

    //Image Information
    private static final String IMAGE_NAME = "userfire.png";
    private static final int IMAGE_HEIGHT = 10;

    //Projectile Action Information
    private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Creates a UserProjectile with the given position.
     *
     * @param initialXPos Initial horizontal position.
     * @param initialYPos Initial vertical position.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT,
                initialXPos, initialYPos,
                HORIZONTAL_VELOCITY);
    }

}
