
package com.patrick.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.patrick.entities.Client;
import javafx.scene.control.Label;

import java.util.List;

import com.patrick.core.factory.FactoryServ;

public class InfoClientController {

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, String> surnameColumn;

    @FXML
    private TableColumn<Client, String> telephoneColumn;

    @FXML
    private TableColumn<Client, String> adresseColumn;
    @FXML
    private TextField searchphonenumber;
    @FXML
    private  Label  nameinfo;
    @FXML
    private  Label  telephoneinfo;
    @FXML
    private Label  adresseinfo;
   
    private FactoryServ factoryServ = new FactoryServ();
    private List<Client> clients;


    @FXML
    private void initialize() {
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        refreshClientsList();
    }
    @FXML
    private void refreshClientsList() {
        clients = factoryServ.getInstanceClientService().findAll();
        ObservableList<Client> clientList = FXCollections.observableArrayList(clients);
        clientsTable.setItems(clientList);
  
    }
    @FXML
    private void filterbytelephone() {
        Client client = factoryServ.getInstanceClientService().search(searchphonenumber.getText());

        if (client != null) {
            ObservableList<Client> filteredList = FXCollections.observableArrayList(client);
            clientsTable.setItems(filteredList);
        } else {
            clientsTable.setItems(FXCollections.observableArrayList());
        }
    }
    @FXML
    private void findbytelephone() {
        Client client = factoryServ.getInstanceClientService().search(searchphonenumber.getText());

        if (client != null) {
            nameinfo.setText(client.getSurname());
            telephoneinfo.setText(client.getTelephone());
            adresseinfo.setText(client.getAdresse());
            
        }else {
            nameinfo.setText("Client non trouvé");
            telephoneinfo.setText("Non renseigné");
            adresseinfo.setText("Non renseigné");
        }
    }

    
}
