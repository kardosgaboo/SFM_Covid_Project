package com.mycompany.oltasiprog.rendeles;

import com.mycompany.oltasiprog.rendeles.Rendeles;

import java.util.List;

public interface RendelesDAO extends AutoCloseable{
        public void saveRendeles(Rendeles c);
        public void deleteRendeles(Rendeles c);
        public void updateRendeles(Rendeles c);
        public List<Rendeles> getRendeles();
    }

