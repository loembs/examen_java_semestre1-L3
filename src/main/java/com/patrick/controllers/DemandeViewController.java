
package com.patrick.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.patrick.entities.Article;
import com.patrick.entities.Client;
import com.patrick.entities.DemandesDette;
import com.patrick.entities.Detail;
import com.patrick.entities.Dette;
import com.patrick.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import javafx.scene.control.TableCell;


import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.Enums.EtatDemande;
import com.patrick.entities.Enums.Role;

public class DemandeViewController {
    private FactoryServ factoryServ = new FactoryServ();
    private List<Detail> panier = new ArrayList<>();

    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private ComboBox<EtatDemande> etatComboBox;
    @FXML
    private TableColumn<Article, String> libelleField;

    @FXML
    private TableColumn<Article, Double> prixField;

    @FXML
    private TableColumn<Article, Integer> qteStockField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField adresseField;
    @FXML
    private TableView<DemandesDette> demandesTable;

    @FXML
    private TableColumn<DemandesDette, LocalDate> dateColumn;

    @FXML
    private TableColumn<DemandesDette, Double> montantColumn;

    @FXML
    private TableColumn<DemandesDette, EtatDemande> etatColumn;

    @FXML
    private TextField quantityField;

    @FXML
    private TableColumn<DemandesDette, Void> detailColumn;


    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montantDemande"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        libelleField.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prixField.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteStockField.setCellValueFactory(new PropertyValueFactory<>("qtestock"));
        etatComboBox.getItems().setAll(EtatDemande.values());
        refreshArticlesList();
        refreshDemandesList();
        addDetailButtonToTable();
    }

    @FXML
    private void handleValidateDemande() {
        DemandesDette demande = demandesTable.getSelectionModel().getSelectedItem();
        if (demande != null) {
            demande.setEtat(EtatDemande.ACCEPTEE);
            factoryServ.getInstanceDemandeServiceImpl().update(demande);
            factoryServ.getInstanceDemandeServiceImpl().update(demande);
                    Client client =demande.getClient();
                    Dette dette=new Dette();
                    dette.setDate(LocalDate.now());
                    dette.setMontant(demande.getMontantDemande());
                    factoryServ.getInstanceDetteServiceImpl().create(dette);
                    client.setDettes(dette);
            refreshDemandesList();
        }
    }

    @FXML
    private void handleCancelDemande() {
        DemandesDette demande = demandesTable.getSelectionModel().getSelectedItem();
        if (demande != null) {
            demande.setEtat(EtatDemande.ANNULEE);
            factoryServ.getInstanceDemandeServiceImpl().update(demande);
            refreshDemandesList();
        }
    }
    private void refreshArticlesList() {
        List<Article> articles = factoryServ.getInstanceArticleServiceImpl().findAll();
        ObservableList<Article> articleList = FXCollections.observableArrayList(articles);
        articlesTable.setItems(articleList);
    }

    private void refreshDemandesList() {
        demandesTable.getItems().setAll(factoryServ.getInstanceDemandeServiceImpl().getDemandesParEtat(EtatDemande.EN_COURS));
           List<DemandesDette> demandes = factoryServ.getInstanceDemandeServiceImpl().findAll();
            ObservableList<DemandesDette> clientList = FXCollections.observableArrayList(demandes);
            demandesTable.setItems(clientList);
    }
    @FXML
    private void onAddArticle()
    {
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
    private void onSaveRequest() {
        DemandesDette newrequest = new DemandesDette();
        String phonenumber = telephoneField.getText();
        Client client = factoryServ.getInstanceClientService().search(phonenumber);
        newrequest.setClient(client);
        

        double montantTotal = panier.stream()
                .mapToDouble(detail -> detail.getPrixVente() * detail.getQteVendu())
                .sum();
        newrequest.setMontantDemande(montantTotal);
        newrequest.setDetails(panier);
        newrequest.setDateDemande(LocalDate.now());

        factoryServ.getInstanceDemandeServiceImpl().create(newrequest);
        System.out.println("Nouvelle demande sauvegardée pour le client: " + telephoneField.getText() + " Montant: " + montantTotal);
        panier.clear();
        refreshArticlesList();
    }
    @FXML
    private void filterbyetat() {
        if (etatComboBox.getSelectionModel().getSelectedItem() != null) {
            EtatDemande selectedEtat = etatComboBox.getSelectionModel().getSelectedItem();
            List<DemandesDette> demandes = factoryServ.getInstanceDemandeServiceImpl().getDemandesParEtat(selectedEtat);
            ObservableList<DemandesDette> demandesList = FXCollections.observableArrayList(demandes);
            demandesTable.setItems(demandesList);   
        }
    }
    @FXML
    private void showArticleDette(DemandesDette selectedDemandesDette) {
        if (selectedDemandesDette!= null) {
            List<Detail> details = selectedDemandesDette.getDetails();
            ObservableList<Article> articleList = FXCollections.observableArrayList();
            for (Detail detail : details) {
                articleList.add(detail.getArticle());
            }

            articlesTable.setItems(articleList);
            demandesTable.setVisible(false);
            articlesTable.setVisible(true);
        }
    }
    private void addDetailButtonToTable() {
        detailColumn.setCellFactory(param -> new TableCell<>() {
            private final Button detailButton = new Button("Détails");{
                detailButton.setOnAction(event -> {
                    DemandesDette selecDemandesDette = getTableView().getItems().get(getIndex());
                    if (selecDemandesDette != null) {
                        showArticleDette(selecDemandesDette);
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

