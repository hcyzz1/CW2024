package com.example.demo;

/**
 * The EnemyProjectile class represents a projectile fired by an enemy plane.
 * It extends the Projectile class and defines behavior specific to the enemy's projectiles, such as movement.
 */
public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile object with the specified initial position.
	 * The projectile is initialized with an image and position.
	 *
	 * @param initialXPos the initial horizontal position of the projectile.
	 * @param initialYPos the initial vertical position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally.
	 * The projectile moves leftward with a constant horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile, including its position.
	 * This method is typically called once per game loop.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}


}
