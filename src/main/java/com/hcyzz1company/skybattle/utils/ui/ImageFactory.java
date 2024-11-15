package com.hcyzz1company.skybattle.utils.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Factory Design pattern for creating image instances.
 * Maybe in the future, we need more ways to create a new Image instance.
 */
public class ImageFactory {

    // Default image to display in case of failure to load a requested image.
    private static final String EXCEPTION_IMAGE_PATH = "/com/hcyzz1company/skybattle/images/loadFailImage.jpg";

    /**
     * Creates and returns an Image instance based on the provided image path.
     * If the image cannot be loaded ， a fallback "load fail" image is returned instead.
     *
     * @param imagePath the path to the image file to be loaded.
     * @return an Image object either from the specified path or a default error image if loading fails.
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
