package com.georlegacy.crypto.eirscos;

import com.georlegacy.crypto.eirscos.util.APIUtil;
import com.georlegacy.crypto.eirscos.util.ParseUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Map;

public class LoginPage {

    private Map<String, String> keys;

    BorderPane mainPane;

    private Scene getSuccessPage(Stage primaryStage) {

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

        VBox encoded = new VBox();
        encoded.setAlignment(Pos.TOP_LEFT);
        encoded.setSpacing(5);
        encoded.setMinHeight(250);
        encoded.setPadding(new Insets(20, 70, 40, 70));

        TextField encodedField = new TextField();
        encodedField.setMaxSize(250, 30);
        encodedField.setMinSize(250, 30);

        Label encodedHeader  = new Label();
        encodedHeader.setText("Eirscos Version 1a");
        encodedHeader.setStyle("-fx-text-fill: #bcbcbc");
        encodedHeader.setFont(Font.font(encodedHeader.getFont().getFamily(), FontWeight.BOLD, 20));

        encoded.getChildren().add(encodedHeader);
        encoded.getChildren().add(encodedField);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.setMaxHeight(100);
        buttons.setPadding(new Insets(20, 10, 20, 10));

        Button translateDown = new Button();
        translateDown.setText("▼");
        translateDown.setStyle("-fx-text-fill: #000000");
        translateDown.setFont(Font.font(translateDown.getFont().getFamily(), 18));
        translateDown.setOnAction((event -> {
            String english = decodedField.getText();

            StringBuilder eirscos = new StringBuilder();
            for (char ch : english.toLowerCase().toCharArray()) {
                if (this.keys.containsKey(String.valueOf(ch))) {
                    eirscos.append(this.keys.get(String.valueOf(ch)));
                } else {
                    eirscos.append(String.valueOf(ch));
                }
            }

            encodedField.setText(eirscos.toString());
        }));

        Label translateLabel = new Label();
        translateLabel.setText("Translate");
        translateLabel.setStyle("-fx-text-fill: #ffffff");
        translateLabel.setFont(Font.font(translateLabel.getFont().getFamily(), FontWeight.BOLD, 20));

        Button translateUp = new Button();
        translateUp.setText("▲");
        translateUp.setStyle("-fx-text-fill: #000000");
        translateUp.setFont(Font.font(translateUp.getFont().getFamily(), 18));
        translateUp.setOnAction((event -> {
            String eirscos = encodedField.getText();

            StringBuilder english = new StringBuilder();
            for (char ch : eirscos.toLowerCase().toCharArray()) {
                if (this.keys.containsValue(String.valueOf(ch))) {
                    String toAppend = null;
                    for (Map.Entry<String, String> candidate : this.keys.entrySet()) {
                        if (String.valueOf(ch).equals(candidate.getValue().toLowerCase())) {
                            toAppend = candidate.getKey();
                        }
                    }
                    english.append(toAppend == null ? String.valueOf(ch) : toAppend);
                } else {
                    english.append(String.valueOf(ch));
                }
            }

            decodedField.setText(english.toString());
        }));

        buttons.getChildren().add(translateDown);
        buttons.getChildren().add(translateLabel);
        buttons.getChildren().add(translateUp);

        translator.setCenter(buttons);

        translator.setBottom(encoded);

        mainPane.setTop(navbar);
        mainPane.setCenter(translator);

        return new Scene(mainPane, 400, 500);
    }

    private Scene getFailPage(Stage primaryStage) {
        mainPane = new BorderPane();

        // Initialising stage
        primaryStage.setResizable(false);
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Login - Translator - Eirscos Cryptography");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.png")));

        // Top navbar
        VBox navbar = new VBox();
        navbar.setStyle("-fx-background-color: #00ff00;");
        navbar.setPadding(new Insets(10, 5, 10, 5));
        navbar.setAlignment(Pos.CENTER);

        Label header = new Label();
        header.setText("Eirscos Translation Service");
        header.setFont(Font.font(header.getFont().getFamily(), 24));

        Label desc = new Label();
        desc.setText("The access code you entered was incorrect");
        desc.setFont(Font.font(desc.getFont().getFamily(), FontWeight.BOLD, 18));
        desc.setStyle("-fx-text-fill: #ff0000");

        navbar.getChildren().add(header);
        navbar.getChildren().add(desc);

        // Login
        VBox login = new VBox();
        login.setAlignment(Pos.TOP_LEFT);
        login.setStyle("-fx-background-color: #3d3d3d");
        login.setPadding(new Insets(80, 50, 10, 50));
        login.setSpacing(5);

        Label secret = new Label();
        secret.setText("Access code");
        secret.setStyle("-fx-text-fill: #bcbcbc");
        secret.setFont(Font.font(secret.getFont().getFamily(), FontWeight.BOLD, 20));

        TextField secretBox = new TextField();

        login.getChildren().add(secret);
        login.getChildren().add(secretBox);

        // Proceed
        StackPane proceed = new StackPane();
        proceed.setAlignment(Pos.CENTER);
        proceed.setStyle("-fx-background-color: #3d3d3d");
        proceed.setPadding(new Insets(40, 50, 180, 50));

        Button proceedButton = new Button();
        proceedButton.setText("Proceed");
        proceedButton.setFont(Font.font(proceedButton.getFont().getFamily(), FontWeight.BOLD, 18));
        proceedButton.setOnAction(event -> {
            keys = ParseUtil.jsonToMap(APIUtil.getKey(secretBox.getText()));
            if (keys == null) {
                primaryStage.setScene(getFailPage(primaryStage));
            } else{
                primaryStage.setScene(getSuccessPage(primaryStage));
            }
        });

        proceed.getChildren().add(proceedButton);

        mainPane.setTop(navbar);
        mainPane.setCenter(login);
        mainPane.setBottom(proceed);

        return new Scene(mainPane, 400, 500);

    }

    public Scene getLoginPage(Stage primaryStage) {
        mainPane = new BorderPane();

        // Initialising stage
        primaryStage.setResizable(false);
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Login - Translator - Eirscos Cryptography");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.png")));

        // Top navbar
        VBox navbar = new VBox();
        navbar.setStyle("-fx-background-color: #00ff00;");
        navbar.setPadding(new Insets(10, 5, 10, 5));
        navbar.setAlignment(Pos.CENTER);

        Label header = new Label();
        header.setText("Eirscos Translation Service");
        header.setFont(Font.font(header.getFont().getFamily(), 24));

        Label desc = new Label();
        desc.setText("Security requires you to login");
        desc.setFont(Font.font(desc.getFont().getFamily(), FontWeight.BOLD, 20));
        desc.setStyle("-fx-text-fill: #ff0000");

        navbar.getChildren().add(header);
        navbar.getChildren().add(desc);

        // Login
        VBox login = new VBox();
        login.setAlignment(Pos.TOP_LEFT);
        login.setStyle("-fx-background-color: #3d3d3d");
        login.setPadding(new Insets(80, 50, 10, 50));
        login.setSpacing(5);

        Label secret = new Label();
        secret.setText("Access code");
        secret.setStyle("-fx-text-fill: #bcbcbc");
        secret.setFont(Font.font(secret.getFont().getFamily(), FontWeight.BOLD, 20));

        TextField secretBox = new TextField();

        login.getChildren().add(secret);
        login.getChildren().add(secretBox);

        // Proceed
        StackPane proceed = new StackPane();
        proceed.setAlignment(Pos.CENTER);
        proceed.setStyle("-fx-background-color: #3d3d3d");
        proceed.setPadding(new Insets(40, 50, 180, 50));

        Button proceedButton = new Button();
        proceedButton.setText("Proceed");
        proceedButton.setFont(Font.font(proceedButton.getFont().getFamily(), FontWeight.BOLD, 18));
        proceedButton.setOnAction(event -> {
            keys = ParseUtil.jsonToMap(APIUtil.getKey(secretBox.getText()));
            if (keys == null) {
                primaryStage.setScene(getFailPage(primaryStage));
            } else{
                primaryStage.setScene(getSuccessPage(primaryStage));
            }
        });

        proceed.getChildren().add(proceedButton);

        mainPane.setTop(navbar);
        mainPane.setCenter(login);
        mainPane.setBottom(proceed);

        return new Scene(mainPane, 400, 500);
    }

}
