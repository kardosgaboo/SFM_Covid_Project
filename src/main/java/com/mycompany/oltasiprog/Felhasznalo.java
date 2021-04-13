package com.mycompany.oltasiprog;



import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Felhasznalo {
    @Id
    private Long id;

    private String name;

    private String taj;

    private String email;

    private String jelszo;


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

    public String getTaj() {
        return taj;
    }

    public void setTaj(String taj) {
        this.taj = taj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + " " + taj + " " + email +" " + jelszo + '\'' +
                '}';
    }
}
