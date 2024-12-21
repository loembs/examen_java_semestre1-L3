package com.patrick.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import com.patrick.App;


public class ClientMenuController extends logoutController {
    
    @FXML
    public void loadDemandeView() {
        try {
            App.loadView("client/do&ListdemandeView");
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
    public void loadPaiementView() {
        try {
            App.loadView("paiementView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void loadProfileView() {
        try {
            App.loadView("profileView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}

