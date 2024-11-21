package com.hcyzz1company.skybattle.core.factory;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import javafx.scene.image.Image;

/**
 * A factory class for creating instances of {@link Image}.
 *
 * This class follows the Factory Design Pattern, centralizing the creation of Image objects.
 * In the future, this factory can be extended to support more complex image creation logic.
 */
public class ImageFactory {
    /**
     * Private constructor to prevent instantiation of the factory class.
     * Since this class only provides static methods, it should not be instantiated.
     */
    private ImageFactory() {
        // Prevent instantiation
    }

    /**
     * The default image path to use if an image fails to load.
     */
    private static final String EXCEPTION_IMAGE_PATH = ImageConstants.IMAGE_ROOT_PATH + "loadFailImage.jpg";

    /**
     * Creates and returns an {@link Image} instance from the specified image path.
     *
     * If the image fails to load due to an exception, a fallback "load fail" image is returned instead.
     * This ensures the application has a graceful way to handle missing or corrupted image files.
     *
     * @param imagePath the relative path to the image file within the application's resources.
     * @return an {@link Image} object loaded from the specified path, or a default error image if loading fails.
     */
    public static Image createImage(String imagePath) {
        Image image;
        try {
            image = new Image(ImageFactory.class.getResource(imagePath).toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());

            image = new Image(EXCEPTION_IMAGE_PATH);
        }

        return image;
    }

}
