package com.patrick.repositories.list;

import com.patrick.entities.Client;

public interface ClientRepository extends Repository<Client> {
    Client selectBytelephone(String telephone);
    
}
