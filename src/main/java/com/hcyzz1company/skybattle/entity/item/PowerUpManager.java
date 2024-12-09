package com.hcyzz1company.skybattle.entity.item;

import java.util.Random;

/**
 * Manages the generation and dropping of power-up items during gameplay.
 * This class determines the type of power-up item to drop based on random probabilities.
 */
public class PowerUpManager {

    /**
     * A random number generator used to determine the type of power-up to drop.
     */
    private Random random;

    /**
     * Constructs a {@code PowerUpManager} object and initializes the random number generator.
     */
    public PowerUpManager() {
        random = new Random();
    }

    /**
     * Drops a power-up item at the specified position based on random chance.
     * 
     * <ul>
     *     <li>30% chance to drop a {@link HeartPowerUp}.</li>
     *     <li>40% chance to drop an {@link AttackSpeedPowerUp}.</li>
     *     <li>30% chance to drop nothing (returns {@code null}).</li>
     * </ul>
     *
     * @param initialXPos the initial X position where the power-up should appear.
     * @param initialYPos the initial Y position where the power-up should appear.
     * @return a {@code PowerUpItem} if one is dropped, or {@code null} if no power-up is dropped.
     */
    public PowerUpItem dropPowerUp(double initialXPos, double initialYPos) {
        double dropChance = random.nextDouble();  // Generate a random number in [0, 1)

        if (dropChance < 0.3) {  // 30% chance to drop a heart power-up
            return new HeartPowerUp(initialXPos, initialYPos);
        } else if (dropChance < 0.7) {  // 40% chance to drop an attack speed power-up
            return new AttackSpeedPowerUp(initialXPos, initialYPos);
        } else {  // 30% chance to drop nothing
            return null;
        }
    }
}
