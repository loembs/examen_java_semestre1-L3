package com.patrick.core.config;

import com.patrick.App;
import com.patrick.core.factory.FactoryServ;
import com.patrick.core.factory.IfactoryRouter;
import com.patrick.core.factory.IfactoryServ;
import com.patrick.entities.User;
import com.patrick.entities.Enums.Role;
import com.patrick.repositories.bd.ClientRepositoryBD;
import com.patrick.repositories.bd.UserRepositoryBD;

import javafx.fxml.FXML;

public class Router extends UserRepositoryBD implements IRouter {

    public Router(ClientRepositoryBD clientRepositoryBD) {
        super(clientRepositoryBD);
    }

    @Override
    public boolean login(String login,String password) {
        try {
            if(selectByLogin(login)!=null && selectByPassword(password)!=null){
                System.out.println("Connexion réussie pour l'utilisateur : " + login);
                return true;
            } 
            System.out.println("Échec de connexion : login ou mot de passe incorrect.");
            return false;    
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
            return false;
        }
    }
     public  void initialiserUsersParDefaut( IfactoryServ factoryServ) {
        User userAdmin = selectByLogin("admin");
        User userBoutiquier = selectByLogin("boutiquier");
        User userClient = selectByLogin("client");

        if (userAdmin == null) {
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);
            factoryServ.getInstanceUserServiceImpl().create(admin);
            System.out.println("Utilisateur Admin créé par défaut : admin / admin123");
        }

        if (userBoutiquier == null) {
            User boutiquier = new User();
            boutiquier.setLogin("boutiquier");
            boutiquier.setPassword("boutiquier123");
            boutiquier.setRole(Role.BOUTIQUIER);
            factoryServ.getInstanceUserServiceImpl().create(boutiquier);
            System.out.println("Utilisateur Boutiquier créé par défaut : boutiquier / boutiquier123");
        }

        if (userClient == null) {
            User client = new User();
            client.setLogin("client");
            client.setPassword("client123");
            client.setRole(Role.CLIENT);
            factoryServ.getInstanceUserServiceImpl().create(client);
            System.out.println("Utilisateur Client créé par défaut : client / client123");
        }
    }

    @Override
    public void logout() {
         try {
            App.loadView("demandeView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    
}
