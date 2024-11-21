package com.hcyzz1company.skybattle.core.handle;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.UserPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles user input for the game, such as plane movement and projectile firing.
 * This class listens for keyboard events and triggers the appropriate actions on the user plane.
 */
public class UserInputHandle {
    private LevelParent level;

    /**
     * Creates a UserInputHandle for a specified game level.
     *
     * @param level the current level for which user input will be handled.
     */
    public UserInputHandle(LevelParent level) {
        this.level = level;
    }

    /**
     * Initializes key event handlers for user input.
     *
     * The background acts as the input receiver and listens for key presses to control the player:
     * <ul>
     *   <li><b>Arrow Keys:</b> Move the player's plane in the respective direction (Up, Down, Left, Right).</li>
     *   <li><b>Spacebar:</b> Fires a projectile from the player's plane.</li>
     * </ul>
     * Key releases stop the movement of the plane.
     */
    public void initializeBackground() {
        ImageView background = this.level.getBackground();
        UserPlane user = this.level.getUser();

        background.setFocusTraversable(true);
        background.setFitHeight(AppConstants.SCREEN_HEIGHT);
        background.setFitWidth(AppConstants.SCREEN_WIDTH);
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.LEFT) user.moveLeft();
                if (kc == KeyCode.RIGHT) user.moveRight();
                if (kc == KeyCode.SPACE) fireProjectile(level);
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVertical();
                if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontal();
            }
        });

        this.level.getRoot().getChildren().add(background);
    }

    /**
     * Fires a projectile from the player's plane.
     *
     * This method creates a new projectile, adds it to the game scene,
     * and tracks it in the level's list of user projectiles.
     *
     * @param level the current level where the projectile is fired.
     */
    private void fireProjectile(LevelParent level) {
        ActiveActorDestructible projectile = level.getUser().fireProjectile();
        level.getRoot().getChildren().add(projectile);
        level.getUserProjectiles().add(projectile);
    }
}
