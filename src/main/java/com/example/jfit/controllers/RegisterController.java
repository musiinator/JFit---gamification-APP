package com.example.jfit.controllers;

import com.example.jfit.domain.User;
import com.example.jfit.domain.UserValidator;
import com.example.jfit.exceptions.ValidationException;
import com.example.jfit.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController {
    private Service service;
    @FXML
    private AnchorPane registerAnchorPane;
    @FXML
    private Button xButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registerError;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;

    /**
     * Sets the service
     * @param service the service
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Registers a new user
     * @throws Exception if the fxml file is not found
     */
    public void verifyRegister() throws Exception {
        try {
            User user = new User(usernameText.getText(), passwordText.getText(), null, null);
            UserValidator userValidator = new UserValidator();
            userValidator.validate(user);
            if(service.findUserByUsername(user.getUsername()) != null)
                throw new ValidationException("Username already exists!");
            service.saveUser(user);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();

        } catch (ValidationException e) {
            registerError.setText(e.getMessage());
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
        registerAnchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        registerAnchorPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) registerAnchorPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
