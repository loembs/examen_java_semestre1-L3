package com.patrick.repositories.list;

import java.util.List;

import com.patrick.entities.DemandesDette;
import com.patrick.entities.Enums.EtatDemande;

public interface DemandeRepository extends Repository<DemandesDette> {
    List<DemandesDette> getDemandesParEtat(EtatDemande etat);
    void doRelancerDemande(int demandeId);
    DemandesDette selectById(int id);

    
}
