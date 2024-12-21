package com.patrick.core.factory;

import com.patrick.services.ClientService;
import com.patrick.services.Impl.ArticleServiceImpl;
import com.patrick.services.Impl.DemandeServiceImpl;
import com.patrick.services.Impl.DetteServiceImpl;
import com.patrick.services.Impl.PaiementServiceImpl;
import com.patrick.services.Impl.UserServiceImpl;

public interface IfactoryServ {
    UserServiceImpl getInstanceUserServiceImpl();
    ClientService  getInstanceClientService();
    ArticleServiceImpl getInstanceArticleServiceImpl();
    DetteServiceImpl getInstanceDetteServiceImpl();
    PaiementServiceImpl getInstancePaiementServiceImpl();
    DemandeServiceImpl getInstanceDemandeServiceImpl();
    
}
