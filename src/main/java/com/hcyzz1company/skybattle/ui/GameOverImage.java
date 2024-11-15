package com.hcyzz1company.skybattle.ui;

import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class represents the "Game Over" image that is displayed when the player wins the game.
 * It uses ImageUtil to create and display the image on the screen.
 */
public class GameOverImage extends ImageView {

	private static final String GAME_OVER_IMAGE_PATH = "/com/hcyzz1company/skybattle/images/gameover.png";
	private static final double DEFAULT_GAME_OVER_WIDTH = -160;
	private static final double DEFAULT_GAME_OVER_X_POSITION = -160;
	private static final double DEFAULT_GAME_OVER_Y_POSITION = -350;


	/**
	 * Constructs a WinImage object.
	 * Set the width & height & position of the Pic.
	 */
	public GameOverImage() {
		super(ImageUtil.creteImage(GAME_OVER_IMAGE_PATH));
		ImageUtil.setImageViewRatio(this, DEFAULT_GAME_OVER_WIDTH, -1);
		ImageUtil.setImagePositon(this, DEFAULT_GAME_OVER_X_POSITION, DEFAULT_GAME_OVER_Y_POSITION);
	}

}
