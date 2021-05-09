package com.mycompany.oltasiprog.rendeles;

import com.mycompany.oltasiprog.customer.Felhasznalo;
import com.mycompany.oltasiprog.oltopont.Oltopont;
import com.mycompany.oltasiprog.vakcina.Vakcina;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Rendeles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Felhasznalo felhasznalo;

    @ManyToOne
    private Oltopont oltopont;

    @ManyToOne
    private Vakcina vakcina;

    @Column(name="rendeles_datum")
    private LocalDateTime rendelesDatum;

    public Rendeles(Felhasznalo felhasznalo, Oltopont oltopont, Vakcina vakcina, LocalDateTime rendelesDatum) {
        this.felhasznalo = felhasznalo;
        this.oltopont = oltopont;
        this.vakcina = vakcina;
        this.rendelesDatum = rendelesDatum;
    }

    public Rendeles(){}

    @Override
    public String toString() {
        return "Rendeles{" +
                "felhasznalo=" + felhasznalo +
                ", oltopont=" + oltopont +
                ", vakcina=" + vakcina +
                ", rendelesDatum=" + rendelesDatum +
                '}';
    }
}
