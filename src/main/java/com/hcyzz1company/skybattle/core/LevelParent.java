package com.hcyzz1company.skybattle.core;

import java.util.*;
import java.util.stream.Collectors;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.core.handle.UserInputHandle;
import com.hcyzz1company.skybattle.entity.actors.Plane;
import com.hcyzz1company.skybattle.entity.actors.UserPlane;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.ui.screenView.LevelView;
import com.hcyzz1company.skybattle.utils.LevelUtil;
import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * The abstract class LevelParent represents a game level in which the user and enemy units interact.
 * It handles the overall game loop, user input, spawning of units, collision detection, and transitions
 * to the next level or game over state.
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
     * Constructor to initialize the game level with the given parameters.
     *
     * @param backgroundImageName the name of the background image for the level
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
        this.background = new ImageView(ImageUtil.creteImage(backgroundImageName));
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    /**
     * Abstract method to initialize the friendly units for the level.
     */
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Method to check whether the game is over.
     * This method will handle the game over logic.
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

    protected abstract boolean winLevel();

    /**
     * Abstract method to spawn enemy units. This method handles the logic for creating and adding enemies to the level.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Abstract method to instantiate the LevelView, which displays game-related information such as health and score.
     *
     * @return a new instance of LevelView for the level
     */
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), this.user.getHealth());
    }

    /**
     * Initializes the scene for the level, including the background, friendly units, and the heart display.
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
     * Starts the game by beginning the animation timeline, which runs the game loop.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    /**
     * Moves to the next level by notifying observers with the level name.
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
     * Updates the game state by performing various actions, such as spawning enemies, updating actors, and handling collisions.
     */
    private void updateScene() {
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
        updateExtraLevelView();
        checkIfGameOver();
    }

    /**
     * Initializes the game timeline for the animation loop, which updates the game state every 50 milliseconds.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(AppConstants.MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    public ImageView getBackground() {
        return this.background;
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return this.userProjectiles;
    }


    /**
     * Generates enemy fire by having each enemy spawn a projectile.
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
     * Updates the state of all actors in the game, including the user, enemies, and projectiles.
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
     * Removes all destroyed actors from the scene and the corresponding actor lists.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes actors that are destroyed from the given list and from the scene.
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
     * Handles collisions between friendly units (planes) and enemy units.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between the player's projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units (planes).
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between two lists of actors. If any actors intersect, they take damage.
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
     * Checks if the enemy has penetrated the player's defenses.
     *
     * @param enemy the enemy actor to check
     * @return true if the enemy has penetrated, false otherwise
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > AppConstants.SCREEN_WIDTH;
    }

    /**
     * Stops the game and shows a win image when the user wins the game.
     */
    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

    /**
     * Stops the game and shows a game over image when the user loses the game.
     */
    protected void loseGame() {
        timeline.stop();
        levelView.showGameOverImage();
    }

    /**
     * Returns the user's plane.
     *
     * @return the user's plane
     */
    public UserPlane getUser() {
        return user;
    }

    /**
     * Returns the root group of the scene.
     *
     * @return the root group
     */
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
     *
     * @param enemy the enemy unit to add
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Returns the maximum Y position where enemies can spawn.
     *
     * @return the maximum Y position for enemy spawn
     */
    protected double getEnemyMaximumYPosition() {
        return AppConstants.SCREEN_HEIGHT - AppConstants.SCREEN_HEIGHT_ADJUSTMENT;
    }

    /**
     * Updates the current number of enemies in the level.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    public void stopCurrentLevelActivities() {
        timeline.stop();
        deleteObservers();
    }

    protected LevelView getLevelView() {
        return this.levelView;
    }

    protected void updateExtraLevelView() {
    }

}
