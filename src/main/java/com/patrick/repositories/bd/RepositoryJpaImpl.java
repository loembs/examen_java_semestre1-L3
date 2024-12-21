package com.patrick.repositories.bd;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.patrick.core.config.RepositoryBD;
import com.patrick.services.YamlService;
import com.patrick.services.Impl.YamlServiceImpl;


public class RepositoryJpaImpl<T> implements RepositoryBD<T> {

    protected EntityManager em;
    protected  Class<T> entityClass;
    private EntityManagerFactory emf;
    YamlService yamlService;

    public RepositoryJpaImpl(Class<T> entityClass) {
        if (em == null) {
            yamlService = new YamlServiceImpl();
            Map<String , Object> mapYaml = yamlService.loadYaml();
            emf = Persistence.createEntityManagerFactory(mapYaml.get("persistence").toString());
            em = emf.createEntityManager();
        }
        this.entityClass = entityClass;
    }

  
   

    @Override
    public void insert(T data) {
        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
       
    }

    @Override
    public List<T> selectAll() {
        return this.em.createQuery("SELECT u FROM " + entityClass.getSimpleName() + " u", entityClass)
                      .getResultList();
    }

    @Override
public boolean update(T object) {
    if (object == null) {
        throw new IllegalArgumentException("L'objet à mettre à jour ne peut pas être null.");
    }
    try {
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        
        return true; 
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace(); 
        return false; 
    }
}
    @Override
    public T selectById(int id) {
        try {
            return em.find(entityClass,id);
            
        } catch (Exception e) {
            return null;
        }
    
    }

    @Override
    public void remove(int id) {
        try {
            em.getTransaction().begin();
           
            em.remove(selectById(id));
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

   
}
