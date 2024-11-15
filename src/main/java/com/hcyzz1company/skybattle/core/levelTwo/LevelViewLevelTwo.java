package com.hcyzz1company.skybattle.core.levelTwo;

import com.hcyzz1company.skybattle.core.LevelView;
import com.hcyzz1company.skybattle.ui.ShieldImage;
import javafx.scene.Group;

/**
 * LevelViewLevelTwo class extends the LevelView class and adds additional functionality
 * specific to level two, such as displaying a shield image.
 */
public class LevelViewLevelTwo extends LevelView {


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
		this.shieldImage = new ShieldImage();
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
