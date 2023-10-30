package fr.eni.airport.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer modele;

    @OneToMany(mappedBy = "avion")
    private List<Personne> passagers = new ArrayList<>();

    public Avion(String code, String constructeur, Integer modele) {
        this.code = code;
        this.constructeur = constructeur;
        this.modele = modele;
    }

}
