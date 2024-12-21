package com.patrick.repositories.list;
import java.util.List;

import com.patrick.entities.Detail;
import com.patrick.entities.Dette;
import com.patrick.entities.Paiement;

public interface DetteRepository extends Repository<Dette> {
    Dette selectById(int id);  
    List<Dette> getDettesNonSoldees();
    List<Detail> getDetailsPourDette(int detteId);
    List<Paiement> getPaiementsPourDette(int detteId);
}
