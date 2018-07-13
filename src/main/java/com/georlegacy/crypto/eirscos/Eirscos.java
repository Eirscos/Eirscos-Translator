package com.georlegacy.crypto.eirscos;

import com.georlegacy.crypto.eirscos.util.APIUtil;
import com.georlegacy.crypto.eirscos.util.ParseUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
