package com.patrick.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetteViewController {

    private FactoryServ factoryServ = new FactoryServ();
    private List<Detail> panier = new ArrayList<>();

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TableView<Article> articlesTable;

    @FXML
    private TableColumn<Article, String> libelleField;

    @FXML
    private TableColumn<Article, Double> prixField;

    @FXML
    private TableColumn<Article, Integer> qteStockField;

    @FXML
    private TextField quantityField;

   
    @FXML
    public void initialize() {
        libelleField.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prixField.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteStockField.setCellValueFactory(new PropertyValueFactory<>("qtestock"));
    
    refreshArticlesList();

        
    }

     private void refreshArticlesList() {
        List<Article> articles = factoryServ.getInstanceArticleServiceImpl().findAll();
        ObservableList<Article> articleList = FXCollections.observableArrayList(articles);
        articlesTable.setItems(articleList);
    }
    
    @FXML
    private void onAddArticle() {

        Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            try {
                int quantite = Integer.parseInt(quantityField.getText());

                if (quantite > selectedArticle.getQtestock()) {
                    System.out.println("Quantité trop élevée en stock.");
                    return;
                }

                Detail detail = new Detail();
                detail.setArticle(selectedArticle);
                detail.setPrixVente(selectedArticle.getPrix());
                detail.setQteVendu(quantite);

                selectedArticle.setQtestock(selectedArticle.getQtestock() - quantite);
                factoryServ.getInstanceArticleServiceImpl().update(selectedArticle);
                refreshArticlesList();
                panier.add(detail);
                System.out.println("Article ajouté au panier: " + selectedArticle.getLibelle());
                factoryServ.getInstanceDetailServiceImpl().create(detail);
                refreshArticlesList();
                quantityField.clear();
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide pour la quantité.");
            }
        } else {
            System.out.println("Aucun article sélectionné !");
        }
    }

    @FXML
    private void onSaveDebt() {
        Dette nouvelleDette = new Dette();
        String phonenumber = phoneNumberField.getText();
        Client client = factoryServ.getInstanceClientService().search(phonenumber);
        nouvelleDette.setClient(client);
        

        double montantTotal = panier.stream()
                .mapToDouble(detail -> detail.getPrixVente() * detail.getQteVendu())
                .sum();
        nouvelleDette.setMontant(montantTotal);
        nouvelleDette.setDetails(panier);
        nouvelleDette.setDate(LocalDate.now());

        factoryServ.getInstanceDetteServiceImpl().create(nouvelleDette);
        System.out.println("Nouvelle dette sauvegardée pour le client: " + phoneNumberField.getText() + " Montant: " + montantTotal);

        panier.clear();
        refreshArticlesList();
    }
    
}
