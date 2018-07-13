package com.georlegacy.crypto.eirscos;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Eirscos extends Application {

    BorderPane mainPane;

    public static void main(String[] args) {
        Logger.getGlobal().info("Loading application...");
        Eirscos.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new LoginPage().getLoginPage(primaryStage));

        primaryStage.show();
    }

}
