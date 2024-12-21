package com.patrick.controllers;

import javafx.fxml.FXML;
import com.patrick.App;

public class BoutiquierMenuController extends logoutController {
    
    @FXML
    public void loadDetteView() {
        try {
            App.loadView("boutiquier/listerDette");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadNewDetteView() {
        try {
            App.loadView("detteView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadClientView() {
        try {
            App.loadView("boutiquier/createClient");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadDemandeView() {
        try {
            App.loadView("demandeView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadPaiementView() {
        try {
            App.loadView("paiementView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadListClientView() {
        try {
            App.loadView("boutiquier/listerinfoclient");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


