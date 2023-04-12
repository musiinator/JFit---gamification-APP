package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.domain.RankDTO;
import com.example.jfit.domain.User;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RankingsController {
    private Service service;
    private String username;

    Parent root;
    @FXML
    private TableView<RankDTO> rankingsTableView;
    @FXML
    private TableColumn<RankDTO, String> usernameTableColumn;
    @FXML
    private TableColumn<RankDTO, Integer> scoreTableColumn;
    @FXML
    private ObservableList<RankDTO> rankings = FXCollections.observableArrayList();
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Label tokenLabel;
    @FXML
    private AnchorPane rankingsAnchorPane;
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
        populateRankings();
        tokenLabel.setText(String.valueOf(service.findUserByUsername(username).getTokens()));
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
        rankingsAnchorPane.setOnMousePressed(event -> {
            rankingsAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) rankingsAnchorPane.getScene().getWindow();
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
     * @throws IOException if the menu cannot be loaded
     */
    public void backToMenu() throws IOException {
        changeScene("menu.fxml");
    }

    /**
     * Populates the rankings table with the users and their scores and sorts them by score
     */
    public void populateRankings() {
        for (User user : service.findAllUsers()) {
            RankDTO rankDTO = new RankDTO(user.getTokens(), user.getUsername());
            rankings.add(rankDTO);
        }
        if(rankings.size() > 0) {
            rankings.sort((o1, o2) -> o2.getScore() - o1.getScore());
            scoreTableColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
            usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            rankingsTableView.setItems(rankings);
        }
    }
}
