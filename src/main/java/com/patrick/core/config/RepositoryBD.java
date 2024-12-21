package com.patrick.core.config;

import java.util.List;

public interface RepositoryBD<T> {
    void insert(T object);
    boolean update(T object);
    void remove(int id);
    List<T> selectAll();
    T selectById(int id);
}
