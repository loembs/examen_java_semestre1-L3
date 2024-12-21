package com.patrick.repositories.bd;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.patrick.entities.DemandesDette;
import com.patrick.entities.Enums.EtatDemande;
import com.patrick.repositories.list.DemandeRepository;

public class DemandeRepositoryBD extends RepositoryJpaImpl<DemandesDette> implements DemandeRepository{
    EtatDemande etat;
    String SQL_SELECT_BY_ETAT= "SELECT d FROM DemandeDette d WHERE d.client.id = :clientId AND d.etat = :etat";
    public DemandeRepositoryBD() {
        super(DemandesDette.class);
    }
    @Override
    public List<DemandesDette> getDemandesParEtat(EtatDemande etat) {
       
    TypedQuery<DemandesDette> query = em.createQuery(SQL_SELECT_BY_ETAT, DemandesDette.class);
                                query.setParameter("etat", etat);
        try {
                    return   query.getResultList();
            
        } catch (Exception e) {
                    return new ArrayList<>();
        }
    }
    public void doRelancerDemande(int demandeId) 
    {
        DemandesDette demande = em.find(DemandesDette.class, demandeId);
        if (demande.getEtat() == EtatDemande.ANNULEE)
        {
            demande.setDateRelance(LocalDate.now());
            em.merge(demande);
            System.out.println("Votre Relance a été envoyée avec succès .");
        } else {
            System.out.println("La demande n'est pas dans un état annulé.");
        }
    }
    
}
