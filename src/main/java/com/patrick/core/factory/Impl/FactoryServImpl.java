package com.patrick.core.factory.Impl;

import com.patrick.core.factory.FactoryRepo;
import com.patrick.core.factory.IfactoryRepo;
import com.patrick.core.factory.IfactoryServ;
import com.patrick.repositories.bd.DemandeRepositoryBD;
import com.patrick.repositories.list.DemandeRepository;
import com.patrick.services.ClientService;
import com.patrick.services.Impl.ArticleServiceImpl;
import com.patrick.services.Impl.ClientServiceImpl;
import com.patrick.services.Impl.DemandeServiceImpl;
import com.patrick.services.Impl.DetailServiceImpl;
import com.patrick.services.Impl.DetteServiceImpl;
import com.patrick.services.Impl.PaiementServiceImpl;
import com.patrick.services.Impl.UserServiceImpl;

public class FactoryServImpl implements IfactoryServ {
    
        UserServiceImpl userServiceImpl= null;
        ClientService clientServiceImpl= null;
        ArticleServiceImpl articleServiceImpl=null;
        DetteServiceImpl detteServiceImpl=null;
        PaiementServiceImpl paiementServiceImpl=null;
        DemandeServiceImpl demandeServiceImpl=null;
        DetailServiceImpl detailServiceImpl=null;
        FactoryRepo factoryService = new FactoryRepo();
        IfactoryRepo fIfactoryRepo;
        
    public FactoryServImpl(IfactoryRepo fIfactoryRepo) {
        this.fIfactoryRepo=fIfactoryRepo;
        }
    public  UserServiceImpl getInstanceUserServiceImpl(){
        if(userServiceImpl==null){
            userServiceImpl=new UserServiceImpl(factoryService.getInstanceUserRepository());
        }
        return userServiceImpl;
    }
    public ClientService  getInstanceClientService(){
        if(clientServiceImpl==null){
            clientServiceImpl= new ClientServiceImpl(factoryService.getInstanceUserRepository(),factoryService.getInstanceClientRepository());
        }
        return  clientServiceImpl;
    }
    public  ArticleServiceImpl getInstanceArticleServiceImpl(){
        if(articleServiceImpl==null){
            articleServiceImpl= new ArticleServiceImpl(factoryService.getInstanceArticleRepository());
        }
        return  articleServiceImpl;
    }
    public  DetteServiceImpl getInstanceDetteServiceImpl(){
        if(detteServiceImpl==null){
            detteServiceImpl= new DetteServiceImpl(factoryService.getInstanceDetteRepository());
        }
        return   detteServiceImpl;
    }
    public   PaiementServiceImpl getInstancePaiementServiceImpl(){
        if(paiementServiceImpl==null){
            paiementServiceImpl= new  PaiementServiceImpl(factoryService.getInstancePaiementRepository());
        }
        return   paiementServiceImpl;
    }
    public   DemandeServiceImpl getInstanceDemandeServiceImpl(){
        if(demandeServiceImpl==null){
            demandeServiceImpl= new  DemandeServiceImpl(factoryService.getInstanceDemandeRepository());
        }
        return   demandeServiceImpl;
    }
    public   DetailServiceImpl getInstanceDetailServiceImpl(){
        if(detailServiceImpl==null){
            detailServiceImpl= new  DetailServiceImpl(factoryService.getInstanceDetailRepository());
        }
        return   detailServiceImpl;
    }
}
