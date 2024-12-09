package com.hcyzz1company.skybattle.ui.specialElements;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Manages and displays heart images representing the player's health.
 */
public class TargetDisplay {
	private static final double HEART_DISPLAY_X_POSITION = 300;
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	private Label targetLabel;

	/**
	 * Constructs the TargetDisplay
	 */
	public TargetDisplay() {
		this.targetLabel = new Label();
		this.initializeLabel();
	}

	/**
	 * Initializes the container for heart images and sets its position.
	 */
	private void initializeLabel() {
		targetLabel.setLayoutX(HEART_DISPLAY_X_POSITION);
		targetLabel.setLayoutY(HEART_DISPLAY_Y_POSITION);
		targetLabel.setFont(new Font(32));

		targetLabel.setText("Target/Finish: 5/0");
	}

	/**
	 * Displays the heart container in the specified root container.
	 *
	 * @param root the container where the hearts should be displayed
	 */
	public void showTargetDisplayInContainer(Group root){
		root.getChildren().add(targetLabel);
	}

	public void updateTarget(int targetNumbers, int killNumbers){
		targetLabel.setText("Target/Finish: " + targetNumbers + "/" + killNumbers);
	}
}
