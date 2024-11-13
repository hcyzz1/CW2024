package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.utils.AlertUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Main Entry Point of the Program in the JavaFX Framework.
 * The entire application will start from here.
 */
public class Main extends Application {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";
    private Controller myController;

    /**
     * Initial Method for Loading the JavaFX Program.
     * This method will load the main window of the JavaFX program —— [Stage] .
     * If any exception occurs, we will show an [Alert] to tell users.
     * And you can check the error Message in the stack.(Where run this JavaFX Application)
     *
     * @param stage The primary stage for JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
                // Handle the uncaught exception here and show an alert
                Platform.runLater(() -> {
                    AlertUtil.showError("Unexpected Error", "An unexpected error occurred", throwable.getMessage());
                });
            });
            stage.setTitle(TITLE);
            stage.setResizable(false);
            stage.setHeight(SCREEN_HEIGHT);
            stage.setWidth(SCREEN_WIDTH);

            myController = new Controller(stage);
            myController.launchGame();
        } catch (Exception e) {
            e.printStackTrace();

            AlertUtil.showError("Start Error",
                    "JavaFX Application Start Error",
                    "An error occurred while starting the game. <br>" +
                            "Please check the stack information");
            Platform.exit();
        }
    }

    /**
     * The main method for launching the JavaFX application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }
}