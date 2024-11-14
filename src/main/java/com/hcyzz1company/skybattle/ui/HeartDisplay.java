package com.hcyzz1company.skybattle.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for displaying a set of heart icons on the screen,
 * typically used to represent the player's health or lives remaining.
 * It provides functionality to initialize and display hearts, as well as remove them as the player loses health.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/hcyzz1company/skybattle/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a HeartDisplay object with a specified position and number of hearts to display.
	 *
	 * @param xPosition the X-coordinate for the heart container's position on the screen
	 * @param yPosition the Y-coordinate for the heart container's position on the screen
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) that holds the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}

	/**
	 * Initializes the specified number of heart images and adds them to the container.
	 * Each heart is represented by an ImageView with a fixed height and preserved aspect ratio.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display. If no hearts remain, no action is performed.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Returns the container (HBox) holding the heart images.
	 *
	 * @return the HBox container that holds the heart images
	 */
	public HBox getContainer() {
		return container;
	}

}
