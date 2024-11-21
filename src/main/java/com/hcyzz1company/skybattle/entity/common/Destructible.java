package com.hcyzz1company.skybattle.entity.common;

/**
 * Interface for entities that can take damage and be destroyed.
 */
public interface Destructible {

	/**
	 * Handles damage taken by the object.
	 */
	void takeDamage();

	/**
	 * Destroys the object.
	 */
	void destroy();
	
}
