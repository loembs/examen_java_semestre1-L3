package com.patrick.services;

import java.util.List;

import com.patrick.entities.Article;

public interface ArticleService {
    void create(Article article);
    List<Article>findAll();
    Article search();
    Article searchById(int id);
    Article searchBylibelle(String libelle);
    
}
