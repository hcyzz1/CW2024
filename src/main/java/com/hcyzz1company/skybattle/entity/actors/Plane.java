package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.common.ExplosionEffect;
import com.hcyzz1company.skybattle.utils.MusicUtil;

import java.util.List;

/**
 * The Plane class represents a fighter plane.
 * It handles health, damage, and projectile firing.
 */
public abstract class Plane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a Plane object with specified parameters.
	 *
	 * @param imageName   the image name for the plane.
	 * @param imageHeight the height of the plane's image.
	 * @param initialXPos the initial X position of the plane.
	 * @param initialYPos the initial Y position of the plane.
	 * @param health      the initial health of the plane.
	 */
	public Plane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the plane.
	 * This method is implemented by subclasses.
	 *
	 * @return the fired projectile.
	 */
	public abstract List<ActiveActorDestructible> fireProjectile();

	/**
	 * Reduces health by 1. If health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			MusicUtil.playExplosionSound();
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for firing a projectile.
	 *
	 * @param xPositionOffset the X offset.
	 * @return the calculated X position.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for firing a projectile.
	 *
	 * @param yPositionOffset the Y offset.
	 * @return the calculated Y position.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if health is zero.
	 *
	 * @return true if health is zero, otherwise false.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the plane.
	 *
	 * @return the plane's current health.
	 */
	public int getHealth() {
		return health;
	}


	public void addHealth() {
		health ++;
	}
		
}
