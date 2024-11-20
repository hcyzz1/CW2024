package com.hcyzz1company.skybattle.entity.projectiles;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * Represents a general projectile in the game, fired by actors like planes.
 * Extends ActiveActorDestructible for damage and movement functionality.
 */
public abstract class Projectile extends ActiveActorDestructible {

    //Projectile Action Information
    private final int horizontalVelocity;

    /**
     * Constructs a Projectile with specified image, size, and position.
     *
     * @param imageName          The projectile's image file name.
     * @param imageHeight        The projectile's height.
     * @param initialXPos        Initial horizontal position.
     * @param initialYPos        Initial vertical position.
     * @param horizontalVelocity Horizontal movement speed.
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, int horizontalVelocity) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.horizontalVelocity = horizontalVelocity;
    }

    /**
     * Destroys the projectile when it takes damage.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }

    /**
     * Updates the projectile's position horizontally.
     * Subclasses define specific movement behavior.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(this.horizontalVelocity);
    }

}
