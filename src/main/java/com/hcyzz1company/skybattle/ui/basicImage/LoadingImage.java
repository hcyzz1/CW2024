package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * Displays the "Loading" image when the game ends.
 */
public class LoadingImage extends ImageView {

    private static final String GAME_OVER_IMAGE_PATH = ImageConstants.IMAGE_ROOT_PATH + "loading.gif";
    private static final double DEFAULT_GAME_OVER_WIDTH = 600;
    private static final double DEFAULT_GAME_OVER_X_POSITION = 300;
    private static final double DEFAULT_GAME_OVER_Y_POSITION = 200;


    /**
     * Initializes the "Game Over" image with default size and position.
     */
    public LoadingImage() {
        super(ImageUtil.createImage(GAME_OVER_IMAGE_PATH));
        ImageUtil.setImageViewRatio(this, DEFAULT_GAME_OVER_WIDTH, -1);
        ImageUtil.setImagePositon(this, DEFAULT_GAME_OVER_X_POSITION, DEFAULT_GAME_OVER_Y_POSITION);
    }

}
