package com.mycompany.oltasiprog;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Felhasznalok")
public class Felhasznalo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;
    @Column(name="nev")
    private String nev;
    @Column(name="taj")
    private String taj;
    @Column(name="email")
    private String email;
    @Column(name="jelszo")
    private String jelszo;
    @Column(name="mobil")
    private String mobil;
    @Column(name="szulido")
    private LocalDate szulido;

    public LocalDate getSzulido() {
        return szulido;
    }

    public void setSzulido(LocalDate szulido) {
        this.szulido = szulido;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String name) {
        this.nev = name;
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
                ", name='" + nev + " " + taj + " " + email +" " + jelszo + '\'' +
                '}';
    }
}
