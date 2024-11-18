package com.hcyzz1company.skybattle.ui.specialElements;

import com.hcyzz1company.skybattle.ui.basicImage.HeartImage;
import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.Group;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for managing and displaying a set of heart images
 * to represent the player's remaining lives or health in the game.
 * It creates a container (HBox) that holds the heart images and provides methods to initialize,
 * display, and update the heart display.
 */
public class HeartDisplay {
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	private HBox container;

	/**
	 * Constructs a HeartDisplay object with a specified number of hearts to display.
	 * Initializes the container and populates it with the specified number of heart images.
	 *
	 * @param heartsToDisplay the initial number of hearts to display in the heart container
	 */
	public HeartDisplay(int heartsToDisplay) {
		this.container = new HBox();
		this.initializeContainer(container);
		this.initializeHearts(heartsToDisplay);
	}

	/**
	 * Initializes the container (HBox) for the heart images and sets its position on the screen.
	 * The container holds all the heart images.
	 *
	 * @param container the HBox that holds the heart images
	 */
	private void initializeContainer(HBox container) {
		container.setLayoutX(HEART_DISPLAY_X_POSITION);
		container.setLayoutY(HEART_DISPLAY_Y_POSITION);
	}

	/**
	 * Initializes the specified number of heart images and adds them to the container (HBox).
	 * Each heart is represented by a HeartImage object, and the ImageUtil is used to display it.
	 *
	 * @param heartsToDisplay the number of heart images to display
	 */
	private void initializeHearts(int heartsToDisplay) {
		for (int i = 0; i < heartsToDisplay; i++) {
			HeartImage heartImage = new HeartImage();
			ImageUtil.showImageInContainer(container, heartImage);
		}
	}

	/**
	 * Removes heart images from the display to reflect the current number of remaining hearts.
	 * The hearts are removed starting from the leftmost image, as the player loses health.
	 *
	 * @param heartsRemaining the number of hearts remaining to display
	 */
	public void removeHeart(int heartsRemaining) {
		int currentNumberOfHearts = container.getChildren().size();
		int heartsToRemove = currentNumberOfHearts - heartsRemaining;

		if (heartsToRemove > 0 && !container.getChildren().isEmpty()) {
			container.getChildren().remove(0, heartsToRemove);
		}
	}

	/**
	 * Displays the heart container in the specified container (Group).
	 * This method adds the heart container (HBox) to the root container (Group),
	 * making the heart display visible on the screen.
	 *
	 * @param root the container (Group) where the heart display should be shown
	 */
	public void showHeartDisplayInContainer(Group root){
		root.getChildren().add(container);
	}
}
