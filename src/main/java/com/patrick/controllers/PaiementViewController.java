package com.patrick.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.patrick.core.factory.FactoryServ;
import com.patrick.entities.*;

public class PaiementViewController {

    private FactoryServ factoryServ = new FactoryServ();

    @FXML
    private TextField debtIdField;

    @FXML
    private TextField amountField;

    @FXML
    private void onProcessPayment() {
        int debtId = Integer.parseInt(debtIdField.getText());
        double amount = Double.parseDouble(amountField.getText());

        Dette selectedDebt = factoryServ.getInstanceDetteServiceImpl().searchById(debtId);
        Paiement paiement = new Paiement();
        paiement.setDette(selectedDebt);
        paiement.setMontantverse(amount);
        selectedDebt.setMontantVerser(amount);
        factoryServ.getInstancePaiementServiceImpl().update(paiement);
        factoryServ.getInstanceDetteServiceImpl().update(selectedDebt);
        // Confirmation or refresh logic
    }
}
