package com.patrick.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import com.patrick.entities.Client;
import com.patrick.entities.User;
import com.patrick.entities.Enums.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.patrick.core.factory.FactoryServ;

public class createClientController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField adresseField;
    @FXML
    private TableView<Client> clientsTable;
    private FactoryServ factoryServ = new FactoryServ();
    private List<Client> clients;

     private List<User> comptes;
    @FXML
    private TextField loginField, nomField, prenomField,telephoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<Role> roleComboBox;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<User> accountTableView;
    @FXML
    private GridPane createAccountForm;

    
    @FXML
    private void initialize() {
        roleComboBox.getItems().setAll(Role.values());
    }

    @FXML
    private void handleSaveClient() {
        Client client = new Client();
        client.setSurname(nameField.getText());
        client.setTelephone(telephoneField.getText());
        client.setAdresse(adresseField.getText());
        factoryServ.getInstanceClientService().create(client);
    }
     @FXML
    private void onSaveUser() 
    {
            Client client = factoryServ.getInstanceClientService().search(telephoneField.getText());
            if (client.getUser() == null) {
                User newUser = new User();
                newUser.setLogin(loginField.getText());
                newUser.setNom(nomField.getText());
                newUser.setPrenom(prenomField.getText());
                newUser.setPassword(passwordField.getText());
                newUser.setRole(Role.CLIENT);
                factoryServ.getInstanceUserServiceImpl().create(newUser);
                client.setUser(newUser);
                messageLabel.setText("Utilisateur créé et associé au client.");
            } else {
                messageLabel.setText("Ce client a déjà un compte.");
            }
        }
    @FXML
    private void showformule() {
        createAccountForm.setVisible(true);
    }
}
