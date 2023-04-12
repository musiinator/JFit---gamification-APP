module com.example.jfit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.jfit to javafx.fxml;
    exports com.example.jfit.service;
    exports com.example.jfit.repository.interfaces;
    exports com.example.jfit.domain;
    exports com.example.jfit;
    exports com.example.jfit.controllers;
    opens com.example.jfit.controllers to javafx.fxml;
}