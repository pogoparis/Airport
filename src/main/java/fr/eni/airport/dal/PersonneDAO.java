package fr.eni.airport.dal;

import fr.eni.airport.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonneDAO extends JpaRepository<Personne, Integer> {
}
