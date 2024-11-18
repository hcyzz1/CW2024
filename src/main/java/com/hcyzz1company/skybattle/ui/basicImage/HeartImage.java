package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * The HeartImage class represents the "Heart" image that is displayed to show the health of the player.
 * It uses ImageUtil to create and display the image on the screen.
 */
public class HeartImage extends ImageView {
    private static final String HEART_PATH = "/com/hcyzz1company/skybattle/images/heart.png";
    private static final int HEART_HEIGHT = 50;
    private static final double HEART_X_POSITION = 5;
    private static final double HEART_Y_POSITION = 25;


    /**
     * Constructs a HeartImage object.
     * Set the width & heigh & position of the Pic.
     */
    public HeartImage() {
        super(ImageUtil.creteImage(HEART_PATH));
        ImageUtil.setImageViewRatio(this, -1, HEART_HEIGHT);
        ImageUtil.setImagePositon(this, HEART_X_POSITION, HEART_Y_POSITION);
    }
}
