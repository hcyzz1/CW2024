package com.hcyzz1company.skybattle.entity.item;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;

/**
 * Represents a power-up item in the game. 
 * Power-up items are destructible entities that provide special effects when activated.
 * The behavior of the power-up is defined by its subclasses.
 */
public abstract class PowerUpItem extends ActiveActorDestructible {

    /**
     * The horizontal velocity of the power-up item.
     */
    private final int horizontalVelocity;

    /**
     * Constructs a {@code PowerUpItem} object with the specified properties.
     *
     * @param imageName         the name of the image file representing the power-up item.
     * @param initialXPos       the initial X position of the power-up item.
     * @param initialYPos       the initial Y position of the power-up item.
     * @param horizontalVelocity the horizontal velocity of the power-up item.
     */
    public PowerUpItem(String imageName, double initialXPos, double initialYPos, int horizontalVelocity) {
        super(imageName, 30, initialXPos, initialYPos); // Assume a fixed height of 30 for all power-up items.
        this.horizontalVelocity = horizontalVelocity;
    }

    /**
     * Updates the position of the power-up item.
     * The item moves horizontally at the specified velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(this.horizontalVelocity);
    }

    /**
     * Activates the effect of the power-up item.
     * The specific effect is implemented in subclasses.
     */
    public abstract void activateEffect();
}
