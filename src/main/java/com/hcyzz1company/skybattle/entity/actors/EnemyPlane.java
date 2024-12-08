package com.hcyzz1company.skybattle.entity.actors;

import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.entity.projectiles.EnemyProjectile;

import java.util.ArrayList;
import java.util.List;

/**
 * The EnemyPlane class represents an enemy aircraft.
 * It extends the Plane class and includes movement, firing projectiles, and taking damage.
 */
public class EnemyPlane extends Plane {

    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -3;
    private static final double PROJECTILE_X_POSITION_OFFSET = -10.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 30.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    /**
     * Constructs an EnemyPlane object with the specified initial position.
     * The plane is initialized with an image, health, and firing rate.
     *
     * @param initialXPos the initial horizontal position of the enemy plane.
     * @param initialYPos the initial vertical position of the enemy plane.
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the enemy plane by moving it horizontally.
     * The plane moves leftward with a constant horizontal velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile if the random chance based on the fire rate condition is met.
     * The projectile is fired from the calculated X and Y positions.
     *
     * @return an EnemyProjectile object if the projectile is fired, or null if no projectile is fired.
     */
    @Override
    public List<ActiveActorDestructible> fireProjectile() {
        List<ActiveActorDestructible> list = new ArrayList<>();
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            list.add(new EnemyProjectile(projectileXPosition, projectileYPostion));
        }
        return list;
    }


}
