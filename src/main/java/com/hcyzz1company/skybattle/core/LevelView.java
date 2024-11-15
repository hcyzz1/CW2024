package com.hcyzz1company.skybattle.core;

import com.hcyzz1company.skybattle.ui.GameOverImage;
import com.hcyzz1company.skybattle.ui.HeartDisplay;
import com.hcyzz1company.skybattle.ui.WinImage;
import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * The LevelView class handles the graphical display elements for a specific game level.
 * It manages the heart display (for showing player health), the win image, and the game over image.
 */
public class LevelView {


	private final Group root;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructor to initialize the LevelView.
	 *
	 * @param root the root Group in which the UI elements will be added
	 * @param heartsToDisplay the initial number of hearts to display (based on player health)
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(heartsToDisplay);
	}

	/**
	 * Adds the heart display (showing the player's current health) to the scene.
	 */
	public void showHeartDisplay() {
		heartDisplay.showHeartDisplayInContainer(root);
	}

	/**
	 * Displays the win image (typically shown when the player wins the level).
	 */
	public void showWinImage() {
		ImageUtil.showImageInContainer(root, new WinImage());
	}

	/**
	 * Displays the game over image (shown when the player loses).
	 */
	public void showGameOverImage() {
		ImageUtil.showImageInContainer(root, new GameOverImage());
	}

	/**
	 * Updates the heart display by removing hearts based on the remaining health.
	 *
	 * @param heartsRemaining the current number of hearts to be displayed (based on player health)
	 */
	public void removeHearts(int heartsRemaining) {
		heartDisplay.removeHeart(heartsRemaining);
	}

}
