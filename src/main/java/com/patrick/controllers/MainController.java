package com.patrick.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private TextField myTextField;

    @FXML
    private Button myButton;

    @FXML
    public void handleButtonClick() {
        System.out.println("Button clicked!");
        // Ici tu peux appeler des services JPA si besoin
    }
}

