package com.example.demo;

/**
 * The ActiveActorDestructible class extends the ActiveActor class and implements
 * the Destructible interface, representing an actor that can be destroyed in the game.
 * It adds functionality for damage handling and destruction state.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the specified image and position.
	 * The actor starts in an undestroyed state.
	 *
	 * @param imageName The name of the image file to be used for the actor.
	 * @param imageHeight The height of the image in pixels.
	 * @param initialXPos The initial horizontal position of the actor.
	 * @param initialYPos The initial vertical position of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Abstract method to update the position of the actor.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Abstract method to update the actor's state. This could include actions like movement or behavior changes.
	 */
	public abstract void updateActor();

	/**
	 * Abstract method for handling damage taken by the actor. The actor's state should be changed accordingly.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor by setting its destroyed state to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed state of the actor.
	 *
	 * @param isDestroyed The new destroyed state of the actor.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns whether the actor is destroyed or not.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
