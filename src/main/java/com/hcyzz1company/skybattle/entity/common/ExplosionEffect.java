package com.hcyzz1company.skybattle.entity.common;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * ExplosionEffect class represents the static explosion image with some special effects.
 */
public class ExplosionEffect extends ImageView {

    private static final String EXPLOSION_IMAGE_PATH = ImageConstants.IMAGE_ROOT_PATH + "destory.png"; // Path to the explosion sprite sheet

    /**
     * Constructs an ExplosionEffect object at the specified position with dynamic frame size.
     * 
     * @param x The X-coordinate of the explosion's position.
     * @param y The Y-coordinate of the explosion's position.
     * @param planeWidth The width of the plane for scaling the explosion.
     * @param planeHeight The height of the plane for scaling the explosion.
     */
    public ExplosionEffect(double x, double y, double planeWidth, double planeHeight) {
        // Load explosion image
        Image explosionImage = new Image(getClass().getResource(EXPLOSION_IMAGE_PATH).toExternalForm());

        // Set the image for this effect (no animation)
        this.setImage(explosionImage);

        // Dynamically calculate image size based on the plane size
        final double scaleFactor = 1.0; // You can adjust this scale factor as needed (1.0 means no scaling)
        final double scaledFrameWidth = planeWidth * scaleFactor;
        final double scaledFrameHeight = planeHeight * scaleFactor;

        // Set the width and height for the explosion image view
        this.setFitWidth(scaledFrameWidth);
        this.setFitHeight(scaledFrameHeight);

        // Set the position of the explosion on the screen
        this.setLayoutX(x - scaledFrameWidth / 2);  // Position relative to the plane's center
        this.setLayoutY(y - scaledFrameHeight / 2); // Position relative to the plane's center

        // Create a fade-out transition
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Create a scale transition for shrinking the image during the fade
        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(1), this);
        scaleDown.setFromX(1.0);
        scaleDown.setFromY(1.0);
        scaleDown.setToX(0.5);  // Shrink the explosion to 50% of its original size
        scaleDown.setToY(0.5);

        // Chain the fade-out and scale-down effects to run simultaneously
        fadeOut.play();
        scaleDown.play();

        // Use PauseTransition to wait for 1 second and then remove the explosion from the parent
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            // Safely get the parent and cast to Group
            if (getParent() instanceof Group) {
                Group parent = (Group) getParent();
                parent.getChildren().remove(ExplosionEffect.this);  // Remove the explosion effect from parent
            }
        });

        // Start the pause transition (it will wait for 1 second before triggering the action)
        pause.play();
    }
}
