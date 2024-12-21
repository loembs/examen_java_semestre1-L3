package com.patrick.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.Dette;

import java.util.List;

public class DetteViewTwoController {
    private FactoryServ factoryServ = new FactoryServ();
    
    // Listes de dettes
    private ObservableList<Dette> archivedDettes = FXCollections.observableArrayList();
    private ObservableList<Dette> activeDettes = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Dette> dettesTable;
    @FXML
    private TableColumn<Dette, String> date;
    @FXML
    private TableColumn<Dette, Double> montant;
    @FXML
    private TableColumn<Dette, Double> montantVerser;
    @FXML
    private TableColumn<Dette, String> etat;

    @FXML
    private TableView<Dette> archivedDettesTable;
    @FXML
    private TableColumn<Dette, String> archivedDate;
    @FXML
    private TableColumn<Dette, Double> archivedMontant;
    @FXML
    private TableColumn<Dette, Double> archivedMontantVerser;
    @FXML
    private TableColumn<Dette, String> archivedEtat;
    
    @FXML
    private Button showActiveDettesButton;

    @FXML
    public void initialize() {

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montantVerser.setCellValueFactory(new PropertyValueFactory<>("montantVerser"));
        etat.setCellValueFactory(cellData -> createEtatCell(cellData.getValue()));

        archivedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        archivedMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        archivedMontantVerser.setCellValueFactory(new PropertyValueFactory<>("montantVerser"));
        archivedEtat.setCellValueFactory(cellData -> createEtatCell(cellData.getValue()));

        refreshActiveDettesList();
        
        archivedDettesTable.setVisible(false);
        showActiveDettesButton.setVisible(false);
    }

    private void refreshActiveDettesList() {
        List<Dette> dettes = factoryServ.getInstanceDetteServiceImpl().findAll();
        activeDettes.setAll(dettes);
        dettesTable.setItems(activeDettes);
    }

    private SimpleStringProperty createEtatCell(Dette dette) {
        double montantValue = (dette.getMontant() != null) ? dette.getMontant() : 0.0;
        double montantVerserValue = (dette.getMontantVerser() != null) ? dette.getMontantVerser() : 0.0;
        String etatValue = montantValue <= montantVerserValue ? "Soldé" : "Non soldé";
        return new SimpleStringProperty(etatValue);
    }

    @FXML
    private void archiveDette() {
        Dette selectedDette = dettesTable.getSelectionModel().getSelectedItem();
        if (selectedDette != null) {
            activeDettes.remove(selectedDette);
            archivedDettes.add(selectedDette);
            archivedDettesTable.setItems(archivedDettes);
            System.out.println("Dette archivée: " + selectedDette.getMontant());
        } else {
            System.out.println("Aucune dette sélectionnée pour archivage !");
        }
    }

    @FXML
    private void showArchivedDettes() {
        archivedDettesTable.setItems(archivedDettes);
        archivedDettesTable.setVisible(true);
        dettesTable.setVisible(false);
        showActiveDettesButton.setVisible(true);
    }

    @FXML
    private void showActiveDettes() {
        archivedDettesTable.setVisible(false);
        dettesTable.setVisible(true);
        showActiveDettesButton.setVisible(false);
    }
}
