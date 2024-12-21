package com.patrick.controllers;

import javafx.fxml.FXML;
import com.patrick.App;


public class AdminMenuController extends logoutController {
    
    @FXML
    public void loadClientView() {
        try {
            App.loadView("clientView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadArticleView()  {
        try {
            App.loadView("articleView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadDemandeView()  {
        try {
            App.loadView("demandeView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadDetteView() {
        try {
            App.loadView("detteView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadUserView()  {
        try {
            App.loadView("compteAdd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadListDette() {
        try {
            App.loadView("DetteViewTwo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

