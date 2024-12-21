package com.patrick.services.Impl;

import java.util.List;

import com.patrick.entities.Article;
import com.patrick.entities.Paiement;
import com.patrick.repositories.bd.RepositoryJpaImpl;
import com.patrick.repositories.list.PaiementRepository;
import com.patrick.services.PaiementService;

public class PaiementServiceImpl extends RepositoryJpaImpl<Paiement> implements PaiementService {
    PaiementRepository paiementRepository;
    

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        super(Paiement.class);
        this.paiementRepository = paiementRepository;
    }

    @Override
    public void create(Paiement paiement) {
        paiementRepository.insert(paiement);
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.selectAll();
       
    }

    
    
}
