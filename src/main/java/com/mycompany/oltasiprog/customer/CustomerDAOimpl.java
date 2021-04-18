package com.mycompany.oltasiprog.customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
    public Optional<Felhasznalo> getCustomerByEmailAndPassword(String email, String password) {
        List<Felhasznalo> resultList = entityManager.createQuery("select f from Felhasznalo f " +
                "where f.email like :providedEmail and f.jelszo like :providedPassword",Felhasznalo.class)
                .setParameter("providedEmail",email)
                .setParameter("providedPassword", password)
                .setMaxResults(1)
                .getResultList();

        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));

    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
