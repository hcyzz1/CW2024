package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * Represents the shield image of actors in the game.
 */
public class ShieldImage extends ImageView {
    private static final String SHIELD_PATH = ImageConstants.IMAGE_ROOT_PATH + "shield.png";
    private static final int SHIELD_X_POSITION = 100;
    private static final int SHIELD_Y_POSITION = 40;
    private static final int SHIELD_WIDTH = 200;
    private static final int SHIELD_HEIGHT = 200;

    /**
     * Initializes the shield image with position, size, and opacity.
     */
    public ShieldImage() {
        super(ImageUtil.createImage(SHIELD_PATH));
        ImageUtil.setImageViewRatio(this, SHIELD_WIDTH, SHIELD_HEIGHT);
        ImageUtil.setImagePositon(this, SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.setOpacity(0.5);

        this.setVisible(false);
    }

    /**
     * Makes the shield visible.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield.
     */
    public void hideShield() {
        this.setVisible(false);
    }

}
