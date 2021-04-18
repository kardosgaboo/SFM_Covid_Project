package com.mycompany.oltasiprog.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends AutoCloseable{

    public void saveCustomer(Felhasznalo c);
    public void deleteCustomer(Felhasznalo c);
    public void updateCustomer(Felhasznalo c);
    public List<Felhasznalo> getCustomers();
    Optional<Felhasznalo> getCustomerByEmailAndPassword(String email, String password);
    List<Felhasznalo> getCustomerByEmail(String email);
    List<Felhasznalo> getCustomerByTaj(String taj);
}
