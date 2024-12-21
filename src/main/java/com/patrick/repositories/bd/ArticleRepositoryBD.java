package com.patrick.repositories.bd;

import javax.persistence.TypedQuery;

import com.patrick.entities.Article;
import com.patrick.repositories.list.ArticleRepository;

public class ArticleRepositoryBD extends RepositoryJpaImpl<Article> implements ArticleRepository{
    private final String SQL_SELECT_BY_LIBELLE = "SELECT u FROM Article u WHERE u.libelle = :libelle";
    private final String SQL_SELECT_BY_QTE = "SELECT a FROM Article a WHERE a.qtestock != 0";
    
    public ArticleRepositoryBD(){
        super(Article.class);
        entityClass = Article.class;

    }
    @Override
    public Article selectByQte() {
        TypedQuery<Article> query = em.createQuery(SQL_SELECT_BY_QTE, entityClass);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Article selectBylibelle(String libelle) {
        TypedQuery<Article> query = em.createQuery(SQL_SELECT_BY_LIBELLE, entityClass);
        query.setParameter("libelle", libelle);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
     
    
}
