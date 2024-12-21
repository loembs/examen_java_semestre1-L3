package com.patrick.services;

import java.util.List;

import com.patrick.entities.Detail;

public interface DetailService {
    void create(Detail detail);
    List<Detail>findAll();
    
}
