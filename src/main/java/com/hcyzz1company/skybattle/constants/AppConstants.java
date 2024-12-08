package com.hcyzz1company.skybattle.constants;

/**
 * This class contains constant values used in the Sky Battle game.
 * It includes settings for the screen size, window title, and other configurations.
 */
public class AppConstants {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class is only meant to store constant values and should not be instantiated.
     */
    private AppConstants() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * The width of the game window.
     */
    public static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the game window.
     */
    public static final int SCREEN_HEIGHT = 750;

    /**
     * The RESIZABLE settings of the game window.
     */
    public static final boolean RESIZABLE = false;

    /**
     * The title of the game window.
     */
    public static final String TITLE = "Sky Battle";

    /**
     * The upper height adjusted value used for Actors.
     */
    public static final double SCREEN_HEIGHT_UPPER_ADJUSTED = 50;

    /**
     * The lower height adjusted value used for Actors.
     */
    public static final double SCREEN_HEIGHT_LOWER_ADJUSTED = 650;

    /**
     * The delay between game updates in milliseconds.
     */
    public static final int MILLISECOND_DELAY = 16;

}
