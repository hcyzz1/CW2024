package com.hcyzz1company.skybattle.ui.basicImage;

import com.hcyzz1company.skybattle.constants.ImageConstants;
import com.hcyzz1company.skybattle.utils.ui.ImageUtil;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents the shield of the actors.
 * It uses ImageUtil to create and display the image on the screen.
 */
public class ShieldImage extends ImageView {
    private static final String SHIELD_PATH = ImageConstants.IMAGE_ROOT_PATH + "shield.png";
    private static final int SHIELD_X_POSITION = 100;
    private static final int SHIELD_Y_POSITION = 40;
    private static final int SHIELD_WIDTH = 200;
    private static final int SHIELD_HEIGHT = 200;

    /**
     * Constructs a WinImage object.
     * Set the width & height & position of the Pic.
     */
    public ShieldImage() {
        super(ImageUtil.creteImage(SHIELD_PATH));
        ImageUtil.setImageViewRatio(this, SHIELD_WIDTH, SHIELD_HEIGHT);
        ImageUtil.setImagePositon(this, SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.setOpacity(0.5);

        this.setVisible(false);
    }

    /**
     * Makes the shield visible on the screen.
     * This method is called when the shield is activated.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield from the screen.
     * This method is called when the shield is deactivated.
     */
    public void hideShield() {
        this.setVisible(false);
    }

}
