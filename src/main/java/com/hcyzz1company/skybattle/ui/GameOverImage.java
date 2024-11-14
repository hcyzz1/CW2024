package com.hcyzz1company.skybattle.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class represents the image that is displayed when the game is over.
 * This class extends the ImageView class and sets up the game over image at a specific position.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/hcyzz1company/skybattle/images/gameover.png";

	/**
	 * Constructs a GameOverImage object with the specified position.
	 *
	 * @param xPosition the X-coordinate of the image's position.
	 * @param yPosition the Y-coordinate of the image's position.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}
