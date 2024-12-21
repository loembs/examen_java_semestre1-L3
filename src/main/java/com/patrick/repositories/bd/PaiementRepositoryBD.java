package com.patrick.repositories.bd;


import com.patrick.entities.Paiement;
import com.patrick.repositories.list.PaiementRepository;

public class PaiementRepositoryBD extends RepositoryJpaImpl<Paiement> implements PaiementRepository{

    public PaiementRepositoryBD() {
        super(Paiement.class);
    }  
}
