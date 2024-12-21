package com.patrick.services.Impl;

import java.util.List;
import com.patrick.entities.DemandesDette;
import com.patrick.entities.Enums.EtatDemande;
import com.patrick.repositories.bd.RepositoryJpaImpl;
import com.patrick.repositories.list.DemandeRepository;
import com.patrick.services.DemandeService;

public class DemandeServiceImpl extends RepositoryJpaImpl<DemandesDette> implements DemandeService {
    DemandeRepository demandeRepo;

     public DemandeServiceImpl(DemandeRepository demandeRepo){
        super(DemandesDette.class);
        this.demandeRepo = demandeRepo;
    }

    @Override
    public void create(DemandesDette demandesDette) {
        demandeRepo.insert(demandesDette);
    }

    @Override
    public List<DemandesDette> findAll() {
       return demandeRepo.selectAll();
    }
    @Override
    public List<DemandesDette> getDemandesParEtat(EtatDemande etat) {
       return demandeRepo.selectAll();
    }
    @Override
    public void relancerDemande(int demandeId) {
        demandeRepo.doRelancerDemande(demandeId);
    }
    public DemandesDette searchById(int id){
        return  demandeRepo.selectById(id);
    }
    
}
