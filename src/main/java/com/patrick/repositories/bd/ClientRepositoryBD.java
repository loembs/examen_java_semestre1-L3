package com.patrick.repositories.bd;
import javax.persistence.TypedQuery;

import com.patrick.entities.Client;
import com.patrick.repositories.list.ClientRepository;

public class ClientRepositoryBD extends RepositoryJpaImpl<Client> implements ClientRepository {
    
    private final String SQL_SELECT_BY_TELEPHONE = "SELECT u FROM Client u WHERE u.telephone = :telephone";
    public ClientRepositoryBD(){
        super(Client.class);
        entityClass=Client.class;

    }
     @Override
    public Client selectBytelephone(String telephone) {
        TypedQuery<Client> query = em.createQuery(SQL_SELECT_BY_TELEPHONE, entityClass);
        query.setParameter("telephone", telephone);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
   


 
}
