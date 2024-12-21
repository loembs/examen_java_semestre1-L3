package com.patrick.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.*;
import com.patrick.entities.Enums.Role;

public class UserViewController {

    private FactoryServ factoryServ = new FactoryServ();

    @FXML
    private TextField loginField, nomField, prenomField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private void initialize() {
        roleComboBox.getItems().setAll(Role.values());
    }

    @FXML
    private void onSaveUser() {
        User newUser = new User();
        newUser.setLogin(loginField.getText());
        newUser.setNom(nomField.getText());
        newUser.setPrenom(prenomField.getText());
        newUser.setPassword(passwordField.getText());
        newUser.setRole(roleComboBox.getValue());

        factoryServ.getInstanceUserServiceImpl().create(newUser);

        // Confirmation or refresh logic
    }
}
