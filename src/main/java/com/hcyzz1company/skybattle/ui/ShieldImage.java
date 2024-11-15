package com.hcyzz1company.skybattle.ui;

import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents the shield of the actors.
 * It uses ImageUtil to create and display the image on the screen.
 */
public class ShieldImage extends ImageView {
	private static final String SHIELD_PATH = "/com/hcyzz1company/skybattle/images/shield.png";
	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private static final int SHIELD_WIDTH = 200;
	private static final int SHIELD_HEIGHT = 200;

	/**
	 * Constructs a WinImage object.
	 * Set the width & height & position of the Pic.
	 */
	public ShieldImage() {
		super(ImageUtil.creteImage(SHIELD_PATH));
		ImageUtil.setImageViewRatio(this, SHIELD_WIDTH, SHIELD_HEIGHT);
		ImageUtil.setImagePositon(this, SHIELD_X_POSITION, SHIELD_Y_POSITION);

		this.setVisible(false);
	}

	/**
	 * Makes the shield visible on the screen.
	 * This method is called when the shield is activated.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield from the screen.
	 * This method is called when the shield is deactivated.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}
