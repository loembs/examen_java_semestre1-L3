package com.patrick.core.config;

import com.patrick.core.factory.IfactoryServ;

public interface IRouter {
    boolean login(String login,String password);
    void logout();
    void initialiserUsersParDefaut( IfactoryServ factoryServ);
}
