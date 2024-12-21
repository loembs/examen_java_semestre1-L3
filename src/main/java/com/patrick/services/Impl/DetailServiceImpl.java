package com.patrick.services.Impl;

import java.util.List;
import com.patrick.entities.Detail;
import com.patrick.repositories.list.DetailRepository;
import com.patrick.services.DetailService;

public class DetailServiceImpl implements DetailService {
    DetailRepository detailrepo ;

    public DetailServiceImpl(DetailRepository detailrepo){
            this.detailrepo=detailrepo;
    }
    @Override
    public void create(Detail detail) {
        detailrepo.insert(detail);
    }

    @Override
    public List<Detail> findAll() {
        return detailrepo.selectAll();
    }  
}
