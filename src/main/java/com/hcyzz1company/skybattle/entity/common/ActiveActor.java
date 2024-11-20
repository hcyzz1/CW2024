package com.hcyzz1company.skybattle.entity.common;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.core.factory.ImageFactory;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.image.*;

/**
 * Represents a movable actor with an image.
 */
public abstract class ActiveActor extends ImageView {

    /**
     * Constructs an ActiveActor with the specified image and initial position.
     *
     * @param imageName   The name of the image file for the actor.
     * @param imageHeight The height of the image in pixels.
     * @param initialXPos The initial horizontal position.
     * @param initialYPos The initial vertical position.
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        Image image = ImageFactory.createImage(ImageConstants.IMAGE_ROOT_PATH + imageName);
        this.setImage(image);
        ImageUtil.setImageViewRatio(this, -1, imageHeight);
        ImageUtil.setImagePositon(this, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the actor. Must be implemented by subclasses.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the given amount.
     *
     * @param horizontalMove The distance to move horizontally.
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the given amount.
     *
     * @param verticalMove The distance to move vertically.
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }

}
