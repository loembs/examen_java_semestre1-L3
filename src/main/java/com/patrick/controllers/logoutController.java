package com.patrick.controllers;

import com.patrick.App;
import javafx.fxml.FXML;


public class logoutController {
    @FXML
    public void logout() {
         try {
            App.setRoot("login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
