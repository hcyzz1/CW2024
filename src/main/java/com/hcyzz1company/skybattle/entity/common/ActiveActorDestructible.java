package com.hcyzz1company.skybattle.entity.common;

import javafx.scene.image.ImageView;

/**
 * Represents a destructible actor that can be destroyed in the game.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

    private boolean isDestroyed;

    /**
     * Constructs an ActiveActorDestructible with the specified image and position.
     * The actor starts in an undestroyed state.
     *
     * @param imageName   The name of the image file to be used for the actor.
     * @param imageHeight The height of the image in pixels.
     * @param initialXPos The initial horizontal position of the actor.
     * @param initialYPos The initial vertical position of the actor.
     */
    public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    /**
     * Updates the actor's position.
     */
    @Override
    public abstract void updatePosition();

    /**
     * Handles damage taken by the actor.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Marks the actor as destroyed.    
     */
    @Override
    public void destroy() {
        setDestroyed(true);
    }

    /**
     * Sets the actor's destroyed state.
     * @param isDestroyed a boolean indicating whether the actor is destroyed
     */
    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * Returns if the actor is destroyed.
     * 
     * @return true if the actor is destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

}
