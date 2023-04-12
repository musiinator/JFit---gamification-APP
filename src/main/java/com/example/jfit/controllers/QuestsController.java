package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.domain.Quest;
import com.example.jfit.domain.QuestDTO;
import com.example.jfit.domain.QuestStatus;
import com.example.jfit.domain.User;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuestsController{
    private Service service;
    private String username;
    private User user;

    Parent root;
    @FXML
    private ObservableList<QuestDTO> quests = FXCollections.observableArrayList();
    @FXML
    private TableView<QuestDTO> questsTableView;
    @FXML
    private TableColumn<QuestDTO, String> descriptionTableColumn;
    @FXML
    private TableColumn<QuestDTO, String> typeTableColumn;
    @FXML
    private TableColumn<QuestDTO, Integer> rewardTableColumn;
    @FXML
    private TableColumn<QuestDTO, QuestStatus> statusTableColumn;
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button doneButton;
    @FXML
    private Button addQuestButton;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField rewardTextField;
    @FXML
    private Label tokenLabel;
    @FXML
    private AnchorPane questsAnchorPane;
    @FXML
    private Label usernameLabel;

    /**
     * Sets the service and the username
     * @param service the service
     * @param username the username of the logged user
     */
    public void setService(Service service, String username) {
        this.service = service;
        this.username = username;
        user = service.findUserByUsername(username);
        populateQuests();
        populateComboBox();
        tokenLabel.setText(user.getTokens().toString());
        usernameLabel.setText("Logged in as : " + username);
    }

    /**
     * Populates the table view with the available quests
     */
    public void populateQuests() {
        quests.clear();
        for(Quest quest : service.findAllQuests()){
            if(quest.getIdUsers().contains(user.getId())) {
                if (service.findOneUser(quest.getIdUser()).getTokens() > quest.getReward()) {
                    quests.add(new QuestDTO(quest.getDescription(), quest.getType(), quest.getReward(), QuestStatus.SOLVED));
                }
            }
            else{
                if (service.findOneUser(quest.getIdUser()).getTokens() > quest.getReward()) {
                    quests.add(new QuestDTO(quest.getDescription(), quest.getType(), quest.getReward(), QuestStatus.UNSOLVED));
                }
            }
        }

        if(quests.size() > 0) {
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            rewardTableColumn.setCellValueFactory(new PropertyValueFactory<>("reward"));
            statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            questsTableView.setItems(quests);
        }
    }

    /**
     * Populates the combo box with the available types of exercises
     */
    public void populateComboBox() {
        typeComboBox.getItems().add("Cardio - treadmill");
        typeComboBox.getItems().add("Cardio - bicycle");

        typeComboBox.getItems().add("Abs");
        typeComboBox.getItems().add("Chest");
        typeComboBox.getItems().add("Back");
        typeComboBox.getItems().add("Shoulders");
        typeComboBox.getItems().add("Biceps");
        typeComboBox.getItems().add("Triceps");
        typeComboBox.getItems().add("Legs");
        typeComboBox.getItems().add("Other");
    }

    /**
     * Minimizes the window
     * @throws Exception if the window is not found
     */
    public void minimize() throws Exception {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Closes the window
     * @throws Exception if the window is not found
     */
    public void close() throws Exception {
        Stage stage = (Stage) xButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Makes the window draggable
     */
    public void setDraggable(){
        questsAnchorPane.setOnMousePressed(event -> {
            questsAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) questsAnchorPane.getScene().getWindow();
                stage.setX(event1.getScreenX() - event.getX());
                stage.setY(event1.getScreenY() - event.getY());
            });
        });
    }

    /**
     * Changes the scene to the one specified in the parameter
     * @param fxml the fxml file of the scene
     * @throws IOException if the fxml file is not found
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
     * @throws IOException if the fxml file is not found
     */
    public void backToMenu() throws IOException {
        changeScene("menu.fxml");
    }

    /**
     * Adds a quest to the list of quests
     */
    public void addQuest(){

        String description = descriptionTextField.getText();
        String type = typeComboBox.getValue();
        int reward = Integer.parseInt(rewardTextField.getText());
        List<Long> idUsers = new ArrayList<>();
        try{
            Quest quest = new Quest(description, type, reward, user.getId(), idUsers, QuestStatus.UNSOLVED);
            service.saveQuest(quest);
            quests.add(new QuestDTO(description, type, reward, QuestStatus.UNSOLVED));
            questsTableView.setItems(quests);
            populateQuests();
            MenuController.notifications.put(user.getId(), "Congratulations! Your quest: " + quest.getDescription() + " has been published successfully!");

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid data");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    /**
     * Adds the user to the list of users who have done the quest
     */
    public void doneQuest(ActionEvent actionEvent) {
        QuestDTO questDTO = questsTableView.getSelectionModel().getSelectedItem();
        List<Long> idUsers = new ArrayList<>();
        Quest foundQuest = null;
        if(questDTO != null) {
            if(questDTO.getStatus().equals(QuestStatus.SOLVED)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Quest already done");
                alert.setContentText("You have already done this quest!");
                alert.showAndWait();
                return;
            }
            questsTableView.getItems().remove(questDTO);
            for (Quest quest : service.findAllQuests()) {
                if (Objects.equals(quest.getDescription(), questDTO.getDescription()) && Objects.equals(quest.getType(), questDTO.getType()) && Objects.equals(quest.getReward(), questDTO.getReward())) {
                    idUsers = quest.getIdUsers();
                    idUsers.add(user.getId());
                    quest.setIdUsers(idUsers);
                    service.updateQuest(quest);
                    foundQuest = quest;
                    User creatorOfQuest = service.findOneUser(quest.getIdUser());
                    if(creatorOfQuest != null) {
                        getBadge(creatorOfQuest, quest.getType());
                    }
                    break;
                }
            }
            populateQuests();
            //readjust the tokens for the users and create notifications
            if(foundQuest != null){
                //for user1
                user.setTokens(user.getTokens() + foundQuest.getReward());
                tokenLabel.setText(String.valueOf(user.getTokens()));
                service.updateUser(user);
                MenuController.notifications.put(user.getId(), "Congratulations! You have completed the quest: " + foundQuest.getDescription() + " successfully!");

                //for user2
                User creatorOfQuest = service.findOneUser(foundQuest.getIdUser());
                creatorOfQuest.setTokens(creatorOfQuest.getTokens() - foundQuest.getReward());
                service.updateUser(creatorOfQuest);
                MenuController.notifications.put(creatorOfQuest.getId(), "Congratulations! You have received a new Badge! Check your profile!");
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No quest selected");
            alert.setContentText("Please select a quest!");
            alert.showAndWait();
        }
    }

    /**
     * sets the badges for the user
     * @param user the user for which the badges are set
     * @param type the type of the quest
     */
    public void getBadge(User user, String type){
        String badges = "";
        if(user.getBadges() != null) {
            badges = user.getBadges() + ",";
            if(user.getBadges().split(",").length == 5){
                badges += "zeusBadge,"; //if the user has all the badges, he gets the zeus badge
            }
        }
        switch (type) {
            case "Cardio - treadmill":
                badges += "runBadge,";
                user.setBadges(badges);
                service.updateUser(user);
                break;
            case "Cardio - bicycle":
                badges += "bicycleBadge,";
                user.setBadges(badges);
                service.updateUser(user);
                break;
            case "Abs":
            case "Chest":
            case "Back":
            case "Shoulders":
                badges += "absBadge,";
                user.setBadges(badges);
                service.updateUser(user);
                break;
            case "Biceps":
            case "Triceps":
                badges += "armBadge";
                user.setBadges(badges);
                service.updateUser(user);
                break;
            case "Legs":
                badges += "legBadge";
                user.setBadges(badges);
                service.updateUser(user);
                break;
            case "Other":
                break;
        }
    }
}
