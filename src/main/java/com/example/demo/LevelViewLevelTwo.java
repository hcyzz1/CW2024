package com.example.demo;

import javafx.scene.Group;

/**
 * LevelViewLevelTwo class extends the LevelView class and adds additional functionality
 * specific to level two, such as displaying a shield image.
 */
public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructor to initialize LevelViewLevelTwo.
	 *
	 * @param root the root Group in which the shield and heart display are added
	 * @param heartsToDisplay the initial number of hearts to display (based on player health)
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image to the root Group.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Shows the shield image (make it visible).
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image (makes it invisible).
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

}
