package com.patrick.core.factory;

import com.patrick.entities.User;
import com.patrick.repositories.list.ArticleRepository;
import com.patrick.repositories.list.ClientRepository;
import com.patrick.repositories.list.DemandeRepository;
import com.patrick.repositories.list.DetteRepository;
import com.patrick.repositories.list.PaiementRepository;
import com.patrick.repositories.list.UserRepository;

public interface IfactoryRepo  {
    ClientRepository getInstanceClientRepository();
    UserRepository<User>  getInstanceUserRepository();
    ArticleRepository  getInstanceArticleRepository();
    DetteRepository getInstanceDetteRepository();
    PaiementRepository   getInstancePaiementRepository();
    DemandeRepository   getInstanceDemandeRepository();
    
}
