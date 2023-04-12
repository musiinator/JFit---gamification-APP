package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.domain.User;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MenuController {

    public static final Map<Long, String> notifications = new HashMap<>();

    private FXMLLoader loader;
    private Service service;

    private String username;

    private Parent root;
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private Label usernameLabel;

    /**
     * Sets the service and the username
     * @param service the service
     * @param username the username of the user that is logged in
     */
    public void setService(Service service, String username) {
        this.service = service;
        this.username = username;
        usernameLabel.setText("Logged in as : " + username);
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
     * Makes the window draggable
     */
    public void setDraggable(){
        menuAnchorPane.setOnMousePressed(event -> {
            menuAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) menuAnchorPane.getScene().getWindow();
                stage.setX(event1.getScreenX() - event.getX());
                stage.setY(event1.getScreenY() - event.getY());
            });
        });
    }

    /**
     * Changes the scene to the fxml file given as parameter
     * @param fxml the fxml file to be loaded
     * @throws IOException if the fxml file cannot be loaded
     */
    public void changeScene(String fxml) throws IOException {
        Stage stage = (Stage) xButton.getScene().getWindow();
        loader = new FXMLLoader(Main.class.getResource(fxml));
        root = loader.load();
        Scene scene = new Scene(root, 850, 500);
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        stage.setScene(scene);
    }

    /**
     * Opens the badges window
     * @throws IOException if the fxml file cannot be loaded
     */
    public void openBadges() throws IOException {
        changeScene("badges.fxml");
        BadgesController badgesController = loader.getController();
        badgesController.setService(service, username);
    }

    /**
     * Opens the quests window
     * @throws IOException if the fxml file cannot be loaded
     */
    public void openQuests() throws IOException {
        changeScene("quests.fxml");
        QuestsController questsController = loader.getController();
        questsController.setService(service, username);
    }

    /**
     * Opens the ranking window
     * @throws IOException if the fxml file cannot be loaded
     */
    public void openRankings() throws IOException {
        changeScene("rankings.fxml");
        RankingsController rankingsController = loader.getController();
        rankingsController.setService(service, username);
    }

    /**
     * Opens the notifications window
     * @throws IOException if the fxml file cannot be loaded
     */
    public void openNotifications() throws IOException {
        changeScene("notifications.fxml");
        NotificationsController notificationsController = loader.getController();
        notificationsController.setService(service, username);
    }

    /**
     * closes the window
     * @throws IOException if the window cannot be closed
     */
    public void openLogOut() throws IOException {
        Stage stage = (Stage) xButton.getScene().getWindow();
        stage.close();
    }


}
