package com.mycompany.oltasiprog.oltopont;

import com.mycompany.oltasiprog.customer.Felhasznalo;

import java.util.List;

public interface OltopontDAO extends AutoCloseable{
    public void saveOltopont(Oltopont c);
    public void deleteOltopont(Oltopont c);
    public void updateOltopont(Oltopont c);
    public List<Oltopont> getOltopontok();
}
