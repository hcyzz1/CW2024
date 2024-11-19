package com.hcyzz1company.skybattle.entity.common;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ui.ImageFactory;
import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.image.*;

/**
 * The ActiveActor class represents an actor in the game that has an image
 * and can be moved both horizontally and vertically. It extends ImageView
 * and provides basic functionality for moving the actor.
 */
public abstract class ActiveActor extends ImageView {

    /**
     * Constructs an ActiveActor with the specified image and position.
     *
     * @param imageName   The name of the image file to be used for the actor.
     * @param imageHeight The height of the image in pixels.
     * @param initialXPos The initial horizontal position of the actor.
     * @param initialYPos The initial vertical position of the actor.
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        Image image = ImageFactory.createImage(ImageConstants.IMAGE_ROOT_PATH + imageName);
        this.setImage(image);
        ImageUtil.setImageViewRatio(this, -1, imageHeight);
        ImageUtil.setImagePositon(this, initialXPos, initialYPos);
    }

    /**
     * Abstract method that must be implemented by subclasses to update the position
     * of the actor.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the specified amount.
     *
     * @param horizontalMove The amount to move the actor horizontally.
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     *
     * @param verticalMove The amount to move the actor vertically.
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }

}
