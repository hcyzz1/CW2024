package com.hcyzz1company.skybattle.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class represents the "You Win" image that is displayed when the player wins the game.
 * It extends the ImageView class to display an image on the screen.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/hcyzz1company/skybattle/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a WinImage object with the specified position on the screen.
	 * The image is initially set to invisible.
	 *
	 * @param xPosition the X position to place the "You Win" image.
	 * @param yPosition the Y position to place the "You Win" image.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Displays the "You Win" image by setting its visibility to true.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

}
