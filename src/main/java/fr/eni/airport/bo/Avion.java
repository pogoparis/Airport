package fr.eni.airport.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Avion {

    @Id
    @GeneratedValue
    private Integer idAvion;
    private String code;
    private String constructeur;
    private Integer model;

    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personne> lstPassagers;

    public Avion(String code, String constructeur, Integer modele) {
        this.code = code;
        this.constructeur = constructeur;
        this.model = modele;
    }

}
