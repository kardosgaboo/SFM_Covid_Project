package com.mycompany.oltasiprog.customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO{

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveCustomer(Felhasznalo c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCustomer(Felhasznalo c) {
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateCustomer(Felhasznalo c) {
        saveCustomer(c);
    }

    @Override
    public List<Felhasznalo> getCustomers() {
        TypedQuery<Felhasznalo> query = entityManager.createQuery("select f from Felhasznalo f",Felhasznalo.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
