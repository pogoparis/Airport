package fr.eni.airport.bo;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@JsonView(Views.SansPassagers.class)
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvion;
    private String code;
    private String constructeur;
    private Integer model;

    @JsonView(Views.AvecPassagers.class)
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personne> lstPassagers;

    public Avion(String code, String constructeur, Integer modele) {
        this.code = code;
        this.constructeur = constructeur;
        this.model = modele;
    }

}
