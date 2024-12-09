package com.hcyzz1company.skybattle.core;

import com.hcyzz1company.skybattle.utils.MusicUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the game's start screen with background and buttons.
 */
public class GameStartScreen {

    private final Stage stage;
    private final Runnable onStartGame;

    /**
     * Constructor for GameStartScreen.
     *
     * @param stage       The main stage for the game.
     * @param onStartGame Action to start the game.
     */
    public GameStartScreen(Stage stage, Runnable onStartGame) {
        this.stage = stage;
        this.onStartGame = onStartGame;
    }

    /**
     * Shows the start screen.
     */
    public void show() {
        // Root layout
        StackPane layout = new StackPane();

        // Load background image
        Image backgroundImage = new Image(
            getClass().getResource("/com/hcyzz1company/skybattle/images/background1.jpg").toExternalForm()
        );
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Bind the background image size to the stage's size
        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());
        backgroundImageView.setPreserveRatio(false);

        // Add the background to the layout
        layout.getChildren().add(backgroundImageView);

        // Buttons
        VBox buttonLayout = new VBox(15);
        buttonLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> onStartGame.run());

        Button controlsButton = new Button("Controls");
        controlsButton.setOnAction(e -> showControlScreen());

        Button soundSettingsButton = new Button("Sound Settings");
        soundSettingsButton.setOnAction(e -> showSoundSettingsScreen());

        buttonLayout.getChildren().addAll(startButton, controlsButton, soundSettingsButton);

        // Add buttons on top of the background
        layout.getChildren().add(buttonLayout);

        // Create the scene and bind its size to the stage
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows the control screen with a centered image on a background.
     */
    private void showControlScreen() {
        // Root layout for the control screen
        StackPane controlLayout = new StackPane();

        // Load background image
        Image backgroundImage = new Image(
            getClass().getResource("/com/hcyzz1company/skybattle/images/background1.jpg").toExternalForm()
        );
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Bind the background image size to the stage's size
        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());
        backgroundImageView.setPreserveRatio(false);

        // Load control information image
        Image controlImage = new Image(
            getClass().getResource("/com/hcyzz1company/skybattle/images/control.png").toExternalForm()
        );
        ImageView controlImageView = new ImageView(controlImage);

        // Set fixed size for the control image
        controlImageView.setFitWidth(1000); // Example width
        controlImageView.setFitHeight(1000); // Example height
        controlImageView.setPreserveRatio(true);

        // Back button to return to the start screen
        Button backButton = new Button("BACK");
        backButton.setStyle("-fx-font-size: 16; -fx-background-color: white; -fx-padding: 5 10;");
        backButton.setOnAction(e -> show()); // Return to the start screen

        // Center the control image and place the back button below it
        VBox controlBox = new VBox(15, controlImageView, backButton);
        controlBox.setAlignment(Pos.CENTER);

        // Add background and control content to the layout
        controlLayout.getChildren().addAll(backgroundImageView, controlBox);

        // Create the scene for the control layout
        Scene controlScene = new Scene(controlLayout);
        stage.setScene(controlScene);
        stage.show(); // Update the stage to apply changes
    }

    /**
     * Shows the sound settings screen with centered layout and background image.
     */
    private void showSoundSettingsScreen() {
        // Root layout for the sound settings screen
        StackPane rootLayout = new StackPane();

        // Load background image
        Image backgroundImage = new Image(
                getClass().getResource("/com/hcyzz1company/skybattle/images/background1.jpg").toExternalForm()
        );
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Bind the background image size to the stage's size
        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());
        backgroundImageView.setPreserveRatio(false);

        // Main content layout
        VBox layout = new VBox(30); // Increased spacing for better alignment
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER); // Center the whole layout

        // Title
        Label titleLabel = new Label("Voice Control");
        titleLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: white;");

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        // Music Section
        VBox musicSection = new VBox(10);
        musicSection.setAlignment(Pos.CENTER);

        Label musicLabel = new Label("Music");
        musicLabel.setStyle("-fx-font-size: 24; -fx-text-fill: orange;");

        // Background music option (CheckBox)
        CheckBox backgroundMusic = new CheckBox("Background Music");
        backgroundMusic.setSelected(true); // Default to checked (enabled)

        // Add listener to handle play/stop actions
        backgroundMusic.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    MusicUtil.setIsBackGroundPlaying(true);
                } else {
                    MusicUtil.setIsBackGroundPlaying(false);
                }
                MusicUtil.playBackGroundSound();
            }
        });



        VBox musicOptions = new VBox(5, backgroundMusic);
        musicOptions.setAlignment(Pos.CENTER);


        musicSection.getChildren().addAll(musicLabel, musicOptions);

        // Game Sound Section
        VBox soundSection = new VBox(10);
        soundSection.setAlignment(Pos.CENTER);

        Label soundLabel = new Label("Game Sound");
        soundLabel.setStyle("-fx-font-size: 24; -fx-text-fill: orange;");

        CheckBox explosionSound = new CheckBox("Explosion Sound");
        explosionSound.setSelected(true);
        // Add listener to handle play/stop actions
        explosionSound.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    MusicUtil.setPlayExplosionSound(true);
                } else {
                    MusicUtil.setPlayExplosionSound(false);
                }
            }
        });

        CheckBox shootSound = new CheckBox("Shoot Sound");
        shootSound.setSelected(true);
        // Add listener to handle play/stop actions
        shootSound.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    MusicUtil.setIsShootPlaying(true);
                } else {
                    MusicUtil.setIsShootPlaying(false);
                }
            }
        });


        CheckBox getObjectSound = new CheckBox("Get Object Sound");
        getObjectSound.setSelected(true);

        CheckBox userDamageSound = new CheckBox("User Damage Sound");
        userDamageSound.setSelected(true);

        CheckBox shieldSound = new CheckBox("Shield Sound");
        shieldSound.setSelected(true);

        VBox soundOptions = new VBox(5, explosionSound, shootSound, getObjectSound, userDamageSound, shieldSound);
        soundOptions.setAlignment(Pos.CENTER); // Center options
        soundSection.getChildren().addAll(soundLabel, soundOptions);

        // Close Button
        HBox closeButtonBox = new HBox();
        closeButtonBox.setAlignment(Pos.CENTER); // Center close button
        Label closeButton = new Label("CLOSE");
        closeButton.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-cursor: hand;");
        closeButton.setOnMouseClicked(e -> show());
        closeButtonBox.getChildren().add(closeButton);

        // Combine all sections
        layout.getChildren().addAll(titleBox, musicSection, soundSection, closeButtonBox);

        // Add the background and main content to the root layout
        rootLayout.getChildren().addAll(backgroundImageView, layout);

        // Create the scene
        Scene scene = new Scene(rootLayout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }



}
