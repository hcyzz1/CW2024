package com.hcyzz1company.skybattle.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents the image of a shield in the game.
 * It is displayed and hidden based on the status of the shield in the game.
 * This class extends ImageView to display the shield's image.
 */
public class ShieldImage extends ImageView {
	
	private static final String IMAGE_NAME = "/images/shield.png";
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a ShieldImage object at the specified position.
	 * Initializes the shield's image, visibility, and size.
	 *
	 * @param xPosition the horizontal position of the shield image.
	 * @param yPosition the vertical position of the shield image.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		//this.setImage(new Image(IMAGE_NAME));

		this.setImage(new Image(getClass().getResource("/com/hcyzz1company/skybattle/images/shield.png").toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
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
