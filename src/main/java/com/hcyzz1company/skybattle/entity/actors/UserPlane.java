package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.entity.projectiles.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.projectiles.UserProjectile;

/**
 * The UserPlane class represents the player's plane in the game.
 * It extends the FighterPlane class and handles the movement, firing,
 * and state updates for the user's plane.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a UserPlane object with the specified initial health.
	 * Initializes the player's plane position, image, and other properties.
	 *
	 * @param initialHealth the initial health of the player's plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user's plane based on its movement.
	 * The plane can move up or down within a specified range of Y coordinates.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the state of the user's plane.
	 * This method is called each game cycle to apply changes in position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Creates a new projectile fired by the user's plane.
	 * The projectile is positioned based on the user's plane position.
	 *
	 * @return a new UserProjectile object.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks whether the user's plane is moving.
	 * The plane is moving if the velocity multiplier is not zero.
	 *
	 * @return true if the plane is moving, false otherwise.
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	/**
	 * Moves the user's plane up.
	 * Sets the velocity multiplier to move the plane upwards.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane down.
	 * Sets the velocity multiplier to move the plane downwards.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the movement of the user's plane.
	 * Sets the velocity multiplier to zero.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Gets the number of enemy planes destroyed by the user's plane.
	 *
	 * @return the number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of enemy planes destroyed by the user's plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}
