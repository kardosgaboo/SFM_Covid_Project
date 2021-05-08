package com.mycompany.oltasiprog.raktarkeszlet;

import com.mycompany.oltasiprog.oltopont.Oltopont;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RaktarkeszletDAOimpl implements RaktarkeszletDAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void saveRaktarkeszlet(Raktarkeszlet c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteRaktarkeszlet(Raktarkeszlet c) {
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateRaktarkeszlet(Raktarkeszlet c) {saveRaktarkeszlet(c);    }

    @Override
    public List<Raktarkeszlet> getRaktarkeszlet() {
        TypedQuery<Raktarkeszlet> query = entityManager.createQuery("select f from Raktarkeszlet f",Raktarkeszlet.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
