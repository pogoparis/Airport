package fr.eni.airport.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue
    private Integer idPersonne;
    private String nom;
    private String prenom;
    private Integer age;

    @ManyToOne
    private Avion avion;

    public Personne(String nom, String prenom, Integer age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
}
