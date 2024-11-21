package com.hcyzz1company.skybattle.ui.specialElements;

import com.hcyzz1company.skybattle.ui.basicImage.HeartImage;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.Group;
import javafx.scene.layout.HBox;

/**
 * Manages and displays heart images representing the player's health.
 */
public class HeartDisplay {
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	private HBox container;

	/**
	 * Constructs the HeartDisplay with a specified number of hearts.
	 *
	 * @param heartsToDisplay the initial number of hearts
	 */
	public HeartDisplay(int heartsToDisplay) {
		this.container = new HBox();
		this.initializeContainer(container);
		this.initializeHearts(heartsToDisplay);
	}

	/**
	 * Initializes the container for heart images and sets its position.
	 */
	private void initializeContainer(HBox container) {
		container.setLayoutX(HEART_DISPLAY_X_POSITION);
		container.setLayoutY(HEART_DISPLAY_Y_POSITION);
	}

	/**
	 * Adds the specified number of heart images to the container.
	 *
	 * @param heartsToDisplay the number of hearts to display
	 */
	private void initializeHearts(int heartsToDisplay) {
		for (int i = 0; i < heartsToDisplay; i++) {
			HeartImage heartImage = new HeartImage();
			ImageUtil.showImageInContainer(container, heartImage);
		}
	}

	/**
	 * Removes heart images to match the current number of remaining hearts.
	 *
	 * @param heartsRemaining the number of hearts remaining
	 */
	public void removeHeart(int heartsRemaining) {
		int currentNumberOfHearts = container.getChildren().size();
		int heartsToRemove = currentNumberOfHearts - heartsRemaining;

		if (heartsToRemove > 0 && !container.getChildren().isEmpty()) {
			container.getChildren().remove(0, heartsToRemove);
		}
	}

	/**
	 * Displays the heart container in the specified root container.
	 *
	 * @param root the container where the hearts should be displayed
	 */
	public void showHeartDisplayInContainer(Group root){
		root.getChildren().add(container);
	}
}
