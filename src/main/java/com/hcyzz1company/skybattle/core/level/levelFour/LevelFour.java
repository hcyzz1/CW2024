package com.hcyzz1company.skybattle.core.level.levelFour;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.level.LevelParent;
import com.hcyzz1company.skybattle.entity.actors.BossPlane;
import com.hcyzz1company.skybattle.entity.actors.BossPlane1;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane;
import com.hcyzz1company.skybattle.entity.actors.EnemyPlane1;
import com.hcyzz1company.skybattle.entity.common.ActiveActorDestructible;
import com.hcyzz1company.skybattle.ui.specialElements.HealthBar;
import javafx.application.Platform;

/**
 * Represents the fourth level in the Sky Battle game.
 * This level involves defeating two bosses: BossPlane first, and then BossPlane1 after.
 * The level ends when both bosses are destroyed.
 */
public class LevelFour extends LevelParent {

    // Path to the background image for this level
    private static final String BACKGROUND_IMAGE_NAME = ImageConstants.IMAGE_ROOT_PATH + "background2.jpg";
    // Initial health of the player's plane
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    // The two bosses for this level
    private BossPlane boss;
    private BossPlane1 bossPlane1;


    private boolean bossPlane1Spawned = false;  // 标志变量，控制 BossPlane1 只生成一次

    /**
     * Constructs the LevelFour instance.
     * Initializes the level with the background image, player health, and creates the bosses.
     */
    public LevelFour() {
        super(BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
        boss = new BossPlane();  // Initialize the first boss (BossPlane)
        bossPlane1 = new BossPlane1();  // Initialize the second boss (BossPlane1)
    }

    /**
     * Checks if the player has won the level.
     *
     * @return {@code true} if both bosses are destroyed, {@code false} otherwise.
     */
    @Override
    protected boolean winLevel() {
        return boss.isDestroyed() && bossPlane1.isDestroyed();
    }

    @Override
    protected int getKills() {
        return 0;
    }

    /**
     * Spawns the bosses and other enemies when no enemies are currently on the screen.
     * Adds the first boss to the screen, and after it is destroyed, switches to the second boss.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            // Spawn the first boss (BossPlane)
            addEnemyUnit(boss);
            addShieldViewForFirstBoss();
            addHealthBarForFirstBoss();   // Add health bar for the first boss

            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // If BossPlane is destroyed, spawn BossPlane1
                    if (boss.isDestroyed()&& !bossPlane1Spawned) {
                        Platform.runLater(() -> {
                            getRoot().getChildren().remove(boss.getHealthBar());
                            getRoot().getChildren().remove(boss.getSheildImageView());
                            addEnemyUnit(bossPlane1);  // Add second boss (BossPlane1)
                            addShieldViewForSecondBoss();
                            addHealthBarForSecondBoss();  // Add health bar for second boss
                            bossPlane1Spawned = true;
                        });
                    }

                    // Spawn other enemy planes
                    if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                        double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                        if (newEnemyInitialYPosition < getEnemyMinimumYPosition()) {
                            newEnemyInitialYPosition = getEnemyMinimumYPosition();
                        }
                        ActiveActorDestructible newEnemy = new EnemyPlane(AppConstants.SCREEN_WIDTH, newEnemyInitialYPosition);
                        Platform.runLater(() -> {
                            addEnemyUnit(newEnemy);
                        });
                    }

                    if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                        double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                        if (newEnemyInitialYPosition < getEnemyMinimumYPosition()) {
                            newEnemyInitialYPosition = getEnemyMinimumYPosition();
                        }
                        ActiveActorDestructible newEnemy1 = new EnemyPlane1(AppConstants.SCREEN_WIDTH, newEnemyInitialYPosition);
                        Platform.runLater(() -> {
                            addEnemyUnit(newEnemy1);
                        });
                    }
                }
            }).start();
        }
    }

    /**
     * Adds the shield view for the first boss (BossPlane) to the screen.
     */
    private void addShieldViewForFirstBoss() {
        super.getLevelView().addElement(this.boss.getSheildImageView());
    }

    /**
     * Adds the shield view for the second boss (BossPlane1) to the screen.
     */
    private void addShieldViewForSecondBoss() {
        super.getLevelView().addElement(this.bossPlane1.getSheildImageView());
    }

    /**
     * Updates the shield view on the screen for both bosses.
     */
    @Override
    protected void updateExtraLevelView() {
        if (boss.isDestroyed()) {
            this.bossPlane1.updateShieldView();  // Update second boss only after first boss is destroyed
            this.bossPlane1.updatePosition();
        }else{
            this.boss.updateShieldView();
            this.boss.updatePosition();
        }
    }

    // Adds the health bar for the first boss (BossPlane)
    private void addHealthBarForFirstBoss() {
        HealthBar bossHealthBar = boss.getHealthBar();
        super.getRoot().getChildren().add(bossHealthBar);
    }

    // Adds the health bar for the second boss (BossPlane1)
    private void addHealthBarForSecondBoss() {
        HealthBar bossHealthBar = bossPlane1.getHealthBar();
        super.getRoot().getChildren().add(bossHealthBar);
    }



}
