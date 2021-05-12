package com.mycompany.oltasiprog.oltopont;

import com.mycompany.oltasiprog.vakcina.Vakcina;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="Oltopont")
public class Oltopont {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="Name",unique = true,nullable = false)
    private String name;

    @Column(name="Address")
    private String address;

    public Oltopont( String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Oltopont(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Oltopont{" +
                "name='" + name + '\'' +
                '}';

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


