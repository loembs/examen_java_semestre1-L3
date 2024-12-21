package com.patrick.services.Impl;

import com.patrick.entities.Article;
import com.patrick.entities.User;
import com.patrick.entities.Enums.Role;
import com.patrick.repositories.bd.RepositoryJpaImpl;
import com.patrick.repositories.list.UserRepository;
import com.patrick.services.UserService;

import java.util.List;

public class UserServiceImpl extends RepositoryJpaImpl<User> implements UserService {

    private UserRepository<User> repository;
    
    public UserServiceImpl(UserRepository<User> repository){
        super(User.class);
        this.repository = repository;
    }
    
    public void create(User user){
        repository.insert(user);    
    }
    public List<User>findAll(){
        return repository.selectAll();
    }

    public User search(String login){
        return repository.selectByLogin(login);
    }
    public List<User> searchrole(Role role){
        return repository.selectByRole(role);
    }
       
}
