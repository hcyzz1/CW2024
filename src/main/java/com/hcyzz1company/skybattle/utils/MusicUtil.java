package com.hcyzz1company.skybattle.utils;

import com.hcyzz1company.skybattle.core.handle.UserInputHandle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public  class  MusicUtil {


    public static  boolean isShootPlaying = true;
    public static  boolean isBackGroundPlaying = true;

    public static  boolean playExplosionSound = true;

    public static final MediaPlayer backGroudmediaPlayer = new MediaPlayer(new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/全民飞机大战游戏配乐音效_爱给网_aigei_com.mp3").toExternalForm()));




    /**
     * shot sound
     */
    public static void playShootSound() {
        if (isShootPlaying){
            MediaPlayer shotMediaPlayer = new MediaPlayer(new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/开枪射击声音 08_爱给网_aigei_com.mp3").toExternalForm()));
            // 播放音频
            shotMediaPlayer.play();
        }
    }



    /**
     * shot sound
     */
    public static void playExplosionSound() {
        if (playExplosionSound){
            MediaPlayer explosionMediaPlayer = new MediaPlayer(new Media(MusicUtil.class.getResource("/com/hcyzz1company/skybattle/images/效果sfx bossfashezidan1(effcet_s_爱给网_aigei_com.mp3").toExternalForm()));
            // 播放音频
            explosionMediaPlayer.play();
        }
    }


    /**
     * playBackGroundSound
     */
    public static void playBackGroundSound(){


        if(isBackGroundPlaying){
            backGroudmediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backGroudmediaPlayer.play();
        }else {
            backGroudmediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backGroudmediaPlayer.stop();
        }

    }







    public static boolean isIsShootPlaying() {
        return isShootPlaying;
    }

    public static void setIsShootPlaying(boolean isShootPlaying) {
        MusicUtil.isShootPlaying = isShootPlaying;
    }

    public static boolean isIsBackGroundPlaying() {
        return isBackGroundPlaying;
    }

    public static void setIsBackGroundPlaying(boolean isBackGroundPlaying) {
        MusicUtil.isBackGroundPlaying = isBackGroundPlaying;
    }

    public static boolean isPlayExplosionSound() {
        return playExplosionSound;
    }

    public static void setPlayExplosionSound(boolean playExplosionSound) {
        MusicUtil.playExplosionSound = playExplosionSound;
    }
}
