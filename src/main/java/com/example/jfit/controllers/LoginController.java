package com.example.jfit.controllers;

import com.example.jfit.Main;
import com.example.jfit.domain.Quest;
import com.example.jfit.domain.User;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginController {

    private Service service;

    public Parent root;

    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label loginError;
    @FXML
    private Button xButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private AnchorPane loginAnchorPane;

    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Opens the menu
     * @param stage the stage
     * @return the loader
     * @throws Exception if the fxml file is not found
     */
    private FXMLLoader openMenu(Stage stage) throws Exception{
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root, 850, 500);
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), root);
        fade.setFromValue(0.2);
        fade.setToValue(1.0);
        fade.play();
        stage.setTitle("JFit Menu");
        stage.setScene(scene);
        stage.show();
        return loader;
    }

    /**
     * Opens the register
     * @param stage the stage
     * @return the loader
     * @throws Exception if the fxml file is not found
     */
    private FXMLLoader openRegister(Stage stage) throws Exception{
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("register.fxml"));
        root = loader.load();
        Scene scene = new Scene(root, 392, 600);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), root);
        fade.setFromValue(0.3);
        fade.setToValue(1.0);
        fade.play();
        stage.setTitle("Register to JFit!");
        stage.setScene(scene);
        stage.show();
        return loader;
    }

    /**
     * Opens the rules window
     * @param stage the stage
     * @return the loader
     * @throws Exception if the fxml file is not found
     */
    private FXMLLoader openPopup(Stage stage) throws Exception{
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("popup.fxml"));
        Parent root1 = loader.load();
        Scene scene = new Scene(root1, 545, 795);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), root1);
        fade.setFromValue(0.3);
        fade.setToValue(1.0);
        fade.play();
        stage.setTitle("Welcome to JFit!");
        stage.setScene(scene);
        stage.showAndWait();
        return loader;
    }

    /**
     * Verifies the login data
     * @throws Exception if the fxml file is not found
     */
    public void verifyLogin() throws Exception {
        String username = usernameText.getText();
        String password = passwordText.getText();
        boolean okLogin = false;
        for(User user : service.findAllUsers()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                okLogin = true;
                break;
            }
        }
        if(!okLogin) {
            loginError.setText("Incorrect username or password!");
        }
        else{
            usernameText.clear();
            passwordText.clear();
            loginError.setText("");
            Stage stagePopup = new Stage();
            stagePopup.initStyle(StageStyle.UNDECORATED);
            FXMLLoader loaderPopup = openPopup(stagePopup);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            FXMLLoader loader = openMenu(stage);
            MenuController menuController = loader.getController();
            menuController.setService(service, username);
        }
    }

    /**
     * Closes the window
     * @throws Exception if the fxml file is not found
     */
    public void close() throws Exception {
        Stage stage = (Stage) xButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimizes the window
     * @throws Exception if the fxml file is not found
     */
    public void minimize() throws Exception {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Makes the window draggable
     */
    private double xOffset = 0;
    private double yOffset = 0;

    public void setDraggable() {
        loginAnchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        loginAnchorPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    /**
     * Opens the register window
     * @throws Exception if the fxml file is not found
     */
    public void register() throws Exception {
        usernameText.clear();
        passwordText.clear();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = openRegister(stage);
        RegisterController registerController = loader.getController();
        registerController.setService(service);
    }

}