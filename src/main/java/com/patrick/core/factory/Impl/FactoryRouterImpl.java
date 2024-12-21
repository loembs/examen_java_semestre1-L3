package com.patrick.core.factory.Impl;

import com.patrick.core.config.Router;
import com.patrick.core.factory.IfactoryRouter;
import com.patrick.repositories.bd.ClientRepositoryBD;

public class FactoryRouterImpl implements IfactoryRouter{
       
        IfactoryRouter factoryrouter;
        Router router =null;
        
        
    public FactoryRouterImpl(IfactoryRouter factoryrouter) {
        this.factoryrouter=factoryrouter;

        }
    public  Router getInstanceRouter(){
        ClientRepositoryBD clientRepositoryBD = new ClientRepositoryBD();
        if(router==null){
            router=new Router(clientRepositoryBD);
        }
        return router;
    }


    
}
