package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class NotificationsController {

    private Service service;
    private String username;
    private Parent root;
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private ListView<String> notificationsListView;
    @FXML
    private AnchorPane notificationsAnchorPane;
    @FXML
    private Label noNotificationsLabel;
    @FXML
    private Label tokenLabel;
    @FXML
    private Label usernameLabel;

    /**
     * Sets the service and the username
     * @param service the service
     * @param username the username of the user
     */
    public void setService(Service service, String username) {
        this.service = service;
        this.username = username;
        populateNotifications();
        tokenLabel.setText(service.findUserByUsername(username).getTokens().toString());
        usernameLabel.setText("Logged in as : " + username);
    }

    /**
     * Populates the notifications list view with the notifications of the user
     */
    public void populateNotifications() {
        notificationsListView.getItems().clear();
        noNotificationsLabel.setVisible(false);
        for(Map.Entry<Long, String> entry : MenuController.notifications.entrySet()) {
            if(Objects.equals(entry.getKey(), service.findUserByUsername(username).getId())){
                notificationsListView.getItems().add(entry.getValue());
            }
        }
        if(notificationsListView.getItems().isEmpty()){
            noNotificationsLabel.setVisible(true);
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
     * Makes the window draggable
     */
    public void setDraggable(){
        notificationsAnchorPane.setOnMousePressed(event -> {
            notificationsAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) notificationsAnchorPane.getScene().getWindow();
                stage.setX(event1.getScreenX() - event.getX());
                stage.setY(event1.getScreenY() - event.getY());
            });
        });
    }

    /**
     * Changes the scene to the menu
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
     * Changes the scene to the menu
     * @throws IOException if the fxml file cannot be loaded
     */
    public void backToMenu() throws IOException {
        changeScene("menu.fxml");
    }
}
