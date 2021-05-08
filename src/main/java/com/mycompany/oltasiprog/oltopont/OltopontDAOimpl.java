package com.mycompany.oltasiprog.oltopont;

import com.mycompany.oltasiprog.customer.Felhasznalo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class OltopontDAOimpl implements OltopontDAO{

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveOltopont(Oltopont c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteOltopont(Oltopont c) {
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateOltopont(Oltopont c) {saveOltopont(c);    }

    @Override
    public List<Oltopont> getOltopontok() {
        TypedQuery<Oltopont> query = entityManager.createQuery("select f from Oltopont f",Oltopont.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
