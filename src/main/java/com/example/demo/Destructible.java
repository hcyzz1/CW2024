package com.example.demo;

/**
 * The Destructible interface represents an entity that can take damage and be destroyed.
 * Any class that implements this interface should define the behavior for taking damage and being destroyed.
 */
public interface Destructible {

	/**
	 * This method is used to handle the damage taken by the destructible object.
	 * The exact behavior of this method depends on the implementation in the class.
	 */
	void takeDamage();

	/**
	 * This method is used to destroy the destructible object.
	 * Once an object is destroyed, it cannot be used further.
	 */
	void destroy();
	
}
