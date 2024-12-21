package com.patrick.repositories.list;

import com.patrick.entities.Article;

public interface ArticleRepository extends Repository<Article>{
    Article selectByQte();
    Article selectById(int id);
    Article selectBylibelle(String libelle);  
}
