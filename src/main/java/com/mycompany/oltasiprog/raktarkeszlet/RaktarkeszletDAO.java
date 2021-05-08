package com.mycompany.oltasiprog.raktarkeszlet;

import com.mycompany.oltasiprog.oltopont.Oltopont;

import java.util.List;

public interface RaktarkeszletDAO extends AutoCloseable{
    public void saveRaktarkeszlet(Raktarkeszlet c);
    public void deleteRaktarkeszlet(Raktarkeszlet c);
    public void updateRaktarkeszlet(Raktarkeszlet c);
    public List<Raktarkeszlet> getRaktarkeszlet();
}