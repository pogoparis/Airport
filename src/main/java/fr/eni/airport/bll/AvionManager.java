package fr.eni.airport.bll;

import fr.eni.airport.bo.Avion;
import fr.eni.airport.bo.Personne;

import java.util.List;
import java.util.Optional;

public interface AvionManager {

    void creerAvion(Avion avion);
    void embarquerPassager(Integer idAvion, Integer idPersonne);

    void debarquerPassagers(Integer idAvion);

    List<Avion> getListeAvions();

//    void creerDonneesDeTest();

    List<Personne> getListePersonnes();

    Avion getAvionById(Integer idAvion);

    void supprimerAvion(Integer avionId);
    List<Personne> getListePersonnesSansAvion();

    List<Avion> getAvionsState();
}
