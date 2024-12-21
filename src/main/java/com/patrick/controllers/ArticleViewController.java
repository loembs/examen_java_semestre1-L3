package com.patrick.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.patrick.entities.Article;

import java.util.List;

import com.patrick.core.factory.FactoryServ;

public class ArticleViewController {

    @FXML
    private TextField mylibelleField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField qteStockField;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, Double> prixColumn;
    @FXML
    private TableColumn<Article, Integer> qteStockColumn;
    @FXML
    private ListView<Article> articlesList;

    private FactoryServ factoryServ = new FactoryServ();

    @FXML
    public void initialize() {
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qteStockColumn.setCellValueFactory(new PropertyValueFactory<>("qtestock"));

        refreshArticlesList();
    }

    @FXML
    private void handleSaveArticle() {
        Article article = new Article();
        article.setLibelle(mylibelleField.getText());
        article.setPrix(Double.parseDouble(prixField.getText()));
        article.setQtestock(Integer.parseInt(qteStockField.getText()));
        factoryServ.getInstanceArticleServiceImpl().create(article);
        refreshArticlesList();
    }

    @FXML
    private void handleUpdateQte() {
        Article article = articlesTable.getSelectionModel().getSelectedItem();
        if (article != null) {
            int newQty = Integer.parseInt(qteStockField.getText());
            article.setQtestock(article.getQtestock() + newQty);
            factoryServ.getInstanceArticleServiceImpl().update(article);
            refreshArticlesList();
        }
    }

    private void refreshArticlesList() {
        List<Article> articles = factoryServ.getInstanceArticleServiceImpl().findAll();
        ObservableList<Article> articleList = FXCollections.observableArrayList(articles);
        articlesTable.setItems(articleList);
    }
}
