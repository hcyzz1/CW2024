package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * Displays the "Heart" image representing the player's health.
 */
public class HeartImage extends ImageView {
    private static final String HEART_PATH = ImageConstants.IMAGE_ROOT_PATH + "heart.png";
    private static final int HEART_HEIGHT = 50;
    private static final double HEART_X_POSITION = 5;
    private static final double HEART_Y_POSITION = 25;


    /**
     * Initializes the heart image with default size and position.
     */
    public HeartImage() {
        super(ImageUtil.createImage(HEART_PATH));
        ImageUtil.setImageViewRatio(this, -1, HEART_HEIGHT);
        ImageUtil.setImagePositon(this, HEART_X_POSITION, HEART_Y_POSITION);
    }
}
