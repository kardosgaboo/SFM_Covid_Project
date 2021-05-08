package com.mycompany.oltasiprog.vakcina;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vakcina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="Name",unique = true,nullable = false)
    private String name;

    @Column(name="Description", length = 4000)
    private String description;

    public Vakcina(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Vakcina(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
