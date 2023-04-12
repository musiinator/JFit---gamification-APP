package com.example.jfit;

import com.example.jfit.controllers.LoginController;
import com.example.jfit.repository.database.QuestDataBase;
import com.example.jfit.repository.database.UserDataBase;
import com.example.jfit.repository.interfaces.QuestRepoInterface;
import com.example.jfit.repository.interfaces.UserRepoInterface;
import com.example.jfit.service.Service;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

    UserRepoInterface repoUser = new UserDataBase("jdbc:postgresql://localhost:5432/JFitDB", "postgres", "postgres");
    QuestRepoInterface repoQuest = new QuestDataBase("jdbc:postgresql://localhost:5432/JFitDB", "postgres", "postgres");

    public Service service = Service.getInstance(repoUser, repoQuest);
    Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root, 392, 600);
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), root);
        fade.setFromValue(0.5);
        fade.setToValue(1.0);
        fade.play();
        stage.setTitle("Welcome to JFit!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}