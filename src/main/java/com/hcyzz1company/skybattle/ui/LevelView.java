package com.hcyzz1company.skybattle.ui;

import com.hcyzz1company.skybattle.ui.basicImage.GameOverImage;
import com.hcyzz1company.skybattle.ui.specialElements.HeartDisplay;
import com.hcyzz1company.skybattle.ui.basicImage.WinImage;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Manages graphical elements for a game level, including health display, win, and game over images.
 */
public class LevelView {

	private final Group root;
	private final HeartDisplay heartDisplay;

	/**
	 * Initializes LevelView with root container and heart display.
	 *
	 * @param root            the root container for UI elements
	 * @param heartsToDisplay the initial number of hearts to show
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(heartsToDisplay);
	}

	/**
	 * Adds the heart display to the scene.
	 */
	public void showHeartDisplay() {
		heartDisplay.showHeartDisplayInContainer(root);
	}

	/**
	 * Displays the win image.
	 */
	public void showWinImage() {
		ImageUtil.showImageInContainer(root, new WinImage());
	}

	/**
	 * Displays the game over image.
	 */
	public void showGameOverImage() {
		ImageUtil.showImageInContainer(root, new GameOverImage());
	}

	/**
	 * Updates the heart display by removing hearts.
	 *
	 * @param heartsRemaining the current number of hearts
	 */
	public void removeHearts(int heartsRemaining) {
		heartDisplay.removeHeart(heartsRemaining);
	}

	/**
	 * Adds an element (like a shield) to the scene.
	 *
	 * @param imageView the image element to add
	 */
	public void addElement(ImageView imageView) {
		root.getChildren().add(imageView);
	}

	/**
	 * Removes an element from the scene.
	 *
	 * @param imageView the image element to remove
	 */
	public void removeElement(ImageView imageView) {
		root.getChildren().remove(imageView);
	}
}
