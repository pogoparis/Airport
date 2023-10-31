package fr.eni.airport.dal;

import fr.eni.airport.bo.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AvionDAO extends JpaRepository<Avion, Integer>
{
}
