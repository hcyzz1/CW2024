package com.hcyzz1company.skybattle.core.level;

import java.util.*;
import java.util.stream.Collectors;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.core.handle.UserInputHandle;
import com.hcyzz1company.skybattle.entity.actors.Plane;
import com.hcyzz1company.skybattle.entity.actors.UserPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.ui.LevelView;
import com.hcyzz1company.skybattle.utils.LevelUtil;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

/**
 * Abstract class that represents a game level.
 * Handles the game loop, user input, unit spawning, collision detection, and level transitions.
 */
public abstract class LevelParent extends Observable {
    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private LevelView levelView;

    private boolean levelChanging = false;

    /**
     * Initializes the game level with background and player health.
     *
     * @param backgroundImageName the name of the background image
     * @param playerInitialHealth the initial health of the player
     */
    public LevelParent(String backgroundImageName, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.background = new ImageView(ImageUtil.createImage(backgroundImageName));
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    /**
     * Initializes friendly units for the level.
     */
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over and handles transitions.
     */
    protected void checkIfGameOver() {
        if (user.isDestroyed()) {
            loseGame();
        } else if (winLevel()) {
            //If win, try to get next level;
            String currentLevelClassName = this.getClass().getName();
            //If is already the final level, winGame
            if (LevelUtil.isFinalLevel(currentLevelClassName)) {
                winGame();
            } else {
                // If is not the final level, go to the next level.
                String nextLevel = LevelUtil.getNextLevel(currentLevelClassName);
                goToNextLevel(nextLevel);
            }
        }
    }

    /**
     * Abstract method to check if the level is won.
     *
     * @return true if the level is won, false otherwise
     */
    protected abstract boolean winLevel();

    /**
     * Abstract method to spawn enemy units for the level.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Instantiates the LevelView, which displays health and score.
     *
     * @return the LevelView instance for the level
     */
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), this.user.getHealth());
    }

    /**
     * Initializes the scene for the level, including the background and friendly units.
     *
     * @return the Scene for this level
     */
    public Scene initializeScene() {
        new UserInputHandle(this).initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    /**
     * Starts the game by beginning the animation timeline.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    /**
     * Moves to the next level by notifying observers.
     *
     * @param levelName the name of the next level
     */
    public void goToNextLevel(String levelName) {
        if (levelChanging) {
            return;
        }
        levelChanging = true;

        setChanged();
        notifyObservers(levelName); // Notify the observers about the level change

        stopCurrentLevelActivities();
        levelChanging = false;
    }

    /**
     * Updates the game state by spawning enemies, handling collisions, and updating actors.
     */
    private void updateScene() {
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handleProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
        updateExtraLevelView();
        checkIfGameOver();
    }

    /**
     * Initializes the game timeline to update the scene every 50 milliseconds.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(AppConstants.MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }


    /**
     * Returns the background image of the level.
     *
     * @return the background image as an ImageView
     */
    public ImageView getBackground() {
        return this.background;
    }

    /**
     * Returns the list of projectiles fired by the user.
     *
     * @return a list of user projectiles
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return this.userProjectiles;
    }


    /**
     * Generates enemy projectiles by having each enemy fire.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((Plane) enemy).fireProjectile()));
    }

    /**
     * Adds an enemy projectile to the scene if it is not null.
     *
     * @param projectile the enemy projectile to spawn
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates the positions of all actors in the game (user, enemies, projectiles).
     */
    private void updateActors() {
        updateActorPositions(friendlyUnits);
        updateActorPositions(enemyUnits);
        updateActorPositions(userProjectiles);
        updateActorPositions(enemyProjectiles);
    }

    private <T extends ActiveActorDestructible> void updateActorPositions(List<T> actors) {
        actors.stream().forEach(ActiveActorDestructible::updatePosition);
    }

    /**
     * Removes destroyed actors from the scene and their corresponding lists.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from the specified list and the scene.
     *
     * @param actors the list of actors to check for destruction
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between friendly units and enemy units.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and user projectiles.
     */
    private void handleProjectileCollisions() {
        handleCollisions(enemyProjectiles, userProjectiles);
    }

    /**
     * Handles collisions between two lists of actors. Both actors take damage if they collide.
     *
     * @param actors1 the first list of actors
     * @param actors2 the second list of actors
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1,
                                  List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles the case where enemies have penetrated the player's defenses and cause damage to the user.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Updates the level view by removing hearts corresponding to the player's health.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * Updates the player's kill count based on the number of enemies destroyed.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if an enemy has penetrated the player's defenses.
     *
     * @param enemy the enemy to check
     * @return true if the enemy has penetrated, false otherwise
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > AppConstants.SCREEN_WIDTH;
    }

    /**
     * Stops the game and shows a win image when the user wins.
     */
    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

    /**
     * Stops the game and shows a game over image when the user loses.
     */
    protected void loseGame() {
        timeline.stop();
        levelView.showGameOverImage();
    }


    public UserPlane getUser() {
        return user;
    }

    public Group getRoot() {
        return root;
    }

    /**
     * Returns the current number of enemies in the level.
     *
     * @return the current number of enemies
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }


    /**
     * Adds an enemy unit to the level and the root group.
     * The enemy is added to both the list of enemy units and the scene's root group for rendering.
     *
     * @param enemy the enemy unit to add
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Returns the min Y position where enemies can spawn.
     *
     * @return the min Y position for enemy spawn
     */
    protected double getEnemyMinimumYPosition() {
        return AppConstants.SCREEN_HEIGHT_UPPER_ADJUSTED;
    }

    /**
     * Returns the maximum Y position where enemies can spawn.
     * This position is determined by the screen height and any necessary adjustments.
     *
     * @return the maximum Y position for enemy spawn
     */
    protected double getEnemyMaximumYPosition() {
        return AppConstants.SCREEN_HEIGHT_LOWER_ADJUSTED;
    }

    /**
     * Updates the current number of enemies in the level.
     * This method recalculates the number of enemies based on the current state of the enemy units list.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Stops all activities related to the current level.
     * This method stops the game timeline and deletes any observers associated with the level.
     */
    public void stopCurrentLevelActivities() {
        timeline.stop();
        deleteObservers();
    }

    /**
     * Returns the LevelView object that displays game-related information such as health and score.
     *
     * @return the LevelView for this level
     */
    protected LevelView getLevelView() {
        return this.levelView;
    }

    /**
     * Updates additional views related to the level. This method can be overridden to update extra views.
     * Currently, it does nothing in this base class.
     * If specific level need to the action, you can rewrite it in Child class.
     */
    protected void updateExtraLevelView() {
    }

}
