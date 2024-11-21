package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import javafx.stage.Stage;

/**
 * Manages the flow of levels in the game, including advancing to the next level and checking if it's the final level.
 */
public class LevelUtil {
    // Constants for the levels (fully-qualified class names)
    private static final String LEVEL_ONE = "com.hcyzz1company.skybattle.core.level.levelOne.LevelOne";
    private static final String LEVEL_TWO = "com.hcyzz1company.skybattle.core.level.levelTwo.LevelTwo";
    private static final String LEVEL_THREE = "com.hcyzz1company.skybattle.core.level.levelThree.LevelThree";
    private static final String FINAL_LEVEL = LEVEL_THREE;

    /**
     * Get the next level class name based on the current level class name.
     * If the current level is the last one, it returns null.
     *
     * @param currentLevel The current level class name.
     * @return The next level class name, or null if the current level is the final level.
     */
    public static String getNextLevel(String currentLevel) {
        switch (currentLevel) {
            case LEVEL_ONE:
                return LEVEL_TWO;
            case LEVEL_TWO:
                return LEVEL_THREE;
            default:
                return null;
        }
    }

    /**
     * Checks if the current level is the final level in the game.
     *
     * @param currentLevel The current level class name.
     * @return true if it's the final level, otherwise false.
     */
    public static boolean isFinalLevel(String currentLevel) {
        if (currentLevel.equals(FINAL_LEVEL)) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves the class name of the first level.
     *
     * @return The class name of the first level.
     */
    public static String getFirstLevel() {
        return LEVEL_ONE;
    }
}
