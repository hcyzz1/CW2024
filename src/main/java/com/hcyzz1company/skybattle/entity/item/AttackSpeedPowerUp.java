package com.hcyzz1company.skybattle.entity.item;

/**
 * Represents a power-up item that increases the attack speed of the player.
 * This item moves at a fixed speed and is destroyed when it takes damage.
 */
public class AttackSpeedPowerUp extends PowerUpItem {

    /**
     * Constructs an {@code AttackSpeedPowerUp} object with the specified initial position.
     *
     * @param initialXPos the initial X position of the power-up.
     * @param initialYPos the initial Y position of the power-up.
     */
    public AttackSpeedPowerUp(double initialXPos, double initialYPos) {
        super("speed.png", initialXPos, initialYPos, -5); // Set the image name and movement speed.
    }

    /**
     * Activates the effect of the attack speed power-up.
     * This method should be implemented to define the specific effect on the player.
     */
    @Override
    public void activateEffect() {
        // Implement the logic for increasing the player's attack speed.
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
