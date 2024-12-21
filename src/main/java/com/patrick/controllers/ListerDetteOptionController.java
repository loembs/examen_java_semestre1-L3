package com.patrick.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.Article;
import com.patrick.entities.Dette;
import com.patrick.entities.Detail;

public class ListerDetteOptionController {
    private FactoryServ factoryServ = new FactoryServ();

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
    private TableView<Dette> detteTableView;
    
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, Double> prixColumn;
    @FXML
    private TableColumn<Article, Integer> qteStockColumn;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Dette, Void> detailColumn;

    @FXML
    public void initialize() 
    {
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montantVerser.setCellValueFactory(new PropertyValueFactory<>("montantVerser"));
        etat.setCellValueFactory(cellData -> createEtatCell(cellData.getValue()));

        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteStockColumn.setCellValueFactory(new PropertyValueFactory<>("qtestock"));
        addDetailButtonToTable();
        refreshList(); 
    }
    private SimpleStringProperty createEtatCell(Dette dette) 
    {
        double montantValue = (dette.getMontant() != null) ? dette.getMontant() : 0.0;
        double montantVerserValue = (dette.getMontantVerser() != null) ? dette.getMontantVerser() : 0.0;
        String etatValue = montantValue <= montantVerserValue ? "Soldé" : "Non soldé";
        return new SimpleStringProperty(etatValue);
    }
    private void refreshList() 
    {
        List<Dette> dettes = factoryServ.getInstanceDetteServiceImpl().findAll();
        if (dettes == null || dettes.isEmpty()) {
            System.out.println("Aucune donnée récupérée !");
            return;
        }
        ObservableList<Dette> comptesList = FXCollections.observableArrayList(dettes);
        detteTableView.setItems(comptesList);
    }
    @FXML
    private void showArticleDette(Dette selectedDette) {
        if (selectedDette != null) {
            List<Detail> details = selectedDette.getDetails();
            ObservableList<Article> articleList = FXCollections.observableArrayList();
            for (Detail detail : details) {
                articleList.add(detail.getArticle());
            }

            articlesTable.setItems(articleList);
            detteTableView.setVisible(false);
            articlesTable.setVisible(true);
        }
    }
    private void addDetailButtonToTable() {
        detailColumn.setCellFactory(param -> new TableCell<>() {
            private final Button detailButton = new Button("Détails");{
                detailButton.setOnAction(event -> {
                    Dette selectedDette = getTableView().getItems().get(getIndex());
                    if (selectedDette != null) {
                        showArticleDette(selectedDette);
                    }
                });
            }
    
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailButton);
                }
            }
        });
    }

    
}
