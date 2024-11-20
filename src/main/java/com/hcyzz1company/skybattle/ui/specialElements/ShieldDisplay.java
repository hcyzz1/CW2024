package com.hcyzz1company.skybattle.ui.specialElements;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShieldDisplay {
    private static final double DISPLAY_X_POSITION = 1000; // Adjust based on screen width
    private static final double DISPLAY_Y_POSITION = 25; // Adjust for top-right positioning

    private final HBox container;
    private final Label targetLabel;
    private final Label killedLabel;

    private int targetEnemies;  // Total number of enemies to destroy
    private int enemiesKilled; // Current number of enemies destroyed

    /**
     * Constructs a ShieldDisplay object with the specified target number of enemies.
     *
     * @param targetEnemies the target number of enemies to destroy.
     */
    public ShieldDisplay(int targetEnemies) {
        this.targetEnemies = targetEnemies;
        this.enemiesKilled = 0;

        // Create HBox and Labels
        container = new HBox();
        targetLabel = new Label("Target: " + targetEnemies);
        killedLabel = new Label("Killed: " + enemiesKilled);

        // Initialize and style the container
        initializeContainer(container);
    }

    /**
     * Initializes the container (HBox) for the target display and sets its position on the screen.
     *
     * @param container the HBox that holds the target and killed labels.
     */
    private void initializeContainer(HBox container) {
        container.setLayoutX(DISPLAY_X_POSITION);
        container.setLayoutY(DISPLAY_Y_POSITION);

        // Add labels to the container
        container.getChildren().addAll(targetLabel, killedLabel);

        // Optional: Add spacing or styles for better appearance
        container.setSpacing(10);
    }

    /**
     * Updates the number of enemies destroyed and refreshes the display.
     *
     * @param enemiesKilled the updated number of enemies destroyed.
     */
    public void updateKilledCount(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
        killedLabel.setText("Killed: " + enemiesKilled);
    }

    /**
     * Updates the target number of enemies to destroy and refreshes the display.
     *
     * @param targetEnemies the updated target number of enemies to destroy.
     */
    public void updateTargetCount(int targetEnemies) {
        this.targetEnemies = targetEnemies;
        targetLabel.setText("Target: " + targetEnemies);
    }

    /**
     * Displays the target container in the specified container (Group).
     * This method adds the target container (HBox) to the root container (Group),
     * making the target display visible on the screen.
     *
     * @param root the container (Group) where the target display should be shown.
     */
    public void showTargetDisplayInContainer(Group root) {
        root.getChildren().add(container);
    }
}
