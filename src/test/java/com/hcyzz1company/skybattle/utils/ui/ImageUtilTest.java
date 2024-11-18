package com.hcyzz1company.skybattle.utils.ui;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ImageUtil class to verify correct behavior of methods related to
 * ImageView manipulation and image display in JavaFX containers.
 */
class ImageUtilTest extends ApplicationTest {

    /**
     * Sets up the JavaFX test environment by initializing a stage.
     *
     * @param stage the primary stage for JavaFX application
     * @throws Exception if an error occurs during stage initialization
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the setImageViewRatio method to ensure it correctly sets the width, height,
     * and preserve ratio properties of an ImageView.
     */
    @Test
    public void testSetImageViewRatio() {
        ImageView imageView = new ImageView();
        ImageUtil.setImageViewRatio(imageView, 200, 100);

        assertEquals(200, imageView.getFitWidth(), "The width should be set to 200.");
        assertEquals(100, imageView.getFitHeight(), "The height should be set to 100.");
        assertTrue(imageView.isPreserveRatio(), "Preserve ratio should be true.");
    }

    /**
     * Tests the setImageViewRatio method with negative values to ensure it handles invalid input gracefully.
     */
    @Test
    public void testSetImageViewRatioWithNegativeValues() {
        ImageView imageView = new ImageView();
        ImageUtil.setImageViewRatio(imageView, -1, -1);

        // Ensure the width and height are not set to -1
        assertEquals(0, imageView.getFitWidth(), "Width should not be set to -1.");
        assertEquals(0, imageView.getFitHeight(), "Height should not be set to -1.");
    }

    /**
     * Tests the setImagePosition method to ensure it correctly sets the X and Y positions of an ImageView.
     */
    @Test
    public void testSetImagePosition() {
        ImageView imageView = new ImageView();
        ImageUtil.setImagePositon(imageView, 50, 100);

        assertEquals(50, imageView.getLayoutX(), "The X position should be 50.");
        assertEquals(100, imageView.getLayoutY(), "The Y position should be 100.");
    }

    /**
     * Tests the showImageInContainer method when the container is a Group.
     * Ensures the ImageView is correctly added to the Group's children.
     */
    @Test
    void testShowImageInContainerWithGroup() {
        Platform.runLater(() -> {
            Group group = new Group();
            Image image = new Image(getClass().getResource(ImageConstants.IMAGE_ROOT_PATH + "loadFailImage.jpg").toExternalForm());
            ImageView imageView = new ImageView(image);

            ImageUtil.showImageInContainer(group, imageView);
            assertTrue(group.getChildren().contains(imageView));
        });
    }

    /**
     * Tests the showImageInContainer method when the container is an HBox.
     * Ensures the ImageView is correctly added to the HBox's children.
     */
    @Test
    public void testShowImageInContainerWithHBox() {
        Platform.runLater(() -> {
            HBox hbox = new HBox();
            Image image = new Image(getClass().getResource(ImageConstants.IMAGE_ROOT_PATH + "loadFailImage.jpg").toExternalForm());
            ImageView imageView = new ImageView(image);

            ImageUtil.showImageInContainer(hbox, imageView);
            assertTrue(hbox.getChildren().contains(imageView));
        });
    }

}