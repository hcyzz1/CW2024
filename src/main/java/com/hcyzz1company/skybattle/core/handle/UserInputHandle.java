package com.hcyzz1company.skybattle.core.handle;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.core.LevelParent;
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
     * Constructs a UserInputHandle instance for a given level.
     *
     * @param level The level for which the user input is being handled.
     */
    public UserInputHandle(LevelParent level) {
        this.level = level;
    }

    /**
     * Initializes the background image and sets up key event handlers for user input.
     * This includes setting up handlers for moving the plane and firing projectiles.
     *
     * <p>This method attaches event handlers to the background to listen for key presses and releases:
     * <ul>
     *     <li>Up Arrow: Moves the plane up.</li>
     *     <li>Down Arrow: Moves the plane down.</li>
     *     <li>Spacebar: Fires a projectile from the user's plane.</li>
     * </ul>
     * </p>
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
     * Fires a projectile from the player's plane and adds it to the scene.
     * The projectile is added to the level's root and to the list of user projectiles.
     *
     * @param level The current game level, which contains the user plane and root scene.
     */
    private void fireProjectile(LevelParent level) {
        ActiveActorDestructible projectile = level.getUser().fireProjectile();
        level.getRoot().getChildren().add(projectile);
        level.getUserProjectiles().add(projectile);
    }
}
