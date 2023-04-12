package com.example.jfit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupController implements Initializable {
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private AnchorPane popupAnchorPane;

    @FXML
    private Text rulesText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rulesText.setText("Welcome to JFit, the fitness gamification app that helps you stay motivated and achieve your fitness goals! To ensure a positive experience for all users, we've created some rules that we ask everyone to follow. Please read through the following rules carefully to help us maintain a fair and enjoyable environment for everyone on the app:\n" +
                "\n" +
                "Badges:\n" +
                "- Badges are a fun way to show off your achievements.\n" +
                "- You can find your badges in the \"Badges\" section from the main menu.\n" +
                "- Each badge is specific to a category of exercise, such as running, weightlifting, or cycling.\n" +
                "- You can earn badges by creating quests related to a category of exercise.\n" +
                "- After creating a quest, you'll earn a specific badge only when someone solves your quest.\n" +
                "- You can earn a badge only once.\n" +
                "\n" +
                "Quests:\n" +
                "- Quests are a fun way to challenge yourself and earn rewards.\n" +
                "- You can find quests in the \"Quests\" section from the main menu.\n" +
                "- Every quest comes with a description, exercise type, and reward in tokens.\n" +
                "- After completing a quest, you'll receive the amount of tokens provided by the creator in the quest reward field. These tokens will be deducted from the creator's wallet.\n" +
                "- To mark a quest as done, simply select it from the list of quests and click the \"Done\" button.\n" +
                "- To create a quest, fill in the fields and click the \"Add Quest\" button.\n" +
                "\n" +
                "Rankings:\n" +
                "- You can find the leaderboard in the \"Rankings\" section from the main menu.\n" +
                "- Your ranking on the leaderboard is based on your total number of tokens.\n" +
                "- The more quests you complete, the higher your ranking will be.\n" +
                "\n" +
                "Notifications:\n" +
                "- You'll receive notifications when you complete a quest or earn a new badge.\n" +
                "- You can find your notifications in the \"Notifications\" section from the main menu.\n" +
                "\n" +
                "Token System:\n" +
                "- Everyone starts with 1000 tokens.\n" +
                "- You can earn tokens by completing quests.\n" +
                "- You can spend tokens by creating quests.\n" +
                "- To earn the Zeus Badge, you must either reach 2000 tokens or earn all the other badges.\n" +
                "We hope these rules help create a fair and fun environment for everyone on JFit. If you have any questions or concerns, please don't hesitate to reach out to our support team. Happy questing!");
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
     * Allows the window to be dragged
     */
    public void setDraggable(){
        popupAnchorPane.setOnMousePressed(event -> {
            popupAnchorPane.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) popupAnchorPane.getScene().getWindow();
                stage.setX(event1.getScreenX() - event.getX());
                stage.setY(event1.getScreenY() - event.getY());
            });
        });
    }
}
