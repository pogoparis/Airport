package fr.eni.airport.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue
    private Integer idPersonne;
    private String nom;
    private String prenom;
    private Integer age;

    @JsonIgnore
    @ManyToOne
    private Avion avion;

    public Personne(String nom, String prenom, Integer age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
}
