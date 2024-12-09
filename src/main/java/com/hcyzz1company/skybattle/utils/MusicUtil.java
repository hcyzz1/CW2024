package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.core.handle.UserInputHandle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * Utility class for managing music and sound effects in the Sky Battle game.
 * Provides methods to play background music, shooting sounds, and explosion sounds.
 * Also includes toggles to enable or disable specific sound effects.
 */
public class MusicUtil {

    /** Indicates whether the shooting sound effect is enabled. */
    public static boolean isShootPlaying = true;

    /** Indicates whether the background music is enabled. */
    public static boolean isBackGroundPlaying = true;

    /** Indicates whether the explosion sound effect is enabled. */
    public static boolean playExplosionSound = true;

    /** MediaPlayer instance for playing background music in a loop. */
    public static final MediaPlayer backGroudmediaPlayer = new MediaPlayer(
            new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/全民飞机大战游戏配乐音效_爱给网_aigei_com.mp3").toExternalForm())
    );

    /**
     * Plays the shooting sound effect.
     * This method checks if the shooting sound is enabled before playing.
     */
    public static void playShootSound() {
        if (isShootPlaying) {
            MediaPlayer shotMediaPlayer = new MediaPlayer(
                    new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/开枪射击声音 08_爱给网_aigei_com.mp3").toExternalForm())
            );
            shotMediaPlayer.play();
        }
    }

    /**
     * Plays the explosion sound effect.
     * This method checks if the explosion sound is enabled before playing.
     */
    public static void playExplosionSound() {
        if (playExplosionSound) {
            MediaPlayer explosionMediaPlayer = new MediaPlayer(
                    new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/效果sfx bossfashezidan1(effcet_s_爱给网_aigei_com.mp3").toExternalForm())
            );
            explosionMediaPlayer.play();
        }
    }

    /**
     * Plays or stops the background music based on the current setting.
     * The background music plays in a loop if enabled.
     */
    public static void playBackGroundSound() {
        if (isBackGroundPlaying) {
            backGroudmediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backGroudmediaPlayer.play();
        } else {
            backGroudmediaPlayer.stop();
        }
    }

    /**
     * Returns whether the shooting sound effect is enabled.
     * 
     * @return {@code true} if shooting sound is enabled; {@code false} otherwise.
     */
    public static boolean isIsShootPlaying() {
        return isShootPlaying;
    }

    /**
     * Sets whether the shooting sound effect is enabled.
     * 
     * @param isShootPlaying {@code true} to enable shooting sound; {@code false} to disable.
     */
    public static void setIsShootPlaying(boolean isShootPlaying) {
        MusicUtil.isShootPlaying = isShootPlaying;
    }

    /**
     * Returns whether the background music is enabled.
     * 
     * @return {@code true} if background music is enabled; {@code false} otherwise.
     */
    public static boolean isIsBackGroundPlaying() {
        return isBackGroundPlaying;
    }

    /**
     * Sets whether the background music is enabled.
     * 
     * @param isBackGroundPlaying {@code true} to enable background music; {@code false} to disable.
     */
    public static void setIsBackGroundPlaying(boolean isBackGroundPlaying) {
        MusicUtil.isBackGroundPlaying = isBackGroundPlaying;
    }

    /**
     * Returns whether the explosion sound effect is enabled.
     * 
     * @return {@code true} if explosion sound is enabled; {@code false} otherwise.
     */
    public static boolean isPlayExplosionSound() {
        return playExplosionSound;
    }

    /**
     * Sets whether the explosion sound effect is enabled.
     * 
     * @param playExplosionSound {@code true} to enable explosion sound; {@code false} to disable.
     */
    public static void setPlayExplosionSound(boolean playExplosionSound) {
        MusicUtil.playExplosionSound = playExplosionSound;
    }
}
