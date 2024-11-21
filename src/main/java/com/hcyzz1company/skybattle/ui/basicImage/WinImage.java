package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * Represents the "You Win" image displayed when the player wins.
 */
public class WinImage extends ImageView {

    private static final String WIN_IMAGE_PATH = ImageConstants.IMAGE_ROOT_PATH + "youwin.png";
    private static final double DEFAULT_WIN_IMAGE_WIDTH = 600;
    private static final double DEFAULT_WIN_IMAGE_HEIGHT = 500;
    private static final double DEFAULT_WIN_X_POSITION = 100;
    private static final double DEFAULT_WIN_Y_POSITION = 100;


    /**
     * Initializes the "You Win" image with position and size.
     */
    public WinImage() {
        super(ImageUtil.createImage(WIN_IMAGE_PATH));
        ImageUtil.setImageViewRatio(this, DEFAULT_WIN_IMAGE_WIDTH, DEFAULT_WIN_IMAGE_HEIGHT);
        ImageUtil.setImagePositon(this, DEFAULT_WIN_X_POSITION, DEFAULT_WIN_Y_POSITION);
    }

}
