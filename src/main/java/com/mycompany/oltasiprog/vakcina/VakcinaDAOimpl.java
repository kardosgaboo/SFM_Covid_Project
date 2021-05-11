package com.mycompany.oltasiprog.vakcina;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class VakcinaDAOimpl implements VakcinaDAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveVakcina(Vakcina c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteVakcina(Vakcina c) {
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateVakcina(Vakcina c) {saveVakcina(c);    }

    @Override
    public List<Vakcina> getVakcina() {
        TypedQuery<Vakcina> query = entityManager.createQuery("select f from Vakcina f",Vakcina.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
