package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * The FighterPlane class is an abstract class that represents a fighter plane in the game.
 * It extends the ActiveActorDestructible class and provides functionality related to health,
 * damage handling, and projectile firing for fighter planes.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a FighterPlane object with the specified parameters.
	 *
	 * @param imageName the name of the image representing the fighter plane.
	 * @param imageHeight the height of the image representing the fighter plane.
	 * @param initialXPos the initial horizontal position of the fighter plane.
	 * @param initialYPos the initial vertical position of the fighter plane.
	 * @param health the initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * This method is abstract and should be implemented by subclasses to define the behavior of projectile firing.
	 *
	 * @return an instance of ActiveActorDestructible representing the fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the fighter plane's health by 1 and checks if the plane's health reaches zero.
	 * If the health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for firing a projectile, based on the plane's current position and an offset.
	 *
	 * @param xPositionOffset the offset to apply to the X position.
	 * @return the calculated X position for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for firing a projectile, based on the plane's current position and an offset.
	 *
	 * @param yPositionOffset the offset to apply to the Y position.
	 * @return the calculated Y position for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane is zero.
	 *
	 * @return true if the health is zero, otherwise false.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return the current health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}
		
}
