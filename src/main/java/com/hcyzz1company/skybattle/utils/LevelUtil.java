package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.exceptions.LevelLoadingException;
import javafx.stage.Stage;

/**
 * Manages the flow of levels in the game.
 */
public class LevelUtil {
    private static final String LEVEL_ONE = "com.hcyzz1company.skybattle.core.levelOne.LevelOne";
    private static final String LEVEL_TWO = "com.hcyzz1company.skybattle.core.levelTwo.LevelTwo";
    private static final String LEVEL_THREE = "com.hcyzz1company.skybattle.core.levelThree.LevelThree";
    private static final String FINAL_LEVEL = LEVEL_THREE;

    /**
     * Get the next level class name based on the current level class name.
     *
     * @param currentLevel The current level class name.
     * @return The next level class name.
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
     * Get the current level is the final level or not.
     *
     * @param currentLevel The current level class name.
     * @return if it is the final level, true;else, false.
     */
    public static boolean isFinalLevel(String currentLevel) {
        if (currentLevel.equals(FINAL_LEVEL)) {
            return true;
        }
        return false;
    }

    /**
     * Get the first level class name.
     *
     * @return The first level class name.
     */
    public static String getFirstLevel() {
        return LEVEL_ONE;
    }
}
