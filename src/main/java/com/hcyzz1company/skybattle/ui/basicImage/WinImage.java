package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * The WinImage class represents the "You Win" image that is displayed when the player wins the game.
 * It uses ImageUtil to create and display the image on the screen.
 */
public class WinImage extends ImageView {

	private static final String WIN_IMAGE_PATH = "/com/hcyzz1company/skybattle/images/youwin.png";
	private static final double DEFAULT_WIN_IMAGE_WIDTH = 600;
	private static final double DEFAULT_WIN_IMAGE_HEIGHT = 500;
	private static final double DEFAULT_WIN_X_POSITION = 355;
	private static final double DEFAULT_WIN_Y_POSITION = 175;


	/**
	 * Constructs a WinImage object.
	 * Set the width & height & position of the Pic.
	 */
	public WinImage() {
		super(ImageUtil.creteImage(WIN_IMAGE_PATH));
		ImageUtil.setImageViewRatio(this, DEFAULT_WIN_IMAGE_WIDTH, DEFAULT_WIN_IMAGE_HEIGHT);
		ImageUtil.setImagePositon(this, DEFAULT_WIN_X_POSITION, DEFAULT_WIN_Y_POSITION);
	}

}
