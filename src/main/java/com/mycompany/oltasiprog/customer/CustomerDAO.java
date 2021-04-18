package com.mycompany.oltasiprog.customer;

import java.util.List;

public interface CustomerDAO extends AutoCloseable{

    public void saveCustomer(Felhasznalo c);
    public void deleteCustomer(Felhasznalo c);
    public void updateCustomer(Felhasznalo c);
    public List<Felhasznalo> getCustomers();
}
