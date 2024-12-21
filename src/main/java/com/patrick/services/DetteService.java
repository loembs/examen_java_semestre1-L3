package com.patrick.services;

import java.util.List;

import com.patrick.entities.Detail;
import com.patrick.entities.Dette;
import com.patrick.entities.Paiement;

public interface DetteService {
    void create(Dette dette);
    List<Dette>findAll();
    Dette searchById(int id); 
    List<Dette> getDettesNonSoldees();
    List<Detail> getDetailsPourDette(int detteId);
    List<Paiement> getPaiementsPourDette(int detteId); 
}
