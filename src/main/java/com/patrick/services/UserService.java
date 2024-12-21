package com.patrick.services;

import com.patrick.entities.User;
import com.patrick.entities.Enums.Role;

import java.util.List;

public interface UserService {
    
    User search(String login);
    List<User> searchrole(Role role);
}
