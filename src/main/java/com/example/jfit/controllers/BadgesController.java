package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.domain.User;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BadgesController {

    private Service service;
    private String username;
    Parent root;

    @FXML
    private ImageView bodyBadge;
    @FXML
    private ImageView armBadge;
    @FXML
    private ImageView legBadge;
    @FXML
    private ImageView runBadge;
    @FXML
    private ImageView bicycleBadge;
    @FXML
    private ImageView zeusBadge;
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private AnchorPane badgesAnchorPane;
    @FXML
    private Label tokenLabel;
    @FXML
    private Label usernameLabel;

    /**
     * Sets the service and the username
     * @param service the service
     * @param username the username
     */
    public void setService(Service service, String username) {
        this.service = service;
        this.username = username;
        setBadges();
        tokenLabel.setText(service.findUserByUsername(username).getTokens().toString());
        usernameLabel.setText("Logged in as : " + username);
    }

    /**
     * Sets the badges to their original state
     */
    public void setBadges() {
        if(service.findUserByUsername(username).getBadges() == null || service.findUserByUsername(username).getBadges().equals("")){
            return;
        }
        String[] badges = service.findUserByUsername(username).getBadges().split(",");
        for (String badge : badges) {
            switch (badge) {
                case "absBadge" -> {
                    bodyBadge.setOpacity(1);
                    bodyBadge.effectProperty().setValue(null);
                }
                case "armBadge" -> {
                    armBadge.setOpacity(1);
                    armBadge.effectProperty().setValue(null);
                }
                case "legBadge" -> {
                    legBadge.setOpacity(1);
                    legBadge.effectProperty().setValue(null);
                }
                case "runBadge" -> {
                    runBadge.setOpacity(1);
                    runBadge.effectProperty().setValue(null);
                }
                case "bicycleBadge" -> {
                    bicycleBadge.setOpacity(1);
                    bicycleBadge.effectProperty().setValue(null);
                }
                case "zeusBadge" -> {
                    zeusBadge.setOpacity(1);
                    zeusBadge.effectProperty().setValue(null);
                }
            }
        }
    }

    /**
     * Minimizes the window
     * @throws Exception if the window cannot be minimized
     */
    public void minimize() throws Exception {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Closes the window
     * @throws Exception if the window cannot be closed
     */
    public void close() throws Exception {
        Stage stage = (Stage) xButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the window draggable
     */
    public void setDraggable(){
        badgesAnchorPane.setOnMousePressed(event -> {
            badgesAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) badgesAnchorPane.getScene().getWindow();
                stage.setX(event1.getScreenX() - event.getX());
                stage.setY(event1.getScreenY() - event.getY());
            });
        });
    }

    /**
     * Changes the scene
     * @param fxml the fxml file
     * @throws IOException if the fxml file cannot be loaded
     */
    public void changeScene(String fxml) throws IOException {
        Stage stage = (Stage) xButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        root = loader.load();
        Scene scene = new Scene(root, 850, 500);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), root);
        fade.setFromValue(0.7);
        fade.setToValue(1);
        fade.play();

        stage.setScene(scene);
        if (fxml.equals("menu.fxml")) {
            MenuController menuController = loader.getController();
            menuController.setService(service, username);
        }
    }

    /**
     * Goes back to the menu
     * @throws IOException if the fxml file cannot be loaded
     */
    public void backToMenu() throws IOException {
        changeScene("menu.fxml");
    }
}
