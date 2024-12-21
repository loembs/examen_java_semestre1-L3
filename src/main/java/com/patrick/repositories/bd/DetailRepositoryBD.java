package com.patrick.repositories.bd;

import com.patrick.entities.Detail;
import com.patrick.repositories.list.DetailRepository;

public class DetailRepositoryBD extends RepositoryJpaImpl<Detail> implements DetailRepository {

    public DetailRepositoryBD() {
        super(Detail.class);
    }
}
