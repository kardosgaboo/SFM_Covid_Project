package com.mycompany.oltasiprog.vakcina;

import com.mycompany.oltasiprog.raktarkeszlet.Raktarkeszlet;

import java.util.List;

public interface VakcinaDAO extends AutoCloseable{
    public void saveVakcina(Vakcina c);
    public void deleteVakcina(Vakcina c);
    public void updateVakcina(Vakcina c);
    public List<Vakcina> getVakcina();
}
