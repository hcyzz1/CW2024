package com.hcyzz1company.skybattle.entity.item;

/**
 * Represents a power-up item that increases the player's health.
 * This item moves at a fixed speed and is destroyed when it takes damage.
 */
public class HeartPowerUp extends PowerUpItem {

    /**
     * Constructs a {@code HeartPowerUp} object with the specified initial position.
     *
     * @param initialXPos the initial X position of the power-up.
     * @param initialYPos the initial Y position of the power-up.
     */
    public HeartPowerUp(double initialXPos, double initialYPos) {
        super("love.png", initialXPos, initialYPos, -5); // Set the image name and movement speed.
    }

    /**
     * Activates the effect of the heart power-up.
     * This effect increases the player's health.
     * The actual implementation should interact with the player's health system.
     */
    @Override
    public void activateEffect() {
        // Activate the effect of the power-up, such as increasing the player's health.
        System.out.println("Heart power-up activated! Increase player's health.");
        // Example: If a Player class exists, you might call a method like:
        // player.increaseHealth(1);
    }

    /**
     * Handles damage taken by the power-up.
     * This will destroy the power-up when called.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }
}
