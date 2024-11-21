package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.projectiles.UserProjectile;

/**
 * The UserPlane class represents the player's plane.
 * It handles movement, firing, and state updates for the player's plane.
 */
public class UserPlane extends Plane {

    //Image Information
    private static final String IMAGE_NAME = "userplane.png";
    private static final int IMAGE_HEIGHT = 50;
    //Projectile offset from Plane --- Image Information
    private static final int PROJECTILE_X_POSITION_OFFSET = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    // Move Range Information
    private static final double X_UPPER_BOUND = 0;
    private static final double X_LOWER_BOUND = 600.0;
    private static final double Y_UPPER_BOUND = AppConstants.SCREEN_HEIGHT_UPPER_ADJUSTED;
    private static final double Y_LOWER_BOUND = AppConstants.SCREEN_HEIGHT_LOWER_ADJUSTED;
    // Init Postion Information
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    // Speed Information
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;

    private int velocityMultiplier;
    private int horizontalMultiplier;

    private int numberOfKills;

    /**
     * Constructs a UserPlane with initial health, position, and other properties.
     *
     * @param initialHealth the initial health of the user's plane.
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        velocityMultiplier = 0;
        horizontalMultiplier = 0;
    }

    /**
     * Updates the position of the user's plane based on movement input.
     * The plane can move up/down and left/right within bounds.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            // move velocity
            double initialTranslateY = getTranslateY();
            this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
            double newPosition = getLayoutY() + getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }

            //move horizontally
            double initialTranslateX = getTranslateX();
            this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalMultiplier);
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionX < X_UPPER_BOUND || newPositionX > X_LOWER_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Fires a projectile from the user's plane.
     *
     * @return a new UserProjectile.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Checks if the plane is moving.
     *
     * @return true if the plane is moving, false otherwise.
     */
    private boolean isMoving() {
        return velocityMultiplier != 0 || horizontalMultiplier != 0;
    }

    /**
     * Moves the plane up by setting the vertical velocity multiplier.
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Moves the plane down by setting the vertical velocity multiplier.
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Moves the plane left by setting the horizontal velocity multiplier.
     */
    public void moveLeft() {
        horizontalMultiplier = -1;
    }

    /**
     * Moves the plane right by setting the horizontal velocity multiplier.
     */
    public void moveRight() {
        horizontalMultiplier = 1;
    }

    /**
     * Stops vertical movement.
     */
    public void stopVertical() {
        velocityMultiplier = 0;
    }

    /**
     * Stops horizontal movement.
     */
    public void stopHorizontal() {
        horizontalMultiplier = 0;
    }

    /**
     * Gets the number of kills (enemy planes destroyed).
     *
     * @return the number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count by 1.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }

}
