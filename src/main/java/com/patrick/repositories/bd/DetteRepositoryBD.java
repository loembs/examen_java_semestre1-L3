package com.patrick.repositories.bd;

import java.util.List;

import com.patrick.entities.Detail;
import com.patrick.entities.Dette;
import com.patrick.entities.Paiement;
import com.patrick.repositories.list.DetteRepository;

public class DetteRepositoryBD extends RepositoryJpaImpl<Dette> implements DetteRepository {
    String SQL_GET_DETTE_NONSOLDEES="SELECT d FROM Dette d WHERE d.client.id = :clientId AND (d.montant - d.montantVerser)!=0";
    public DetteRepositoryBD() {
        super(Dette.class);
    }
    @Override
    public List<Dette> getDettesNonSoldees() {
        return em.createQuery(SQL_GET_DETTE_NONSOLDEES, Dette.class)
                            .getResultList();
    }
    @Override
    public List<Detail> getDetailsPourDette(int detteId) {
            Dette dette = em.find(Dette.class, detteId);
            return dette.getDetails(); 
    }
    @Override
    public List<Paiement> getPaiementsPourDette(int detteId) {
        Dette dette = em.find(Dette.class, detteId);
        return dette.getPaiements();    
    }
   
}
