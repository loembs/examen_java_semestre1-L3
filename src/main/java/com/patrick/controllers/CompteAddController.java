package com.patrick.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.Client;
import com.patrick.entities.User;
import com.patrick.entities.Enums.Etat;
import com.patrick.entities.Enums.Role;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompteAddController {
    private FactoryServ factoryServ = new FactoryServ();

    private List<User> comptes;
    @FXML
    private TextField loginField, nomField, prenomField,telephoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<Role> roleComboBox;
    @FXML
    private ComboBox<Role>  addroleComboBox;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<User> accountTableView;

    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> nomColumn;
    @FXML
    private TableColumn<User, String> prenomColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, Role> roleColumn;
    @FXML
    private TableColumn<User, Etat> etatColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        addroleComboBox.getItems().setAll(
            Arrays.stream(Role.values())
                  .filter(role -> role == Role.BOUTIQUIER || role == Role.ADMIN)
                  .collect(Collectors.toList())
        );
        roleComboBox.getItems().setAll(Role.values());

        refreshList();
    }

    @FXML
    private void onSaveUser() 
    {
        if (telephoneField.getText().isEmpty()) {
            User newUser = new User();
            newUser.setLogin(loginField.getText());
            newUser.setNom(nomField.getText());
            newUser.setPrenom(prenomField.getText());
            newUser.setPassword(passwordField.getText());
            newUser.setRole(addroleComboBox.getValue());

            factoryServ.getInstanceUserServiceImpl().create(newUser);
            messageLabel.setText("Utilisateur créé avec succès.");
        } else {
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
    }
    @FXML
    private void refreshList() 
    {
        comptes = factoryServ.getInstanceUserServiceImpl().findAll();
        System.out.println("Comptes trouvés : " + comptes); 
        ObservableList<User> comptesList = FXCollections.observableArrayList(comptes);
        accountTableView.setItems(comptesList);
    }
    @FXML
    private void activeaccount() {
        User selectedcompte = accountTableView.getSelectionModel().getSelectedItem();
        if (selectedcompte != null) {
            selectedcompte.setEtat(Etat.ACTIVER);
            factoryServ.getInstanceUserServiceImpl().update(selectedcompte);
            refreshList();
        } else {
            messageLabel.setText("Veuillez sélectionner un compte.");
        }
    }
    
    @FXML
    private void desactiveaccount() {
        User selectedcompte = accountTableView.getSelectionModel().getSelectedItem();
        if (selectedcompte != null) {
            selectedcompte.setEtat(Etat.DESACTIVER);
            factoryServ.getInstanceUserServiceImpl().update(selectedcompte);
            refreshList();
        } else {
            messageLabel.setText("Veuillez sélectionner un compte.");
        }
    }
    @FXML
    private void filterbyrole() {
        if (roleComboBox.getSelectionModel().getSelectedItem() != null) {
            Role selectedRole = roleComboBox.getSelectionModel().getSelectedItem();
            comptes = factoryServ.getInstanceUserServiceImpl().searchrole(selectedRole );
            ObservableList<User> comptesList = FXCollections.observableArrayList(comptes);
            accountTableView.setItems(comptesList);   
        }
    }
}
