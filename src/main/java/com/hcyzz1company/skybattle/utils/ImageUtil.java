package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.core.factory.ImageFactory;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Utility class providing methods for creating and displaying images.
 */
public class ImageUtil {

    /**
     * Private constructor to prevent instantiation of the utility class.
     * Since this class only provides static methods, it should not be instantiated.
     */
    private ImageUtil() {
        // Prevent instantiation
    }

    /**
     * Creates an Image instance from the specified image path.
     * Uses the ImageFactory class to load the image.
     *
     * @param imagePath the path to the image file to be loaded.
     * @return an Image object.
     */
    public static Image createImage(String imagePath) {
        Image image = ImageFactory.createImage(imagePath);
        return removeTransparentPixels(image);
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

    /**
     * Removes transparent pixels from the given image
     * and returns a smaller cropped image containing only the non-transparent pixels.
     *
     * @param image the Image to be processed. Must not be null.
     * @return a new Image without transparent pixels.
     */
    private static Image removeTransparentPixels(Image image) {
        if (image == null) {
            WritableImage emptyImage = new WritableImage(1, 1);
            emptyImage.getPixelWriter().setColor(0, 0, Color.TRANSPARENT);
            return emptyImage;
        }

        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader == null) {
            return image; // Return the original image if it has no pixel reader
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Initialize bounds for non-transparent pixels
        int minX = width, minY = height, maxX = 0, maxY = 0;

        // Scan the image to find the bounds of non-transparent pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                if (color.getOpacity() > 0) {
                    if (x < minX) minX = x;
                    if (y < minY) minY = y;
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                }
            }
        }

        // If no non-transparent pixels were found, return a 1x1 transparent image
        if (minX > maxX || minY > maxY) {
            return new WritableImage(1, 1);
        }

        // Crop the image to the bounds of non-transparent pixels
        int newWidth = maxX - minX + 1;
        int newHeight = maxY - minY + 1;
        return new WritableImage(pixelReader, minX, minY, newWidth, newHeight);
    }

}
