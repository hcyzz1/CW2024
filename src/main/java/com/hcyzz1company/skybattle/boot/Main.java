package com.hcyzz1company.skybattle.boot;

import com.hcyzz1company.skybattle.constants.AppConstants;
import com.hcyzz1company.skybattle.logic.Controller;
import com.hcyzz1company.skybattle.utils.AlertUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Main Entry Point of the Program in the JavaFX Framework.
 * The entire application will start from here.
 */
public class Main extends Application {

    private Controller myController;

    /**
     * Initial Method for Loading the JavaFX Program.
     * This method will load the main window of the JavaFX program —— [Stage] .
     * If any exception occurs, we will show an [Alert] to tell users.
     * And you can check the error Message in the stack. (in the console Where run this JavaFX Application)
     *
     * @param stage The primary stage for JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle(AppConstants.TITLE);
            stage.setResizable(AppConstants.RESIZABLE);
            stage.setHeight(AppConstants.SCREEN_HEIGHT);
            stage.setWidth(AppConstants.SCREEN_WIDTH);

            myController = new Controller(stage);
            myController.launchGame();
        } catch (Exception e) {
            e.printStackTrace();

            AlertUtil.showError("Start Error",
                    "JavaFX Application Start Error",
                    "An error occurred while starting the game. \n" +
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