package com.patrick.repositories.list;

import com.patrick.entities.User;
import com.patrick.entities.Enums.Role;

import java.util.List;

public interface UserRepository<T> extends Repository<User> {
    T selectByLogin(String login);
    T selectByPassword(String password);
    List<T> selectByRole(Role role);
}
