package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.core.factory.ImageFactory;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Utility class providing methods for creating and displaying images.
 */
public class ImageUtil {

    /**
     * Creates an Image instance from the specified image path.
     * Uses the ImageFactory class to load the image.
     *
     * @param imagePath the path to the image file to be loaded.
     * @return an Image object.
     */
    public static Image creteImage(String imagePath) {
        Image image = ImageFactory.createImage(imagePath);
        return image;
    }

    /**
     * Sets the width, height, and preserves the ratio properties of the provided ImageView.
     * Adjusts the width and height of the ImageView while maintaining the image's aspect ratio.
     *
     * @param imageView the ImageView whose dimensions and aspect ratio are to be set.
     * @param width     the desired width of the image (use -1 for the original width).
     * @param height    the desired height of the image (use -1 for the original height).
     */
    public static void setImageViewRatio(ImageView imageView, double width, double height) {
        if (width > 0) {
            imageView.setFitWidth(width);
        }
        if (height > 0) {
            imageView.setFitHeight(height);
        }
        imageView.setPreserveRatio(true);
    }

    /**
     * Sets the position of the provided ImageView in the X and Y coordinates.
     *
     * @param imageView the ImageView to which the position is to be applied.
     * @param xPosition the X-coordinate of the ImageView.
     * @param yPosition the Y-coordinate of the ImageView.
     */
    public static void setImagePositon(ImageView imageView, double xPosition, double yPosition) {
        imageView.setLayoutX(xPosition);
        imageView.setLayoutY(yPosition);
    }


    /**
     * Displays the given ImageView within the specified container.
     * The image will be added to the container and made visible on the screen.
     *
     * @param root      the container where the image will be displayed (can be Group or HBox).
     * @param imageView the ImageView containing the image to be displayed.
     */
    public static void showImageInContainer(Group root, ImageView imageView) {
        root.getChildren().add(imageView);
        imageView.setVisible(true);
    }


    /**
     * Displays the given ImageView within the specified HBox container.
     * The image will be added to the container and made visible on the screen.
     *
     * @param root      the HBox container where the image will be displayed.
     * @param imageView the ImageView containing the image to be displayed.
     */
    public static void showImageInContainer(HBox root, ImageView imageView) {
        root.getChildren().add(imageView);
        imageView.setVisible(true);
    }


}
