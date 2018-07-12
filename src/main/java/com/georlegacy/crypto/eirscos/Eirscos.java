package com.georlegacy.crypto.eirscos;

import com.georlegacy.crypto.eirscos.util.APIUtil;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Eirscos extends Application {

    BorderPane mainPane;

    public static void main(String[] args) {
        Logger.getGlobal().info("Loading application...");
        Eirscos.launch(args);
        System.out.println(APIUtil.getKey(""));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainPane = new BorderPane();

        // Initialising stage
        primaryStage.setResizable(false);
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Translator - Eirscos Cryptography");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.png")));

        // Top navbar
        HBox navbar = new HBox();
        navbar.setStyle("-fx-background-color: #00ff00;");
        navbar.setPadding(new Insets(10, 5, 10, 5));
        navbar.setAlignment(Pos.CENTER);

        Label header = new Label();
        header.setText("Eirscos Translation Service");
        header.setFont(Font.font(header.getFont().getFamily(), 24));

        navbar.getChildren().add(header);

        // Translation
        BorderPane translator = new BorderPane();
        translator.setStyle("-fx-background-color: #3d3d3d;");

        VBox decoded = new VBox();
        decoded.setAlignment(Pos.CENTER_LEFT);
        decoded.setSpacing(5);
        decoded.setPadding(new Insets(40, 70, 20, 70));

        Label decodedHeader  = new Label();
        decodedHeader.setText("English");
        decodedHeader.setStyle("-fx-text-fill: #bcbcbc");
        decodedHeader.setFont(Font.font(decodedHeader.getFont().getFamily(), FontWeight.BOLD, 20));

        TextField decodedField = new TextField();
        decodedField.setMaxSize(250, 30);
        decodedField.setMinSize(250, 30);

        decoded.getChildren().add(decodedHeader);
        decoded.getChildren().add(decodedField);

        translator.setTop(decoded);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.setMaxHeight(100);
        buttons.setPadding(new Insets(20, 10, 20, 10));

        Button translateDown = new Button();
        translateDown.setText("▼");
        translateDown.setStyle("-fx-text-fill: #000000");
        translateDown.setFont(Font.font(translateDown.getFont().getFamily(), 18));

        Label translateLabel = new Label();
        translateLabel.setText("Translate");
        translateLabel.setStyle("-fx-text-fill: #ffffff");
        translateLabel.setFont(Font.font(translateLabel.getFont().getFamily(), FontWeight.BOLD, 20));

        Button translateUp = new Button();
        translateUp.setText("▲");
        translateUp.setStyle("-fx-text-fill: #000000");
        translateUp.setFont(Font.font(translateUp.getFont().getFamily(), 18));

        buttons.getChildren().add(translateDown);
        buttons.getChildren().add(translateLabel);
        buttons.getChildren().add(translateUp);

        translator.setCenter(buttons);

        VBox encoded = new VBox();
        encoded.setAlignment(Pos.TOP_LEFT);
        encoded.setSpacing(5);
        encoded.setMinHeight(250);
        encoded.setPadding(new Insets(20, 70, 40, 70));

        Label encodedHeader  = new Label();
        encodedHeader.setText("Eirscos Version 1a");
        encodedHeader.setStyle("-fx-text-fill: #bcbcbc");
        encodedHeader.setFont(Font.font(encodedHeader.getFont().getFamily(), FontWeight.BOLD, 20));

        TextField encodedField = new TextField();
        encodedField.setMaxSize(250, 30);
        encodedField.setMinSize(250, 30);

        encoded.getChildren().add(encodedHeader);
        encoded.getChildren().add(encodedField);

        translator.setBottom(encoded);

        mainPane.setTop(navbar);
        mainPane.setCenter(translator);

        Scene scene = new Scene(mainPane, 400, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
