package com.patrick.services.Impl;

import java.util.List;

import com.patrick.entities.Article;
import com.patrick.repositories.bd.RepositoryJpaImpl;
import com.patrick.repositories.list.ArticleRepository;
import com.patrick.services.ArticleService;

public class ArticleServiceImpl extends RepositoryJpaImpl<Article> implements ArticleService {
     ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        super(Article.class);
        this.articleRepository = articleRepository;
    }
    public void create(Article article){

        if (searchBylibelle(article.getLibelle())==null) {
            articleRepository.insert(article);
        }else{
            Article article1=searchBylibelle(article.getLibelle());
            int qteTotal=article1.getQtestock()+article.getQtestock();
            article1.setQtestock(qteTotal); 
        }     
    }
    public List<Article>findAll(){
        return articleRepository.selectAll();
    }
    public Article search(){
        return articleRepository.selectByQte();
    }
    public Article searchById(int id){
        return articleRepository.selectById(id);
    }
    public Article searchBylibelle(String libelle){
        return articleRepository.selectBylibelle(libelle);
    }
   
}
