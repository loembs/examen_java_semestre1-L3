package com.patrick.services;

import com.patrick.entities.Client;
import java.util.List;

public interface ClientService {
    void create(Client client);
    List<Client>findAll();
    Client search(String teletphone);
}
