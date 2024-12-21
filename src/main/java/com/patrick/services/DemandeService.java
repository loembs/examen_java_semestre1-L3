package com.patrick.services;

import java.util.List;

import com.patrick.entities.DemandesDette;
import com.patrick.entities.Enums.EtatDemande;

public interface DemandeService {
    void create(DemandesDette demandesDette);
    List<DemandesDette>findAll();
    List<DemandesDette> getDemandesParEtat(EtatDemande etat);
    void relancerDemande(int demandeId);
    DemandesDette  searchById(int id);
    
}
