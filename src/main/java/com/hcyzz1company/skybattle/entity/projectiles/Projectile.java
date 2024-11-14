package com.hcyzz1company.skybattle.entity.projectiles;

import com.hcyzz1company.skybattle.entity.projectiles.ActiveActorDestructible;

/**
 * The Projectile class represents a general projectile object in the game,
 * which is fired by various actors such as planes. It extends from ActiveActorDestructible
 * and includes basic functionality for managing damage and movement.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a Projectile object with the specified image, size, and position.
	 *
	 * @param imageName the name of the image file for the projectile.
	 * @param imageHeight the height of the projectile image.
	 * @param initialXPos the initial horizontal position of the projectile.
	 * @param initialYPos the initial vertical position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the damage taken by the projectile. Once a projectile takes damage,
	 * it is destroyed.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Abstract method to update the position of the projectile.
	 * This method must be implemented by subclasses to define how the projectile moves.
	 */
	@Override
	public abstract void updatePosition();

}
