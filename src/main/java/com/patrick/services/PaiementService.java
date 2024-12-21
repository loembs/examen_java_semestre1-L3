package com.patrick.services;
import java.util.List;
import com.patrick.entities.Paiement;

public interface PaiementService {
    void create(Paiement paiement);
    List<Paiement>findAll(); 
    
}
