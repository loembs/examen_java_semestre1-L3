package com.patrick.core.factory.Impl;
import com.patrick.core.factory.IfactoryRepo;
import com.patrick.entities.User;
import com.patrick.repositories.bd.ArticleRepositoryBD;
import com.patrick.repositories.bd.ClientRepositoryBD;
import com.patrick.repositories.bd.DemandeRepositoryBD;
import com.patrick.repositories.bd.DetailRepositoryBD;
import com.patrick.repositories.bd.DetteRepositoryBD;
import com.patrick.repositories.bd.PaiementRepositoryBD;
import com.patrick.repositories.bd.UserRepositoryBD;
import com.patrick.repositories.list.ArticleRepository;
import com.patrick.repositories.list.ClientRepository;
import com.patrick.repositories.list.DemandeRepository;
import com.patrick.repositories.list.DetailRepository;
import com.patrick.repositories.list.DetteRepository;
import com.patrick.repositories.list.PaiementRepository;
import com.patrick.repositories.list.UserRepository;

public class FactoryRepoImpl implements IfactoryRepo{
        private  ArticleRepository articleRepository=null;
        private  ClientRepository clientRepo=null;
        private  DetteRepository detteRepo=null;
        private  PaiementRepository paiementRepository=null;
        private  DemandeRepository demandeRepository=null;
        private  UserRepository<User> userRepository=null;
        private  DetailRepository detailRepository=null;
        private IfactoryRepo fIfactoryRepo ;
        
    public FactoryRepoImpl(IfactoryRepo fIfactoryRepo) {
        this.fIfactoryRepo=fIfactoryRepo;
        }
    public  ClientRepository getInstanceClientRepository(){
        if(clientRepo==null){
            clientRepo=new ClientRepositoryBD();
        }
        return clientRepo;
    }
    public  DetteRepository getInstanceDetteRepository(){
        if(detteRepo==null){
            detteRepo=new DetteRepositoryBD();
        }
        return detteRepo;
    }
    public  DetailRepository getInstanceDetailRepository(){
        if(detailRepository==null){
            detailRepository=new DetailRepositoryBD();
        }
        return detailRepository;
    }
    public  UserRepository<User>  getInstanceUserRepository(){
        ClientRepositoryBD clientRepositoryBD = new ClientRepositoryBD();
        if( userRepository==null){
            userRepository= new UserRepositoryBD(clientRepositoryBD);
        }
        return  userRepository;
    }
     public  ArticleRepository  getInstanceArticleRepository(){
        if(articleRepository==null){
            articleRepository= new ArticleRepositoryBD();
        }
        return  articleRepository;
    }

    public PaiementRepository   getInstancePaiementRepository(){
        if(paiementRepository==null){
            paiementRepository= new PaiementRepositoryBD();
        }
        return  paiementRepository;
    }
    public DemandeRepository   getInstanceDemandeRepository(){
        if(demandeRepository==null){
            demandeRepository= new DemandeRepositoryBD();
        }
        return  demandeRepository;
    }
    


    //UserRepository<User> userRepository= new UserRepositoryBD(clientRepositoryBD);
    
}
