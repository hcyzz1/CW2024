package com.hcyzz1company.skybattle.core;

import java.util.*;
import java.util.stream.Collectors;

import com.hcyzz1company.skybattle.entity.actors.FighterPlane;
import com.hcyzz1company.skybattle.entity.actors.UserPlane;
import com.hcyzz1company.skybattle.entity.projectiles.ActiveActorDestructible;
import javafx.animation.*;
import javafx.event.EventHandler;
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

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

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
	 * @param screenHeight        the height of the game screen
	 * @param screenWidth         the width of the game screen
	 * @param playerInitialHealth the initial health of the player
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Abstract method to initialize the friendly units for the level.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check whether the game is over. This method will handle the game over logic.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units. This method handles the logic for creating and adding enemies to the level.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the LevelView, which displays game-related information such as health and score.
	 *
	 * @return a new instance of LevelView for the level
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene for the level, including the background, friendly units, and the heart display.
	 *
	 * @return the Scene for this level
	 */
	public Scene initializeScene() {
		initializeBackground();
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
		checkIfGameOver();
	}

	/**
	 * Initializes the game timeline for the animation loop, which updates the game state every 50 milliseconds.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image and sets up key event handlers for user input (e.g., movement, shooting).
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the player's plane and adds it to the scene.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire by having each enemy spawn a projectile.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
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
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
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
		return Math.abs(enemy.getTranslateX()) > screenWidth;
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
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Returns the root group of the scene.
	 *
	 * @return the root group
	 */
	protected Group getRoot() {
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
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the screen width of the level.
	 *
	 * @return the screen width
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user has been destroyed.
	 *
	 * @return true if the user is destroyed, false otherwise
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
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

}
