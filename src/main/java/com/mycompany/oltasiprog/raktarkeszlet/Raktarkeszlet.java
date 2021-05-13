package com.mycompany.oltasiprog.raktarkeszlet;

import com.mycompany.oltasiprog.oltopont.Oltopont;
import com.mycompany.oltasiprog.vakcina.Vakcina;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Raktarkeszlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    private Oltopont oltopont;


    @ManyToOne
    private Vakcina vakcina;

    @Column(name="quantity",nullable = false)
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Oltopont getOltopont() {
        return oltopont;
    }

    public void setOltopont(Oltopont oltopont) {
        this.oltopont = oltopont;
    }

    public Vakcina getVakcina() {
        return vakcina;
    }

    public void setVakcina(Vakcina vakcina) {
        this.vakcina = vakcina;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Raktarkeszlet( Oltopont oltopont, Vakcina vakcina, Long quantity) {
        this.oltopont = oltopont;
        this.vakcina = vakcina;
        this.quantity = quantity;
    }

    public Raktarkeszlet(){}
}
