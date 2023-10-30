package fr.eni.airport.dal;

import fr.eni.airport.bo.Personne;
import org.springframework.data.repository.CrudRepository;

public interface PersonneDAO extends CrudRepository<Personne, Integer> {
}
