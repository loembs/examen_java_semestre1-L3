package com.patrick.services.Impl;

import java.util.List;

import com.patrick.entities.Detail;
import com.patrick.entities.Dette;
import com.patrick.entities.Paiement;
import com.patrick.repositories.list.DetailRepository;
import com.patrick.repositories.list.DetteRepository;
import com.patrick.repositories.list.PaiementRepository;
import com.patrick.repositories.bd.RepositoryJpaImpl;
import com.patrick.services.DetteService;

public class DetteServiceImpl extends RepositoryJpaImpl<Dette> implements DetteService {
    DetteRepository detteReposity;
    DetailRepository detailRepository;
    PaiementRepository paiementRepository;
    

    public DetteServiceImpl(DetteRepository detteReposity) {
        super(Dette.class);
        this.detteReposity = detteReposity;
    }
    @Override
    public void create(Dette dette) {
        detteReposity.insert(dette); 
    }

    @Override
    public List<Dette> findAll() {
        return  detteReposity.selectAll();
    }

    @Override
    public Dette searchById(int id) {
        return detteReposity.selectById(id);
    }
    @Override
    public List<Dette> getDettesNonSoldees() {
        return  detteReposity.selectAll();
    }
    @Override
    public List<Detail> getDetailsPourDette(int detteId) {
        return  detailRepository.selectAll();
    }
    @Override
    public List<Paiement> getPaiementsPourDette(int detteId) {
        return  paiementRepository.selectAll();
    }

    
}
