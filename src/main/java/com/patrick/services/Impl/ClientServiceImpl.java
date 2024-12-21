package com.patrick.services.Impl;

import com.patrick.entities.Client;
import com.patrick.entities.User;
import com.patrick.repositories.list.ClientRepository;
import com.patrick.repositories.list.Repository;
import com.patrick.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService{
    Repository<User> userRepository;
    ClientRepository clientRepository;
    
    public ClientServiceImpl(Repository<User> userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }
    
    public void create(Client client)
    {
        clientRepository.insert(client);    
    }
    public List<Client>findAll()
    {
        return clientRepository.selectAll();
    }
    public Client search(String teletphone){
        return clientRepository.selectBytelephone(teletphone);
    }
    
}
